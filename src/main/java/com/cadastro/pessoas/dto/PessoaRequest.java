package com.cadastro.pessoas.dto;

import com.cadastro.pessoas.validation.CpfValido;
import com.cadastro.pessoas.validation.DataNascimentoValida;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PessoaRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)+$",
            message = "Nome deve conter pelo menos duas palavras, apenas letras e espaços, sem acentos ou caracteres especiais")
    private String nome;

    @NotBlank(message = "Documento é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido. Use o formato xxx.xxx.xxx-xx")
    @CpfValido
    private String documento;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatória")
    @DataNascimentoValida
    private LocalDate dataNascimento;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
    private String cep;

    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "UF é obrigatória")
    private String uf;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    private String complemento;
}
