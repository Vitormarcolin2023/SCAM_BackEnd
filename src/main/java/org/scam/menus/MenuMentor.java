package org.scam.menus;

import org.scam.cadastros.MentorCadastro;
import org.scam.classes.Mentor;
import org.scam.classes.Projeto;
import org.scam.entities.ProjetoEntity;
import org.scam.repository.MentorRepository;
import org.scam.repository.ProjetoRepository;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;


public class MenuMentor {
    private Mentor mentor;
    private EntityManager em;
    Projeto projeto = new Projeto();
    ProjetoRepository projetoRepository = new ProjetoRepository(em);

    public MenuMentor(Mentor mentor, EntityManager em){
        this.mentor = mentor;
        this.em = em;
        this.projetoRepository = new ProjetoRepository(em);
    }


    public MenuMentor(){}

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int continuar = 0;
       do{
           System.out.println("======== MENU DO MENTOR =======");
           System.out.println("= [1] - Login                 =");
           System.out.println("= [2] - Cadastre-se           =");
           System.out.println("= [3] - Sair                  =");
           System.out.println("=============================\n");
           System.out.println("- Selecione a opção desejada: ");
           continuar = sc.nextInt();
           sc.nextLine();

           switch (continuar){
               case 1:
                   return;
               case 2:
                   new MentorCadastro().cadastrarMentor();
                   break;
               case 3:
                   break;
               default:
                   System.out.println("Opção inválida!");
                   break;
           }
       }while (continuar != 3);
    }

    public void exibirMenu() {

        Scanner sc = new Scanner(System.in);
        int opcao;

        do{
            System.out.println("========= PAINEL DO MENTOR =============");
            System.out.println("= [1] - Visualizar Projetos            =");
            System.out.println("= [2] - Atualizar Conta                =");
            System.out.println("= [3] - Desativar Conta                  =");
            System.out.println("= [4] - Voltar ao Menu Mentor          =");
            System.out.println("=======================================\n");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    List<ProjetoEntity> listaProjetos = projetoRepository.buscarTodosMentor("idMentor", mentor.getId());

                    if (!listaProjetos.isEmpty()) {
                        mostrarProjetos(listaProjetos);
                        System.out.println("- Digite o ID do projeto que deseja visualizar mais informações ou 0 para sair: ");
                        long idProjeto = sc.nextInt();
                        if(idProjeto != 0){
                            projetoCompleto(idProjeto);
                        }
                    }
                    break;
                case 2:
                    new MentorCadastro().editarMentor();
                    break;
                case 3:
                    System.out.print("Tem certeza que deseja deletar sua conta? (s/n): ");
                    String confirmacao = sc.nextLine();

                    if (confirmacao.equalsIgnoreCase("s")) {
                        MentorRepository mentorRepository = new MentorRepository(em);
                        mentorRepository.removerPorId(mentor.getId());
                        System.out.println("✅ Conta deletada com sucesso.");

                        opcao = 4;
                    } else {
                        System.out.println("❌ Operação cancelada.");
                    }
                    break;
                case 4:
                    System.out.println("=========================");
                    System.out.println("=Voltando ao Menu Mentor=");
                    System.out.println("=========================");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        }while(opcao != 4);
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

    public void projetoCompleto(long id){
        ProjetoEntity projeto = projetoRepository.buscarUmProjetoMentor(id, mentor.getId());

        if(projeto == null){
            System.out.println("\n- Projeto com ID " + id + " não encontrado!");
            return;
        }

        System.out.println("- ID: [" + projeto.getId() + "]");
        System.out.println("- Nome do projeto:" + projeto.getNomeDoProjeto());
        System.out.println("- Descrição: " + projeto.getDescricao());
        System.out.println("- Área de atuação: " + projeto.getAreaDeAtuacao());
        System.out.println("- Início: " + projeto.getDataInicioProjeto());
        System.out.println("- Término: " + projeto.getDataFinalProjeto());
        System.out.println("- Grupo: " + projeto.getTamanhoDoGrupo() + " Integrantes");
        System.out.println("- Curso: " + projeto.getCurso());
        System.out.println("- Período: " + projeto.getPeriodo());
    }
}