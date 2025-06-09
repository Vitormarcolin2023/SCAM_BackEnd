package org.scam.view.aluno;

import javax.swing.*;
import java.awt.*;

public class PrincipalAlunoView {

    public static void principalAluno(){
        // Janela principal
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // Painel superior (barra verde com título)
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 128, 66));
        topo.setPreferredSize(new Dimension(frame.getWidth(), 60));
        topo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        // Painel central (conteúdo principal)
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(new Color(60, 60, 60));
        frame.add(painelCentral, BorderLayout.CENTER);

        // Painel lateral com botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(45, 45, 45));
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Janelas Internas
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(60,60,60));
        painelCentral.add(desktopPane,BorderLayout.CENTER);

        //BOTOES
        JButton JBCadastrarProjeto = new JButton("Cadastrar Projeto");
        JButton JBGerenciarProjeto = new JButton("Gerenciar Projeto");
        JButton JBListMentores = new JButton("Listar Mentores");

        Dimension botaoTamanho = new Dimension(165, 30);
        Font fonteBotao = new Font("SansSerif", Font.PLAIN, 14);

        for (JButton btn : new JButton[]{JBCadastrarProjeto, JBGerenciarProjeto, JBListMentores}) {
            btn.setMaximumSize(botaoTamanho);
            btn.setPreferredSize(botaoTamanho);
            btn.setFont(fonteBotao);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelBotoes.add(btn);
            painelBotoes.add(Box.createVerticalStrut(15));
        }


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

        JBListMentores.addActionListener(e ->{
            JInternalFrame mentorFrame = ListMentoresView.ListMentores();
            desktopPane.add(mentorFrame);
            mentorFrame.setLocation(
                    (desktopPane.getWidth() - mentorFrame.getWidth()) /2,
                    (desktopPane.getHeight() - mentorFrame.getHeight()) /2
            );
            try{
                mentorFrame.setSelected(true);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });




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
