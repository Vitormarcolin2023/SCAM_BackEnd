package org.scam.classes;

public class Aluno extends Pessoa {

    public int id;
    public int ra;

    public Aluno(String nome, String email, String senha, int id, int ra) {
        super (nome, email, senha);
        this.id = id;
        this.ra = ra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }
}