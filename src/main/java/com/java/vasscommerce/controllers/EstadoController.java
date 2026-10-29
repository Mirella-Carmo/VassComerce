package com.java.vasscommerce.controllers;

import com.java.vasscommerce.model.Cidade;
import com.java.vasscommerce.model.Estado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/estado", produces = "application/json")
public class EstadoController {

    private final List<Estado> estados = new ArrayList<>();
    private final List<Cidade> cidades = new ArrayList<>();

    public EstadoController() {
        Estado rj = new Estado(1L, "RJ", "Rio de Janeiro");
        Estado sp = new Estado(2L, "SP", "São Paulo");
        Estado mg = new Estado(3L, "MG", "Minas Gerais");
        estados.add(rj);
        estados.add(sp);
        estados.add(mg);

        cidades.add(new Cidade(1L, "Barra do Piraí", rj));
        cidades.add(new Cidade(2L, "Vassouras", rj));
        cidades.add(new Cidade(3L, "São Paulo", sp));
        cidades.add(new Cidade(4L, "Campinas", sp));
        cidades.add(new Cidade(5L, "Belo Horizonte", mg));
    }

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{idEstado}/cidade")
    public ResponseEntity<List<Cidade>> listarCidades(@PathVariable Long idEstado) {
        boolean existe = false;
        for (Estado estado : estados) {
            if (Objects.equals(estado.getId(), idEstado)) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            return ResponseEntity.notFound().build();
        }

        List<Cidade> resultado = new ArrayList<>();
        for (Cidade cidade : cidades) {
            if (Objects.equals(cidade.getEstado().getId(), idEstado)) {
                resultado.add(cidade);
            }
        }
        return ResponseEntity.ok(resultado);
    }
}
