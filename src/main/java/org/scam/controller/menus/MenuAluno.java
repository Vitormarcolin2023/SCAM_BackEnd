package org.scam.controller.menus;
import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.cadastros.Curso;
import org.scam.controller.cadastros.ProjetoCadastro;
import org.scam.controller.classes.Aluno;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.ProjetoRepository;
import org.scam.model.services.Sessao;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuAluno {

    private Aluno aluno;

    private final Scanner sc = new Scanner(System.in);
    EntityManager em = CustomizerFactory.getEntityManager();
    ProjetoCadastro projetoCadastro = new ProjetoCadastro();
    //Projeto projeto = new Projeto();
    ProjetoRepository projetoRepository = new ProjetoRepository(em);

    public MenuAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void exibirMenu() {

        Sessao.setRaAluno(aluno.ra);

        int opcao;

        do {
            System.out.println("\n=========== MENU xDO ALUNO ===========");
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
                    List<MentorEntity> listarMentores = mentorRepository.listarMentoresAtivo();
                    mostrarMentores(listarMentores);
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...\n");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    public void mostrarMentores(List<MentorEntity> mentores){
        System.out.println("\n=============== MENTORES ===============");
        for(MentorEntity mentor : mentores){
            System.out.println("- ID: [" + mentor.getIdMentor() + "]");
            System.out.println("- Nome: " + mentor.getNome());
            System.out.println("- Área de atuação: " + mentor.getAreaDeAtuacao());
            System.out.println("- Tempo de experiência: " + mentor.getTempoDeExperiencia());
            System.out.println("- Email: " + mentor.getEmail());
            System.out.println("--------------------------------------");
        }
    }

    public void gerenciarProjetos() {
        List<ProjetoEntity> listaProjetos = projetoRepository.buscarTodos(aluno.ra);
        int operacao = 0;

        do {
            System.out.println("\n======== GERENCIAR PROJETOS ========");
            System.out.println("[1] - Visualizar projetos");
            System.out.println("[2] - Editar projetos");
            System.out.println("[3] - Sair");
            operacao = sc.nextInt();

            switch (operacao) {
                case 1: {
                    if (!listaProjetos.isEmpty()) {
                        mostrarProjetos(listaProjetos);
                        System.out.println("- Digite o ID do projeto que deseja visualizar mais informações ou 0 para sair: ");
                        long idProjeto = sc.nextInt();
                        if(idProjeto != 0){
                            projetoCompleto(idProjeto, false);
                        }
                    }
                    else {
                        System.out.println("Aluno sem projetos.\n");
                    }
                    break;
                }
                case 2: {
                        atualizarInfoProjeto(listaProjetos);
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
        System.out.println("\n========================= PROJETOS ============================");
        for (ProjetoEntity projeto : listaProjetos) {
            System.out.println("- ID: [" + projeto.getId() + "]");
            System.out.println("- Nome do projeto:" + projeto.getNomeDoProjeto());
            System.out.println("- Descrição: " + projeto.getDescricao());
            System.out.println("---------------------------------------------------------------\n");
        }
    }

    public void projetoCompleto(long id, boolean enumerar) {
        ProjetoEntity projeto = projetoRepository.buscarUmProjeto(id, aluno.ra);

        if (projeto == null) {
            System.out.println("\n- Projeto com ID " + id + " não encontrado!");
            return;
        }

        System.out.println("\n================== DETALHES DO PROJETO ====================");

        if (enumerar) {
            System.out.println("[1] - Nome do projeto:" + projeto.getNomeDoProjeto());
            System.out.println("[2] - Descrição: " + projeto.getDescricao());
            System.out.println("[3] - Área de atuação: " + projeto.getAreaDeAtuacao());
            System.out.println("[4] - Início: " + projeto.getDataInicioProjeto());
            System.out.println("[5] - Término: " + projeto.getDataFinalProjeto());
            System.out.println("[6] - Grupo: " + projeto.getTamanhoDoGrupo() + " Integrantes");
            System.out.println("[7] - Curso: " + projeto.getCurso());
            System.out.println("[8] - Período: " + projeto.getPeriodo());
        } else {
            System.out.println("- ID: [" + projeto.getId() + "]");
            System.out.println("- Nome do projeto:" + projeto.getNomeDoProjeto());
            System.out.println("- Descrição: " + projeto.getDescricao());
            System.out.println("- Área de atuação: " + projeto.getAreaDeAtuacao());
            System.out.println("- Início: " + projeto.getDataInicioProjeto());
            System.out.println("- Término: " + projeto.getDataFinalProjeto());
            System.out.println("- Grupo: " + projeto.getTamanhoDoGrupo() + " Integrantes");
            System.out.println("- Curso: " + projeto.getCurso());
            System.out.println("- Período: " + projeto.getPeriodo());
            System.out.println("- Alunos participantes: ");
            for (AlunoEntity aluno : projeto.getAlunos()){
                System.out.println("- RA: " + aluno.getRa() + " | Nome: " + aluno.getNome());
            }
        }
        System.out.println("---------------------------------------------------------------\n");
    }


    public void atualizarInfoProjeto(List<ProjetoEntity> listaProjetos){

        if (listaProjetos.isEmpty()){
            System.out.println("Aluno sem projetos.\n");
            return;
        }

        mostrarProjetos(listaProjetos);
        System.out.println("- Digite o ID do projeto que deseja visualizar mais informações ou 0 para sair: ");
        long idProjeto = sc.nextLong();
        sc.nextLine(); // limpar buffer

        if(idProjeto == 0) return;

        ProjetoEntity projeto = projetoRepository.buscarUmProjeto(idProjeto, aluno.ra);

        if (projeto == null) {
            System.out.println("- Projeto não encontrado.");
            return;
        }

        projetoCompleto(idProjeto, true);
        System.out.println("- Informe o número do campo que deseja atualizar: ");
        int opAtualizar = sc.nextInt();
        sc.nextLine(); // limpar buffer

        try {
            switch (opAtualizar){
                case 1 : {
                    System.out.println("- Novo nome do projeto: ");
                    String novoNome = sc.nextLine();
                    projeto.setNomeDoProjeto(novoNome);
                    break;
                }
                case 2 : {
                    System.out.println("- Nova descrição: ");
                    String novaDescricao = sc.nextLine();
                    projeto.setDescricao(novaDescricao);
                    break;
                }
                case 3 : {
                    System.out.println("- Nova área de atuação (opções):");
                    for (AreaDeAtuacao area : AreaDeAtuacao.values()) {
                        System.out.println("  - " + area.name());
                    }
                    System.out.print("- Digite a nova área: ");
                    String novaArea = sc.nextLine().toUpperCase();
                    projeto.setAreaDeAtuacao(AreaDeAtuacao.valueOf(novaArea));
                    break;
                }
                case 4 : {
                    System.out.println("- Nova data de início (yyyy-MM-dd): ");
                    String novaData = sc.nextLine();
                    projeto.setDataInicioProjeto(LocalDate.parse(novaData));
                    break;
                }
                case 5 : {
                    System.out.println("- Nova data de término (yyyy-MM-dd): ");
                    String novaData = sc.nextLine();
                    projeto.setDataFinalProjeto(LocalDate.parse(novaData));
                    break;
                }
                case 6 : {
                    System.out.println("- Novo tamanho do grupo (mínimo 2, máximo 6): ");
                    int novoTamanho = sc.nextInt();
                    sc.nextLine(); // limpar buffer
                    projeto.setTamanhoDoGrupo(novoTamanho);
                    break;
                }
                case 7 : {
                    System.out.println("- Novo curso (opções):");
                    for (Curso curso : Curso.values()) {
                        System.out.println("  - " + curso.name());
                    }
                    System.out.print("- Digite o novo curso: ");
                    String novoCurso = sc.nextLine().toUpperCase();
                    projeto.setCurso(Curso.valueOf(novoCurso));
                    break;
                }
                case 8 : {
                    System.out.println("- Novo período: ");
                    String novoPeriodo = sc.nextLine();
                    projeto.setPeriodo(novoPeriodo);
                    break;
                }
                default : {
                    System.out.println("- Opção inválida.");
                    return;
                }
            }

            projetoRepository.atualizar(projeto);
            System.out.println("- Projeto atualizado com sucesso!");

        } catch (Exception e) {
            System.out.println("- Erro ao atualizar projeto: " + e.getMessage());
        }
    }


}