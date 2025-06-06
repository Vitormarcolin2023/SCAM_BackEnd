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
import org.scam.view.coordenacao.PainelPrincipalView;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//nome da funcao loginMentorOne


public class LoginOneMentorView {

    public static void main (String[]args) {
        JFrame telaLogin = new JFrame("Painel do Mentor");
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
        panel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JLabel titulo = new JLabel("OLÁ MENTOR!");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton cadastro = new JButton("Cadastre-se");
        cadastro.setFont(new Font("SansSerif", Font.BOLD, 14));
        cadastro.setAlignmentX(Component.RIGHT_ALIGNMENT);
       cadastro.setBackground(new Color(0, 200, 100));
       cadastro.setForeground(Color.BLACK);


        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 200, 100));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Adiciona ao painel
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(cadastro);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));


        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginButton);

        // Adiciona painel ao centro
        containerCentro.add(panel);
        telaLogin.add(containerCentro, BorderLayout.CENTER);

        telaLogin.setVisible(true);


    }
}
