package com.cadastro.pessoas.service;

import com.cadastro.pessoas.client.ViaCepClient;
import com.cadastro.pessoas.dto.PessoaRequest;
import com.cadastro.pessoas.dto.PessoaResponse;
import com.cadastro.pessoas.entity.Pessoa;
import com.cadastro.pessoas.repository.PessoaRepository;
import com.cadastro.pessoas.strategy.LoginStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final LoginStrategy loginStrategy;
    private final ViaCepClient viaCepClient;

    public PessoaResponse cadastrar(PessoaRequest request) {

        // valida unicidade do CPF
        if (pessoaRepository.existsByDocumento(request.getDocumento())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        // valida unicidade do e-mail
        if (pessoaRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        // valida se o CEP existe no ViaCEP
        if (!viaCepClient.cepExiste(request.getCep())) {
            throw new IllegalArgumentException("CEP não encontrado");
        }

        // busca logins existentes e gera o login
        Set<String> loginsExistentes = pessoaRepository.findAllLogins();
        String login = loginStrategy.gerar(request.getNome(), loginsExistentes);
        log.info("Login gerado para {}: {}", request.getNome(), login);

        // monta a entidade
        Pessoa pessoa = Pessoa.builder()
            .nome(request.getNome().trim())
            .documento(request.getDocumento().replaceAll("[^0-9]", ""))
            .email(request.getEmail())
            .dataNascimento(request.getDataNascimento())
            .cep(request.getCep().replaceAll("-", ""))
            .logradouro(request.getLogradouro())
            .bairro(request.getBairro())
            .cidade(request.getCidade())
            .uf(request.getUf())
            .numero(request.getNumero())
            .complemento(request.getComplemento())
            .login(login)
            .build();

        Pessoa salva = pessoaRepository.save(pessoa);
        log.info("Pessoa cadastrada com sucesso: id={}, login={}", salva.getId(), salva.getLogin());

        // monta e retorna o response
        return PessoaResponse.builder()
            .id(salva.getId())
            .nome(salva.getNome())
            .documento(salva.getDocumento())
            .email(salva.getEmail())
            .dataNascimento(salva.getDataNascimento())
            .cep(salva.getCep())
            .logradouro(salva.getLogradouro())
            .bairro(salva.getBairro())
            .cidade(salva.getCidade())
            .uf(salva.getUf())
            .numero(salva.getNumero())
            .complemento(salva.getComplemento())
            .login(salva.getLogin())
            .criadoEm(salva.getCriadoEm())
            .build();
    }
}
