package org.scam.view;

import org.scam.controller.classes.Validacao;
import org.scam.controller.login.Credenciais;
import org.scam.view.TelaSelecaoUsuarioView;
import org.scam.view.coordenacao.PainelPrincipalView;
import org.scam.view.coordenacao.LoginCoordenacaoView;
<<<<<<< HEAD

import org.scam.view.mentor.DesativarContaMentorView;
import org.scam.view.mentor.LoginOneMentorView;
import org.scam.view.mentor.LoginTwoMentorView;
//import org.scam.view.mentor.TelaInicialMentorView;


=======
import org.scam.view.mentor.DesativarContaMentorView;
import org.scam.view.mentor.LoginOneMentorView;
import org.scam.view.mentor.LoginTwoMentorView;
>>>>>>> 47cbd6c315a6b0594aac4041712ee6d8dd1b0c1f

import java.util.Scanner;

//public class Main {
    //public static void main(String[] args) {

<<<<<<< HEAD
       // VisualizarProjetoMentorView.visualizarProjeto();
        //TelaInicialMentor.telaMentor();
=======



>>>>>>> 47cbd6c315a6b0594aac4041712ee6d8dd1b0c1f
        //DesativarContaMentorView.desativarContaM();
        //LoginOneMentorView.loginOne();
        //LoginTwoMentorView.loginTwo();
        //LoginCoordenacaoView.loginCoordenacao();
        //LoginTwoMentorView.loginTwo();
        //LoginViewCoordenação.loginCoordenacao(); - Coordenação
        //LoginAlunoView.loginAluno(); - Aluno

<<<<<<< HEAD

       // TelaSelecaoUsuarioView.exibirTelaSelecao(); // Ja o menu
        //PainelPrincipalView.painelCoordenacao();

        //TelaSelecaoUsuarioView.exibirTelaSelecao(); // Ja o menu
=======
        TelaSelecaoUsuarioView.exibirTelaSelecao(); // Ja o menu
        //PainelPrincipalView.painelCoordenacao();
>>>>>>> 47cbd6c315a6b0594aac4041712ee6d8dd1b0c1f

        //ProjetoCadastro projetoCadastro = new ProjetoCadastro();
        //projetoCadastro.cadastrarProjeto();

        //MentorCadastro mentorCadastro = new MentorCadastro();
        //mentorCadastro.cadastrarMentor();

<<<<<<< HEAD
        do {

            System.out.println("======== SISTEMA DE MENTORIA ========");
            System.out.println("= [1] - Coordenador                 =");
            System.out.println("= [2] - Mentor                      =");
            System.out.println("= [3] - Aluno                       =");
            System.out.println("= [4] - Sair                        =");
            System.out.println("=====================================\n");
            System.out.println("- Selecione a opção desejada: ");
            continuar = sc.nextInt();
            sc.nextLine();

            switch (continuar) {
                case 1: {
                    Credenciais credenciais = menuLogin(continuar);
                    Coordenador coordenador = login.loginCoordenador(credenciais.getEmail(), credenciais.getSenha());
                    if(coordenador!=null){
                        MenuCoordenador menuCoordenador = new MenuCoordenador(coordenador);
                        menuCoordenador.exibirMenu();
                    }
                   else {
                        System.out.println("\nUsuário ou senha inválidos!");
                    }
                    break;
                }
                case 2: {
                    MenuMentor menuMentor = new MenuMentor();
                    boolean fazLogin = menuMentor.menu();
                    if(fazLogin) {
                        Credenciais credenciais = menuLogin(continuar);
                        Mentor mentor = login.loginMentor(credenciais.getEmail(), credenciais.getSenha());
                        if (mentor != null) {
                            menuMentor.exibirMenu();
                        } else {
                            System.out.println("\nUsuário ou senha inválidos!");
                        }
                    }
                    break;
                }
                case 3: {
                    Credenciais credenciais = menuLogin(continuar);
                    Aluno aluno = login.loginAluno(credenciais.getEmail(), credenciais.getSenha());
                    if(aluno!=null){
                        MenuAluno menuAluno = new MenuAluno(aluno);
                        menuAluno.exibirMenu();
                    }
                    else {
                        System.out.println("\nUsuário ou senha inválidos!");
                    }
                    break;
                }
                case 4: {
                    System.out.println("\nSaindo...");
                    break;
                }
                default: {
                    System.out.println("\nOpção inválida, tente novamente.");
                    break;
                }
            }
        }while (continuar != 4) ;*/



    /*public static Credenciais menuLogin (int tipoUsuario) {

        Scanner sc = new Scanner(System.in);
        Validacao validacao = new Validacao(); // utilizada para validar os dados digitados pelo usuário para login

        System.out.println("\n=============== LOGIN ===============");
        String email = validacao.lerEmail("- Informe seu email: ");
        System.out.println("- Informe sua senha: ");
        String senha = sc.nextLine();

        return new Credenciais(email, senha);
    }*/

=======
    }
}
>>>>>>> 47cbd6c315a6b0594aac4041712ee6d8dd1b0c1f


