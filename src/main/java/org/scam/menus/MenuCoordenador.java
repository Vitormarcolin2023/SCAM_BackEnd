package org.scam.menus;

import org.scam.classes.Coordenador;
import org.scam.entities.MentorEntity;
import org.scam.repository.CoordenacaoRepository;
import org.scam.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class MenuCoordenador {

    private Coordenador coordenador;

    public MenuCoordenador(Coordenador coordenador){
        this.coordenador = coordenador;
    }

    public void exibirMenu(){
        System.out.println("Deu certo, usuário: " + coordenador.getNome());
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        EntityManager em = CustomizerFactory.getEntityManager();
        CoordenacaoRepository coor = new CoordenacaoRepository(em);

        do{
            System.out.println("\n========MENU COORDENADOR=======");
            System.out.println("[1]Listar Mentores");
            System.out.println("[2]Remover Mentor");
            System.out.println("[3]Sair");
            System.out.println("================================");
            System.out.println("Escolha uma opção:");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    List<MentorEntity> mentores = coor.listarTodosMentores();
                    System.out.println("\n======Lista de mentores======");
                    if (mentores.isEmpty()){
                        System.out.println("Nenhum mentor encontrado.");
                    }else{
                        for (MentorEntity m : mentores){
                            System.out.println("ID: " + m.getIdMentor() +
                                    " | Nome: " + m.getNome() +
                                    "| Email: " + m.getEmail() +
                                    "| Area de atuacao: " + m.getAreaDeAtuacao());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Digite o ID do mentor que deseja remover");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    coor.removerPorId(id);
                    System.out.println("Mentor removido com sucesso.");

                    break;
                case 3:
                    System.out.println("Saindo do painel no coordenador....");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while (opcao !=3);



    }

}