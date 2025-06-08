package org.scam.view.coordenacao;

import org.scam.controller.LoginCoordenacaoController;
import org.scam.controller.classes.Coordenador;
import org.scam.controller.menus.MenuCoordenador;
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
        telaLogin.getContentPane().setBackground(new Color(30, 30, 30));

        // Faixa superior verde
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 200, 100));
        topo.setPreferredSize(new Dimension(telaLogin.getWidth(), 50));

        JLabel tituloTopo = new JLabel("SISTEMA DE CADASTRO E GERENCIAMENTO DE MENTORES");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(new Font("SansSerif", Font.BOLD, 21));
        tituloTopo.setAlignmentX(Component.CENTER_ALIGNMENT);
        topo.add(tituloTopo);

        telaLogin.add(topo, BorderLayout.NORTH);

        // Container central com GridBagLayout
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(new Color(30, 30, 30));

        // Painel de login
        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 45, 45));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel titulo = new JLabel("LOGIN - COORDENAÇÃO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel email = new JLabel("Email");
        email.setForeground(Color.WHITE);
        email.setFont(new Font("SansSerif", Font.BOLD, 15));
        email.setAlignmentX(Component.RIGHT_ALIGNMENT);
        email.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 70));

        JTextField userField = new JTextField();
        userField.setMaximumSize(new Dimension(250, 40));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel senha = new JLabel("Senha");
        senha.setForeground(Color.WHITE);
        senha.setFont(new Font("SansSerif", Font.BOLD, 15));
        senha.setAlignmentX(Component.RIGHT_ALIGNMENT);
        senha.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 65));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(250, 40));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 200, 100));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginButton.setPreferredSize(new Dimension(165, 30));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(Color.WHITE);
        voltarButton.setForeground(Color.BLACK);
        voltarButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        voltarButton.setPreferredSize(new Dimension(165, 30));
        voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        voltarButton.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(
                    telaLogin,
                    "Tem certeza que deseja voltar?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmar == JOptionPane.YES_OPTION) {
                telaLogin.dispose();
                TelaSelecaoUsuarioView.exibirTelaSelecao();
            }
        });

        // Adiciona ao painel
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(email);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(userField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(senha);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(voltarButton);

        containerCentro.add(panel);
        telaLogin.add(containerCentro, BorderLayout.CENTER);
        telaLogin.setVisible(true);

        // Ação do botão Login
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = userField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                LoginCoordenacaoController controller = new LoginCoordenacaoController();
                Coordenador coordenador = controller.autenticar(email, senha);

                if (coordenador != null) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    telaLogin.dispose();
                    new MenuCoordenador(coordenador);
                    PainelPrincipalView.painelCoordenacao();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}