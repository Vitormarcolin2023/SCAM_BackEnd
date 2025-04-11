package org.scam;

import org.scam.classes.Aluno;
import org.scam.classes.Coordenador;
import org.scam.entities.AlunoEntity;
import org.scam.entities.CoordenacaoEntity;
import org.scam.menus.MenuAluno;
import org.scam.repository.AlunoRepository;
import org.scam.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.sql.SQLOutput;
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

            switch (continuar){
                case 1: {

                    break;
                }
                case 2: {

                    break;
                }
                case 3:{
                       /* MenuAluno menuAluno = new MenuAluno(alunoPadrao);
                        menuAluno.exibirMenu();
                        break;*/
                    funcoes.login(3);
                }
                case 4:{
                    System.out.println("\nSaindo...");
                    break;
                }
            }

        }while (continuar != 4);


    }
}

class Funcoes {

    public void login (int tipoUsuario){

        Scanner sc = new Scanner(System.in);

        EntityManager em = CustomizerFactory.getEntityManager();

        System.out.println("\n=============== LOGIN ===============");
        System.out.println("- Informe seu email: ");
        String email = sc.nextLine();
        System.out.println("- Informe sua senha: ");
        String senha = sc.nextLine();

        switch (tipoUsuario){
            case 1:{

                break;
            }
            case 2:{

                break;
            }
            case 3:{
                    AlunoRepository alunoRepository = new AlunoRepository(em);

                    AlunoEntity aluno = alunoRepository.login(email, senha);

                    System.out.println(aluno.getNome());
                break;
            }
            default:{
                System.out.println("\nUsuário inválido");
                break;
            }
        }
    }

}