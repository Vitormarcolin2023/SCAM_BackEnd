package org.scam.view.aluno;

import org.scam.controller.AlunoController;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;


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

        JPanel painelListaContainer = new JPanel(new BorderLayout());
        painelListaContainer.setBackground(EstilosPadrao.cinzaFundo);

        Consumer<List<ProjetoEntity>> atualizarLista = projetos -> {
            JPanel novaLista = criarPainelProjetos(projetos, desktop);
            painelListaContainer.removeAll();
            painelListaContainer.add(novaLista, BorderLayout.CENTER);
            painelListaContainer.revalidate();
            painelListaContainer.repaint();
        };

        AlunoController.addObserver(atualizarLista);

        List<ProjetoEntity> projetosIniciais = AlunoController.projetosAluno(Sessao.getAlunoLogado().getRa());
        painelListaContainer.add(criarPainelProjetos(projetosIniciais, desktop), BorderLayout.CENTER);

        JButton fechar = new JButton("Fechar");
        fechar.setBackground(EstilosPadrao.verdeUni);
        fechar.setForeground(Color.WHITE);
        fechar.setFont(EstilosPadrao.fonteBotao);
        fechar.setPreferredSize(EstilosPadrao.tamanhoBotao);
        fechar.addActionListener(e -> {
            AlunoController.removeObserver(atualizarLista);
            internalFrame.dispose();
        });

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.setBackground(EstilosPadrao.cinzaFundo);
        painelBotao.add(fechar);

        painelPrincipal.add(painelListaContainer, BorderLayout.CENTER);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        internalFrame.add(painelPrincipal, BorderLayout.CENTER);
        return internalFrame;
    }

    private static JPanel criarPainelProjetos(List<ProjetoEntity> projetos, JDesktopPane desktop) {
        JPanel painelLista = new JPanel();
        painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));
        painelLista.setBackground(EstilosPadrao.cinzaFundo);

        if (projetos.isEmpty()) {
            JLabel avisoP = new JLabel("Nenhum projeto APROVADO para exibir");
            avisoP.setFont(EstilosPadrao.fontePadrao);
            avisoP.setForeground(Color.WHITE);
            avisoP.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelLista.add(avisoP);
        } else {
            for (ProjetoEntity projeto : projetos) {
                JPanel painelProjeto = criarItemProjeto(projeto, desktop);
                painelLista.add(painelProjeto);
                painelLista.add(Box.createVerticalStrut(10));
            }
        }

        return painelLista;
    }

    private static JPanel criarItemProjeto(ProjetoEntity projeto, JDesktopPane desktop) {
        JPanel painelProjeto = new JPanel(new BorderLayout());
        painelProjeto.setBackground(EstilosPadrao.cinzaClaro);
        painelProjeto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        painelProjeto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblNomeProjeto = new JLabel("Projeto: " + projeto.getNomeDoProjeto());
        lblNomeProjeto.setForeground(Color.WHITE);
        lblNomeProjeto.setFont(EstilosPadrao.fontePadrao);

        JButton btnDetalhes = new JButton("Detalhes");
        btnDetalhes.setBackground(EstilosPadrao.verdeUni);
        btnDetalhes.setForeground(Color.WHITE);
        btnDetalhes.setFocusPainted(false);
        btnDetalhes.setFont(EstilosPadrao.fonteBotao);
        btnDetalhes.setPreferredSize(EstilosPadrao.tamanhoBotao);
        btnDetalhes.addActionListener(e -> detalhesProjeto(projeto, desktop));

        painelProjeto.add(lblNomeProjeto, BorderLayout.WEST);
        painelProjeto.add(btnDetalhes, BorderLayout.EAST);

        return painelProjeto;
    }


    private static void detalhesProjeto(ProjetoEntity projeto, JDesktopPane desktop) {
        JInternalFrame detalhesFrame = new JInternalFrame("Detalhes do Projeto", true, true, true, true);
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

        adicionarLinhaTabela("Nome do projeto:", projeto.getNomeDoProjeto(), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Descrição:", projeto.getDescricao(), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Área de atuação:", projeto.getAreaDeAtuacao().toString(), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Data de início:", projeto.getDataInicioProjeto().format(formatter), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Data de término:", projeto.getDataFinalProjeto().format(formatter), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Mentor:", projeto.getMentor().getNome(), painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Grupo:", projeto.getTamanhoDoGrupo() + " integrantes", painel, gbc, corTitulo, corTexto, fonte);
        adicionarLinhaTabela("Status:", projeto.getStatus().toString(), painel, gbc, corTitulo, corTexto, fonte);

        JLabel lblAlunos = new JLabel("Alunos:");
        lblAlunos.setFont(fonte);
        lblAlunos.setForeground(corTitulo);
        gbc.gridx = 0;
        gbc.gridy++;
        painel.add(lblAlunos, gbc);

        gbc.gridx = 1;
        for (AlunoEntity aluno : projeto.getAlunos()) {
            JLabel lblNome = new JLabel(aluno.getNome());
            lblNome.setFont(fonte);
            lblNome.setForeground(corTexto);
            painel.add(lblNome, gbc);
            gbc.gridy++;
        }

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
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
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