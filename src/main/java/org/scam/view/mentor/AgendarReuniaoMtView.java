package org.scam.view.mentor;

import org.scam.view.EstilosPadrao;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.util.Date;

public class AgendarReuniaoMtView {

    public static JInternalFrame agendarReuniaoMentor() {
        JInternalFrame internalFrame = new JInternalFrame();
        internalFrame.setSize(EstilosPadrao.tamanhoInternalFrame);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setBackground(EstilosPadrao.cinzaFundo);
        internalFrame.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) internalFrame.getUI();
        ui.setNorthPane(null);

        JLabel lblTitulo = new JLabel("Agendar reunião:");
        lblTitulo.setFont(EstilosPadrao.fonteTitulos);
        lblTitulo.setForeground(EstilosPadrao.verdeUni);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        internalFrame.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelCentro = new JPanel(new GridBagLayout());
        painelCentro.setBackground(EstilosPadrao.cinzaFundo);
        painelCentro.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        internalFrame.add(painelCentro, BorderLayout.CENTER);

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;

        // Motivo
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        JLabel motivoLabel = new JLabel("Explique o motivo da reunião:");
        motivoLabel.setFont(EstilosPadrao.fontePadrao);
        motivoLabel.setForeground(Color.WHITE);
        painelCentro.add(motivoLabel, g);

        // TextArea do motivo
        g.gridy = 1;
        JTextArea motivoArea = new JTextArea(6, 50);
        motivoArea.setLineWrap(true);
        motivoArea.setWrapStyleWord(true);
        motivoArea.setBorder(new LineBorder(EstilosPadrao.cinzaFundo));
        JScrollPane scroll = new JScrollPane(motivoArea);
        scroll.setPreferredSize(new Dimension(500, 130));
        g.weighty = 0.2;
        g.fill = GridBagConstraints.BOTH;
        painelCentro.add(scroll, g);

        g.weighty = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridwidth = 1;

        // Data
        g.gridx = 0;
        g.gridy = 2;
        JLabel dataLabel = new JLabel("Data:");
        dataLabel.setFont(EstilosPadrao.fontePadrao);
        dataLabel.setForeground(Color.WHITE);
        painelCentro.add(dataLabel, g);

        g.gridx = 1;
        SpinnerDateModel dataModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        JSpinner dataSpinner = new JSpinner(dataModel);
        dataSpinner.setEditor(new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy"));
        dataSpinner.setBorder(new LineBorder(EstilosPadrao.cinzaFundo));
        painelCentro.add(dataSpinner, g);

        // Horário
        g.gridx = 0;
        g.gridy = 3;
        JLabel horaLabel = new JLabel("Horário:");
        horaLabel.setFont(EstilosPadrao.fontePadrao);
        horaLabel.setForeground(Color.WHITE);
        painelCentro.add(horaLabel, g);

        g.gridx = 1;
        SpinnerDateModel horaModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.MINUTE);
        JSpinner horaSpinner = new JSpinner(horaModel);
        horaSpinner.setEditor(new JSpinner.DateEditor(horaSpinner, "HH:mm"));
        horaSpinner.setBorder(new LineBorder(EstilosPadrao.cinzaFundo));
        painelCentro.add(horaSpinner, g);

        // Tipo de Reunião
        g.gridx = 0;
        g.gridy = 4;
        JLabel tipoReuniaoLabel = new JLabel("Tipo de reunião:");
        tipoReuniaoLabel.setFont(EstilosPadrao.fontePadrao);
        tipoReuniaoLabel.setForeground(Color.WHITE);
        painelCentro.add(tipoReuniaoLabel, g);

        g.gridx = 1;
        JComboBox<String> comboTipo = new JComboBox<>();
        comboTipo.addItem("Presencial");
        comboTipo.addItem("Online");
        comboTipo.setPreferredSize(new Dimension(200, 25));
        comboTipo.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 1));
        painelCentro.add(comboTipo, g);

        // Local
        g.gridx = 0;
        g.gridy = 5;
        JLabel localLabel = new JLabel("Local:");
        localLabel.setFont(EstilosPadrao.fontePadrao);
        localLabel.setForeground(Color.WHITE);
        painelCentro.add(localLabel, g);

        g.gridx = 1;
        JTextField localField = new JTextField(20);
        localField.setBorder(new LineBorder(EstilosPadrao.cinzaFundo));
        painelCentro.add(localField, g);

        // Projeto
        g.gridx = 0;
        g.gridy = 6;
        JLabel projetoLabel = new JLabel("Projeto:");
        projetoLabel.setFont(EstilosPadrao.fontePadrao);
        projetoLabel.setForeground(Color.WHITE);
        painelCentro.add(projetoLabel, g);

        g.gridx = 1;
        JComboBox<String> comboProjeto = new JComboBox<>();
        comboProjeto.setPreferredSize(new Dimension(200, 25));
        comboProjeto.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 1));
        comboProjeto.addItem("Selecione um projeto...");
        painelCentro.add(comboProjeto, g);

        // Botões
        g.gridx = 0;
        g.gridy = 8;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.WEST;

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        painelBotoes.setBackground(EstilosPadrao.cinzaFundo);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(EstilosPadrao.verdeUni);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(internalFrame, "Solicitação de reunião enviada ao mentor para confirmação.");
            internalFrame.dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(EstilosPadrao.verdeUni);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(internalFrame, "Tem certeza que deseja cancelar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                internalFrame.dispose();
            }
        });

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        painelCentro.add(painelBotoes, g);

        // Lógica para desabilitar/habilitar o campo Local sem alterar layout
        comboTipo.addActionListener(e -> {
            boolean presencial = comboTipo.getSelectedItem().equals("Presencial");
            localLabel.setEnabled(presencial);
            localField.setEnabled(presencial);

            // Opcional: limpa o campo quando for online
            if (!presencial) {
                localField.setText("");
            }
        });

        boolean inicialPresencial = comboTipo.getSelectedItem().equals("Presencial");
        localLabel.setEnabled(inicialPresencial);
        localField.setEnabled(inicialPresencial);


        internalFrame.setVisible(true);
        return internalFrame;
    }
}
