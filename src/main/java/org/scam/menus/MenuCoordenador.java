package org.scam.menus;

import org.scam.classes.Coordenador;
import org.scam.entities.*;
import org.scam.entities.CoordenacaoEntity;
import org.scam.entities.MentorEntity;
import org.scam.entities.ProjetoEntity;
import org.scam.repository.CoordenacaoRepository;
import org.scam.repository.CustomizerFactory;
import org.scam.repository.MentorRepository;
import org.scam.repository.ProjetoRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class MenuCoordenador {

    private Coordenador coordenador;

    public MenuCoordenador(Coordenador coordenador){
        this.coordenador = coordenador;
    }

    public void exibirMenu(){
        System.out.println("Usuário: " + coordenador.getNome());
        Scanner sc = new Scanner(System.in);
        int opcao;

        EntityManager em = CustomizerFactory.getEntityManager();
        CoordenacaoRepository coordenacaoRepo = new CoordenacaoRepository(em);
        MentorRepository mentorRepo = new MentorRepository(em);
        ProjetoRepository projetoRepo = new ProjetoRepository(em);
        do{
            System.out.println("\n========MENU COORDENADOR=======");
            System.out.println("- [1] Listar Mentores");
            System.out.println("- [2] Remover Mentor");
            System.out.println("- [3] Listar projetos");
            System.out.println("- [4] Sair");
            System.out.println("================================");
            System.out.println("Escolha uma opção:");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    List<MentorEntity> mentores = mentorRepo.listarTodosMentores();
                    System.out.println("\n======Lista de mentores======");
                    if (mentores.isEmpty()){
                        System.out.println("Nenhum mentor encontrado.");
                    }else{
                        for (MentorEntity m : mentores){
                            System.out.println("ID: " + m.getIdMentor() +
                                    " | Nome: " + m.getNome() +
                                    "\n | CPF: " + m.getCpf() +
                                    "\n | Email: " + m.getEmail() +
                                    "\n | Tipo de Usuário: " + m.getTipoDeUsuario() +
                                    "\n | Telefone: " + m.getTelefone() +
                                    "\n | Tempo de Experiência: " + m.getTempoDeExperiencia() +
                                    "\n | Tipo de Vínculo: " + m.getTipoDeVinculo() +
                                    "\n | Área de Atuação: " + m.getAreaDeAtuacao() +
                                    "\n | Endereço: " + m.getEndereco().toString());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Digite o ID do mentor que deseja remover");
                    int id = (int) sc.nextLong();
                    sc.nextLine();
                    coordenacaoRepo.removerPorId(id);
                    System.out.println("Mentor removido com sucesso.");

                    break;

                case 3:
                    List<ProjetoEntity> projetos = projetoRepo.listarTodosProjetos();

                    System.out.println("\n=============== Lista de Projetos ===============");
                    if(projetos.isEmpty()){
                        System.out.println("Nenhum projeto encontrado");
                    }else{
                        for (ProjetoEntity p: projetos){
                            System.out.println("ID: " + p.getId() +
                                    "| Nome: " + p.getNomeDoProjeto() +
                                    "\n| Curso: " + p.getCurso() +
                                    "\n| Área de atuação: " + p.getAreaDeAtuacao() +
                                    "\n| Aluno: " + p.getRaAluno() +
                                    "\n| Mentor: " + p.getIdMentor()) ;
                        }
                    }

                    break;
                case 4:
                    System.out.println("Saindo do painel no coordenador....");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while (opcao !=3);



    }

}