package org.scam.view.aluno;

import org.scam.controller.AlunoController;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.StatusReuniao;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class VisualizarProjView {

    public static JInternalFrame ListProjeto(JDesktopPane desktop) {
        JInternalFrame internalFrame = new JInternalFrame();
        internalFrame.setSize(EstilosPadrao.tamanhoInternalFrame);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) internalFrame.getUI();
        ui.setNorthPane(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(EstilosPadrao.cinzaFundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<ProjetoEntity> projetos = AlunoController.projetosAluno(Sessao.getAlunoLogado().getRa());

        JPanel painelLista = new JPanel();
        painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));
        painelLista.setBackground(EstilosPadrao.cinzaFundo);

        JScrollPane scroll = new JScrollPane(painelLista);
        scroll.setBorder(null);
        painelPrincipal.add(scroll, BorderLayout.CENTER);

        if (projetos.isEmpty()) {
            JLabel avisoP = new JLabel("Nenhum projeto cadastrado");
            avisoP.setFont(EstilosPadrao.fontePadrao);
            avisoP.setForeground(Color.WHITE);
            avisoP.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelLista.add(avisoP);
        } else {
            for (ProjetoEntity projeto : projetos) {
                JPanel painelProjetos = new JPanel(new BorderLayout());
                painelProjetos.setBackground(EstilosPadrao.cinzaClaro);
                painelProjetos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
                painelProjetos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JPanel painelLabels = new JPanel(new FlowLayout(FlowLayout.LEFT));
                painelLabels.setBackground(EstilosPadrao.cinzaClaro);

                JLabel lblMotivo = new JLabel("Projeto: " + projeto.getNomeDoProjeto());
                lblMotivo.setForeground(Color.WHITE);
                lblMotivo.setFont(EstilosPadrao.fontePadrao);

                painelLabels.add(lblMotivo);

                JButton btnDetalhes = new JButton("Detalhes");
                btnDetalhes.setBackground(EstilosPadrao.verdeUni);
                btnDetalhes.setForeground(Color.WHITE);
                btnDetalhes.setFocusPainted(false);
                btnDetalhes.setFont(EstilosPadrao.fonteBotao);
                btnDetalhes.setPreferredSize(EstilosPadrao.tamanhoBotao);

                btnDetalhes.addActionListener(e -> {
                    detalhesProjeto(projeto, desktop);
                });

                painelProjetos.add(painelLabels, BorderLayout.WEST);
                painelProjetos.add(btnDetalhes, BorderLayout.EAST);

                painelLista.add(painelProjetos);
                painelLista.add(Box.createVerticalStrut(10));
            }
        }

        // Botão de fechar
        JButton fechar = new JButton("Fechar");
        fechar.setBackground(EstilosPadrao.verdeUni);
        fechar.setForeground(Color.WHITE);
        fechar.setFont(EstilosPadrao.fonteBotao);
        fechar.setPreferredSize(EstilosPadrao.tamanhoBotao);
        fechar.addActionListener(e -> internalFrame.dispose());

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.setBackground(EstilosPadrao.cinzaFundo);
        painelBotao.add(fechar);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        internalFrame.add(painelPrincipal, BorderLayout.CENTER);
        return internalFrame;
    }

    private static void detalhesProjeto(ProjetoEntity projeto, JDesktopPane desktop) {
        JInternalFrame detalhesFrame = new JInternalFrame("Detalhes da Reunião", true, true, true, true);
        detalhesFrame.setSize(700, 400);
        detalhesFrame.setLayout(new BorderLayout());
        detalhesFrame.setLocation(200, 120);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) detalhesFrame.getUI();
        ui.setNorthPane(null);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(EstilosPadrao.cinzaFundo);
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        Color corTitulo = EstilosPadrao.verdeUni;
        Color corTexto = Color.WHITE;
        Font fonte = EstilosPadrao.fontePadrao;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Títulos e valores
        adicionarLinhaTabela("Nome do projeto:", projeto.getNomeDoProjeto(), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Descrição:", projeto.getDescricao(), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Área de atuação:", projeto.getAreaDeAtuacao().toString(), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Data de início:", projeto.getDataInicioProjeto().format(formatter), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Data de término:", projeto.getDataFinalProjeto().format(formatter), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Mentor:", projeto.getMentor().getNome(), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Grupo:", projeto.getTamanhoDoGrupo() + " integrantes", painel, gbc, corTitulo, corTexto, fonte);

        // Alunos
        JLabel lblAlunos = new JLabel("Alunos:");
        lblAlunos.setFont(fonte);
        lblAlunos.setForeground(corTitulo);
        gbc.gridx = 0;
        gbc.gridy++;
        painel.add(lblAlunos, gbc);

        for (AlunoEntity aluno : projeto.getAlunos()) {
            JLabel lblNome = new JLabel(aluno.getNome());
            lblNome.setFont(fonte);
            lblNome.setForeground(corTexto);
            gbc.gridx = 1;
            painel.add(lblNome, gbc);
            gbc.gridy++;
        }

        // Botão fechar
        JButton fechar = new JButton("Fechar");
        fechar.setBackground(EstilosPadrao.verdeUni);
        fechar.setForeground(Color.WHITE);
        fechar.setFont(EstilosPadrao.fonteBotao);
        fechar.setPreferredSize(EstilosPadrao.tamanhoBotao);
        fechar.addActionListener(e -> detalhesFrame.dispose());

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.setBackground(EstilosPadrao.cinzaFundo);
        painelBotao.add(fechar);

        JScrollPane scrollPane = new JScrollPane(painel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(EstilosPadrao.cinzaFundo);
        scrollPane.getViewport().setBackground(EstilosPadrao.cinzaFundo);

        detalhesFrame.add(scrollPane, BorderLayout.CENTER);

        detalhesFrame.add(painelBotao, BorderLayout.SOUTH);
        desktop.add(detalhesFrame);
        detalhesFrame.setVisible(true);

        try {
            detalhesFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }


    private static void adicionarLinhaTabela(String titulo, String conteudo, JPanel painel, GridBagConstraints gbc, Color corTitulo, Color corTexto, Font fonte) {
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(fonte);
        lblTitulo.setForeground(corTitulo);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(lblTitulo, gbc);

        if (titulo.equals("Descrição:")) {
            JTextArea txtArea = new JTextArea(conteudo);
            txtArea.setFont(fonte);
            txtArea.setForeground(corTexto);
            txtArea.setBackground(EstilosPadrao.cinzaFundo);
            txtArea.setLineWrap(true);
            txtArea.setWrapStyleWord(true);
            txtArea.setEditable(false);
            txtArea.setBorder(null);
            txtArea.setOpaque(false);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            painel.add(txtArea, gbc);
            gbc.weightx = 0;
        } else {
            JLabel lblConteudo = new JLabel(conteudo);
            lblConteudo.setFont(fonte);
            lblConteudo.setForeground(corTexto);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.NONE;
            painel.add(lblConteudo, gbc);
        }

        gbc.gridy++;
    }


}
