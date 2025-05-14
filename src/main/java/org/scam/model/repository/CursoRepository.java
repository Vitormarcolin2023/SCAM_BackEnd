package org.scam.model.repository;


import org.scam.model.entities.CursoEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class CursoRepository {
    private EntityManager em;

    public CursoRepository(EntityManager em){
        this.em = em;
    }

    public CursoEntity buscarPorId(Long id){
        return em.find(CursoEntity.class, id);
    }

    public void salvar(CursoEntity curso) {
        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Desfaz a transação em caso de erro
            }
            throw e; // Repassa a exceção para ser tratada no cadastro
        }
    }

    public List<CursoEntity> listarTodas() {
        return em.createQuery("SELECT a FROM tb_curso a", CursoEntity.class).getResultList();
    }
}
