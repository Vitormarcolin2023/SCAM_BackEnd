package org.scam.view.mentor;

import org.scam.model.repository.TipoUsuario;
import org.scam.view.EstilosPadrao;
import org.scam.view.Reuniao.AgendaReuniaoView;
import org.scam.view.Reuniao.VisualizarReunioesView;
import org.scam.view.TelaSelecaoUsuarioView;

import javax.swing.*;
import java.awt.*;

public class TelaInicialMentor {

    public static void telaMentor() {
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeSAM);
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
            EdicaoMentorPasso1View.exibirTelaEdicaoPasso1();
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
                TelaSelecaoUsuarioView.exibirTelaSelecao();
            }
        });

        btnReuniao.addActionListener(e -> {
            int selectedIndex = btnReuniao.getSelectedIndex();

            // Evita executar ao selecionar "Reuniões"
            if (selectedIndex == 0) return;

            SwingUtilities.invokeLater(() -> {
                JInternalFrame internalFrame = null;

                if (selectedIndex == 1) { // "Visualizar Reuniões"
                    internalFrame = VisualizarReunioesView.visualizarReunioes(desktopPane, TipoUsuario.MENTOR);
                } else if (selectedIndex == 2) { // "Agendar Reunião"
                    AgendaReuniaoView.setTipoUsuario(TipoUsuario.MENTOR);
                    internalFrame = AgendaReuniaoView.cadastrarReuniao();
                }

                if (internalFrame != null) {
                    desktopPane.add(internalFrame);
                    int x = (desktopPane.getWidth() - internalFrame.getWidth()) / 2;
                    int y = (desktopPane.getHeight() - internalFrame.getHeight()) / 2;
                    internalFrame.setLocation(x, y);
                    internalFrame.setVisible(true);
                    internalFrame.moveToFront();
                    try {
                        internalFrame.setSelected(true);
                    } catch (java.beans.PropertyVetoException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        });

        frame.setVisible(true);
    }
}