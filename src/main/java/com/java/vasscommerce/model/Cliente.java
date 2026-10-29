package com.java.vasscommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{
    private String cpf;
    private LocalDate dataNascimento;
    private final Endereco endereco;
    private final List<Cartao> cartoes = new ArrayList<>();

    public Cliente(Long id, String nomeCompleto, String email, String senha, String fotoUrl,
                   LocalDateTime dataCadastro, LocalDateTime dataUltimaAtualizacao,
                   String cpf, LocalDate dataNascimento, Endereco endereco) {
        super(id, nomeCompleto, email, senha, fotoUrl, dataCadastro, dataUltimaAtualizacao);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public void adicionarCartao(Cartao cartao) {
        cartoes.add(cartao);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    @JsonIgnore
    public List<Cartao> getCartoes() {
        return List.copyOf(cartoes);
    }
}
