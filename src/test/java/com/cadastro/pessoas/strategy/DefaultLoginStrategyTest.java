package com.cadastro.pessoas.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class DefaultLoginStrategyTest {

    private DefaultLoginStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new DefaultLoginStrategy();
    }

    @Test
    void deveGerarLoginComTresNomes() {
        String login = strategy.gerar("Maria Silva Santos", Set.of());
        assertThat(login).isEqualTo("marisis");
    }

    @Test
    void deveGerarLoginComDoisNomes() {
        String login = strategy.gerar("Joao Pedro", Set.of());
        assertThat(login).isEqualTo("joaoped");
    }

    @Test
    void loginDeveTerminarExatamenteSeteCaracteres() {
        String login = strategy.gerar("Ana Clara Souza", Set.of());
        assertThat(login).hasSize(7);
    }

    @Test
    void loginDeveConterApenasLetrasMinusculas() {
        String login = strategy.gerar("Carlos Eduardo Lima", Set.of());
        assertThat(login).matches("[a-z]{7}");
    }

    @Test
    void deveGerarLoginAlternativoQuandoBaseJaExiste() {
        Set<String> existentes = new HashSet<>();
        existentes.add("mariasi"); // login base já ocupado

        String login = strategy.gerar("Maria Silva Santos", existentes);

        assertThat(login).hasSize(7);
        assertThat(login).matches("[a-z]{7}");
        assertThat(login).isNotEqualTo("mariasi");
    }

    @Test
    void deveGarantirUnicidadeComVariosConflitos() {
        // simula vários logins já existentes
        Set<String> existentes = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            existentes.add(strategy.gerar("Maria Silva Santos", existentes));
        }

        // todos os 10 logins gerados devem ser únicos
        assertThat(existentes).hasSize(10);
    }

    @Test
    void loginNaoDeveConterEspacos() {
        String login = strategy.gerar("Paula Fernanda Reis", Set.of());
        assertThat(login).doesNotContain(" ");
    }

    @Test
    void loginNaoDeveConterNumeros() {
        String login = strategy.gerar("Lucas Henrique Prado", Set.of());
        assertThat(login).doesNotContainAnyWhitespaces();
        assertThat(login).matches("[a-z]+");
    }
}