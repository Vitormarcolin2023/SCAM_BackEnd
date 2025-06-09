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

        // Container central com GridBagLayout para centralizar
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(EstilosPadrao.cinzaFundo);

        // Painel cinza com botões
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(EstilosPadrao.cinzaClaro);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        panel.setPreferredSize(new Dimension(320, 280)); // Aumentei um pouco a altura para melhor espaçamento

        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.gridx = 0;
        panelGbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titulo = new JLabel("OLÁ MENTOR!");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(EstilosPadrao.fonteTitulos);
        panelGbc.gridy = 0;
        panelGbc.insets = new Insets(10, 0, 30, 0);
        panel.add(titulo, panelGbc);

        // Botão Cadastre-se
        panelGbc.gridy++;
        panelGbc.insets = new Insets(5, 0, 5, 0); // Espaçamento uniforme
        JButton cadastroButton = new JButton("Cadastre-se");
        styleSecondaryButton(cadastroButton);
        panel.add(cadastroButton, panelGbc);

        // Botão Login
        panelGbc.gridy++;
        JButton loginButton = new JButton("Login");
        stylePrimaryButton(loginButton);
        panel.add(loginButton, panelGbc);

        // Botão Voltar
        panelGbc.gridy++;
        panelGbc.insets = new Insets(15, 0, 5, 0); // Espaço antes do botão voltar
        JButton voltarButton = new JButton("Voltar");
        styleSecondaryButton(voltarButton);
        panel.add(voltarButton, panelGbc);

        // Ação do botão Cadastre-se
        cadastroButton.addActionListener(e -> {
            telaLogin.dispose();
            CadastroMentorPasso1View.exibirTelaCadastroPasso1();
        });

        // Ação do botão Login
        loginButton.addActionListener(e -> {
            telaLogin.dispose();
            LoginTwoMentorView.loginTwo();
        });

        // Ação do botão Voltar
        voltarButton.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(telaLogin,
                    "Tem certeza que deseja voltar para a tela de seleção de usuário?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                telaLogin.dispose();
                TelaSelecaoUsuarioView.exibirTelaSelecao();
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