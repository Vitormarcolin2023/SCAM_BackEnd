package org.scam.view.mentor;

import org.scam.view.EstilosPadrao;

import javax.swing.*;
import java.awt.*;

public class TelaInicialMentor {

    public static void telaMentor() {
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeUni);
        topo.setPreferredSize(new Dimension(0, 60));
        topo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        titulo.setFont(EstilosPadrao.tituloSAM);
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(EstilosPadrao.cinzaFundo);
        frame.add(painelCentral, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(EstilosPadrao.cinzaFundo);
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnVisuProjetos = new JButton("Visualizar Projetos");
        JButton btnAtualizarConta = new JButton("Atualizar Conta");
        JButton btnDesativarConta = new JButton("Desativar Conta");
        JButton btnVoltar = new JButton("Voltar");

        for (JButton btn : new JButton[]{btnVisuProjetos, btnAtualizarConta, btnDesativarConta}) {
            btn.setMaximumSize(EstilosPadrao.tamanhoBotao);
            btn.setPreferredSize(EstilosPadrao.tamanhoBotao);
            btn.setFont(EstilosPadrao.fonteBtnAcaoLateral);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelBotoes.add(btn);
            painelBotoes.add(Box.createVerticalStrut(15));
        }

        String[] opcoesReuniao = {"Reuniões", "Visualizar Reuniões", "Agendar Reunião"};
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
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });

        btnVoltar.setMaximumSize(EstilosPadrao.tamanhoBotao);
        btnVoltar.setPreferredSize(EstilosPadrao.tamanhoBotao);
        btnVoltar.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        btnVoltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnVoltar.setBackground(EstilosPadrao.verdeBotaoVoltar);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        painelBotoes.add(btnVoltar);
        painelBotoes.add(Box.createVerticalStrut(15));

        painelCentral.add(painelBotoes, BorderLayout.WEST);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(EstilosPadrao.cinzaClaro);
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        // ActionListener para Visualizar Projetos ficou mais limpo
        btnVisuProjetos.addActionListener(e -> {
            VisualizarProjetoMentorView.exibir(frame, desktopPane);
        });

        btnAtualizarConta.addActionListener(e -> {
            // EdicaoMentorPasso1View.exibirTelaEdicaoPasso1(desktopPane);
        });

        btnDesativarConta.addActionListener(e -> {
            JInternalFrame internalFrame = DesativarContaMentorView.exibirTelaDesativarConta(frame);

            Dimension desktopSize = desktopPane.getSize();
            Dimension frameSize = internalFrame.getSize();
            int x = (desktopSize.width - frameSize.width) / 2;
            int y = (desktopSize.height - frameSize.height) / 2;
            internalFrame.setLocation(x, y);

            desktopPane.add(internalFrame);
            internalFrame.setVisible(true); // Garante que a janela seja visível
            try {
                internalFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException pve) {
                pve.printStackTrace();
            }
        });

        btnVoltar.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(frame,
                    "Tem certeza que deseja voltar para a tela de login?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                frame.dispose();
                // Supondo que esta view exista
                // LoginOneMentorView.loginOne();
            }
        });

        btnReuniao.addActionListener(e -> {
            JInternalFrame internalFrame = null;
            int posicaoBtnReuniao = btnReuniao.getSelectedIndex();
            if (posicaoBtnReuniao == 1) {
                // Supondo que esta view exista
                // internalFrame = VisualizarReunioesMtView.visualizarReunioesMentor();
            } else if (posicaoBtnReuniao == 2) {
                // Supondo que esta view exista
                // internalFrame = AgendarReuniaoMtView.agendarReuniaoMentor();
            }

            if (internalFrame != null) {
                desktopPane.add(internalFrame);
                internalFrame.setLocation((desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                        (desktopPane.getHeight() - internalFrame.getHeight()) / 2);
                internalFrame.moveToFront();
                internalFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }
}