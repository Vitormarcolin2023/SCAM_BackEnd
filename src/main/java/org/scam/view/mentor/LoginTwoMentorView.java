package org.scam.view.mentor;

import org.scam.controller.classes.Coordenador;
import org.scam.controller.classes.Mentor;
import org.scam.controller.menus.MenuCoordenador;
import org.scam.controller.menus.MenuMentor;
import org.scam.model.entities.CoordenacaoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.UsuarioEntity;
import org.scam.model.repository.CoordenacaoRepository;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.services.Sessao;
import org.scam.view.TelaSelecaoUsuarioView;
import org.scam.view.coordenacao.PainelPrincipalView;



import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginTwoMentorView {
    public static void loginTwo(){
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

        JLabel tituloLogin = new JLabel("LOGIN - MENTOR");
        tituloLogin.setForeground(Color.WHITE);
        tituloLogin.setFont(new Font("SansSerif", Font.BOLD, 18));
        tituloLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Criação da variável gbc
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 30, 0);

        panel.add(tituloLogin, gbc);

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

        // Botão Voltar (abaixo e centralizado)
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(Color.white);
        voltarButton.setForeground(Color.black);
        voltarButton.setPreferredSize(new Dimension(165, 30)); // largura, altura
        voltarButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        voltarButton.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(telaLogin,
                    "Tem certeza que deseja voltar?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                telaLogin.dispose();
                LoginOneMentorView.loginOne(); // <-- Chamada correta para a tela anterior
            }
        });

        // Adiciona ao painel
        panel.add(tituloLogin);
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
        panel.add(voltarButton);

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
                    PainelPrincipalView.painelCoordenacao();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                em.close();
            }
        });
    }
}
