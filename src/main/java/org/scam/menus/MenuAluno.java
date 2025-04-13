package org.scam.menus;
import org.scam.classes.*;
import org.scam.cadastros.ProjetoCadastro;
import org.scam.entities.ProjetoEntity;
import org.scam.repository.CustomizerFactory;
import org.scam.repository.ProjetoRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAluno {

    private Aluno aluno;

    public MenuAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void exibirMenu() {

        EntityManager em = CustomizerFactory.getEntityManager();
        ProjetoCadastro projetoCadastro = new ProjetoCadastro();
        Projeto projeto = new Projeto();

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=========== MENU DO ALUNO ===========");
            System.out.println("[1] - Cadastrar Projeto");
            System.out.println("[2] - Gerenciar Projetos");
            System.out.println("[3] - Listar Mentores");
            System.out.println("[4] - Voltar\n");
            System.out.print("Selecione uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    projeto = projetoCadastro.cadastrarProjeto();
                    System.out.println("Projeto cadastrado com sucesso!");
                    break;
                case 2:
                    //litarProjetos
                    ProjetoRepository projetoRepository = new ProjetoRepository(em);
                    List<ProjetoEntity> listaProjetos = projetoRepository.buscarTodos();
                    System.out.println(listaProjetos);
                    break;
                case 3:
                    //listarMentores
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }
}