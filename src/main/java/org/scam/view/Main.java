package org.scam.view;

import org.scam.controller.classes.Aluno;
import org.scam.controller.classes.Coordenador;
import org.scam.controller.classes.Mentor;
import org.scam.controller.classes.Validacao;
import org.scam.controller.login.Credenciais;
import org.scam.controller.login.Usuario;
import org.scam.controller.menus.MenuAluno;
import org.scam.controller.menus.MenuCoordenador;
import org.scam.controller.menus.MenuMentor;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.services.Sessao;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Usuario login = new Usuario(); // utilizada para validar as informações de login
        EntityManager em = CustomizerFactory.getEntityManager();

        int continuar = 0;

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
                    Credenciais credenciais = menuLogin(continuar);
                    Mentor mentor = login.loginMentor(credenciais.getEmail(), credenciais.getSenha());
                    if(mentor!=null){
                        MenuMentor menuMentor = new MenuMentor(mentor, em);
                        Sessao.setMentorLogado(mentor);
                        menuMentor.exibirMenu();
                    }
                    else {
                        System.out.println("\nUsuário ou senha inválidos!");
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
        }while (continuar != 4) ;
    }



    public static Credenciais menuLogin (int tipoUsuario) {

        Scanner sc = new Scanner(System.in);
        Validacao validacao = new Validacao(); // utilizada para validar os dados digitados pelo usuário para login

        System.out.println("\n=============== LOGIN ===============");
        String email = validacao.lerEmail("- Informe seu email: ");
        System.out.println("- Informe sua senha: ");
        String senha = sc.nextLine();

        return new Credenciais(email, senha);
    }
}


