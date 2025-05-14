package org.scam.model.repository;

import org.scam.model.entities.EnderecoEntity;

import javax.persistence.EntityManager;

public class EnderecoRepository {

    private EntityManager em;

    public EnderecoRepository(EntityManager em){
        this.em = em;
    }

    public EnderecoEntity buscarPorId(Long id){
        return em.find(EnderecoEntity.class, id);
    }

    public void salvar(EnderecoEntity tb_endereco){
        em.getTransaction().begin();
        em.persist(tb_endereco);
        em.getTransaction().commit();
    }



}
