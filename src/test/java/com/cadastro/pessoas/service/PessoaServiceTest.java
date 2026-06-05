package com.cadastro.pessoas.service;

import com.cadastro.pessoas.client.ViaCepClient;
import com.cadastro.pessoas.dto.PessoaRequest;
import com.cadastro.pessoas.dto.PessoaResponse;
import com.cadastro.pessoas.entity.Pessoa;
import com.cadastro.pessoas.repository.PessoaRepository;
import com.cadastro.pessoas.strategy.DefaultLoginStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ViaCepClient viaCepClient;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        pessoaService = new PessoaService(pessoaRepository, new DefaultLoginStrategy(), viaCepClient);
    }

    @Test
    void deveCadastrarPessoaComSucesso() {
        when(pessoaRepository.existsByDocumento(any())).thenReturn(false);
        when(pessoaRepository.existsByEmail(any())).thenReturn(false);
        when(viaCepClient.cepExiste(any())).thenReturn(true);
        when(pessoaRepository.findAllLogins()).thenReturn(Set.of());
        when(pessoaRepository.save(any())).thenAnswer(invocation -> {
            Pessoa p = invocation.getArgument(0);
            p = Pessoa.builder()
                    .id(1L)
                    .nome(p.getNome())
                    .documento(p.getDocumento())
                    .email(p.getEmail())
                    .dataNascimento(p.getDataNascimento())
                    .cep(p.getCep())
                    .login(p.getLogin())
                    .criadoEm(LocalDateTime.now())
                    .build();
            return p;
        });

        PessoaResponse response = pessoaService.cadastrar(buildRequest());

        assertThat(response.getLogin()).isNotNull();
        assertThat(response.getLogin()).hasSize(7);
        assertThat(response.getLogin()).matches("[a-z]{7}");
        verify(pessoaRepository).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaCadastrado() {
        when(pessoaRepository.existsByDocumento(any())).thenReturn(true);

        assertThatThrownBy(() -> pessoaService.cadastrar(buildRequest()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CPF já cadastrado");

        verify(pessoaRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaCadastrado() {
        when(pessoaRepository.existsByDocumento(any())).thenReturn(false);
        when(pessoaRepository.existsByEmail(any())).thenReturn(true);

        assertThatThrownBy(() -> pessoaService.cadastrar(buildRequest()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("E-mail já cadastrado");

        verify(pessoaRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoCepNaoEncontrado() {
        when(pessoaRepository.existsByDocumento(any())).thenReturn(false);
        when(pessoaRepository.existsByEmail(any())).thenReturn(false);
        when(viaCepClient.cepExiste(any())).thenReturn(false);

        assertThatThrownBy(() -> pessoaService.cadastrar(buildRequest()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CEP não encontrado");

        verify(pessoaRepository, never()).save(any());
    }

    @Test
    void deveGerarLoginUnicoQuandoBaseJaExiste() {
        when(pessoaRepository.existsByDocumento(any())).thenReturn(false);
        when(pessoaRepository.existsByEmail(any())).thenReturn(false);
        when(viaCepClient.cepExiste(any())).thenReturn(true);
        when(pessoaRepository.findAllLogins()).thenReturn(Set.of("mariasi")); // login base já ocupado
        when(pessoaRepository.save(any())).thenAnswer(invocation -> {
            Pessoa p = invocation.getArgument(0);
            return Pessoa.builder()
                    .id(1L).nome(p.getNome()).documento(p.getDocumento())
                    .email(p.getEmail()).dataNascimento(p.getDataNascimento())
                    .login(p.getLogin()).criadoEm(LocalDateTime.now()).build();
        });

        PessoaResponse response = pessoaService.cadastrar(buildRequest());

        assertThat(response.getLogin()).isNotEqualTo("mariasi");
        assertThat(response.getLogin()).hasSize(7);
    }

    private PessoaRequest buildRequest() {
        PessoaRequest request = new PessoaRequest();
        request.setNome("Maria Silva Santos");
        request.setDocumento("529.982.247-25"); // CPF válido
        request.setEmail("maria@exemplo.com");
        request.setDataNascimento(LocalDate.of(1998, 3, 14));
        request.setCep("01001-000");
        request.setLogradouro("Praça da Sé");
        request.setBairro("Sé");
        request.setCidade("São Paulo");
        request.setUf("SP");
        request.setNumero("1");
        return request;
    }
}