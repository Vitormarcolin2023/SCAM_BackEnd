package org.scam.controller.classes;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.model.entities.EnderecoEntity;

public class Mentor extends Pessoa implements IAutenticavel {
    int id;
    String nome;
    String cpf;
    String email;
    String senha;
    TipoMentor tipoDeUsuario;
    String telefone;
    String tempoDeExperiencia;
    String tipoDeVinculo;
    AreaDeAtuacao areaDeAtuacao;
    EnderecoEntity endereco;

    public Mentor(int id, String nome, String cpf, String email, String senha, TipoMentor tipoDeUsuario, String telefone, String tempoDeExperiencia, String tipoDeVinculo, AreaDeAtuacao areaDeAtuacao, EnderecoEntity endereco) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public TipoMentor getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(TipoMentor tipoDeUsuario) {
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

    public AreaDeAtuacao getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(AreaDeAtuacao areaDeAtuacao) {
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