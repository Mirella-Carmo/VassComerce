package com.java.vasscommerce.controllers;

import com.java.vasscommerce.dto.ProdutoResponse;
import com.java.vasscommerce.mapper.ProdutoMapper;
import com.java.vasscommerce.model.Categoria;
import com.java.vasscommerce.model.CategoriaModel;
import com.java.vasscommerce.model.Produto;
import com.java.vasscommerce.model.ProdutoModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/categoria", produces = "application/json")
public class CategoriaController {

    private final CategoriaModel categoriaModel;
    private final ProdutoModel produtoModel;

    public CategoriaController(CategoriaModel categoriaModel,
                               @Qualifier("produtoModelMemoria") ProdutoModel produtoModel) {
        this.categoriaModel = categoriaModel;
        this.produtoModel = produtoModel;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(
            @RequestParam(name = "nome", required = false) String nome) {
        if (nome == null || nome.isBlank()) {
            return ResponseEntity.ok(categoriaModel.listar());
        }
        return ResponseEntity.ok(categoriaModel.buscarPorNome(nome));
    }

    @GetMapping("/{idCategoria}/produto")
    public ResponseEntity<List<ProdutoResponse>> listarProdutos(@PathVariable Long idCategoria) {
        if (categoriaModel.obterPorId(idCategoria).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ProdutoResponse> respostas = new ArrayList<>();
        for (Produto produto : produtoModel.listarPorCategoria(idCategoria)) {
            respostas.add(ProdutoMapper.toResponse(produto));
        }
        return ResponseEntity.ok(respostas);
    }
}
