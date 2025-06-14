package org.scam.view.coordenacao;

import org.scam.controller.LoginCoordenacaoController;
import org.scam.controller.classes.Coordenador;
import org.scam.controller.menus.MenuCoordenador;
import org.scam.view.EstilosPadrao;
import org.scam.view.TelaSelecaoUsuarioView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginCoordenacaoView {

    public static void loginCoordenacao() {
        JFrame telaLogin = new JFrame("Login - Coordenação");
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

        JLabel tituloLogin = new JLabel("LOGIN - COORDENAÇÃO");
        tituloLogin.setForeground(Color.WHITE);
        tituloLogin.setFont(EstilosPadrao.fonteTitulos);
        tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
        panelGbc.gridy = 0;
        panel.add(tituloLogin, panelGbc);
        panel.add(Box.createVerticalStrut(10), panelGbc);

        panelGbc.gridy++;
        JLabel email = new JLabel("Email");
        email.setForeground(Color.WHITE);
        email.setFont(EstilosPadrao.fontePadrao);
        panel.add(email, panelGbc);

        panelGbc.gridy++;
        JTextField userField = new JTextField();
        userField.setPreferredSize(new Dimension(250, 30));
        panel.add(userField, panelGbc);

        panelGbc.gridy++;
        JLabel senha = new JLabel("Senha");
        senha.setForeground(Color.WHITE);
        senha.setFont(EstilosPadrao.fontePadrao);
        panel.add(senha, panelGbc);

        panelGbc.gridy++;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 30));
        panel.add(passwordField, panelGbc);

        panelGbc.gridy++;
        panel.add(Box.createVerticalStrut(15), panelGbc);

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

        voltarButton.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(telaLogin, "Tem certeza que deseja voltar?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                telaLogin.dispose();
                TelaSelecaoUsuarioView.exibirTelaSelecao();
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = userField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                LoginCoordenacaoController controller = new LoginCoordenacaoController();
                Coordenador coordenador = controller.autenticar(email, senha);

                if (coordenador != null) {
                    JOptionPane.showMessageDialog(null, "Bem-vindo(a), " + coordenador.getNome());
                    telaLogin.dispose();
                    new MenuCoordenador(coordenador);
                    PainelPrincipalView.painelCoordenacao();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                    userField.setText("");
                    passwordField.setText("");
                }
            }
        });

        containerCentro.add(panel, gbc);
        telaLogin.add(containerCentro, BorderLayout.CENTER);
        telaLogin.setVisible(true);
    }
}