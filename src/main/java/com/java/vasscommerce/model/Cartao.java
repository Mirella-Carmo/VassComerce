package com.java.vasscommerce.model;

import java.time.LocalDateTime;

public class Cartao {
    private Long id;
    private LocalDateTime dataCriacao;
    private TipoCartao tipo;
    private boolean excluido;

    public Cartao(Long id, TipoCartao tipo, boolean excluido) {
        this.id = id;
        this.tipo = tipo;
        this.excluido = excluido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public TipoCartao getTipo() {
        return tipo;
    }

    public void setTipo(TipoCartao tipo) {
        this.tipo = tipo;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }
}
