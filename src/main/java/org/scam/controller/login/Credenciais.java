package org.scam.controller.login;

public class Credenciais {

    private String email;
    private String senha;

    public Credenciais(){}

    public Credenciais(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
