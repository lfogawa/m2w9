package com.devinhouse.m2w9.exercises.dtos;

import com.devinhouse.m2w9.exercises.model.Multa;
import com.devinhouse.m2w9.exercises.model.enums.TipoVeiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class VeiculoRequest{
    @NotBlank
    private String placa;
    @NotBlank
    private TipoVeiculo tipo;
    @NotBlank
    private String nome;
    @NotNull
    private Integer anoFabricacao;
    @NotBlank
    private String cor;

    private List<MultaResponse> multas;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
