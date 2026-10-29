package com.java.vasscommerce.exception;

public class CategoriaNaoEncontradaException extends RuntimeException {

    public CategoriaNaoEncontradaException(Long id) {
        super("Categoria não encontrada: " + id);
    }
}
