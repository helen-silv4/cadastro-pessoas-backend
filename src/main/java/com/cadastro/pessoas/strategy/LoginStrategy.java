package com.cadastro.pessoas.strategy;

import java.util.Set;

public interface LoginStrategy {
    String gerar(String nome, Set<String> loginsExistentes);
}