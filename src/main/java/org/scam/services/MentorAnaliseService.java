package org.scam.services;

import org.scam.entities.MentorEntity;
import org.scam.cadastros.StatusMentor;
import org.scam.repository.CustomizerFactory;
import org.scam.repository.MentorRepository;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class MentorAnaliseService {
    private final EntityManager em;
    private final MentorRepository mentorRepository;

    public MentorAnaliseService() {
        this.em = CustomizerFactory.getEntityManager();
        this.mentorRepository = new MentorRepository(em);
    }

    public void revisarMentoresEmAnalise() {
        List<MentorEntity> mentores = mentorRepository.listarMentoresEmAnalise();

        if (mentores.isEmpty()) {
            System.out.println("Não há mentores pendentes de análise.");
            return;
        }

        System.out.println("\nMentores pendentes de análise:");
        for (MentorEntity mentor : mentores) {
            System.out.println("ID: " + mentor.getIdMentor() + " | Nome: " + mentor.getNome());
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite o ID do mentor que deseja revisar: ");
        int idEscolhido = sc.nextInt();
        sc.nextLine();

        MentorEntity mentorSelecionado = mentorRepository.buscarPorId(idEscolhido);

        if (mentorSelecionado == null || mentorSelecionado.getStatus() != StatusMentor.ANALISE) {
            System.out.println("Mentor não encontrado ou já foi analisado.");
            return;
        }

        System.out.println("\n===== Detalhes do Mentor =====");
        System.out.println("Nome: " + mentorSelecionado.getNome());
        System.out.println("CPF: " + mentorSelecionado.getCpf());
        System.out.println("Email: " + mentorSelecionado.getEmail());
        System.out.println("Telefone: " + mentorSelecionado.getTelefone());
        System.out.println("Tempo de Experiência: " + mentorSelecionado.getTempoDeExperiencia());
        System.out.println("Tipo de Vínculo: " + mentorSelecionado.getTipoDeVinculo());
        System.out.println("Área de Atuação: " + mentorSelecionado.getAreaDeAtuacao());
        System.out.println("Endereço: " + mentorSelecionado.getEndereco().toString());

        System.out.println("\nDeseja aprovar ou negar o mentor?");
        System.out.print("[1] Aprovar\n[2] Negar\nEscolha: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        em.getTransaction().begin();
        if (escolha == 1) {
            mentorSelecionado.setStatus(StatusMentor.ATIVO);
            mentorSelecionado.setMotivoDesativacao(null);
            System.out.println("Mentor aprovado com sucesso.");
        } else {
            mentorSelecionado.setStatus(StatusMentor.DESATIVO);
            System.out.print("Informe o motivo da reprovação: ");
            String motivo = sc.nextLine();
            mentorSelecionado.setMotivoDesativacao(motivo);
            System.out.println("Mentor reprovado.");
        }
        mentorRepository.atualizar(mentorSelecionado);
        em.getTransaction().commit();
    }
}