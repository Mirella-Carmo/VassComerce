package com.java.vasscommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoResponse {
    public ProdutoResponse(Long id, String nome, String descricao, String fotoUrl, BigDecimal valorUnitario, List<CategoriaResponse> categorias) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.fotoUrl = fotoUrl;
        this.valorUnitario = valorUnitario;
        this.categorias = categorias;
    }

    private Long id;
    private String nome;
    private  String descricao;
    private String fotoUrl;
    private BigDecimal valorUnitario;
    private List<CategoriaResponse> categorias;

    public List<CategoriaResponse> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaResponse> categorias) {
        this.categorias = categorias;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
