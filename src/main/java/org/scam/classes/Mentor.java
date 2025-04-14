package org.scam.classes;

import org.scam.entities.EnderecoEntity;

public class Mentor extends Pessoa implements IAutenticavel {
    Long id;
    String nome;
    String cpf;
    String email;
    String senha;
    String tipoDeUsuario;
    String telefone;
    String tempoDeExperiencia;
    String tipoDeVinculo;
    String areaDeAtuacao;
    EnderecoEntity endereco;

    public Mentor(Long id, String nome, String cpf, String email, String senha, String tipoDeUsuario, String telefone, String tempoDeExperiencia, String tipoDeVinculo, String areaDeAtuacao, EnderecoEntity endereco) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.senha = senha;
    this.tipoDeUsuario = tipoDeUsuario;
    this.telefone = telefone;
    this.tempoDeExperiencia = tempoDeExperiencia;
    this.tipoDeVinculo = tipoDeVinculo;
    this.areaDeAtuacao = areaDeAtuacao;
    this.endereco = endereco;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTempoDeExperiencia() {
        return tempoDeExperiencia;
    }

    public void setTempoDeExperiencia(String tempoDeExperiencia) {
        this.tempoDeExperiencia = tempoDeExperiencia;
    }

    public String getTipoDeVinculo() {
        return tipoDeVinculo;
    }

    public void setTipoDeVinculo(String tipoDeVinculo) {
        this.tipoDeVinculo = tipoDeVinculo;
    }

    public String getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(String areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean autenticar(String senha) {
        return false;
    }
}