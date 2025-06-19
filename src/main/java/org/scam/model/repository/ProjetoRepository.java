package org.scam.model.repository;

import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjetoRepository {

    private final EntityManager em;

    public ProjetoRepository(EntityManager em){
        this.em = em;
    }

    public boolean salvar(ProjetoEntity projeto) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        try {
            em.persist(projeto);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println("!!!!!!!!!! ERRO AO SALVAR PROJETO NO REPOSITÓRIO !!!!!!!!!!");
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public ProjetoEntity buscarPorId(Long id){
        return em.find(ProjetoEntity.class, id);
    }

    public List<ProjetoEntity> buscarTodos(int ra) {
        return em.createQuery(
                        "SELECT p FROM ProjetoEntity p JOIN p.alunos a WHERE a.ra = :ra", ProjetoEntity.class)
                .setParameter("ra", ra)
                .getResultList();
    }

    public List<ProjetoEntity> buscarTodosMentor(String fk, int id) {
        String buscarBanco = "SELECT p FROM ProjetoEntity p WHERE p." + fk + " = :id";
        TypedQuery<ProjetoEntity> query = em.createQuery(buscarBanco, ProjetoEntity.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<ProjetoEntity> listarTodosProjetos(){
        String buscarBanco = "SELECT p FROM ProjetoEntity p";
        TypedQuery<ProjetoEntity> query = em.createQuery(buscarBanco, ProjetoEntity.class);
        return query.getResultList();
    }

    public ProjetoEntity buscarUmProjeto(long idProjeto, int ra) {
        try {
            return em.createQuery(
                            "SELECT p FROM ProjetoEntity p JOIN p.alunos a " +
                                    "WHERE p.id = :idProjeto AND a.ra = :ra", ProjetoEntity.class)
                    .setParameter("idProjeto", idProjeto)
                    .setParameter("ra", ra)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public ProjetoEntity buscarUmProjetoMentor(long idProjeto, int id){
        try{
            return em.createQuery("SELECT p FROM ProjetoEntity p WHERE p.id = :idProjeto AND fk_mentor_id = :id", ProjetoEntity.class)
                    .setParameter("idProjeto", idProjeto)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void atualizar(ProjetoEntity tb_projeto) {
        em.merge(tb_projeto);
    }

    public void remover(Long id) {
        ProjetoEntity projeto = buscarPorId(id);
        if (projeto != null) {
            em.getTransaction().begin();
            em.remove(projeto);
            em.getTransaction().commit();
        }
    }

    public List<ProjetoEntity> findByMentor(MentorEntity mentor) {
        String jpql = "SELECT p FROM ProjetoEntity p WHERE p.mentor = :mentor";
        return em.createQuery(jpql, ProjetoEntity.class)
                .setParameter("mentor", mentor)
                .getResultList();
    }

    public List<ProjetoEntity> findByMentorAndStatus(MentorEntity mentor, StatusProjeto status) {
        String jpql = "SELECT p FROM ProjetoEntity p WHERE p.mentor = :mentor AND p.status = :status";
        return em.createQuery(jpql, ProjetoEntity.class)
                .setParameter("mentor", mentor)
                .setParameter("status", status)
                .getResultList();
    }

    public List<ProjetoEntity> findByAlunoRaAndStatus(int ra, StatusProjeto status) {
        String jpql = "SELECT p FROM ProjetoEntity p JOIN p.alunos a WHERE a.ra = :ra AND p.status = :status";
        return em.createQuery(jpql, ProjetoEntity.class)
                .setParameter("ra", ra)
                .setParameter("status", status)
                .getResultList();
    }
}