package org.scam.controller.login;

import org.scam.controller.classes.Aluno;
import org.scam.controller.classes.Coordenador;
import org.scam.controller.classes.Mentor;
import org.scam.controller.classes.Validacao;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.CoordenacaoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.UsuarioEntity;
import org.scam.model.repository.AlunoRepository;
import org.scam.model.repository.CoordenacaoRepository;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;

import javax.persistence.EntityManager;

public class Usuario {

    private final EntityManager em = CustomizerFactory.getEntityManager();
    private final AlunoRepository alunoRepository = new AlunoRepository(em);
    private final MentorRepository mentorRepository = new MentorRepository(em);
    private final CoordenacaoRepository coordenacaoRepository = new CoordenacaoRepository(em);

    public Aluno loginAluno(String email, String senha) {
        AlunoEntity aluno = alunoRepository.login(email, senha);
        return (aluno!=null) ? aluno.toAluno() : null;
    }

    public Coordenador loginCoordenador(String email, String senha){
        CoordenacaoEntity coordenacao = coordenacaoRepository.login(email, senha);
        return (coordenacao!=null) ? coordenacao.toCoordenador() : null;
    }

    public Mentor loginMentor(String email, String senha){
        MentorEntity mentor = mentorRepository.login(email, senha);
        return (mentor!=null) ? mentor.toMentor() : null;
    }
}
