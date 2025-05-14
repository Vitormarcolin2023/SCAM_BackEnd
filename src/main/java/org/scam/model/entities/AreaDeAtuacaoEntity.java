package org.scam.model.entities;

import javax.persistence.*;

@Entity(name = "tb_area_de_atuacao")
public class AreaDeAtuacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "descricao")
    private String descricaoAreaDeAtuacao;

    public AreaDeAtuacaoEntity() {

    }

    public AreaDeAtuacaoEntity(long id, String descricaoAreaDeAtuacao) {
        this.id = id;
        this.descricaoAreaDeAtuacao = descricaoAreaDeAtuacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricaoAreaDeAtuacao() {
        return descricaoAreaDeAtuacao;
    }

    public void setDescricaoAreaDeAtuacao(String descricaoAreaDeAtuacao) {
        this.descricaoAreaDeAtuacao = descricaoAreaDeAtuacao;
    }
}
