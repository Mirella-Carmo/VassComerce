package com.java.vasscommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCartao {
    DEBITO(1), CREDITO(2);

    private final int codigo;

    TipoCartao(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo; }
    public String getNome() { return name(); }
}
