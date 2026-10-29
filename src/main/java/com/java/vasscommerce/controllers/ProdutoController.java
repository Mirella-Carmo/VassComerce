package com.java.vasscommerce.controllers;

import com.java.vasscommerce.dto.ProdutoCreateRequest;
import com.java.vasscommerce.dto.ProdutoResponse;
import com.java.vasscommerce.mapper.ProdutoMapper;
import com.java.vasscommerce.model.Produto;
import com.java.vasscommerce.model.ProdutoModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/produto", produces = "application/json")
public class ProdutoController {

    private final ProdutoModel model;

    public ProdutoController(@Qualifier("produtoModelMemoria")
                             ProdutoModel model) {
        this.model = model;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ProdutoResponse> create(@Valid @RequestBody ProdutoCreateRequest body) {
        Produto created = model.criar(body);
        URI location = URI.create("/produto/" + created.getId());
        return ResponseEntity.created(location).body(ProdutoMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> get(@PathVariable Long id) {
        Optional<Produto> produto = model.obterPorId(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(ProdutoMapper.toResponse(produto.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> list(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "valorMinimo", required = false) BigDecimal valorMinimo,
            @RequestParam(name = "valorMaximo", required = false) BigDecimal valorMaximo) {

        List<Produto> produtos = model.buscar(nome, valorMinimo, valorMaximo);
        List<ProdutoResponse> respostas = new ArrayList<>();
        for (Produto produto : produtos) {
            respostas.add(ProdutoMapper.toResponse(produto));
        }
        return ResponseEntity.ok(respostas);
    }
}