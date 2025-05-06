package org.scam.entities;

import javax.persistence.*;

@Entity(name = "tb_area_de_atuacao")
public class AreaDeAtuacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "descricao")
    private String descricaoAreaDeAtuacao;

    public AreaDeAtuacaoEntity(int id, String descricaoAreaDeAtuacao) {
        this.id = id;
        this.descricaoAreaDeAtuacao = descricaoAreaDeAtuacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoAreaDeAtuacao() {
        return descricaoAreaDeAtuacao;
    }

    public void setDescricaoAreaDeAtuacao(String descricaoAreaDeAtuacao) {
        this.descricaoAreaDeAtuacao = descricaoAreaDeAtuacao;
    }
}
