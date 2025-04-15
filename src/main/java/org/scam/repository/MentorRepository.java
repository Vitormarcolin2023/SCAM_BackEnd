package org.scam.repository;

import org.scam.entities.MentorEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class MentorRepository {
    private EntityManager em;

    public MentorRepository(EntityManager em){
        this.em = em;
    }

    public MentorEntity buscarPorId(Long id){
        return em.find(MentorEntity.class, id);
    }

    public void salvar(MentorEntity tb_mentor){
        em.getTransaction().begin();
        em.persist(tb_mentor);
        em.getTransaction().commit();
    }

    public MentorEntity login(String email, String senha){
        try{
            return em.createQuery(
                    "select m from tb_mentor m where m.email = :email and m.senha = :senha", MentorEntity.class
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

    public List<MentorEntity> buscarTodos(){
        return em.createQuery("SELECT m  FROM tb_mentor m", MentorEntity.class).getResultList();
    }
}
