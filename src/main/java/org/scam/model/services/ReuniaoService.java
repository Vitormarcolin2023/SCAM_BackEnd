package org.scam.model.services;

import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ReuniaoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.ReuniaoRepository;

import javax.persistence.EntityManager;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public class ReuniaoService {

    EntityManager em = CustomizerFactory.getEntityManager();
    ReuniaoRepository reuniaoRepository = new ReuniaoRepository(em);

    public List<ReuniaoEntity> getReunioes(int ra){
        List<ReuniaoEntity> reunioes = reuniaoRepository.buscarReunioes(ra);
        return reunioes;
    }


    public DefaultTableModel gerarTableModel(List<ReuniaoEntity> reunioes){
        String[] colunas = {"ID", "Nome"};
        DefaultTableModel modelo =new DefaultTableModel(colunas, 0);

        for (ReuniaoEntity reuniao : reunioes){
            Object[] linha = {reuniao.getId()};
            modelo.addRow(linha);
        }
        return modelo;
    }
}
