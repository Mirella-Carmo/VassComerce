package com.java.vasscommerce.model;

import java.time.LocalDateTime;

public class Administrador extends Usuario{
    public Administrador(Long id, String nomeCompleto, String email, String senha, String fotoUrl, LocalDateTime dataCadastro, LocalDateTime dataUltimaAtualizacao) {
        super(id, nomeCompleto, email, senha, fotoUrl, dataCadastro, dataUltimaAtualizacao);
    }
}
