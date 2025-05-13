package org.scam;

import org.scam.classes.Aluno;
import org.scam.classes.Coordenador;
import org.scam.classes.Mentor;
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
import org.scam.utils.Sessao;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Funcoes funcoes = new Funcoes();

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
                if(continuar == 2){
                    new MenuMentor().menu();
                }
                UsuarioEntity usuario = funcoes.login(continuar);

                if (usuario != null) {
                    System.out.println("\nBem-vindo(a), " + usuario.getNome());

                    if (usuario instanceof CoordenacaoEntity) {

                        System.out.println("\nAcessando painel do Coordenador...");
                        CoordenacaoEntity coordenacaoEntity = (CoordenacaoEntity) usuario;
                        Coordenador coordenador = coordenacaoEntity.toCoordenador();
                        MenuCoordenador menuCoordenador = new MenuCoordenador(coordenador);
                        menuCoordenador.exibirMenu();

                    } else if (usuario instanceof MentorEntity) {
                        System.out.println("\nAcessando painel do Mentor...");
                        MentorEntity mentorEntity = (MentorEntity) usuario;
                        Mentor mentor = mentorEntity.toMentor();

                        Sessao.setMentorLogado(mentor);

                        EntityManager em = CustomizerFactory.getEntityManager();
                        MenuMentor menuMentor = new MenuMentor(mentor, em);
                        menuMentor.exibirMenu();

                    } else if (usuario instanceof AlunoEntity) {

                        System.out.println("\nAcessando painel do Aluno...");
                        AlunoEntity alunoEntity = (AlunoEntity) usuario; // usuário como AlunoEntity (casting)
                        Aluno aluno = alunoEntity.toAluno(); // transforma a entity em Aluno
                        MenuAluno menuAluno = new MenuAluno(aluno); // instancia o menu aluno
                        // SALVA O RA
                        Sessao.setRaAluno(alunoEntity.getRa());
                        Sessao.setCursoAluno(alunoEntity.getCursoId());

                        menuAluno.exibirMenu(); // chama a funçao p/ rodar o menu aluno

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

