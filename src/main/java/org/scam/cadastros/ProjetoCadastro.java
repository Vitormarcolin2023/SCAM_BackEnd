package org.scam.cadastros;


import org.scam.entities.AlunoEntity;
import org.scam.entities.MentorEntity;
import org.scam.entities.ProjetoEntity;
import org.scam.repository.CustomizerFactory;
import org.scam.repository.MentorRepository;
import org.scam.repository.ProjetoRepository;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.scam.utils.Sessao;

public class ProjetoCadastro {

    public void cadastrarProjeto() {
        Scanner scanner = new Scanner(System.in);

        EntityManager em = CustomizerFactory.getEntityManager();
        MentorRepository mentorRepository = new MentorRepository(em);
        ProjetoRepository projetoRepository = new ProjetoRepository(em);
        AlunoEntity alunoEntity = new AlunoEntity();

        System.out.print("Nome do projeto: ");
        String nomeProjeto = scanner.nextLine();

        System.out.print("Descrição do projeto: ");
        String descricaoProjeto = scanner.nextLine();

        System.out.println("\n[SELEÇÃO A ÁREA DE ATUAÇÃO]");
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

        System.out.print("Data de término do projeto (yyyy-MM-dd): ");
        String dataFinal = scanner.next();

        System.out.print("Quantidade de integrantes: ");
        int qtdParticipante = scanner.nextInt();

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

        //RA automatico
        int ra = Sessao.getRaAluno();

        // Buscar e listar mentores da mesma área
        List<MentorEntity> mentoresDisponiveis = mentorRepository.buscarMentoresPorAreaDeAtuacao(areaDeAtuacao);

        if (mentoresDisponiveis.isEmpty()) {
            System.out.println("Nenhum mentor disponível para essa área de atuação.");
            return;
        }

        System.out.println("Mentores disponíveis para a área " + areaDeAtuacao + ":");
        for (MentorEntity mentorEntity : mentoresDisponiveis) {
            System.out.println("ID: " + mentorEntity.getIdMentor() + " | Nome: " + mentorEntity.getNome());
        }


        System.out.print("ID do mentor (fk_mentor_id): ");
        int mentor = scanner.nextInt();

        ProjetoEntity novoProjeto = new ProjetoEntity();
        novoProjeto.setNomeDoProjeto(nomeProjeto);
        novoProjeto.setDescricao(descricaoProjeto);
        novoProjeto.setAreaDeAtuacao(areaDeAtuacao);
        novoProjeto.setDataInicioProjeto(LocalDate.parse(dataInicio));
        novoProjeto.setDataFinalProjeto(LocalDate.parse(dataFinal));
        novoProjeto.setTamanhoDoGrupo(qtdParticipante);
        novoProjeto.setCurso(cursoEscolhido);
        novoProjeto.setPeriodo(periodo);
        novoProjeto.setRaAluno(ra);
        novoProjeto.setIdMentor(mentor);

        // Valida se não houve erro no cadastro
        if(projetoRepository.salvar(novoProjeto)){
            System.out.println("\nProjeto cadastrado com sucesso!");
        }
        else {
            System.out.println("\nAlgo deu errado, tente novamente.");
        }
    }

}