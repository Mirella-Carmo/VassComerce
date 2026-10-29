package com.java.vasscommerce.model;

import com.java.vasscommerce.dto.ProdutoCreateRequest;
import com.java.vasscommerce.mapper.ProdutoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service("produtoModelMemoria")
@Profile("mem")
public class ProdutoModelMemoria implements ProdutoModel {
    private final List<Produto> banco = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(0);
    private final CategoriaModel categoriaModel;

    public ProdutoModelMemoria(CategoriaModel categoriaModel) {
        this.categoriaModel = categoriaModel;
        criar(new ProdutoCreateRequest("Notebook", "Notebook com 16 GB de RAM",
                "https://exemplo.com/notebook.png", new BigDecimal("3999.90"), List.of(1L)));
        criar(new ProdutoCreateRequest("Fone Bluetooth", "Fone sem fio",
                "https://exemplo.com/fone.png", new BigDecimal("249.90"), List.of(1L)));
        criar(new ProdutoCreateRequest("Código Limpo", "Boas práticas de programação",
                "https://exemplo.com/livro.png", new BigDecimal("89.90"), List.of(2L)));
        criar(new ProdutoCreateRequest("Camiseta Básica", "100% algodão",
                "https://exemplo.com/camiseta.png", new BigDecimal("39.90"), List.of(3L)));
        criar(new ProdutoCreateRequest("Leitor de E-book", "Tela de tinta eletrônica",
                "https://exemplo.com/ebook.png", new BigDecimal("499.90"),
                List.of(1L, 2L)));
    }

    @Override
    public Produto criar(ProdutoCreateRequest req) {
        List<Categoria> categorias = categoriaModel.buscarPorIds(req.getCategoriaIds());
        Produto p = ProdutoMapper.toEntity(req, seq.incrementAndGet(),
                categorias);
        banco.add(p);
        return p;
    }

    @Override
    public Optional<Produto> obterPorId(Long id) {
        return banco.stream().filter(p -> Objects.equals(p.getId(),
                id)).findFirst();
    }

    @Override
    public List<Produto> buscar(String nome, BigDecimal min, BigDecimal max) {
        return banco.stream()
                .filter(p -> nome == null ||
                        p.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(p -> min == null ||
                        p.getValorUnitario().compareTo(min) >= 0)
                .filter(p -> max == null ||
                        p.getValorUnitario().compareTo(max) <= 0)
                .toList();
    }

    @Override
    public List<Produto> listarPorCategoria(Long idCategoria) {
        return banco.stream()
                .filter(p -> p.getCategorias().stream().anyMatch(c ->
                        Objects.equals(c.getId(), idCategoria)))
                .toList();
    }
}