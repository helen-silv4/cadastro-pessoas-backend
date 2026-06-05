package com.cadastro.pessoas.controller;

import com.cadastro.pessoas.dto.PessoaRequest;
import com.cadastro.pessoas.dto.PessoaResponse;
import com.cadastro.pessoas.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponse> cadastrar(@Valid @RequestBody PessoaRequest request) {
        log.info("Recebendo requisição de cadastro para: {}", request.getNome());
        PessoaResponse response = pessoaService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
