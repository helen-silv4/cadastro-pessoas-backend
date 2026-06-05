package com.cadastro.pessoas.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class PessoaResponse {

    private Long id;
    private String nome;
    private String documento;
    private String email;
    private LocalDate dataNascimento;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;
    private String login;
    private LocalDateTime criadoEm;
}
