package org.scam;

import org.scam.classes.Projeto;
import org.scam.cadastros.ProjetoCadastro;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProjetoCadastro cadastro = new ProjetoCadastro();
        Projeto projeto = cadastro.cadastrarProjeto();

        Scanner sc = new Scanner(System.in);
        int continuar = 0;

        do {

            System.out.println("======== SISTEMA DE MENTORIA ========");
            System.out.println("= [1] - Coordenador                 =");
            System.out.println("= [2] - Mentor                      =");
            System.out.println("= [3] - Aluno                       =");
            System.out.println("=====================================\n");
            continuar = sc.nextInt();



        }while (continuar != 4);


    }
}