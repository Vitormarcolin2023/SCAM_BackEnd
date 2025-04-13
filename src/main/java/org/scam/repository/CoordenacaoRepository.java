package org.scam.repository;

import org.scam.entities.AlunoEntity;
import org.scam.entities.CoordenacaoEntity;
import org.scam.entities.MentorEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class CoordenacaoRepository {

    private EntityManager em;

    public CoordenacaoRepository(EntityManager em) {
        this.em = em;
    }

    public CoordenacaoEntity buscarPorId(Long id) {
        return em.find(CoordenacaoEntity.class, id);
    }

    public void salvar(CoordenacaoEntity tb_coordenacao) {
        em.getTransaction().begin();
        em.persist(tb_coordenacao);
        em.getTransaction().commit();
    }

    public void atualizar(CoordenacaoEntity tb_coordenacao) {
        em.getTransaction().begin();
        em.merge(tb_coordenacao);
        em.getTransaction().commit();
    }

    public CoordenacaoEntity login(String email, String senha) {
        try {
            return em.createQuery(
                            "select c from tb_coordenacao c where c.email = :email and c.senha = :senha",
                            CoordenacaoEntity.class
                    )
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<MentorEntity> listarTodosMentores(){
        return em.createQuery("SELECT m FROM tb_mentor m", MentorEntity.class).getResultList();
    }
    public void removerPorId(Long id){
        MentorEntity mentor = em.find(MentorEntity.class, id);
        if (mentor != null){
            em.getTransaction().begin();
            em.remove(mentor);
            em.getTransaction().commit();
        }
    }
}

