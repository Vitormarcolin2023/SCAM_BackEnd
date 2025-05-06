package org.scam.entities;

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
    private int idAreaDeAtuacao;

    public CursoEntity(int id, String descricaoCurso, int idAreaDeAtuacao) {
        this.id = id;
        this.descricaoCurso = descricaoCurso;
        this.idAreaDeAtuacao = idAreaDeAtuacao;
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

    public int getIdAreaDeAtuacao() {
        return idAreaDeAtuacao;
    }

    public void setIdAreaDeAtuacao(int idAreaDeAtuacao) {
        this.idAreaDeAtuacao = idAreaDeAtuacao;
    }
}
