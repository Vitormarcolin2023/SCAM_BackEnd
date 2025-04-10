package org.scam;

import org.scam.entities.AlunoEntity;

import org.scam.repository.AlunoRepository;
import org.scam.repository.CustomizerFactory;

import javax.persistence.EntityManager;


public class Main {
    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
        AlunoRepository alunoRepository = new AlunoRepository(em);

       AlunoEntity aluno = alunoRepository.buscarPorId(1L);

        System.out.println(aluno.getNome());


    }


}