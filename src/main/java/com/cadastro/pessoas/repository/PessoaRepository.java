package com.cadastro.pessoas.repository;

import com.cadastro.pessoas.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByDocumento(String documento);

    boolean existsByEmail(String email);

    @Query("SELECT p.login FROM Pessoa p")
    Set<String> findAllLogins();
}