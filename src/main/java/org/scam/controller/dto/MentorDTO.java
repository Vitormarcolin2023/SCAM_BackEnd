package org.scam.controller.dto;

public class MentorDTO {
    private int id; // deve ser int, não Long
    private String nome;
    private String email;

    public MentorDTO(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}