package com.java.vasscommerce.controllers;

import com.java.vasscommerce.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/cliente", produces = "application/json")
public class ClienteController {

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();

    public ClienteController() {
        LocalDateTime agora = LocalDateTime.now();

        Estado rj = new Estado(1L, "RJ", "Rio de Janeiro");
        Cidade barra = new Cidade(1L, "Barra do Piraí", rj);
        Endereco endereco = new Endereco(1L, "Rua das Flores", 120, "27123-000",
                "Apto 101", "24999990000", "Centro", agora, agora, barra);

        Cliente maria = new Cliente(1L, "Maria Souza", "maria@email.com", "minhasenha", null,
                agora, agora, "123.456.789-00", LocalDate.of(2000, 5, 20), endereco);

        Cartao credito = new Cartao(1L, TipoCartao.CREDITO, false);
        Cartao debito = new Cartao(2L, TipoCartao.DEBITO, false);
        maria.adicionarCartao(credito);
        maria.adicionarCartao(debito);
        maria.adicionarCartao(new Cartao(3L, TipoCartao.CREDITO, true));
        clientes.add(maria);

        Produto fone = new Produto(2L, "Fone Bluetooth", "Fone sem fio",
                "https://exemplo.com/fone.png", new BigDecimal("249.90"), new ArrayList<>());
        Produto camiseta = new Produto(4L, "Camiseta Básica", "100% algodão",
                "https://exemplo.com/camiseta.png", new BigDecimal("39.90"), new ArrayList<>());

        Pedido pedido = new Pedido(1L, LocalDateTime.of(2026, 9, 10, 14, 30),
                PedidoStatus.ENTREGUE_CLIENTE, maria);
        pedido.adicionarItem(new ItemPedido(1L, 1, fone.getValorUnitario(), fone));
        pedido.adicionarItem(new ItemPedido(2L, 2, camiseta.getValorUnitario(), camiseta));

        pedido.adicionarPagamento(new Pagamento(1L, new BigDecimal("200.00"), credito));
        pedido.adicionarPagamento(new Pagamento(2L, new BigDecimal("129.70"), debito));
        pedidos.add(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterPorId(@PathVariable Long id) {
        Cliente cliente = buscarCliente(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{idCliente}/formas-de-pagamento")
    public ResponseEntity<List<Cartao>> listarFormasDePagamento(@PathVariable Long idCliente) {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        List<Cartao> formas = new ArrayList<>();
        for (Cartao cartao : cliente.getCartoes()) {
            if (!cartao.isExcluido()) {
                formas.add(cartao);
            }
        }
        return ResponseEntity.ok(formas);
    }

    @GetMapping("/{idCliente}/endereco")
    public ResponseEntity<Endereco> buscarEndereco(@PathVariable Long idCliente) {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente.getEndereco());
    }

    @GetMapping("/{idCliente}/pedido")
    public ResponseEntity<List<Pedido>> listarPedidos(@PathVariable Long idCliente) {
        if (buscarCliente(idCliente) == null) {
            return ResponseEntity.notFound().build();
        }
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (Objects.equals(pedido.getCliente().getId(), idCliente)) {
                resultado.add(pedido);
            }
        }
        return ResponseEntity.ok(resultado);
    }

    private Cliente buscarCliente(Long id) {
        for (Cliente cliente : clientes) {
            if (Objects.equals(cliente.getId(), id)) {
                return cliente;
            }
        }
        return null;
    }
}