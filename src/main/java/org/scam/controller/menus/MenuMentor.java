package org.scam.controller.menus;

import org.scam.controller.cadastros.MentorCadastro;
import org.scam.controller.classes.Mentor;

import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.ProjetoRepository;
import org.scam.model.services.Sessao;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;


public class MenuMentor {
    private Mentor mentor;
    private final EntityManager em = CustomizerFactory.getEntityManager();

    ProjetoRepository projetoRepository = new ProjetoRepository(em);

    public MenuMentor(Mentor mentor){
        this.mentor = mentor;
        this.projetoRepository = new ProjetoRepository(em);
    }


    public MenuMentor(){}

    public boolean menu() {
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
                   return true;
               case 2:
                   new MentorCadastro().cadastrarMentor();
                   return false;
               case 3:
                   break;
               default:
                   System.out.println("Opção inválida!");
                   break;
           }
       }while (continuar != 3);
       return false;
    }

    public void exibirMenu() {

        Scanner sc = new Scanner(System.in);
        int opcao;

        Sessao.setMentorLogado(mentor);

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
                    System.out.print("Tem certeza que deseja desativar sua conta? (s/n): ");
                    String confirmacao = sc.nextLine();

                    if (confirmacao.equalsIgnoreCase("s")) {
                        System.out.println("Digite o motivo da desativação:");
                        String motivo = sc.nextLine();

                        MentorRepository mentorRepository = new MentorRepository(em);
                        mentorRepository.desativarPorId(mentor.getId(), motivo);
                        System.out.println("✅ Conta desativada com sucesso.");

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