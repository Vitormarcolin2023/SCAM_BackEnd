package org.scam.classes;

public class Aluno extends Pessoa implements IAutenticavel {

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

    @Override
    public boolean autenticar(String senha) {
        return false;
    }
}