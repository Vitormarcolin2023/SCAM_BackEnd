package org.scam.repository;

import org.scam.entities.MentorEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public boolean existePorCpf(String cpf) {
        try {
            em.createQuery(
                            "SELECT m FROM tb_mentor m WHERE m.cpf = :cpf", MentorEntity.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean existePorTelefone(String telefone) {
        try {
            em.createQuery(
                            "SELECT m FROM tb_mentor m WHERE m.telefone = :telefone", MentorEntity.class)
                    .setParameter("telefone", telefone)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean existePorEmail(String email) {
        try {
            em.createQuery(
                            "SELECT m FROM tb_mentor m WHERE m.email = :email", MentorEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }


    @PersistenceContext
    private EntityManager entityManager;

    public List<MentorEntity> buscarMentoresPorAreaDeAtuacao(String area) {
        TypedQuery<MentorEntity> query = em.createQuery(
                "SELECT m FROM tb_mentor m WHERE m.areaDeAtuacao = :area", MentorEntity.class
        );
        query.setParameter("area", area);
        return query.getResultList();
    }
}
