package com.java.vasscommerce.mapper;

import com.java.vasscommerce.dto.CategoriaResponse;
import com.java.vasscommerce.dto.ProdutoCreateRequest;
import com.java.vasscommerce.dto.ProdutoResponse;
import com.java.vasscommerce.model.Categoria;
import com.java.vasscommerce.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoMapper {
    public static Produto toEntity(ProdutoCreateRequest req, Long id, List<Categoria> categorias) {
        return new Produto(id, req.getNome(), req.getDescricao(), req.getFotoUrl(), req.getValorUnitario(), categorias);
    }

    public static ProdutoResponse toResponse(Produto p){
        List<CategoriaResponse> categorias = p.getCategorias().stream()
                .map(c -> new CategoriaResponse(c.getId(), c.getNome()))
                .toList();
        return new ProdutoResponse(p.getId(), p.getNome(), p.getDescricao(), p.getFotoUrl(),
                p.getValorUnitario(), categorias);
    }
}
