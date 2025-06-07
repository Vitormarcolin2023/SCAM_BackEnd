package org.scam.controller;

import org.scam.model.entities.MentorEntity;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.StatusMentor;

import javax.persistence.EntityManager;
import java.util.List;

public class MentorController {
    private final MentorRepository repository;

    public MentorController(EntityManager em) {
        this.repository = new MentorRepository(em);
    }

    public List<MentorEntity> listarMentoresPorStatus(StatusMentor status) {
        if (status == StatusMentor.ATIVO) {
            return repository.listarMentoresAtivo();
        } else {
            return repository.listarMentoresDesativo();
        }
    }
}