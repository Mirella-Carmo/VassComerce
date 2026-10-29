package com.java.vasscommerce.controllers;

import com.java.vasscommerce.model.TipoCartao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tipo-cartao", produces = "application/json")
public class TipoCartaoController {

    @GetMapping
    public ResponseEntity<List<TipoCartao>> listar() {
        List<TipoCartao> tipos = new ArrayList<>();
        for (TipoCartao tipo : TipoCartao.values()) {
            tipos.add(tipo);
        }
        return ResponseEntity.ok(tipos);
    }
}
