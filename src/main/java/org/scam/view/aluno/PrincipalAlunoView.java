package org.scam.view.aluno;

import org.scam.model.repository.TipoUsuario;
import org.scam.view.EstilosPadrao;
import org.scam.view.Reuniao.AgendaReuniaoView;
import org.scam.view.Reuniao.VisualizarReunioesView;
import org.scam.view.TelaSelecaoUsuarioView;

import javax.swing.*;
import java.awt.*;

public class PrincipalAlunoView {

    public static void principal() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLayout(new BorderLayout());

            JPanel topo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
            topo.setBackground(EstilosPadrao.verdeSAM);
            topo.setPreferredSize(new Dimension(0, 60));

            JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
            titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
            titulo.setForeground(Color.WHITE);
            topo.add(titulo);

            JPanel painelCentral = new JPanel(new BorderLayout());
            painelCentral.setBackground(EstilosPadrao.cinzaFundo);

            JDesktopPane desktopPane = new JDesktopPane();
            desktopPane.setBackground(EstilosPadrao.cinzaClaro);
            painelCentral.add(desktopPane, BorderLayout.CENTER);

            JPanel painelBotoes = new JPanel();
            painelBotoes.setBackground(EstilosPadrao.cinzaFundo);
            painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
            painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            JButton cadastrarProjetoBtn = new JButton("Cadastrar Projeto");
            JButton gerenciarProjetoBtn = new JButton("Gerenciar Projeto");
            JButton listarMentoresBtn = new JButton("Listar Mentores");

            String[] opcoesReuniao = {"Reuniões", "Visualizar Reuniões", "Agendar Reunião"};
            JComboBox<String> reuniaoCombo = new JComboBox<>(opcoesReuniao);

            JButton voltarBtn = new JButton("Voltar");

            for (JButton btn : new JButton[]{cadastrarProjetoBtn, gerenciarProjetoBtn, listarMentoresBtn}) {
                btn.setFont(EstilosPadrao.fonteBtnAcaoLateral);
                btn.setMaximumSize(EstilosPadrao.tamanhoBotao);
                btn.setPreferredSize(EstilosPadrao.tamanhoBotao);
                btn.setForeground(Color.BLACK);
                btn.setAlignmentX(Component.LEFT_ALIGNMENT);
                painelBotoes.add(btn);
                painelBotoes.add(Box.createVerticalStrut(15));
            }

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
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    return label;
                }
            });

            voltarBtn.setMaximumSize(EstilosPadrao.tamanhoBotao);
            voltarBtn.setPreferredSize(EstilosPadrao.tamanhoBotao);
            voltarBtn.setFont(EstilosPadrao.fonteBtnAcaoLateral);
            voltarBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
            voltarBtn.setBackground(EstilosPadrao.verdeBotaoVoltar);
            voltarBtn.setForeground(Color.WHITE);
            voltarBtn.setFocusPainted(false);
            painelBotoes.add(Box.createVerticalGlue());
            painelBotoes.add(voltarBtn);


            cadastrarProjetoBtn.addActionListener(e -> {
                CadastrarProjetosView cadastroFrame = new CadastrarProjetosView();

                cadastroFrame.setResizable(false);
                desktopPane.add(cadastroFrame);
                Dimension desktopSize = desktopPane.getSize();
                Dimension frameSize = cadastroFrame.getSize();
                cadastroFrame.setLocation((desktopSize.width - frameSize.width) / 2, (desktopSize.height - frameSize.height) / 2);
                javax.swing.plaf.InternalFrameUI ui = cadastroFrame.getUI();
                if (ui instanceof javax.swing.plaf.basic.BasicInternalFrameUI basicUI) {
                    basicUI.setNorthPane(null);
                }
                //borda
                cadastroFrame.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 3));

                cadastroFrame.setVisible(true);

                try {
                    cadastroFrame.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
            });

            gerenciarProjetoBtn.addActionListener(e -> {
                GerenciarProjetosView gerenciarProjetosView = new GerenciarProjetosView();
                desktopPane.add(gerenciarProjetosView);
                gerenciarProjetosView.setLocation(
                        (desktopPane.getWidth() - gerenciarProjetosView.getWidth()) /2,
                        (desktopPane.getHeight() - gerenciarProjetosView.getHeight()) /2
                );
                gerenciarProjetosView.setVisible(true);
            });

            listarMentoresBtn.addActionListener(e -> {
                JInternalFrame mentorFrame = ListMentoresView.ListMentores();
                desktopPane.add(mentorFrame);
                mentorFrame.setLocation(
                        (desktopPane.getWidth() - mentorFrame.getWidth()) / 2,
                        (desktopPane.getHeight() - mentorFrame.getHeight()) / 2
                );
                try {
                    mentorFrame.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
            });

            btnReuniao.addActionListener(e -> {
                int selectedIndex = btnReuniao.getSelectedIndex();

                // Evita executar ao selecionar "Reuniões"
                if (selectedIndex == 0) return;

                SwingUtilities.invokeLater(() -> {
                    JInternalFrame internalFrame = null;

                    if (selectedIndex == 1) { // "Visualizar Reuniões"
                        internalFrame = VisualizarReunioesView.visualizarReunioes(desktopPane, TipoUsuario.ALUNO);
                    } else if (selectedIndex == 2) { // "Agendar Reunião"
                        AgendaReuniaoView.setTipoUsuario(TipoUsuario.ALUNO);
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

            voltarBtn.addActionListener(e -> {
                int confirmar = JOptionPane.showConfirmDialog(frame,
                        "Tem certeza que deseja voltar para a tela de login?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION);

                if (confirmar == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    TelaSelecaoUsuarioView.exibirTelaSelecao();
                }
            });

            frame.add(topo, BorderLayout.NORTH);
            frame.add(painelCentral, BorderLayout.CENTER);
            frame.add(painelBotoes, BorderLayout.WEST);

            frame.setVisible(true);
        });
    }
}