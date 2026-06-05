package com.cadastro.pessoas.strategy;

import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class DefaultLoginStrategy implements LoginStrategy {

    // remove espaços e coloca tudo em minúsculo
    private String normalizarNome(String nome) {
        return nome.toLowerCase().replaceAll(" ", "");
    }

    // gera o login base a partir do nome
    // regra: 4 letras do primeiro nome + 2 do segundo + 1 do terceiro (se existir)
    // se só dois nomes: 4 do primeiro + 3 do segundo
    private String gerarLoginBase(String nome) {
        String[] partes = nome.toLowerCase().split(" ");

        if (partes.length >= 3) {
            return partes[0].substring(0, Math.min(4, partes[0].length()))
                    + partes[1].substring(0, Math.min(2, partes[1].length()))
                    + partes[2].substring(0, Math.min(1, partes[2].length()));
        } else {
            // dois nomes
            return partes[0].substring(0, Math.min(4, partes[0].length()))
                    + partes[1].substring(0, Math.min(3, partes[1].length()));
        }
    }

    // fallback: percorre o nome completo em blocos de 7 letras até encontrar um login único
    private String gerarLoginFallback(String nomeNormalizado, int tentativa) {

        // garante que tem letras suficientes repetindo o nome se necessário
        String base = nomeNormalizado;
        while (base.length() < 7 + tentativa) {
            base += nomeNormalizado;
        }
        return base.substring(tentativa, tentativa + 7);
    }

    @Override
    public String gerar(String nome, Set<String> loginsExistentes) {
        String loginBase = gerarLoginBase(nome);

        // completa com letras do nome normalizado se ficou menor que 7
        String nomeNormalizado = normalizarNome(nome);
        if (loginBase.length() < 7) {
            loginBase = (loginBase + nomeNormalizado).substring(0, 7);
        }

        // tenta o login base primeiro
        if (!loginsExistentes.contains(loginBase)) {
            return loginBase;
        }

        // fallback: tenta novas combinações avançando uma letra por vez no nome normalizado
        for (int i = 1; i <= nomeNormalizado.length(); i++) {
            String candidato = gerarLoginFallback(nomeNormalizado, i);
            if (!loginsExistentes.contains(candidato)) {
                return candidato;
            }
        }

        // última alternativa: combina nome repetido até achar único
        String base = nomeNormalizado;
        while (base.length() < 7 + loginsExistentes.size()) {
            base += nomeNormalizado;
        }
        for (int i = 0; i < base.length() - 6; i++) {
            String candidato = base.substring(i, i + 7);
            if (!loginsExistentes.contains(candidato)) {
                return candidato;
            }
        }

        throw new RuntimeException("Não foi possível gerar um login único para o nome: " + nome);
    }
}
