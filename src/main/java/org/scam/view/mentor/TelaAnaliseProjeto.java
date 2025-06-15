package org.scam.view.coordenacao;

import org.scam.controller.ProjetoAprovacaoController;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.view.EstilosPadrao;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TelaAnaliseProjeto {

    public static void mostrarProjetosPendentes(JDesktopPane desktopPane, MentorEntity mentorLogado) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            frame.dispose();
        }

        JInternalFrame internalFrame = new JInternalFrame("Análise de Projetos Pendentes", false, true, false, false);
        internalFrame.setSize(1000, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setBorder(new LineBorder(EstilosPadrao.verdeUni, 2));

        EntityManager em = org.scam.model.repository.CustomizerFactory.getEntityManager();
        ProjetoAprovacaoController controller = new ProjetoAprovacaoController(em);

        List<ProjetoEntity> projetos = controller.listarProjetosPendentes(mentorLogado);

        String[] colunas = {"ID", "Nome do Projeto", "Descrição", "Alunos", "Ações"};
        Object[][] dados = new Object[projetos.size()][colunas.length];

        for (int i = 0; i < projetos.size(); i++) {
            ProjetoEntity p = projetos.get(i);
            dados[i][0] = p.getId();
            dados[i][1] = p.getNomeDoProjeto();
            dados[i][2] = p.getDescricao();
            String nomesAlunos = p.getAlunos().stream()
                    .map(AlunoEntity::getNome)
                    .collect(Collectors.joining(", "));
            dados[i][3] = nomesAlunos;
            dados[i][4] = p;
        }

        String[] colunasVisuais = {"ID", "Nome do Projeto", "Descrição", "Alunos"};

        JTable tabela = new JTable(new AbstractTableModel() {
            public int getRowCount() { return dados.length; }
            public int getColumnCount() { return colunasVisuais.length; }
            public Object getValueAt(int r, int c) { return dados[r][c]; }
            public String getColumnName(int c) { return colunasVisuais[c]; }
            public boolean isCellEditable(int r, int c) { return false; }
        });

        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabela.getTableHeader().setBackground(EstilosPadrao.verdeUni);
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.setFont(EstilosPadrao.fontePadrao);
        tabela.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(tabela);

        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelAcoes.setBackground(EstilosPadrao.cinzaClaro);
        painelAcoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAprovar = new JButton("Aprovar Projeto");
        JButton btnNegar = new JButton("Recusar Projeto");

        btnAprovar.setBackground(EstilosPadrao.verdeUni);
        btnAprovar.setForeground(Color.WHITE);
        btnAprovar.setFont(EstilosPadrao.fonteBotao);
        btnAprovar.setPreferredSize(EstilosPadrao.tamanhoBotao);

        btnNegar.setBackground(EstilosPadrao.vermelhoBotao);
        btnNegar.setForeground(Color.WHITE);
        btnNegar.setFont(EstilosPadrao.fonteBotao);
        btnNegar.setPreferredSize(EstilosPadrao.tamanhoBotao);

        painelAcoes.add(btnAprovar);
        painelAcoes.add(Box.createHorizontalStrut(10));
        painelAcoes.add(btnNegar);

        btnAprovar.addActionListener(ev -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                ProjetoEntity projeto = (ProjetoEntity) dados[linha][4];
                controller.aprovarProjeto(projeto);
                JOptionPane.showMessageDialog(internalFrame, "Projeto aprovado!");
                internalFrame.dispose();
                mostrarProjetosPendentes(desktopPane, mentorLogado);
            } else {
                JOptionPane.showMessageDialog(internalFrame, "Selecione um projeto.");
            }
        });

        btnNegar.addActionListener(ev -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                ProjetoEntity projeto = (ProjetoEntity) dados[linha][4];
                String motivo = JOptionPane.showInputDialog(internalFrame, "Motivo da recusa:");
                if (motivo != null && !motivo.trim().isEmpty()) {
                    controller.negarProjeto(projeto, motivo);
                    JOptionPane.showMessageDialog(internalFrame, "Projeto recusado.");
                    internalFrame.dispose();
                    mostrarProjetosPendentes(desktopPane, mentorLogado);
                } else if (motivo != null) {
                    JOptionPane.showMessageDialog(internalFrame, "O motivo é obrigatório.");
                }
            } else {
                JOptionPane.showMessageDialog(internalFrame, "Selecione um projeto.");
            }
        });

        internalFrame.add(scroll, BorderLayout.CENTER);
        internalFrame.add(painelAcoes, BorderLayout.SOUTH);
        desktopPane.add(internalFrame);

        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = internalFrame.getSize();
        int x = (desktopSize.width - frameSize.width) / 2;
        int y = (desktopSize.height - frameSize.height) / 2;
        internalFrame.setLocation(x, y);

        internalFrame.setVisible(true);
    }
}