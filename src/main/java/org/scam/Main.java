package org.scam;

import org.scam.classes.Aluno;
import org.scam.classes.Coordenador;
import org.scam.entities.AlunoEntity;
import org.scam.menus.MenuAluno;
import org.scam.repository.AlunoRepository;
import org.scam.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        VALIDAÇÃO SE ESTÁ OK A LIGAÇÃO COM O BANCO

        EntityManager em = CustomizerFactory.getEntityManager();
        AlunoRepository alunoRepository = new AlunoRepository(em);

        AlunoEntity aluno = alunoRepository.buscarPorId(1L);

        System.out.println(aluno.getNome());*/



        Aluno alunoPadrao = new Aluno("Lethicia", "lethiciamsm@gmail.com", "lethicia1312", 1, 219421);
        Coordenador coordenadorPadrao = new Coordenador("Admin", "admin@uniamerica.br", "admin123");

        Scanner sc = new Scanner(System.in);
        int continuar = 0;

        do {

            System.out.println("======== SISTEMA DE MENTORIA ========");
            System.out.println("= [1] - Coordenador                 =");
            System.out.println("= [2] - Mentor                      =");
            System.out.println("= [3] - Aluno                       =");
            System.out.println("=====================================\n");
            System.out.println("- Selecione a opção desejada: ");
            continuar = sc.nextInt();

            switch (continuar){
                case 1: {

                    break;
                }
                case 2: {

                    break;
                }
                case 3:{
                        MenuAluno menuAluno = new MenuAluno(alunoPadrao);
                        menuAluno.exibirMenu();
                        break;
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

    public static Scanner sc = new Scanner(System.in);

    public static  boolean login(String documento, String senha){
        System.out.println("\n- Informe seu RA ou CPF: ");
        // em desenvolvimento
        return true;
    }

}