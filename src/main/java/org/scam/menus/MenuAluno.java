package org.scam.menus;
import org.scam.classes.*;
import org.scam.cadastros.ProjetoCadastro;
import org.scam.entities.MentorEntity;
import org.scam.entities.ProjetoEntity;
import org.scam.repository.CustomizerFactory;
import org.scam.repository.MentorRepository;
import org.scam.repository.ProjetoRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAluno {

    private Aluno aluno;

    private final Scanner sc = new Scanner(System.in);
    EntityManager em = CustomizerFactory.getEntityManager();
    ProjetoCadastro projetoCadastro = new ProjetoCadastro();
    Projeto projeto = new Projeto();


    public MenuAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void exibirMenu() {
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
                    projetoCadastro.cadastrarProjeto();
                    break;
                case 2:
                    gerenciarProjetos();
                    break;
                case 3:
                    MentorRepository mentorRepository = new MentorRepository(em);
                    List<MentorEntity> listaMentores = mentorRepository.buscarTodos();
                    for(MentorEntity mentor : listaMentores){
                        System.out.println(mentor.getNome());
                    }
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...\n");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    public void gerenciarProjetos() {

        int operacao = 0;

        do {
            System.out.println("\n======== GERENCIAR PROJETOS ========");
            System.out.println("[1] - Visualizar projetos");
            System.out.println("[2] - Editar projetos");
            System.out.println("[3] - Sair");
            operacao = sc.nextInt();

            switch (operacao) {
                case 1: {
                    ProjetoRepository projetoRepository = new ProjetoRepository(em);
                    List<ProjetoEntity> listaProjetos = projetoRepository.buscarTodos("raAluno", aluno.ra);

                    if (!listaProjetos.isEmpty()) {
                        mostrarProjetos(listaProjetos);
                        System.out.println("- Digite o ID do projeto que deseja visualizar mais informações ou [0] para sair: ");
                        int opListProjetos = sc.nextInt();
                    }
                    else {
                        System.out.println("Aluno sem projetos.\n");
                    }
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {
                    System.out.println("Retornando à área do aluno...");
                    break;
                }
                default: {
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
                }
            }
        } while (operacao != 3);
    }

    public void mostrarProjetos(List<ProjetoEntity> listaProjetos){
        System.out.println("\n============ PROJETOS ===============\n");
        for (ProjetoEntity projeto : listaProjetos) {
            System.out.println("- ID: [" + projeto.getId() + "]");
            System.out.println("- Nome do projeto:" + projeto.getNomeDoProjeto());
            System.out.println("- Descrição: " + projeto.getDescricao());
            System.out.println("-------------------------------------");
        }
    }
}