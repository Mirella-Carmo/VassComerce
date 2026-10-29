package com.java.vasscommerce.model;

import org.springframework.stereotype.Service;
import com.java.vasscommerce.exception.CategoriaNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaModelMemoria implements CategoriaModel{
    private final List<Categoria> banco = new ArrayList<>();

    public CategoriaModelMemoria(){
        banco.add(new Categoria(1L, "https://exemplo.com/eletronicos.png",
                "Eletrônicos", "Celulares, notebooks e acessórios"));
        banco.add(new Categoria(2L, "https://exemplo.com/livros.png",
                "Livros", "Livros técnicos e de ficção"));
        banco.add(new Categoria(3L, "https://exemplo.com/roupas.png",
                "Roupas", "Moda masculina e feminina"));
    }
    @Override
    public List<Categoria> listar() {
        return banco;
    }
    @Override
    public List<Categoria> buscarPorNome(String nome){
        return banco.stream().filter(c ->
                c.getNome().toLowerCase().contains(nome.toLowerCase())).toList();
    }
    @Override
    public Optional<Categoria> obterPorId(Long id) {
        return banco.stream().filter(c -> Objects.equals(c.getId(),
                id)).findFirst();
    }
    @Override
    public List<Categoria> buscarPorIds(List<Long> ids){
        return ids.stream()
                .map(id -> obterPorId(id).orElseThrow(() -> new CategoriaNaoEncontradaException(id)))
                .toList();
    }
}
