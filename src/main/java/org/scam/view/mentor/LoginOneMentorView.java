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

//nome da funcao loginMentorOne


public class LoginOneMentorView {

    public static void loginOne() {
        JFrame telaLogin = new JFrame("Painel do Mentor");
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
        tituloTopo.setFont(new Font("SansSerif", Font.BOLD, 18));
        topo.add(tituloTopo);
        telaLogin.add(topo, BorderLayout.NORTH);

        // Container central com GridBagLayout para centralizar
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(new Color(30, 30, 30)); // fundo escuro total

        // Painel cinza com botões
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45, 45, 45));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        panel.setPreferredSize(new Dimension(320, 250));
        GridBagConstraints gbc = new GridBagConstraints();

        // Título
        JLabel titulo = new JLabel("OLÁ MENTOR!");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(titulo, gbc);

        // Botão Cadastre-se
        JButton cadastroButton = new JButton("Cadastre-se");
        cadastroButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        cadastroButton.setPreferredSize(new Dimension(165, 30)); // largura, altura
        cadastroButton.setBackground(new Color(0, 200, 100));
        cadastroButton.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        panel.add(cadastroButton, gbc);

        // Espaço entre os botões
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(Box.createHorizontalStrut(50), gbc);

        // Botão Login
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 200, 100));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("SansSerif", Font.BOLD,16));
        loginButton.setPreferredSize(new Dimension(165, 30)); // largura, altura
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(loginButton, gbc);

        // Botão Voltar (abaixo e centralizado)
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(Color.white);
        voltarButton.setForeground(Color.black);
        voltarButton.setPreferredSize(new Dimension(165, 30)); // largura, altura
        voltarButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 0, 0, 0);
        panel.add(voltarButton, gbc);

        // Correto:
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






        // Adiciona painel ao container central
        containerCentro.add(panel);
        telaLogin.add(containerCentro, BorderLayout.CENTER);

        telaLogin.setVisible(true);
    }


}
