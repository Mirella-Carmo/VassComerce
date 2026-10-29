package com.java.vasscommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PedidoStatus {
    AGUARDANDO_PAGAMENTO(1),
    SEPARANDO_ESTOQUE(2),
    ENTREGUE_TRANSPORTADORA(3),
    ENTREGUE_CLIENTE(4);

    private final int codigo;

    PedidoStatus(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo; }
    public String getNome() { return name(); }
}