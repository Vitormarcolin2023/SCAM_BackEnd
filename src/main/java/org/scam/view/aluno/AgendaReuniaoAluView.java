package org.scam.view.aluno;

import org.scam.model.entities.ProjetoEntity;
import org.scam.model.services.ReuniaoService;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class AgendaReuniaoAluView {

    public static JInternalFrame cadastrarReuniao() {

        JInternalFrame internalFrame = new JInternalFrame();
        internalFrame.setSize(1055, 585);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) internalFrame.getUI();
        ui.setNorthPane(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(EstilosPadrao.cinzaFundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Agendar reunião:");
        lblTitulo.setFont(EstilosPadrao.fonteTitulos);
        lblTitulo.setForeground(EstilosPadrao.verdeUni);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelCentro = new JPanel(new GridBagLayout());
        painelCentro.setBackground(EstilosPadrao.cinzaFundo);
        painelCentro.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;

        // Label do motivo
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 3;
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

        // Tipo de reunião
        g.gridx = 0;
        g.gridy = 4;
        JLabel tipoLabel = new JLabel("Tipo de reunião:");
        tipoLabel.setFont(EstilosPadrao.fontePadrao);
        tipoLabel.setForeground(Color.WHITE);
        painelCentro.add(tipoLabel, g);

        g.gridx = 1;
        String[] tipos = {"Presencial", "Online"};
        JComboBox<String> tipoCombo = new JComboBox<>(tipos);
        tipoCombo.setPreferredSize(new Dimension(200, 25));
        tipoCombo.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 1));
        painelCentro.add(tipoCombo, g);

        // Local (visível apenas se for "Presencial")
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

        // Exibir ou ocultar campo local com base na escolha
        tipoCombo.addActionListener(e -> {
            String selecionado = (String) tipoCombo.getSelectedItem();
            boolean presencial = "Presencial".equals(selecionado);
            localLabel.setVisible(presencial);
            localField.setVisible(presencial);
            painelCentro.revalidate();
            painelCentro.repaint();
        });


        // Projeto
        g.gridx = 0;
        g.gridy = 6;
        JLabel buscarProjeto = new JLabel("Selecione o projeto: ");
        buscarProjeto.setForeground(Color.white);
        buscarProjeto.setFont(EstilosPadrao.fontePadrao);
        painelCentro.add(buscarProjeto, g);

        g.gridx = 1;
        List<ProjetoEntity> listaProjetos = ReuniaoService.buscarProjetos(80554);
        JComboBox<String> comboProjetos = new JComboBox<>();
        comboProjetos.setPreferredSize(new Dimension(200, 25));
        comboProjetos.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 1));
        for (ProjetoEntity projeto : listaProjetos) {
            comboProjetos.addItem(projeto.getNomeDoProjeto());
        }
        painelCentro.add(comboProjetos, g);

        // Botões
        g.gridx = 0;
        g.gridy = 7;
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

        painelPrincipal.add(painelCentro, BorderLayout.CENTER);
        internalFrame.add(painelPrincipal, BorderLayout.CENTER);
        internalFrame.setVisible(true);

        // Oculta o campo local inicialmente, se não for "Presencial"
        tipoCombo.setSelectedItem("Online");

        return internalFrame;
    }
}
