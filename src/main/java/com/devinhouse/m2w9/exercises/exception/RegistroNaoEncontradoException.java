package com.devinhouse.m2w9.exercises.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    private String nome;
    private String identificador;


    public RegistroNaoEncontradoException(String nome, Integer identificador) {
        this(nome, identificador.toString());
    }

    public RegistroNaoEncontradoException(String nome, Long identificador) {
        this(nome, identificador.toString());
    }


    public String getMessage() {
        if (nome == null || identificador == null)
            return null;
        return String.format("Registro '%s' nnao encontrado com identificador '%s'", nome, identificador);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}