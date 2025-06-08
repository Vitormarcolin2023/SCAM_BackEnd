package org.scam.view.aluno;


import org.scam.model.repository.MentorRepository;
import org.scam.view.EstilosPadrao;
import org.scam.model.entities.MentorEntity;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.scam.view.mentor.MentorListView;

public class PrincipalAlunoView {

    public static void princiapal(){
        // CRIACAO DA JANELA PRINCIPAL
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // TOPO VERDE COM TÍTULO
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 128, 66));
        topo.setPreferredSize(new Dimension(frame.getWidth(), 60));
        topo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        titulo.setFont(EstilosPadrao.tituloSAM);
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        // PAINEL CENTRAL COM FUNDO CINZA ESCURO
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(new Color(60, 60, 60));
        frame.add(painelCentral, BorderLayout.CENTER);

        // PAINEL LATERAL COM BOTÕES
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(45, 45, 45));
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // DESKTOP PANE para Internal Frames
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(80, 80, 80));
        painelCentral.add(desktopPane, BorderLayout.CENTER);


        JButton JBCadastrarProjeto = new JButton("Cadastrar Projeto");
        JBCadastrarProjeto.setFont(new Font("SansSerif", Font.BOLD, 16));
        JBCadastrarProjeto.setPreferredSize(new Dimension(80, 30)); // largura, altura
        JBCadastrarProjeto.setBackground(new Color(0, 200, 100));
        JBCadastrarProjeto.setForeground(Color.BLACK);
        painelBotoes.add(JBCadastrarProjeto);
        painelBotoes.add(Box.createRigidArea(new Dimension(100,30)));
        JBCadastrarProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*CadastrarProjetosView cadastrarProjetosView = new CadastrarProjetosView();
                cadastrarProjetosView.setVisible(true);*/
            }
        });

        JButton JBGerenciarProjeto = new JButton("Gerenciar Projeto");
        JBGerenciarProjeto.setFont(new Font("SansSerif", Font.BOLD, 16));
        JBGerenciarProjeto.setBackground(new Color(0, 200, 100));
        JBGerenciarProjeto.setForeground(Color.BLACK);
        painelBotoes.add(JBGerenciarProjeto);
        painelBotoes.add(Box.createRigidArea(new Dimension(165, 30)));
        JBGerenciarProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarProjetosView gerenciarProjetosView = new GerenciarProjetosView();
                gerenciarProjetosView.setVisible(true);
            }
        });

        JButton JBListMentores = new JButton("Listar Mentores");
        JBListMentores.setFont(new Font("SansSerif", Font.BOLD, 16));
        JBListMentores.setBackground(new Color(0, 200, 100));
        JBListMentores.setForeground(Color.BLACK);
        painelBotoes.add(JBListMentores);
        painelBotoes.add(Box.createRigidArea(new Dimension(100,30)));
        JBListMentores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });


        Dimension botaoTamanho = new Dimension(165, 30);
        Font fonteBotao = new Font("SansSerif", Font.PLAIN, 14);

        // botão de reuniões - criar os demais aqui e depois adicionar no for para adicionar na barra lateral
        String opcoesReuniao[] = {"Reuniões", "Visualizar Reuniões", "Agendar Reunião"};
        JComboBox<String> btnReuniao = new JComboBox<>(opcoesReuniao);
        btnReuniao.setFont(fonteBotao);
        btnReuniao.setMaximumSize(botaoTamanho);
        btnReuniao.setPreferredSize(botaoTamanho);
        btnReuniao.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelBotoes.add(btnReuniao);
        painelBotoes.add(Box.createVerticalStrut(15));

        painelCentral.add(painelBotoes, BorderLayout.WEST);


        // AÇÃO DO BOTÃO "btnReuniao"
        btnReuniao.addActionListener(e -> {

            JInternalFrame internalFrame = new JInternalFrame(); // cria o internal frame
            int posicaoBtnReuniao = btnReuniao.getSelectedIndex(); // pega o index da opção que o usuário selecionou
            // Seleção com base no index para redirecionamento para telas
            if(posicaoBtnReuniao==1) {
                internalFrame = ReuniaoView.visualizarReunioes();
            } else if (posicaoBtnReuniao==2) {
                internalFrame = CadastrarReuniaoView.cadastrarReuniao();
            }
            desktopPane.add(internalFrame);
            internalFrame.setLocation((desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                    (desktopPane.getHeight() - internalFrame.getHeight()) / 2);
            internalFrame.moveToFront();
        });

        // Exibir a Janela
        frame.setVisible(true);
    }
}
