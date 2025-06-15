package org.scam.controller;

import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.ProjetoRepository;
import org.scam.model.repository.StatusProjeto;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class ProjetoAprovacaoController {

    private final ProjetoRepository repository;
    private final EntityManager em;

    public ProjetoAprovacaoController(EntityManager em) {
        this.em = em;
        this.repository = new ProjetoRepository(em);
    }

    public List<ProjetoEntity> listarProjetosPendentes(MentorEntity mentor) {
        if (mentor == null) {
            return Collections.emptyList();
        }
        return repository.findByMentorAndStatus(mentor, StatusProjeto.PENDENTE);
    }

    public void aprovarProjeto(ProjetoEntity projeto) {
        try {
            em.getTransaction().begin();
            projeto.setStatus(StatusProjeto.APROVADO);
            repository.atualizar(projeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void negarProjeto(ProjetoEntity projeto, String motivo) {
        try {
            em.getTransaction().begin();
            projeto.setStatus(StatusProjeto.RECUSADO);
            repository.atualizar(projeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}