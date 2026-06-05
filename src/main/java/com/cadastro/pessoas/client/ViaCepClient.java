package com.cadastro.pessoas.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ViaCepClient {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    private final RestTemplate restTemplate;

    public ViaCepClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean cepExiste(String cep) {
        try {
            ViaCepResponse response = restTemplate.getForObject(VIA_CEP_URL, ViaCepResponse.class, cep);
            if (response == null || "true".equals(response.getErro())) {
                log.warn("CEP não encontrado: {}", cep);
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Erro ao consultar ViaCEP para o CEP {}: {}", cep, e.getMessage());
            return false;
        }
    }

    // representa a resposta da API ViaCEP
    @lombok.Data
    public static class ViaCepResponse {
        private String cep;
        private String logradouro;
        private String bairro;
        private String localidade;
        private String uf;
        private String erro;
    }
}