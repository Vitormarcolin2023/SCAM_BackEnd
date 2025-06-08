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

        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(new Font("SansSerif", Font.BOLD, 21));
        topo.add(tituloTopo);
        telaLogin.add(topo, BorderLayout.NORTH);

        // Container central com GridBagLayout para centralizar
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(new Color(30, 30, 30)); // fundo escuro total

        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.insets = new Insets(2, 0, 4, 0);
        panelGbc.gridx = 1;
        panelGbc.gridy = 0;
        panelGbc.fill = GridBagConstraints.HORIZONTAL;

        // Painel cinza com botões
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45, 45, 45));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        panel.setPreferredSize(new Dimension(320, 260));


        // Título
        JLabel titulo = new JLabel("OLÁ MENTOR!");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        panelGbc.gridx = 0;
        panelGbc.gridy = 0;
        panelGbc.gridwidth = 3;
        panelGbc.insets = new Insets(10, 0, 30, 0);
        panel.add(titulo, panelGbc);

        // Botão Cadastre-se
        JButton cadastroButton = new JButton("Cadastre-se");
        panelGbc.gridy++;
        cadastroButton.setBackground(Color.white);
        cadastroButton.setForeground(Color.black);
        cadastroButton.setPreferredSize(new Dimension(165, 30));
        cadastroButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        panelGbc.insets = new Insets(5, 0, 4, 0);
        panel.add(cadastroButton, panelGbc);

        cadastroButton.addActionListener(e -> {
            telaLogin.dispose(); // fecha a tela atual
            CadastroMentorPasso1View.exibirTelaCadastroPasso1(); // Chama o início do cadastro
        });


        // Botão Login
        panelGbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 200, 100));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(165, 30));
        panelGbc.insets = new Insets(2, 0, 15, 0);
        panel.add(loginButton, panelGbc);

        //ação de ao clicar ir para a pagina login 2
        loginButton.addActionListener(e -> {
            telaLogin.dispose(); // fecha a tela atual
            LoginTwoMentorView.loginTwo(); // abre a tela de login final
        });

        // Botão Voltar (abaixo e centralizado)
        panelGbc.gridy++;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(Color.white);
        voltarButton.setForeground(Color.black);
        voltarButton.setPreferredSize(new Dimension(165, 30));
        voltarButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        panelGbc.insets = new Insets(5, 0, 4, 0);
        panel.add(voltarButton, panelGbc);


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
