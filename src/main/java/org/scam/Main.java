package org.scam.view;

import org.scam.controller.classes.Validacao;
import org.scam.controller.login.Credenciais;
import org.scam.view.TelaSelecaoUsuarioView;
import org.scam.view.coordenacao.PainelPrincipalView;
import org.scam.view.coordenacao.LoginCoordenacaoView;
import org.scam.view.mentor.DesativarContaMentorView;
import org.scam.view.mentor.LoginOneMentorView;
import org.scam.view.mentor.LoginTwoMentorView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {




        //DesativarContaMentorView.desativarContaM();
        //LoginOneMentorView.loginOne();
        //LoginTwoMentorView.loginTwo();
        //LoginCoordenacaoView.loginCoordenacao();
        //LoginTwoMentorView.loginTwo();
        //LoginViewCoordenação.loginCoordenacao(); - Coordenação
        //LoginAlunoView.loginAluno(); - Aluno

        TelaSelecaoUsuarioView.exibirTelaSelecao(); // Ja o menu
        //PainelPrincipalView.painelCoordenacao();

        //ProjetoCadastro projetoCadastro = new ProjetoCadastro();
        //projetoCadastro.cadastrarProjeto();

        //MentorCadastro mentorCadastro = new MentorCadastro();
        //mentorCadastro.cadastrarMentor();

    }
}


