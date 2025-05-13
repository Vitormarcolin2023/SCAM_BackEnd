package org.scam.utils;

import org.scam.classes.Mentor;

public class Sessao {
    private static int raAluno;
    private static Mentor mentorLogado;
    private static int cursoAluno;


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

    public static int getCursoAluno() {
        return cursoAluno;
    }

    public static void setCursoAluno(int cursoAluno) {
        Sessao.cursoAluno = cursoAluno;
    }

    public static void limparSessao() {
        raAluno = Integer.parseInt(null);
        mentorLogado = null;
    }
}