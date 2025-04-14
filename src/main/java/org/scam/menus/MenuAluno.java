package org.scam.menus;
import org.scam.classes.*;
import org.scam.cadastros.ProjetoCadastro;

import java.util.Scanner;

public class MenuAluno {

    private Aluno aluno;

    public MenuAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void exibirMenu() {

        ProjetoCadastro projetoCadastro = new ProjetoCadastro();

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DO ALUNO =====");
            System.out.println("[1] - Cadastrar Projeto");
            System.out.println("[2] - Listar Mentores");
            System.out.println("[3] - Voltar");
            System.out.print("Selecione uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    projetoCadastro.cadastrarProjeto();
                    break;
                case 2:
                    //listarMentores();
                    break;
                case 3:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }
}