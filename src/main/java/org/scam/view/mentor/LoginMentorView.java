package org.scam.view.mentor;

import org.scam.controller.classes.Mentor;
import org.scam.controller.menus.MenuMentor;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.UsuarioEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.services.Sessao;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginMentorView {

    public static void loginMentor() {
        // Janela
        JFrame telaLogin = new JFrame("Login - Mentor");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setSize(350, 200);
        telaLogin.setLocationRelativeTo(null);

        // Painel com fundo escuro
        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 45, 45));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel titulo = new JLabel("LOGIN - MENTOR");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField userField = new JTextField();
        userField.setMaximumSize(new Dimension(250, 30));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        userField.setBorder(BorderFactory.createTitledBorder("Email"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(250, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setBorder(BorderFactory.createTitledBorder("Senha"));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 200, 100));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona os componentes ao painel
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(userField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginButton);

        telaLogin.getContentPane().add(panel);
        telaLogin.setVisible(true);

        // Ação do botão
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = userField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                EntityManager em = CustomizerFactory.getEntityManager();
                MentorRepository mentorRepository = new MentorRepository(em);

                UsuarioEntity usuario = mentorRepository.login(email, senha);

                if (usuario != null && usuario instanceof MentorEntity) {
                    MentorEntity mentorEntity = (MentorEntity) usuario;
                    Mentor mentor = mentorEntity.toMentor();

                    Sessao.setMentorLogado(mentor);

                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    telaLogin.dispose();

                    MenuMentor menuMentor = new MenuMentor(mentor);
                    menuMentor.exibirMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                em.close();
            }
        });
    }
}
