package org.scam.controller;

import org.scam.controller.classes.Coordenador;
import org.scam.model.entities.CoordenacaoEntity;
import org.scam.model.entities.UsuarioEntity;
import org.scam.model.repository.CoordenacaoRepository;
import org.scam.model.repository.CustomizerFactory;

import javax.persistence.EntityManager;

public class LoginCoordenacaoController {

    public Coordenador autenticar(String email, String senha) {
        EntityManager em = CustomizerFactory.getEntityManager();
        Coordenador coordenador = null;

        try {
            CoordenacaoRepository coordenacaoRepository = new CoordenacaoRepository(em);
            UsuarioEntity usuario = coordenacaoRepository.login(email, senha);

            if (usuario instanceof CoordenacaoEntity coordenacaoEntity) {
                coordenador = coordenacaoEntity.toCoordenador();
            }
        } finally {
            em.close();
        }

        return coordenador;
    }
}