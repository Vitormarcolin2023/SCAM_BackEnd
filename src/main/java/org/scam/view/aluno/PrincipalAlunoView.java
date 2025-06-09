package org.scam.view.aluno;

<<<<<<< HEAD
import javax.swing.*;
import java.awt.*;

public class PrincipalAlunoView {

    public static void principalAluno(){
        // Janela principal
=======

import org.scam.view.EstilosPadrao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.scam.view.mentor.LoginOneMentorView;

public class PrincipalAlunoView {

    public static void principal(){
        // CRIACAO DA JANELA PRINCIPAL
>>>>>>> 47cbd6c315a6b0594aac4041712ee6d8dd1b0c1f
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
<<<<<<< HEAD
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


=======
        JBCadastrarProjeto.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        JBCadastrarProjeto.setMaximumSize(EstilosPadrao.tamanhoBotao);
        JBCadastrarProjeto.setPreferredSize(EstilosPadrao.tamanhoBotao); // largura, altur
        JBCadastrarProjeto.setForeground(Color.BLACK);
        JBCadastrarProjeto.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelBotoes.add(JBCadastrarProjeto);
        painelBotoes.add(Box.createVerticalStrut(15));
        JBCadastrarProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*CadastrarProjetosView cadastrarProjetosView = new CadastrarProjetosView();
                cadastrarProjetosView.setVisible(true);*/
            }
        });

        JButton JBGerenciarProjeto = new JButton("Gerenciar Projeto");
        JBGerenciarProjeto.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        JBGerenciarProjeto.setMaximumSize(EstilosPadrao.tamanhoBotao);
        JBGerenciarProjeto.setPreferredSize(EstilosPadrao.tamanhoBotao);
        JBGerenciarProjeto.setForeground(Color.BLACK);
        painelBotoes.add(JBGerenciarProjeto);
        painelBotoes.add(Box.createVerticalStrut(15));
        JBGerenciarProjeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarProjetosView gerenciarProjetosView = new GerenciarProjetosView();
                gerenciarProjetosView.setVisible(true);
            }
        });

        JButton JBListMentores = new JButton("Listar Mentores");
        JBListMentores.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        JBListMentores.setMaximumSize(EstilosPadrao.tamanhoBotao);
        JBListMentores.setPreferredSize(EstilosPadrao.tamanhoBotao);
        JBListMentores.setForeground(Color.BLACK);
        painelBotoes.add(JBListMentores);
        painelBotoes.add(Box.createVerticalStrut(15));
        JBListMentores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

>>>>>>> 47cbd6c315a6b0594aac4041712ee6d8dd1b0c1f
        // botão de reuniões - criar os demais aqui e depois adicionar no for para adicionar na barra lateral
        String opcoesReuniao[] = {"Reuniões", "Visualizar Reuniões", "Agendar Reunião"};
        JComboBox<String> btnReuniao = new JComboBox<>(opcoesReuniao);
        btnReuniao.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        btnReuniao.setMaximumSize(EstilosPadrao.tamanhoBotao);
        btnReuniao.setPreferredSize(EstilosPadrao.tamanhoBotao);
        btnReuniao.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelBotoes.add(btnReuniao);
        painelBotoes.add(Box.createVerticalStrut(15));

        btnReuniao.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER); // centraliza itens da lista
                return label;
            }
        });

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
                internalFrame = VisualizarReunioesAluView.visualizarReunioes();
            } else if (posicaoBtnReuniao==2) {
                internalFrame = AgendaReuniaoAluView.cadastrarReuniao();
            }
            desktopPane.add(internalFrame);
            internalFrame.setLocation((desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                    (desktopPane.getHeight() - internalFrame.getHeight()) / 2);
            internalFrame.moveToFront();
        });

        // Estilo especial para o botão "Voltar"
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setMaximumSize(EstilosPadrao.tamanhoBotao);
        btnVoltar.setPreferredSize(EstilosPadrao.tamanhoBotao);
        btnVoltar.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        btnVoltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnVoltar.setBackground(EstilosPadrao.verdeBotaoVoltar);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        painelBotoes.add(btnVoltar);
        painelBotoes.add(Box.createVerticalStrut(15));

        btnVoltar.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(frame,
                    "Tem certeza que deseja voltar para a tela de login?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                frame.dispose(); // Fecha a janela atual
                LoginOneMentorView.loginOne(); // Abre a tela de login
            }
        });


        painelCentral.add(painelBotoes, BorderLayout.WEST);
        // Exibir a Janela
        frame.setVisible(true);
    }
}
