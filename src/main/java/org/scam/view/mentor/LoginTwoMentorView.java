package org.scam.view.mentor;

import org.scam.controller.classes.Coordenador;
import org.scam.controller.classes.Mentor;
import org.scam.controller.login.Usuario;
import org.scam.controller.menus.MenuCoordenador;
import org.scam.model.entities.CoordenacaoEntity;
import org.scam.model.entities.UsuarioEntity;
import org.scam.model.repository.CoordenacaoRepository;
import org.scam.model.repository.CustomizerFactory;
import org.scam.view.coordenacao.PainelPrincipalView;

import javax.persistence.EntityManager;
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
        telaLogin.getContentPane().setBackground(new Color(30, 30, 30));

        // Faixa superior verde
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 200, 100));
        topo.setPreferredSize(new Dimension(telaLogin.getWidth(), 50));

        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(new Font("SansSerif", Font.BOLD, 21));
        topo.add(tituloTopo);
        telaLogin.add(topo, BorderLayout.NORTH);

        // Container central com GridBagLayout
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Painel de login com GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45, 45, 45));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.insets = new Insets(5, 0, 10, 0);
        panelGbc.gridx = 0;
        panelGbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel tituloLogin = new JLabel("LOGIN - MENTOR");
        tituloLogin.setForeground(Color.WHITE);
        tituloLogin.setFont(new Font("SansSerif", Font.BOLD, 18));
        tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
        panelGbc.gridy = 0;
        panel.add(tituloLogin, panelGbc);
        panel.add(Box.createVerticalStrut(10), panelGbc); // Espaço de 30px


        // Email
        panelGbc.gridy++;
        JLabel email = new JLabel("Email");
        email.setForeground(Color.WHITE);
        email.setFont(new Font("SansSerif", Font.BOLD, 15));
        panel.add(email, panelGbc);

        panelGbc.gridy++;
        JTextField userField = new JTextField();
        userField.setPreferredSize(new Dimension(250, 25));
        panel.add(userField, panelGbc);

        // Senha
        panelGbc.gridy++;
        JLabel senha = new JLabel("Senha");
        senha.setForeground(Color.WHITE);
        senha.setFont(new Font("SansSerif", Font.BOLD, 15));
        panel.add(senha, panelGbc);

        panelGbc.gridy++;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 25));
        panel.add(passwordField, panelGbc);

        // Botão Login
        panelGbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 200, 100));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(165, 30));
        panel.add(loginButton, panelGbc);

        // Botão Voltar
        panelGbc.gridy++;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(Color.white);
        voltarButton.setForeground(Color.black);
        voltarButton.setPreferredSize(new Dimension(165, 30));
        voltarButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(voltarButton, panelGbc);

        // Ação botão voltar
        voltarButton.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(telaLogin,
                    "Tem certeza que deseja voltar?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                telaLogin.dispose();
                LoginOneMentorView.loginOne();
            }
        });


        // Ação botão login
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = userField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                Mentor mentor = Usuario.loginMentor(email, senha);

                if(mentor!=null){
                 JOptionPane.showMessageDialog(null, "Bem-vindo(a), " + mentor.getNome());
                 TelaInicialMentor.telaMentor();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adiciona painel central
        gbc.gridy = 0;
        containerCentro.add(panel, gbc);
        telaLogin.add(containerCentro, BorderLayout.CENTER);
        telaLogin.setVisible(true);
    }
}
