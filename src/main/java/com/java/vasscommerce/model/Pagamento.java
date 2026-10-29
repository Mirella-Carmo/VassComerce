package com.java.vasscommerce.model;

import java.math.BigDecimal;

public class Pagamento {
    private Long id;
    private BigDecimal valorPago;
    private Cartao cartao;

    public Pagamento(Long id, BigDecimal valorPago, Cartao cartao) {
        this.id = id;
        this.valorPago = valorPago;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
