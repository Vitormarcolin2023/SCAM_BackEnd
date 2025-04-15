package org.scam.menus;

import org.scam.cadastros.MentorCadastro;
import org.scam.classes.Mentor;
import org.scam.repository.MentorRepository;

import java.util.Scanner;
import javax.persistence.EntityManager;


public class MenuMentor {
    private Mentor mentor;
    private EntityManager em;
    public MenuMentor(Mentor mentor){
        this.mentor = mentor;
        this.em = em;
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
               default:
                   System.out.println("Opção inválida!");
           }
       }while (continuar != 3);
    }

    public void exibirMenu() {

        Scanner sc = new Scanner(System.in);
        int opcao;

        // Cria o repositório com a instância compartilhada de EntityManager 'em'
        MentorRepository mentorRepository = new MentorRepository(em);

        do{
            System.out.println("========= PAINEL DO MENTOR =============");
            System.out.println("= [1] - Visualizar Projetos            =");
            System.out.println("= [2] - Atualizar Conta                =");
            System.out.println("= [3] - Deletar Conta                  =");
            System.out.println("= [4] - Voltar ao Menu Mentor          =");
            System.out.println("=======================================\n");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    //visualizar projeto;
                    break;
                case 2:
                    new MentorCadastro().editarMentor();
                    break;
                case 3:
                    //deletar conta
                    break;
                case 4:
                    System.out.println("=========================");
                    System.out.println("=Voltando ao Menu Mentor=");
                    System.out.println("=========================");
                default:
                    System.out.println("Opção inválida!");
            }

        }while(opcao != 4);
    }
}
