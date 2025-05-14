package org.scam.model.entities;

import javax.persistence.*;

@Entity(name = "tb_curso")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "descricao")
    private String descricaoCurso;

    //Foreing Keys
    @Column(name = "fk_area_de_atuacao_id")
    private int fk_area_de_atuacao_id;

    public int getAreaDeAtuacao() {
        return fk_area_de_atuacao_id;
    }

    public void setAreaDeAtuacao(int areaDeAtuacao) {
        this.fk_area_de_atuacao_id = areaDeAtuacao;
    }

    public CursoEntity() {

    }

    public CursoEntity(String descricaoCurso, int areaDeAtuacao) {
        this.descricaoCurso = descricaoCurso;
        this.fk_area_de_atuacao_id = areaDeAtuacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoCurso() {
        return descricaoCurso;
    }

    public void setDescricaoCurso(String descricaoCurso) {
        this.descricaoCurso = descricaoCurso;
    }

    public int getFk_area_de_atuacao_id() {
        return fk_area_de_atuacao_id;
    }

    public void setFk_area_de_atuacao_id(int fk_area_de_atuacao_id) {
        this.fk_area_de_atuacao_id = fk_area_de_atuacao_id;
    }
}
