package org.scam.controller;

import org.scam.model.entities.MentorEntity;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.StatusMentor;

import javax.persistence.EntityManager;
import java.util.List;

public class MentorAprovacaoController {

    private final MentorRepository mentorRepository;
    private final EntityManager em;

    public MentorAprovacaoController(EntityManager em) {
        this.em = em;
        this.mentorRepository = new MentorRepository(em);
    }

    public List<MentorEntity> listarMentoresPendentes() {
        return em.createQuery("SELECT m FROM tb_mentor m WHERE m.status = :status", MentorEntity.class)
                .setParameter("status", StatusMentor.PENDENTE)
                .getResultList();
    }

    public void aprovarMentor(MentorEntity mentor) {
        em.getTransaction().begin();
        mentor.setStatus(StatusMentor.ATIVO);
        mentor.setMotivoDesativacao(null);
        mentorRepository.editarMentor(mentor);
        em.getTransaction().commit();
    }

    public void negarMentor(MentorEntity mentor, String motivo) {
        em.getTransaction().begin();
        mentor.setStatus(StatusMentor.NEGADO); // adiciona esse status se ainda não existir
        mentor.setMotivoDesativacao(motivo);
        mentorRepository.editarMentor(mentor);
        em.getTransaction().commit();
    }
}
