package org.scam;


import org.scam.entities.AlunoEntity;

import org.scam.repository.AlunoRepository;
import org.scam.repository.CustomizerFactory;

import javax.persistence.EntityManager;


import org.scam.entities.AlunoEntity;
import org.scam.repository.AlunoRepository;
import org.scam.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
        AlunoRepository alunoRepository = new AlunoRepository(em);


       AlunoEntity aluno = alunoRepository.buscarPorId(1L);

        System.out.println(aluno.getNome());


        System.out.println(aluno.getNome());
        System.out.println(aluno.getRa());
        System.out.println(aluno.getSenha());


        /*alunoRepository.salvar(
                new AlunoEntity(
                        null, "Vitor Hugo Marcolin", "506507", "123457"
                )
        );*/

    }


}