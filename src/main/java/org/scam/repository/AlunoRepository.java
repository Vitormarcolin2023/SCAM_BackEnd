package org.scam.repository;

import org.scam.classes.IAutenticavel;
import org.scam.entities.AlunoEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

    public AlunoEntity login(String email, String senha){
        try{
            return em.createQuery(
                    "select a from tb_aluno a where a.email = :email and a.senha = :senha",
                    AlunoEntity.class
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