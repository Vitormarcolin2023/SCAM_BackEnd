package org.scam.repository;

import org.scam.classes.IAutenticavel;
import org.scam.entities.AlunoEntity;

import javax.persistence.EntityManager;

public class AlunoRepository {

    private EntityManager em;

    public AlunoRepository(EntityManager em){
        this.em = em;
    }

    public AlunoEntity buscarPorId(Long id){
        return em.find(AlunoEntity.class, id);
    }

    public void salvar(AlunoEntity tb_aluno){
        em.getTransaction().begin();
        em.persist(tb_aluno);
        em.getTransaction().commit();
    }
}

