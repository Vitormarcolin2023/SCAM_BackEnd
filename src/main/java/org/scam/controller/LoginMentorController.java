package org.scam.controller;

import org.scam.controller.dto.MentorDTO;
import org.scam.model.entities.MentorEntity;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.StatusMentor;

import javax.persistence.EntityManager;

public class LoginMentorController {
    private final MentorRepository mentorRepository;

    public LoginMentorController(EntityManager em) {
        this.mentorRepository = new MentorRepository(em);
    }

    public MentorEntity autenticar(String email, String senha) {
        return mentorRepository.login(email, senha);
    }

    public MentorDTO autenticarRetornandoDTO(String email, String senha) {
        MentorEntity mentor = mentorRepository.login(email, senha);
        if (mentor == null) return null;
        return new MentorDTO(mentor.getIdMentor(), mentor.getNome(), mentor.getEmail());
    }

    public StatusMentor verificarStatus(MentorEntity mentor) {
        return mentor.getStatus();
    }

    public String obterMotivoNegacao(MentorEntity mentor) {
        return mentor.getMotivoDesativacao();
    }

    public void reenviarCadastro(MentorEntity mentor) {
        mentor.setStatus(StatusMentor.PENDENTE);
        mentorRepository.editarMentor(mentor);
    }

    public void reativarMentor(MentorEntity mentor) {
        mentor.setStatus(StatusMentor.ATIVO);
        mentorRepository.editarMentor(mentor);
    }
}