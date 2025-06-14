package org.scam.model.repository;

import org.scam.model.entities.ReuniaoEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class ReuniaoRepository {

    private final EntityManager em;

    public ReuniaoRepository(EntityManager em){
        this.em = em;
    }

    public List<ReuniaoEntity> buscarReunioes(Long id){
        return em.createQuery(
                        "SELECT r FROM ReuniaoEntity r WHERE r.projeto.id = :id", ReuniaoEntity.class)
                .setParameter("id", id)
                .getResultList();
    }



    public boolean cancelarPorId(long id, String motivo){
        ReuniaoEntity reuniao = em.find(ReuniaoEntity.class, id);
        try {
            em.getTransaction().begin();
            reuniao.setStatusReuniao(StatusReuniao.CANCELADA);
            reuniao.setMotivoCancelamento(motivo);
            em.merge(reuniao);
            em.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean alterarStatus(long id, StatusReuniao novoStatus){
        ReuniaoEntity reuniao = em.find(ReuniaoEntity.class, id);
        try {
            em.getTransaction().begin();
            reuniao.setStatusReuniao(novoStatus);
            em.merge(reuniao);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean salvar(ReuniaoEntity reuniao){
        try {
            em.getTransaction().begin();
            em.persist(reuniao);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
