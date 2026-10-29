package com.java.vasscommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PromocaoItem {
    private Long id;
    private BigDecimal valorPromocao;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataUltimaAtualizacao;
    private Produto produto;

    public PromocaoItem(Long id, BigDecimal valorPromocao, LocalDateTime dataCadastro, LocalDateTime dataUltimaAtualizacao, Produto produto) {
        this.id = id;
        this.valorPromocao = valorPromocao;
        this.dataCadastro = dataCadastro;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorPromocao() {
        return valorPromocao;
    }

    public void setValorPromocao(BigDecimal valorPromocao) {
        this.valorPromocao = valorPromocao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
