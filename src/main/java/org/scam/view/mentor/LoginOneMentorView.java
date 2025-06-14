package org.scam.view.mentor;

import org.scam.view.EstilosPadrao;
import org.scam.view.TelaSelecaoUsuarioView;

import javax.swing.*;
import java.awt.*;

public class LoginOneMentorView {

    public static void loginOne() {
        JFrame telaLogin = new JFrame("Painel do Mentor");
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(EstilosPadrao.cinzaClaro);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.insets = new Insets(5, 0, 10, 0);
        panelGbc.gridx = 0;
        panelGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("OLÁ, MENTOR!");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(EstilosPadrao.fonteTitulos);
        panelGbc.gridy = 0;
        panel.add(titulo, panelGbc);

        panelGbc.gridy++;
        panel.add(Box.createVerticalStrut(15), panelGbc);

        panelGbc.gridy++;
        JButton cadastroButton = new JButton("Cadastre-se");
        cadastroButton.setBackground(EstilosPadrao.verdeUni);
        cadastroButton.setForeground(Color.BLACK);
        cadastroButton.setFont(EstilosPadrao.fonteBotao);
        cadastroButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        panel.add(cadastroButton, panelGbc);

        panelGbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(EstilosPadrao.verdeUni);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(EstilosPadrao.fonteBotao);
        loginButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        panel.add(loginButton, panelGbc);

        panelGbc.gridy++;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(EstilosPadrao.verdeBotaoVoltar);
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setFont(EstilosPadrao.fonteBotao);
        voltarButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        panel.add(voltarButton, panelGbc);

        cadastroButton.addActionListener(e -> {
            telaLogin.dispose();
            CadastroMentorPasso1View.exibirTelaCadastroPasso1();
        });

        loginButton.addActionListener(e -> {
            telaLogin.dispose();
            LoginTwoMentorView.loginTwo();
        });

        voltarButton.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(telaLogin,
                    "Tem certeza que deseja voltar?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                telaLogin.dispose();
                TelaSelecaoUsuarioView.exibirTelaSelecao();
            }
        });
        containerCentro.add(panel, gbc);
        telaLogin.add(containerCentro, BorderLayout.CENTER);
        telaLogin.setVisible(true);
    }
}