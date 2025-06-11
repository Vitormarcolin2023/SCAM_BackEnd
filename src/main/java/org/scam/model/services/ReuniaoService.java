package org.scam.model.services;

import org.scam.model.entities.ProjetoEntity;
import org.scam.model.entities.ReuniaoEntity;
import org.scam.model.repository.*;

import javax.persistence.EntityManager;
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

    public static boolean agendarReuniao(String motivo, LocalDate data, LocalTime hora, String local, TipoReuniao tipo, ProjetoEntity projeto){

        ReuniaoEntity novaReuniao = new ReuniaoEntity();

        novaReuniao.setMotivoReuniao(motivo);
        novaReuniao.setDataReuniao(data);
        novaReuniao.setHorarioReuniao(hora);
        novaReuniao.setLocalReuniao(local);
        novaReuniao.setTipoReuniao(tipo);
        novaReuniao.setProjeto(projeto);
        novaReuniao.setStatusReuniao(StatusReuniao.AGUARDANDO_CONFIRMACAO);
        novaReuniao.setReuniaoConfirmada(false);

        if(reuniaoRepository.salvar(novaReuniao)){
            return true;
        }
        else {
            return false;
        }
    }
}
