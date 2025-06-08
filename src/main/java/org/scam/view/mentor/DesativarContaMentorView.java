package org.scam.view.mentor;

import javax.swing.*;
import java.awt.*;

public class DesativarContaMentorView {

    public static void abrirFormularioDesativacao(JDesktopPane desktopPane) {
        JInternalFrame internalFrame = new JInternalFrame();
        internalFrame.setSize(1055, 585);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setBorder(BorderFactory.createLineBorder(new Color(37, 36, 36), 2));
        javax.swing.plaf.InternalFrameUI ui = internalFrame.getUI();
        if (ui instanceof javax.swing.plaf.basic.BasicInternalFrameUI basicUI) {
            basicUI.setNorthPane(null); // remove a barra de título
        }

        JPanel painelDialog = new JPanel();
        painelDialog.setBackground(new Color(50, 50, 50));
        painelDialog.setLayout(new BoxLayout(painelDialog, BoxLayout.Y_AXIS));
        painelDialog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Desativação de Conta");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 200, 100));
        lblTitulo.setAlignmentX(0.0f);
        painelDialog.add(lblTitulo);
        painelDialog.add(Box.createVerticalStrut(20));

        JLabel lblConfirma = new JLabel("Tem certeza que deseja desativar sua conta?");
        lblConfirma.setForeground(Color.WHITE);
        lblConfirma.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblConfirma.setAlignmentX(0.0f);
        painelDialog.add(lblConfirma);
        painelDialog.add(Box.createVerticalStrut(10));

        JRadioButton btnSim = new JRadioButton("Sim");
        btnSim.setBackground(new Color(50, 50, 50));
        btnSim.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnSim.setForeground(Color.WHITE);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(btnSim);

        JPanel opcoes = new JPanel();
        opcoes.setBackground(new Color(50, 50, 50));
        opcoes.setLayout(new BoxLayout(opcoes, BoxLayout.Y_AXIS));
        opcoes.add(btnSim);

        painelDialog.add(opcoes);
        painelDialog.add(Box.createVerticalStrut(20));

        JLabel lblMotivo = new JLabel("Digite o motivo da desativação da conta:");
        lblMotivo.setForeground(Color.WHITE);
        lblMotivo.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblMotivo.setAlignmentX(0.0f);
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
            if (!btnSim.isSelected()) {
                JOptionPane.showMessageDialog(internalFrame,
                        "Por favor, clique na opção Sim para continuar",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (areaTexto.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(internalFrame,
                        "Por favor, informe o motivo da desativação.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(internalFrame, "Conta desativada com sucesso!");
            internalFrame.dispose();
        });

        painelDialog.add(btnConfirmar);
        internalFrame.add(painelDialog, BorderLayout.CENTER);
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        internalFrame.setLocation(
                (desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                (desktopPane.getHeight() - internalFrame.getHeight()) / 2);
        internalFrame.moveToFront();
    }
}
