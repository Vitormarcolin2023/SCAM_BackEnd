package org.scam;

import org.scam.classes.Coordenador;

import org.scam.classes.Projeto;
import org.scam.cadastros.ProjetoCadastro;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProjetoCadastro cadastro = new ProjetoCadastro();
        Projeto projeto = cadastro.cadastrarProjeto();

        Coordenador coordenador = new Coordenador();

        Scanner sc = new Scanner(System.in);
        int continuar = 0;

        do {

            System.out.println("======== SISTEMA DE MENTORIA ========");
            System.out.println("= [1] - Coordenador                 =");
            System.out.println("= [2] - Mentor                      =");
            System.out.println("= [3] - Aluno                       =");
            System.out.println("=====================================\n");
            System.out.println("- Selecione a opção desejada: ");
            continuar = sc.nextInt();

            switch (continuar){
                case 1: {

                    break;
                }
                case 2: {

                    break;
                }
                case 3:{

                    break;
                }
                case 4:{
                    System.out.println("\nSaindo...");
                    break;
                }
            }

        }while (continuar != 4);


    }
}

class Funcoes {

    public static Scanner sc = new Scanner(System.in);

    public static  boolean login(String documento, String senha){
        System.out.println("\n- Informe seu RA ou CPF: ");
        // em desenvolvimento
        return true;
    }

}