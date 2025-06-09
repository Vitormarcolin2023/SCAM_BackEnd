package org.scam.view.mentor;

import org.scam.controller.classes.Mentor;
import org.scam.controller.login.Usuario;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginTwoMentorView {
    public static void loginTwo() {
        JFrame telaLogin = new JFrame("Login - Mentor");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setLayout(new BorderLayout());
        telaLogin.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeUni);
        topo.setPreferredSize(new Dimension(telaLogin.getWidth(), 50));

        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(EstilosPadrao.tituloSAM);
        topo.add(tituloTopo);
        telaLogin.add(topo, BorderLayout.NORTH);

        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(EstilosPadrao.cinzaFundo);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(EstilosPadrao.cinzaClaro);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel tituloLogin = new JLabel("LOGIN - MENTOR");
        tituloLogin.setForeground(Color.WHITE);
        tituloLogin.setFont(EstilosPadrao.fonteTitulos);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 25, 5);
        panel.add(tituloLogin, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(EstilosPadrao.fontePadrao);
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField userField = new JTextField(13);
        panel.add(userField, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;

        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setForeground(Color.WHITE);
        senhaLabel.setFont(EstilosPadrao.fontePadrao);
        panel.add(senhaLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JPasswordField passwordField = new JPasswordField(13);
        panel.add(passwordField, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(25, 5, 5, 5);
        JButton loginButton = new JButton("Login");
        stylePrimaryButton(loginButton);
        panel.add(loginButton, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        JButton voltarButton = new JButton("Voltar");
        styleSecondaryButton(voltarButton);
        panel.add(voltarButton, gbc);

        voltarButton.addActionListener(e -> {
            telaLogin.dispose();
            LoginOneMentorView.loginOne();
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = userField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                Mentor mentor = Usuario.loginMentor(email, senha);

                if (mentor != null) {
                    Sessao.setMentorLogado(mentor);
                    JOptionPane.showMessageDialog(null, "Bem-vindo(a), " + mentor.getNome());
                    telaLogin.dispose();
                    TelaInicialMentor.telaMentor();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        containerCentro.add(panel);
        telaLogin.add(containerCentro, BorderLayout.CENTER);
        telaLogin.setVisible(true);
    }

    private static void stylePrimaryButton(JButton button) {
        button.setBackground(EstilosPadrao.verdeUni);
        button.setForeground(Color.WHITE);
        button.setFont(EstilosPadrao.fonteBotao);
        button.setPreferredSize(EstilosPadrao.tamanhoBotao);
        button.setFocusPainted(false);
    }

    private static void styleSecondaryButton(JButton button) {
        button.setBackground(EstilosPadrao.verdeBotaoVoltar);
        button.setForeground(Color.WHITE);
        button.setFont(EstilosPadrao.fonteBotao);
        button.setPreferredSize(EstilosPadrao.tamanhoBotao);
        button.setFocusPainted(false);
    }
}