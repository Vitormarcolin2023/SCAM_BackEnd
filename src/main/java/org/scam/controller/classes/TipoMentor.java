package org.scam.controller.classes;

public enum TipoMentor {
    VOLUNTARIO("Voluntário"),
    CONTRATADO("Contratado");

    private final String descricao;

    TipoMentor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}