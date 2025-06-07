package org.scam.view.aluno;

import org.scam.view.EstilosPadrao;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;

public class CadastrarReuniaoView {

    public static JInternalFrame cadastrarReuniao(){

        JInternalFrame internalFrame = new JInternalFrame();
        internalFrame.setSize(1055, 585);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) internalFrame.getUI();
        ui.setNorthPane(null); // remove a barra de título

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(EstilosPadrao.cinzaFundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel lblTitulo = new JLabel("Agendar reunião:");
        lblTitulo.setFont(EstilosPadrao.fonteTitulos);
        lblTitulo.setForeground(EstilosPadrao.verdeUni);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Painel para agendamento de reunião
        JPanel painelCadastro = new JPanel(new FlowLayout());
        painelCadastro.setBackground(EstilosPadrao.cinzaFundo);

        JLabel motivoLable = new JLabel("Explique o motivo da reunião: ");
        motivoLable.setFont(EstilosPadrao.fontePadrao);
        motivoLable.setForeground(Color.WHITE);
        painelCadastro.add(motivoLable);
        painelCadastro.add(Box.createVerticalStrut(10));
        motivoLable.setAlignmentX(0.0f);

        JTextArea motivoUser = new JTextArea(5, 30);
        motivoUser.setLineWrap(true);
        motivoUser.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(motivoUser);

        painelCadastro.add(scrollPane);

        painelCadastro.add(motivoUser);
        painelPrincipal.add(painelCadastro);

        // Adiciona os componentes no internal frame
        internalFrame.add(painelPrincipal);
        internalFrame.setVisible(true);
        return internalFrame;
    }
}
