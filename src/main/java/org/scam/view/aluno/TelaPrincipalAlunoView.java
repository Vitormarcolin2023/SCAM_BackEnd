package org.scam.view.aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipalAlunoView {
    public  static void main(String[] args){
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // TOPO VERDE
        JPanel topo = new JPanel();
        topo.setBackground(new Color(114, 196, 95)); // Verde
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
        painelCentral.setLayout(new FlowLayout());
        frame.add(painelCentral, FlowLayout.CENTER);

        JButton JBCadastrarProjeto = new JButton("Cadastrar Projeto");
        JBCadastrarProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Colocar para qual janela ir
            }
        });

        JButton JBGerenciarProjeto = new JButton("Gerenciar Projeto");
        JBGerenciarProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Colocar a janela
            }
        });

        JButton JBListMentores = new JButton("Listar Mentores");
        JBListMentores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Colocar a janela
            }
        });

        estilozarBotao(JBCadastrarProjeto);
        estilozarBotao(JBGerenciarProjeto);
        estilozarBotao(JBListMentores);

        painelCentral.add(JBCadastrarProjeto);
        painelCentral.add(JBGerenciarProjeto);
        painelCentral.add(JBListMentores);
        frame.add(painelCentral);
        frame.setVisible(true);
    }


    //Estilo dos botaos
    private static void estilozarBotao (JButton botao){
        botao.setBackground(new Color(114, 196, 95));//cor do botao
        botao.setForeground(Color.WHITE); //Texto do botao
        botao.setPreferredSize(new Dimension(150,50));


        botao.setFocusPainted(false);

    }


}
