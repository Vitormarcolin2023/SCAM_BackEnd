package org.scam.repository;

import org.scam.entities.AlunoEntity;
import org.scam.entities.AreaDeAtuacaoEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class AreaDeAtuacaoRepository {
    private EntityManager em;

    public AreaDeAtuacaoRepository(EntityManager em){
        this.em = em;
    }

    public AlunoEntity buscarPorId(Long id){
        return em.find(AlunoEntity.class, id);
    }

    public void salvar(AreaDeAtuacaoEntity areaDeAtuacao) {
        try {
            em.getTransaction().begin();
            em.persist(areaDeAtuacao);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Desfaz a transação em caso de erro
            }
            throw e; // Repassa a exceção para ser tratada no cadastro
        }
    }

}
