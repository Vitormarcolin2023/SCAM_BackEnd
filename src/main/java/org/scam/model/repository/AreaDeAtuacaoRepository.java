package org.scam.model.repository;

import org.scam.model.entities.AreaDeAtuacaoEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class AreaDeAtuacaoRepository {
    private EntityManager em;

    public AreaDeAtuacaoRepository(EntityManager em){
        this.em = em;
    }

    public AreaDeAtuacaoEntity buscarPorId(Long id){
        return em.find(AreaDeAtuacaoEntity.class, id);
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

    public List<AreaDeAtuacaoEntity> listarTodas() {
        return em.createQuery("SELECT a FROM tb_area_de_atuacao a", AreaDeAtuacaoEntity.class).getResultList();
    }

}
