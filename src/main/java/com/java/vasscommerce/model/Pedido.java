package com.java.vasscommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Long id;
    private LocalDateTime dataCadastro;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private PedidoStatus statusAtual;
    private Cliente cliente;
    private final List<ItemPedido> itens = new ArrayList<>();
    private final List<Pagamento> pagamentos = new ArrayList<>();

    public Pedido(Long id, LocalDateTime dataCadastro, PedidoStatus statusAtual, Cliente cliente) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.statusAtual = statusAtual;
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        valorTotal = valorTotal.add(item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
    }

    // regra da nota do diagrama: o cartão usado no pagamento precisa ser de um dos cartões do cliente do pedido
    public void adicionarPagamento(Pagamento pagamento) {
        if (!cliente.getCartoes().contains(pagamento.getCartao())) {
            throw new IllegalArgumentException("O cartão não pertence ao cliente do pedido");
        }
        pagamentos.add(pagamento);
    }

    public BigDecimal totalPago() {
        BigDecimal total = BigDecimal.ZERO;
        for (Pagamento pagamento : pagamentos) {
            total = total.add(pagamento.getValorPago());
        }
        return total;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public PedidoStatus getStatusAtual() { return statusAtual; }
    public void setStatusAtual(PedidoStatus statusAtual) { this.statusAtual = statusAtual; }
    public List<ItemPedido> getItens() { return List.copyOf(itens); }
    public List<Pagamento> getPagamentos() { return List.copyOf(pagamentos); }

    @JsonIgnore // indica ao Jackson que o campo não deve aparecer no JSON, ou seja, o cliente não se repete dentro de cada pedido
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
