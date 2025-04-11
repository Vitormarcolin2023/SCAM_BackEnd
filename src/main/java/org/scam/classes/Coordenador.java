package org.scam.classes;

public class Coordenador extends Pessoa implements IAutenticavel {

    public Coordenador(){}
    public Coordenador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    // Implementação da interface
    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}