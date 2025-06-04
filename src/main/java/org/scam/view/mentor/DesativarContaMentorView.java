package org.scam.view.mentor;

import javax.swing.*;
import java.awt.*;

public class DesativarContaMentorView {
    public static void main(String[] args) {
        // Criação da janela principal
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // TOPO VERDE
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 200, 100)); // Verde vibrante
        topo.setPreferredSize(new Dimension(frame.getWidth(), 60));
        topo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15)); // Centralizar o título

        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        // PAINEL CENTRAL com fundo cinza escuro e layout BorderLayout
        JPanel painelCentral = new JPanel();
        painelCentral.setBackground(new Color(60, 60, 60)); // fundo geral
        painelCentral.setLayout(new BorderLayout());
        frame.add(painelCentral, BorderLayout.CENTER);

        // PAINEL DE BOTÕES LATERAL (esquerdo)
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(45, 45, 45));
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Margem interna

        // Criação dos botões
        JButton btnVisuProjetos = new JButton("Visualizar Projetos");
        JButton btnAtualizarConta = new JButton("Atualizar Conta");
        JButton btnDesativarConta = new JButton("Desativar Conta");

        // Estilização uniforme dos botões
        Dimension botaoTamanho = new Dimension(165, 30);
        Font fonteBotao = new Font("SansSerif", Font.PLAIN, 14);

        for (JButton btn : new JButton[]{btnVisuProjetos, btnAtualizarConta, btnDesativarConta}) {
            btn.setMaximumSize(botaoTamanho);
            btn.setPreferredSize(botaoTamanho);
            btn.setFont(fonteBotao);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelBotoes.add(btn);
            painelBotoes.add(Box.createVerticalStrut(15)); // Espaçamento entre botões
        }

        painelCentral.add(painelBotoes, BorderLayout.WEST);

        // ÁREA DE CONTEÚDO CINZA (ao lado dos botões)
        JPanel painelConteudo = new JPanel();
        painelConteudo.setBackground(new Color(80, 80, 80)); // cinza mais claro
        painelCentral.add(painelConteudo, BorderLayout.CENTER);

        // Exibir a janela
        frame.setVisible(true);
    }
}
