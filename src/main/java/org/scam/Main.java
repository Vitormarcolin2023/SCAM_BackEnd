package org.scam;

import org.scam.classes.Aluno;
import org.scam.classes.Coordenador;
import org.scam.entities.AlunoEntity;
import org.scam.entities.CoordenacaoEntity;
import org.scam.entities.MentorEntity;
import org.scam.entities.UsuarioEntity;
import org.scam.menus.MenuAluno;
import org.scam.menus.MenuCoordenador;
import org.scam.menus.MenuMentor;
import org.scam.repository.AlunoRepository;
import org.scam.repository.CoordenacaoRepository;
import org.scam.repository.CustomizerFactory;
import org.scam.repository.MentorRepository;

import javax.persistence.EntityManager;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Funcoes funcoes = new Funcoes();
        //MenuAluno menuAluno = new MenuAluno();
        MenuCoordenador menuCoordenador = new MenuCoordenador();
        MenuMentor menuMentor = new MenuMentor();

        Scanner sc = new Scanner(System.in);
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

            if (continuar >= 1 && continuar <= 3) {
                UsuarioEntity usuario = funcoes.login(continuar);

                if (usuario != null) {
                    System.out.println("\nBem-vindo(a), " + usuario.getNome());

                    if (usuario instanceof CoordenacaoEntity) {

                        System.out.println("- Acessando painel do Coordenador...");

                    } else if (usuario instanceof MentorEntity) {

                        System.out.println("- Acessando painel do Mentor...");

                    } else if (usuario instanceof AlunoEntity) {

                        System.out.println("- Acessando painel do Aluno...");

                    }

                } else {
                    System.out.println("\nUsuário ou senha inválidos.");
                }
            } else if (continuar == 4) {
                System.out.println("\nSaindo...");
            } else {
                System.out.println("\nOpção inválida. Tente novamente.");
            }

        }while (continuar != 4);


    }
}

class Funcoes {

    public UsuarioEntity login (int tipoUsuario) {

        Scanner sc = new Scanner(System.in);
        EntityManager em = CustomizerFactory.getEntityManager();

        System.out.println("\n=============== LOGIN ===============");
        System.out.println("- Informe seu email: ");
        String email = sc.nextLine();
        System.out.println("- Informe sua senha: ");
        String senha = sc.nextLine();

        switch (tipoUsuario){
            case 1: {
                CoordenacaoRepository coordenacaoRepository = new CoordenacaoRepository(em);
                return coordenacaoRepository.login(email, senha);
            }
            case 2: {
                MentorRepository mentorRepository = new MentorRepository(em);
                return mentorRepository.login(email, senha);
            }
            case 3: {
                AlunoRepository alunoRepository = new AlunoRepository(em);
                return alunoRepository.login(email, senha);
            }
            default: {
                System.out.println("\nUsuário inválido");
                return null;
            }
        }
    }
}

