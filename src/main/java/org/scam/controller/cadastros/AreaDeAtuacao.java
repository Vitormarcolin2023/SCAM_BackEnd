package org.scam.controller.cadastros;

public enum AreaDeAtuacao {

    AGRARIAS_E_BIOLOGIA("Agrárias e Biologia"),
    EDUCACAO("Educação"),
    ENGENHARIA_E_ARQUITETURA("Engenharia e Arquitetura"),
    GESTAO("Gestão"),
    SAUDE("Saúde"),
    SOCIAIS("Sociais"),
    TECNOLOGIA("Tecnologia");

    private final String descricao;

    AreaDeAtuacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}