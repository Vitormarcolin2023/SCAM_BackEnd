package org.scam.view;

import javax.swing.*;
import java.awt.*;

public class ReuniaoView {

    public static void main(String [] args) {
        // Criação da Janela Principal
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
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
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

        JButton btnVisuProjetos = new JButton("Visualizar Projetos");
        JButton btnAtualizarConta = new JButton("Atualizar Conta");
        JButton btnDesativarConta = new JButton("Desativar Conta");

        Dimension botaoTamanho = new Dimension(165, 30);
        Font fonteBotao = new Font("SansSerif", Font.PLAIN, 14);

        for (JButton btn : new JButton[]{btnVisuProjetos, btnAtualizarConta, btnDesativarConta}) {
            btn.setMaximumSize(botaoTamanho);
            btn.setPreferredSize(botaoTamanho);
            btn.setFont(fonteBotao);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelBotoes.add(btn);
            painelBotoes.add(Box.createVerticalStrut(15));
        }

        painelCentral.add(painelBotoes, BorderLayout.WEST);

        // DESKTOP PANE para Internal Frames
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(80, 80, 80));
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        // AÇÃO DO BOTÃO "Desativar Conta"
        btnDesativarConta.addActionListener(e -> {
            JInternalFrame internalFrame = new JInternalFrame();
            internalFrame.setSize(1055, 585);
            internalFrame.setLayout(new BorderLayout());
            internalFrame.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
            javax.swing.plaf.InternalFrameUI ui = internalFrame.getUI();
            if (ui instanceof javax.swing.plaf.basic.BasicInternalFrameUI basicUI) {
                basicUI.setNorthPane(null); // remove a barra de título
            }


            JPanel painelDialog = new JPanel();
            painelDialog.setBackground(new Color(50, 50, 50));
            painelDialog.setLayout(new BoxLayout(painelDialog, BoxLayout.Y_AXIS));
            painelDialog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel lblTitulo = new JLabel("Desativação de Conta");
            lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
            lblTitulo.setForeground(new Color(0, 200, 100));
            lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelDialog.add(lblTitulo);
            painelDialog.add(Box.createVerticalStrut(20));

            JLabel lblConfirma = new JLabel("Tem certeza que deseja desativar sua conta?");
            lblConfirma.setForeground(Color.WHITE);
            lblConfirma.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lblConfirma.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelDialog.add(lblConfirma);
            painelDialog.add(Box.createVerticalStrut(10));

            JRadioButton btnSim = new JRadioButton("Sim");
            JRadioButton btnNao = new JRadioButton("Não");
            btnSim.setBackground(new Color(50, 50, 50));
            btnNao.setBackground(new Color(50, 50, 50));
            btnSim.setForeground(Color.WHITE);
            btnNao.setForeground(Color.WHITE);

            ButtonGroup grupo = new ButtonGroup();
            grupo.add(btnSim);
            grupo.add(btnNao);

            JPanel opcoes = new JPanel();
            opcoes.setBackground(new Color(50, 50, 50));
            opcoes.setLayout(new BoxLayout(opcoes, BoxLayout.Y_AXIS));
            opcoes.add(btnSim);
            opcoes.add(btnNao);
            painelDialog.add(opcoes);
            painelDialog.add(Box.createVerticalStrut(20));

            JLabel lblMotivo = new JLabel("Digite o motivo da desativação da conta:");
            lblMotivo.setForeground(Color.WHITE);
            lblMotivo.setFont(new Font("SansSerif", Font.PLAIN, 14));
            painelDialog.add(lblMotivo);
            painelDialog.add(Box.createVerticalStrut(10));

            JTextArea areaTexto = new JTextArea(5, 30);
            areaTexto.setLineWrap(true);
            areaTexto.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane(areaTexto);
            painelDialog.add(scroll);
            painelDialog.add(Box.createVerticalStrut(20));

            JButton btnConfirmar = new JButton("Confirmar");
            btnConfirmar.setBackground(new Color(0, 200, 100));
            btnConfirmar.setForeground(Color.WHITE);
            btnConfirmar.setFocusPainted(false);
            btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 14));
            btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

            btnConfirmar.addActionListener(ev -> {
                JOptionPane.showMessageDialog(frame, "Conta desativada com sucesso!");
                internalFrame.dispose();
            });

            painelDialog.add(btnConfirmar);
            internalFrame.add(painelDialog, BorderLayout.CENTER);
            internalFrame.setVisible(true);
            desktopPane.add(internalFrame);
            internalFrame.setLocation((desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                    (desktopPane.getHeight() - internalFrame.getHeight()) / 2);
            internalFrame.moveToFront();
        });

        // Exibir a Janela
        frame.setVisible(true);
    }
}
