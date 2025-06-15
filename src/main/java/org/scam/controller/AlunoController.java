package org.scam.controller;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.ProjetoRepository;
import org.scam.model.repository.StatusMentor;
import org.scam.model.services.Sessao;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class AlunoController {

    private static final EntityManager em = CustomizerFactory.getEntityManager();
    private static final ProjetoRepository projetoRepository = new ProjetoRepository(em);
    private static final MentorRepository mentorRepository = new MentorRepository(em);

    public static List<ProjetoEntity> projetosAluno(int ra){
        List<ProjetoEntity> projetos = projetoRepository.buscarTodos(ra);
        return projetos;
    }

    public static List<MentorEntity> listarMentoresPorStatus(StatusMentor status) {
        if (status == StatusMentor.ATIVO) {
            return mentorRepository.listarMentoresAtivo();
        }else {
            return Collections.emptyList();
        }
    }

    private static final List<Consumer<List<ProjetoEntity>>> observers = new ArrayList<>();

    // Adiciona um observer para ser notificado quando a lista de projetos mudar
    public static void addObserver(Consumer<List<ProjetoEntity>> observer) {
        observers.add(observer);
    }

    // Remove um observer
    public static void removeObserver(Consumer<List<ProjetoEntity>> observer) {
        observers.remove(observer);
    }

    // Notifica todos os observers com a nova lista de projetos
    public static void notificarObservers() {
        List<ProjetoEntity> projetos = projetosAluno(Sessao.getAlunoLogado().getRa());
        for (Consumer<List<ProjetoEntity>> observer : observers) {
            observer.accept(projetos);
        }
    }

}




