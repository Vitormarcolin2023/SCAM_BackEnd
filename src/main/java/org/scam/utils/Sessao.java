package org.scam.utils;

public class Sessao {
    private static int raAluno;

    public static void setRaAluno(int ra) {
        Sessao.raAluno = ra;
    }

    public static int getRaAluno() {
        return raAluno;
    }
}