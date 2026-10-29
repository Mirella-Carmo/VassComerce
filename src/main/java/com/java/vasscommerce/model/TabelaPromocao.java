package com.java.vasscommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TabelaPromocao {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataUltimaAtualizacao;
    private final List<ItemPedido> itens = new ArrayList<ItemPedido>();
    private Administrador responsavel;


    public TabelaPromocao(Long id, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, LocalDateTime dataCadastro, LocalDateTime dataUltimaAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataCadastro = dataCadastro;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public void addItem(ItemPedido item){
        itens.add(item);
    }

    public List<ItemPedido> getItens() {
        return List.copyOf(itens);
    }

    @JsonIgnore
    public Administrador getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Administrador responsavel) {
        this.responsavel = responsavel;
    }

}
