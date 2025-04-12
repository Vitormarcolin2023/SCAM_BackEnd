package org.scam.repository;

import org.scam.entities.AlunoEntity;
import org.scam.entities.CoordenacaoEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class CoordenacaoRepository {

    private EntityManager em;

    public CoordenacaoRepository (EntityManager em){
        this.em = em;
    }

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

    public CoordenacaoEntity login(String email, String senha){
        try{
            return em.createQuery(
                            "select c from tb_coordenacao c where c.email = :email and c.senha = :senha",
                            CoordenacaoEntity.class
                    )
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
