package org.scam.model.repository;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MentorRepository {
    private EntityManager em;

    public MentorRepository(EntityManager em){
        this.em = em;
    }

    public MentorEntity buscarPorId(int id){
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

    public List<MentorEntity> listarMentoresAtivo(){
        return em.createQuery("SELECT m FROM tb_mentor m WHERE m.status = :status", MentorEntity.class)
                .setParameter("status", StatusMentor.ATIVO)
                .getResultList();
    }
    public List<MentorEntity> listarMentoresDesativo(){
        return em.createQuery("SELECT m FROM tb_mentor m WHERE m.status = :status", MentorEntity.class)
                .setParameter("status", StatusMentor.DESATIVO)
                .getResultList();
    }

    public void desativarPorId(int id,String motivo){
        MentorEntity mentor = em.find(MentorEntity.class, id);
        if (mentor != null && mentor.getStatus() == StatusMentor.ATIVO){
            em.getTransaction().begin();
            mentor.setStatus(StatusMentor.DESATIVO);
            mentor.setMotivoDesativacao(motivo);
            em.merge(mentor);
            em.getTransaction().commit();
        }
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


    public MentorEntity buscarPorEmail(String email) {
        try {
            return em.createQuery("SELECT m FROM tb_mentor m WHERE m.email = :email", MentorEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void editarMentor(MentorEntity mentor) {
        boolean novaTransacao = false;

        try {
            // Só começa se não estiver ativa
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
                novaTransacao = true;
            }

            em.merge(mentor);

            if (novaTransacao) {
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (novaTransacao && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            System.out.println("❌ Erro ao atualizar mentor.");
        }
    }

    public List<MentorEntity> buscarMentoresPorAreaDeAtuacao(AreaDeAtuacao area) {
        return em.createQuery("FROM tb_mentor m WHERE m.areaDeAtuacao = :area AND m.status = :status", MentorEntity.class)
                .setParameter("area", area)
                .setParameter("status", StatusMentor.ATIVO)
                .getResultList();
    }

    public List<ProjetoEntity> buscarTodos(String fk, int ra) {
        String buscarBanco = "SELECT p FROM ProjetoEntity p WHERE p." + fk + " = :ra";
        TypedQuery<ProjetoEntity> query = em.createQuery(buscarBanco, ProjetoEntity.class);
        query.setParameter("ra", ra);
        return query.getResultList();
    }


    public void desativarMentorPorEmail(String email, String motivo) {
        MentorEntity mentor = buscarPorEmail(email);

        if (mentor != null) {
            mentor.setStatus(StatusMentor.DESATIVO);
            mentor.setMotivoDesativacao(motivo);

            em.getTransaction().begin();
            em.merge(mentor);
            em.getTransaction().commit();
        } else {
            throw new IllegalArgumentException("Mentor não encontrado com o e-mail: " + email);
        }
    }

}
