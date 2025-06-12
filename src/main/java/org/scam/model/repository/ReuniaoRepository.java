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



    public void cancelarPorId(int id, String motivo){
        ReuniaoEntity reuniao = em.find(ReuniaoEntity.class, id);
        if (reuniao != null && reuniao.getStatusReuniao() == StatusReuniao.AGENDADA){
            em.getTransaction().begin();
            reuniao.setStatusReuniao(StatusReuniao.CANCELADA);
            reuniao.setMotivoReuniao(motivo);
            em.merge(reuniao);
            em.getTransaction().commit();
        }
    }

    public void reuniaoConcluida(int id){
        ReuniaoEntity reuniao = em.find(ReuniaoEntity.class, id);
        if (reuniao != null && reuniao.getStatusReuniao() == StatusReuniao.AGENDADA){
            em.getTransaction().begin();
            reuniao.setStatusReuniao(StatusReuniao.REALIZADA);

            em.merge(reuniao);
            em.getTransaction().commit();
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
