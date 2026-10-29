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

@Service("produtoModelSql")
@Profile("sql")
public class ProdutoModelSql implements ProdutoModel {
    private final List<Produto> tabelaProduto = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1000);
    private final CategoriaModel categoriaModel;

    public ProdutoModelSql(CategoriaModel categoriaModel) {
        this.categoriaModel = categoriaModel;
    }

    @Override
    public Produto criar(ProdutoCreateRequest req) {
        List<Categoria> categorias = categoriaModel.buscarPorIds(req.getCategoriaIds());
        Produto p = ProdutoMapper.toEntity(req, seq.incrementAndGet(), categorias);
        tabelaProduto.add(p);
        return p;
    }

    @Override
    public Optional<Produto> obterPorId(Long id) {
        return tabelaProduto.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
    }

    @Override
    public List<Produto> buscar(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo) {
        return tabelaProduto.stream()
                .filter(p -> nome == null || nome.isBlank()
                        || p.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(p -> valorMinimo == null || p.getValorUnitario().compareTo(valorMinimo) >= 0)
                .filter(p -> valorMaximo == null || p.getValorUnitario().compareTo(valorMaximo) <= 0)
                .toList();
    }

    @Override
    public List<Produto> listarPorCategoria(Long idCategoria) {
        return tabelaProduto.stream()
                .filter(p -> p.getCategorias().stream()
                        .anyMatch(c -> Objects.equals(c.getId(), idCategoria)))
                .toList();
    }
}