package org.scam.model.repository;

import org.scam.model.entities.AlunoEntity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

public class AlunoRepository {

    private EntityManager em;

    public AlunoRepository(EntityManager em){
        this.em = em;
    }

    public AlunoEntity buscarPorId(Long id){
        return em.find(AlunoEntity.class, id);
    }

    public void salvar(AlunoEntity aluno) {
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Desfaz a transação em caso de erro
            }
            throw e; // Repassa a exceção para ser tratada no cadastro
        }
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