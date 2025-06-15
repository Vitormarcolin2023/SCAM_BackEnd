package org.scam.controller;

import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.ProjetoRepository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class ProjetoController {
    private final ProjetoRepository repository;

    public ProjetoController(EntityManager em) {
        this.repository = new ProjetoRepository(em);
    }

    public List<ProjetoEntity> listarTodosProjetos() {
        return repository.listarTodosProjetos();
    }

    public List<ProjetoEntity> listarProjetosPorMentor(MentorEntity mentor) {
        if (mentor == null) {
            return Collections.emptyList();
        }
        return repository.findByMentor(mentor);
    }
}