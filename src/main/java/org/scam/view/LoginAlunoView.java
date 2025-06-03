package org.scam.view;

import org.scam.controller.classes.Aluno;
import org.scam.controller.menus.MenuAluno;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.UsuarioEntity;
import org.scam.model.repository.AlunoRepository;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.services.Sessao;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginAlunoView {

    public static void loginAluno() {
        JFrame telaLogin = new JFrame("Login - Aluno");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setSize(300, 180);
        telaLogin.setLocationRelativeTo(null); // centraliza

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Email:");
        JTextField userField = new JTextField(10);

        JLabel passwordLabel = new JLabel("Senha:");
        JPasswordField passwordField = new JPasswordField(10);

        JButton loginButton = new JButton("Login");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // espaço vazio
        panel.add(loginButton);

        telaLogin.getContentPane().add(panel);
        telaLogin.setVisible(true);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = userField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                EntityManager em = CustomizerFactory.getEntityManager();
                AlunoRepository alunoRepository = new AlunoRepository(em);

                UsuarioEntity usuario = alunoRepository.login(email, senha);

                if (usuario != null && usuario instanceof AlunoEntity) {
                    AlunoEntity alunoEntity = (AlunoEntity) usuario;
                    Aluno aluno = alunoEntity.toAluno();

                    Sessao.setRaAluno(alunoEntity.getRa()); // salva RA na sessão

                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    telaLogin.dispose();

                    MenuAluno menuAluno = new MenuAluno(aluno);
                    menuAluno.exibirMenu(); // CLI
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                em.close();
            }
        });
    }
}
