package com.devinhouse.m2w9.exercises.dtos;

public class AutenticacaoResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AutenticacaoResponse() {
    }

    public AutenticacaoResponse(String token) {
        this.token = token;
    }
}
