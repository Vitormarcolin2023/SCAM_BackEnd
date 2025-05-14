package org.scam.controller.classes;

import java.util.Date;

public class Projeto {
    private int id;
    private String nomeDoProjeto;
    private String descricao;
    private String areaDeAtuacao;
    private Date dataInicioProjeto;
    private Date dataFinalProjeto;
    private int tamanhoDoGrupo;
    private String curso;
    private String periodo;
    private int fk_aluno_id;
    private int fk_mentor_id;

    public Projeto() {
    }

    public Projeto(int id, String nomeDoProjeto, String descricao, String areaDeAtuacao,
                   Date dataInicioProjeto, Date dataFinalProjeto, int tamanhoDoGrupo,
                   String curso, String periodo, int fk_aluno_id, int fk_mentor_id) {
        this.id = id;
        this.nomeDoProjeto = nomeDoProjeto;
        this.descricao = descricao;
        this.areaDeAtuacao = areaDeAtuacao;
        this.dataInicioProjeto = dataInicioProjeto;
        this.dataFinalProjeto = dataFinalProjeto;
        this.tamanhoDoGrupo = tamanhoDoGrupo;
        this.curso = curso;
        this.periodo = periodo;
        this.fk_aluno_id = fk_aluno_id;
        this.fk_mentor_id = fk_mentor_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDoProjeto() {
        return nomeDoProjeto;
    }

    public void setNomeDoProjeto(String nomeDoProjeto) {
        this.nomeDoProjeto = nomeDoProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(String areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }

    public Date getDataInicioProjeto() {
        return dataInicioProjeto;
    }

    public void setDataInicioProjeto(Date dataInicioProjeto) {
        this.dataInicioProjeto = dataInicioProjeto;
    }

    public Date getDataFinalProjeto() {
        return dataFinalProjeto;
    }

    public void setDataFinalProjeto(Date dataFinalProjeto) {
        this.dataFinalProjeto = dataFinalProjeto;
    }

    public int getTamanhoDoGrupo() {
        return tamanhoDoGrupo;
    }

    public void setTamanhoDoGrupo(int tamanhoDoGrupo) {
        this.tamanhoDoGrupo = tamanhoDoGrupo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getFk_aluno_id() {
        return fk_aluno_id;
    }

    public void setFk_aluno_id(int fk_aluno_id) {
        this.fk_aluno_id = fk_aluno_id;
    }

    public int getFk_mentor_id() {
        return fk_mentor_id;
    }

    public void setFk_mentor_id(int fk_mentor_id) {
        this.fk_mentor_id = fk_mentor_id;
    }
}