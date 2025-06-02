package org.scam.controller.cadastros;


import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.ProjetoRepository;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.scam.model.services.Sessao;

public class ProjetoCadastro {

    public void cadastrarProjeto() {
        Scanner scanner = new Scanner(System.in);

        EntityManager em = CustomizerFactory.getEntityManager();
        MentorRepository mentorRepository = new MentorRepository(em);
        ProjetoRepository projetoRepository = new ProjetoRepository(em);
        AlunoEntity alunoEntity = new AlunoEntity();
        ProjetoEntity novoProjeto = new ProjetoEntity();

        System.out.print("Nome do projeto: ");
        String nomeProjeto = scanner.nextLine();

        System.out.print("Descrição do projeto: ");
        String descricaoProjeto = scanner.nextLine();

        System.out.println("\n[SELEÇÃO ÁREA DE ATUAÇÃO]");
        AreaDeAtuacao[] tiposA = AreaDeAtuacao.values();
        for (int i = 0; i < tiposA.length; i++) {
            System.out.printf("%d - %s%n", i + 1, tiposA[i].name());
        }
        System.out.print("Digite o número correspondente ao tipo: ");

        int escolhaTipoA;
        AreaDeAtuacao areaDeAtuacao = null;
        try {
            escolhaTipoA = Integer.parseInt(scanner.nextLine());
            areaDeAtuacao = tiposA[escolhaTipoA - 1];
        } catch (Exception e) {
            System.out.println("Escolha inválida!.");
        }

        System.out.print("Data de início do projeto (yyyy-MM-dd): ");
        String dataInicio = scanner.next();

        // calcula data de término automaticamente
        LocalDate inicioInformado = LocalDate.parse(dataInicio);
        LocalDate dataTermino = inicioInformado.plusMonths(5);

        //Utiliza enum para apresentar os cursos
        System.out.println("\n[SELEÇÃO O CURSO]");
        Curso[] tiposC = Curso.values();
        for (int i = 0; i < tiposC.length; i++) {
            System.out.printf("%d - %s%n", i + 1, tiposC[i].name());
        }

        Curso cursoEscolhido = null;
        scanner.nextLine(); // limpar o buffer
        while (cursoEscolhido == null) {
            try {
                System.out.print("Digite o número correspondente ao tipo: ");
                int escolhaTipoC = Integer.parseInt(scanner.nextLine());
                if (escolhaTipoC >= 1 && escolhaTipoC <= tiposC.length) {
                    cursoEscolhido = tiposC[escolhaTipoC - 1];
                } else {
                    System.out.println("Número fora do intervalo. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }

        System.out.print("Período: ");
        String periodo = scanner.nextLine();

        // adiciona o aluno que está cadastrando o projeto
        AlunoEntity alunoPrincipal = em.find(AlunoEntity.class, Sessao.getRaAluno());
        novoProjeto.getAlunos().add(alunoPrincipal);

        // Para adicionar vários alunos no projeto por RA - adiciona dinamicamente
        int qtdParticipante = 0; // contador para alunos adicionados no projeto
        while (true) {
            System.out.print("Digite o RA do aluno para adicionar ao projeto (ou 0 para finalizar): ");
            int ra = scanner.nextInt();
            scanner.nextLine();

            if (ra == 0) {
                break;
            }

            AlunoEntity aluno = em.find(AlunoEntity.class, ra);
            if (aluno != null) {
                if (!novoProjeto.getAlunos().contains(aluno)) {
                    novoProjeto.getAlunos().add(aluno);
                    System.out.println("Aluno adicionado.");
                    qtdParticipante++;
                } else {
                    System.out.println("Este aluno já foi adicionado.");
                }
            } else {
                System.out.println("Aluno com RA " + ra + " não encontrado.");
            }
        }


        // Buscar e listar mentores da mesma área
        List<MentorEntity> mentoresDisponiveis = mentorRepository.buscarMentoresPorAreaDeAtuacao(areaDeAtuacao);

        if (mentoresDisponiveis.isEmpty()) {
            System.out.println("Nenhum mentor disponível para essa área de atuação.\nNão é possível cadastrar o projeto.");
            return;
        }


        System.out.println("======== MENTOR CADASTRADO NA ÁREA DE: "+ areaDeAtuacao +" ========");

        for (MentorEntity mentorEntity : mentoresDisponiveis) {
            System.out.println("===============================================================");
            System.out.println("ID: " + mentorEntity.getIdMentor() + " | Nome: " + mentorEntity.getNome());
            System.out.println("Tempo de experincia: " + mentorEntity.getTempoDeExperiencia());
            System.out.println("===============================================================");
        }


        System.out.print("ID do mentor: ");
        int mentor = scanner.nextInt();

        MentorEntity mentorSelecionado = em.find(MentorEntity.class, mentor);
        if (mentorSelecionado != null) {
            novoProjeto.setMentor(mentorSelecionado);
        } else {
            System.out.println("Mentor não encontrado.");
            return;
        }


        novoProjeto.setNomeDoProjeto(nomeProjeto);
        novoProjeto.setDescricao(descricaoProjeto);
        novoProjeto.setAreaDeAtuacao(areaDeAtuacao);
        novoProjeto.setDataInicioProjeto(LocalDate.parse(dataInicio));
        novoProjeto.setDataFinalProjeto(dataTermino);
        novoProjeto.setTamanhoDoGrupo(qtdParticipante);
        novoProjeto.setCurso(cursoEscolhido);
        novoProjeto.setPeriodo(periodo);

        // Valida se não houve erro no cadastro
        if(projetoRepository.salvar(novoProjeto)){
            System.out.println("\nProjeto cadastrado com sucesso!");
        }
        else {
            System.out.println("\nAlgo deu errado, tente novamente.");
        }
    }

}