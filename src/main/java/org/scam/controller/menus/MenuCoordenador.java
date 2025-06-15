package org.scam.controller.menus;

import org.scam.controller.classes.Coordenador;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CoordenacaoRepository;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.ProjetoRepository;

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
        List<MentorEntity> mentoresAtivos = mentorRepo.listarMentoresAtivo();
        List<MentorEntity> mentoresDesativos = mentorRepo.listarMentoresDesativo();
        do{
            System.out.println("\n========MENU COORDENADOR=======");
            System.out.println("- [1] Listar Mentores Ativos");
            System.out.println("- [2] Listar Mentores Desativo");
            System.out.println("- [3] Desativar Mentor");
            System.out.println("- [4] Listar projetos");
            System.out.println("- [5] Sair");
            System.out.println("================================");
            System.out.println("Escolha uma opção:");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    mostrarMentores(mentoresAtivos, false);
                    break;
                case 2:
                    mostrarMentores(mentoresDesativos,true);
                    break;
                case 3:
                    System.out.println("Digite o ID do mentor que deseja remover");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Informe o motivo para a desativação do mentor:");
                    String motivo = sc.nextLine();

                    mentorRepo.desativarPorId(id, motivo);
                    System.out.println("Mentor desativado com sucesso.");

                    break;
                case 4:
                    List<ProjetoEntity> projetos = projetoRepo.listarTodosProjetos();

                    System.out.println("\n=============== Lista de Projetos ===============");
                    if(projetos.isEmpty()){
                        System.out.println("Nenhum projeto encontrado");
                    }else{
                        for (ProjetoEntity p: projetos){
                            System.out.println("ID: " + p.getId() +
                                    "\n| Nome: " + p.getNomeDoProjeto() +
                                    "\n| Curso: " + p.getCurso() +
                                    "\n| Área de atuação: " + p.getAreaDeAtuacao());
                            System.out.println("| Alunos: ");
                            for (AlunoEntity aluno : p.getAlunos()){
                                System.out.println("| RA: " + aluno.getRa() + " | Nome: " + aluno.getNome());
                            }
                            MentorEntity mentor = p.getMentor();
                            System.out.println("| Mentor: " + mentor.getNome());
                        }
                    }

                    break;
                case 5:
                    System.out.println("Saindo do painel no coordenador....");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while (opcao !=5);

    }

    public void mostrarMentores(List<MentorEntity> mentores, boolean mostrarMotivoDesativacao ){
        System.out.println("\n======Lista de mentores======");
        if (mentores.isEmpty()){
            System.out.println("Nenhum mentor encontrado.");
        }else{
            for (MentorEntity m : mentores){
                System.out.println("ID: " + m.getIdMentor() +
                        "\n | Nome: " + m.getNome() +
                        "\n | CPF: " + m.getCpf() +
                        "\n | Email: " + m.getEmail() +
                        "\n | Tipo de Usuário: " + m.getTipoDeUsuario() +
                        "\n | Telefone: " + m.getTelefone() +
                        "\n | Tempo de Experiência: " + m.getTempoDeExperiencia() +
                        "\n | Tipo de Vínculo: " + m.getTipoDeVinculo() +
                        "\n | Área de Atuação: " + m.getAreaDeAtuacao() +
                        "\n | Endereço: " + m.getEndereco().toString());
                if(mostrarMotivoDesativacao){
                   System.out.println("| Motivo que foi desativado: " + m.getMotivoDesativacao());
                }


            }
        }
    }

}