package org.scam.model.repository;

import org.scam.model.entities.ReuniaoEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class ReuniaoRepository {

    private final EntityManager em;

    public ReuniaoRepository(EntityManager em){
        this.em = em;
    }

    public List<ReuniaoEntity> buscarReunioes(int ra){
            return em.createQuery(
                "SELECT r FROM ReuniaoEntity r JOIN r.alunos a WHERE a.ra = :ra", ReuniaoEntity.class)
                    .setParameter("ra", ra)
                    .getResultList();
    }

    public List<ReuniaoEntity> buscarReunioesMentor(int idMentor){
        try {
            return em.createQuery(
                    "SELECT r FROM ReuniaoEntity r WHERE r.mentor = :idMentor", ReuniaoEntity.class)
                    .setParameter("idMentor", idMentor)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    public ReuniaoEntity buscarUmaReuniao(int id, int ra){
        try{
            return em.createQuery(
                    "SELECT r FROM ReuniaoEntity r JOIN r.alunos a" +
                            "WHERE r.id = :id AND a.ra = :ra", ReuniaoEntity.class)
                    .setParameter("id", id)
                    .setParameter("ra", ra)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
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
