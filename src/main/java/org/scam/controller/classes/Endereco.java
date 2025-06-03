package org.scam.controller.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco {
    private String logradouro;
    private String localidade;
    private String estado;
    private String bairro;
    private String cep;
    private int numero;

    // Construtor padrão
    public Endereco() {}

    // Construtor parametrizado


    public Endereco(String logradouro, String localidade, String estado, String bairro, String cep) {
        this.logradouro = logradouro;
        this.localidade = localidade;
        this.estado = estado;
        this.bairro = bairro;
        this.cep = cep;
    }

    // Construtor de cópia
    public Endereco(Endereco outroEndereco) {
        this.logradouro = outroEndereco.logradouro;
        this.localidade = outroEndereco.localidade;
        this.estado = outroEndereco.estado;
    }

    // Getters e Setters
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getLocalidade() {
        return localidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }
}