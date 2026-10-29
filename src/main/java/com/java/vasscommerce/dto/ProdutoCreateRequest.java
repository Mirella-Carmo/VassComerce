package com.java.vasscommerce.dto;

import com.java.vasscommerce.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoCreateRequest {
    @NotBlank
    @Size(max = 100, message = "nome e descricao devem ter no max 100 caracteres")
    private String nome;
    private String descricao;

    @NotBlank
    private String fotoUrl;

    @NotBlank @Positive
    private BigDecimal valorUnitario;

    @NotEmpty
    private List<Categoria> categoriaIds;

    public ProdutoCreateRequest(String notebook, String s, String url, BigDecimal bigDecimal, List<Long> longs) {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valor) {
        this.valorUnitario = valor;
    }

    public List<Long> getCategoriaIds() {
        return categoriaIds;
    }

    public void setCategorias(List<Categoria> categoriaIds) {
        this.categoriaIds = categoriaIds;
    }
}
