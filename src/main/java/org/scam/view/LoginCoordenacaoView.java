package org.scam.view;

import org.scam.controller.classes.Coordenador;
import org.scam.controller.menus.MenuCoordenador;
import org.scam.model.entities.CoordenacaoEntity;
import org.scam.model.entities.UsuarioEntity;
import org.scam.model.repository.CoordenacaoRepository;
import org.scam.model.repository.CustomizerFactory;

import javax.persistence.EntityManager;
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
        topo.setBackground(new Color(0, 128, 66));
        topo.setPreferredSize(new Dimension(telaLogin.getWidth(), 50));

        JLabel tituloTopo = new JLabel("SISTEMA DE CADASTRO E GERENCIAMENTO DE MENTORES");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(new Font("SansSerif", Font.BOLD, 18));
        tituloTopo.setAlignmentX(Component.CENTER_ALIGNMENT);
        topo.add(tituloTopo);

        telaLogin.add(topo, BorderLayout.NORTH);

        // Container central com GridBagLayout para centralizar
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(new Color(30, 30, 30)); // fundo escuro total

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
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        // Adiciona painel ao centro
        containerCentro.add(panel);
        telaLogin.add(containerCentro, BorderLayout.CENTER);

        telaLogin.setVisible(true);

        // Ação do botão
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = userField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                EntityManager em = CustomizerFactory.getEntityManager();
                CoordenacaoRepository coordenacaoRepository = new CoordenacaoRepository(em);

                UsuarioEntity usuario = coordenacaoRepository.login(email, senha);

                if (usuario != null && usuario instanceof CoordenacaoEntity) {
                    CoordenacaoEntity coordenacaoEntity = (CoordenacaoEntity) usuario;
                    Coordenador coordenador = coordenacaoEntity.toCoordenador();

                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    telaLogin.dispose();

                    MenuCoordenador menuCoordenador = new MenuCoordenador(coordenador);
                    menuCoordenador.exibirMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                em.close();
            }
        });
    }
}
