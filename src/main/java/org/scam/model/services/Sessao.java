package org.scam.model.services;

import org.scam.controller.classes.Mentor;

public class Sessao {
    private static int raAluno;
    private static Mentor mentorLogado;
    private static String email;


    public static void setRaAluno(int ra) {
        Sessao.raAluno = ra;
    }

    public static int getRaAluno() {
        return raAluno;
    }

    public static void setMentorLogado(Mentor mentor) {
        mentorLogado = mentor;
    }

    public static Mentor getMentorLogado() {
        return mentorLogado;
    }

    public static void limparSessao() {
        raAluno = Integer.parseInt(null);
        mentorLogado = null;
    }

    public static String getEmail() {
        if (mentorLogado != null) {
            return mentorLogado.getEmail();
        }
        return null;
    }

}