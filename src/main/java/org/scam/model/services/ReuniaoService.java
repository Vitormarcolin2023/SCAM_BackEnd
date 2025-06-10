package org.scam.model.services;

import org.scam.controller.classes.Mentor;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.entities.ReuniaoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.ProjetoRepository;
import org.scam.model.repository.ReuniaoRepository;
import org.scam.model.repository.StatusReuniao;

import javax.persistence.EntityManager;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReuniaoService {

    private static EntityManager em = CustomizerFactory.getEntityManager();
    private static ReuniaoRepository reuniaoRepository = new ReuniaoRepository(em);
    private static ProjetoRepository projetoRepository = new ProjetoRepository(em);

    public List<ReuniaoEntity> getReunioes(int ra){
        List<ReuniaoEntity> reunioes = reuniaoRepository.buscarReunioes(ra);
        return reunioes;
    }

    public static List<ProjetoEntity> buscarProjetos(int ra){
        List<ProjetoEntity> projetos = projetoRepository.buscarTodos(ra);
        return projetos;
    }

    public void agendarReuniao(String motivo, LocalDate data, LocalTime hora, String local, MentorEntity mentor, ProjetoEntity projeto){

        ReuniaoEntity novaReuniao = new ReuniaoEntity();

        novaReuniao.setMotivoReuniao(motivo);
        novaReuniao.setDataReuniao(data);
        novaReuniao.setHorarioReuniao(hora);
        novaReuniao.setLocalReuniao(local);
        novaReuniao.setMentor(mentor);
        novaReuniao.setProjeto(projeto);
        novaReuniao.setStatusReuniao(StatusReuniao.AGUARDANDO_CONFIRMACAO);
    }
}
