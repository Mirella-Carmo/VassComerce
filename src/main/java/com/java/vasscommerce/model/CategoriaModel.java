package com.java.vasscommerce.model;

import java.util.List;
import java.util.Optional;

public interface CategoriaModel {
    List<Categoria> listar();

    List<Categoria> buscarPorNome(String nome);

    Optional<Categoria> obterPorId(Long id);

    List<Categoria> buscarPorIds(List<Long> ids);
}