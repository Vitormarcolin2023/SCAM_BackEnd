package org.scam.repository;

import org.scam.entities.CoordenacaoEntity;

import javax.persistence.EntityManager;

public class CoordenacaoRepository {

    private EntityManager em;

    public CoordenacaoEntity buscarPorId(Long id){
        return em.find(CoordenacaoEntity.class, id);
    }

    public void salvar(CoordenacaoEntity tb_coordenacao){
        em.getTransaction().begin();
        em.persist(tb_coordenacao);
        em.getTransaction().commit();
    }

    public void atualizar(CoordenacaoEntity tb_coordenacao){
        em.getTransaction().begin();
        em.merge(tb_coordenacao);
        em.getTransaction().commit();
    }
}
