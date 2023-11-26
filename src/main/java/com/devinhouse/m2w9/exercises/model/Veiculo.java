package com.devinhouse.m2w9.exercises.model;

import com.devinhouse.m2w9.exercises.model.enums.TipoVeiculo;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "VEICULOS")
public class Veiculo {

    @Id
    @Column(nullable = false)
    private String placa;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVeiculo tipo;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Integer anoFabricacao;

    @Column(nullable = false)
    private String cor;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "veiculo")
    private List<Multa> multas;


    public Veiculo() {
    }

    public Veiculo(String placa, TipoVeiculo tipo, String nome, Integer anoFabricacao, String cor) {
        this.placa = placa;
        this.tipo = tipo;
        this.nome = nome;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
    }

    public boolean temMultas(){
        return this.multas != null && !this.multas.isEmpty();
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", tipo=" + tipo +
                ", nome='" + nome + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", cor='" + cor + '\'' +
                ", multas=" + multas +
                '}';
    }

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

    public List<Multa> getMultas() {
        return multas;
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }
}
