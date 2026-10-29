package com.java.vasscommerce.exception;

import com.java.vasscommerce.exception.CategoriaNaoEncontradaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidBody(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) { // acessar informações da requisição (URL)

        List<Map<String, String>> fields = ex.getBindingResult() // obtém o resultado da validação
                .getFieldErrors()
                .stream()
                .map(err -> Map.of(
                        "name", err.getField(),
                        "message", err.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(montarCorpo(fields, request)); // 400
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class) // trata a exceção lançada pelo modeL - categoriaIds com id inexistente também vira 400
    public ResponseEntity<Map<String, Object>> handleCategoriaNaoEncontrada(
            CategoriaNaoEncontradaException ex,
            HttpServletRequest request) {

        List<Map<String, String>> fields = List.of(Map.of(
                "name", "categoriaIds",
                "message", ex.getMessage()));

        return ResponseEntity.badRequest().body(montarCorpo(fields, request)); // 400
    }

    private Map<String, Object> montarCorpo(List<Map<String, String>> fields, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "Invalid input");
        body.put("fields", fields);
        body.put("path", request.getRequestURI());
        return body;
    }
}