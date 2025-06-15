package org.scam.controller;

import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.ProjetoRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class AlunoController {

    private static final EntityManager em = CustomizerFactory.getEntityManager();
    private static final ProjetoRepository projetoRepository = new ProjetoRepository(em);

    public static List<ProjetoEntity> projetosAluno(int ra){
        List<ProjetoEntity> projetos = projetoRepository.buscarTodos(ra);
        return projetos;
    }

}
