package org.scam.view.coordenacao;

import javax.swing.*;
import java.awt.*;

public class PainelPrincipalView {

    public static void painelCoordenacao() {
        JFrame frame = new JFrame("Sistema de Cadastro e Gerenciamento de Mentores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // TOPO VERDE
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 200, 100)); // Verde vibrante
        topo.setPreferredSize(new Dimension(frame.getWidth(), 50));
        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORES");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        // PAINEL CENTRAL ESCURO
        JPanel painelCentral = new JPanel();
        painelCentral.setBackground(new Color(60, 60, 60));
        painelCentral.setLayout(new BorderLayout());
        frame.add(painelCentral, BorderLayout.CENTER);

        // PAINEL DE CONTROLES (TOP CENTRO)
        // Painel de login
        JPanel painel = new JPanel();
        painel.setBackground(new Color(45, 45, 45));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        String[] opcoes = {"Listar Mentor"};
        JComboBox<String> comboMentores = new JComboBox<>(opcoes);
        JButton btnDesativar = new JButton("desativar mentor");
        JButton btnProjetos = new JButton("listar projetos");

        painel.add(comboMentores);
        painel.add(btnDesativar);
        painel.add(btnProjetos);

        painelCentral.add(painel, BorderLayout.NORTH);

        // ÁREA DE CONTEÚDO (onde podem aparecer cards, listas, etc)
        JPanel painelConteudo = new JPanel();
        painelConteudo.setBackground(new Color(80, 80, 80)); // cinza escuro
        painelCentral.add(painelConteudo, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
