package org.scam.model.repository;

import org.scam.model.entities.ProjetoEntity;

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

    // utilizado no menu do coordenador
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

    public boolean salvar(ProjetoEntity projeto){
        try {
            em.getTransaction().begin();
            em.persist(projeto);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
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