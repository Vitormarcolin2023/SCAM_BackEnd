package org.scam.repository;

import org.scam.entities.AlunoEntity;
import org.scam.entities.CoordenacaoEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

    public CoordenacaoEntity login(String email, String senha){
        try{
            return em.createQuery(
                            "select a from tb_coordenacao a where a.email = :email and a.senha = :senha",
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
