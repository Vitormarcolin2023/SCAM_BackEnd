package org.scam.model.services;

public class SessaoMentor {
    private static String emailMentorLogado;

    public static void setEmail(String email) {
        emailMentorLogado = email;
    }

    public static String getEmail() {
        return emailMentorLogado;
    }

    public static void limpar() {
        emailMentorLogado = null;
    }
}

