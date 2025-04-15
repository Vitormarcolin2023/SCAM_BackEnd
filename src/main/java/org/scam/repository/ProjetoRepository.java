package org.scam.repository;

import org.scam.entities.ProjetoEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjetoRepository {

    private final EntityManager em;

    public ProjetoRepository(EntityManager em){
        this.em = em;
    }

    public ProjetoEntity buscarPorId(Long id){
        return em.find(ProjetoEntity.class, id);
    }

    public List<ProjetoEntity> buscarTodos(int ra) {
        TypedQuery<ProjetoEntity> query = em.createQuery("SELECT p FROM ProjetoEntity p WHERE fk_aluno_ra = :ra", ProjetoEntity.class).setParameter("ra", ra);
        return query.getResultList();
    }

    public void salvar(ProjetoEntity projeto){
        em.getTransaction().begin();
        em.persist(projeto);
        em.getTransaction().commit();
    }

    public void atualizar(ProjetoEntity tb_projeto) {
        em.getTransaction().begin();
        em.merge(tb_projeto);
        em.getTransaction().commit();
    }

    public void remover(Long id) {
        ProjetoEntity projeto = buscarPorId(id);
        if (projeto != null) {
            em.getTransaction().begin();
            em.remove(projeto);
            em.getTransaction().commit();
        }
    }
}