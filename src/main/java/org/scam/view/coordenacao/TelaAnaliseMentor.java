package org.scam.view.coordenacao;

import org.scam.controller.MentorAprovacaoController;
import org.scam.model.entities.MentorEntity;
import org.scam.view.EstilosPadrao;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.util.List;

public class TelaAnaliseMentor {

    public static void mostrarMentoresPendentes(JDesktopPane desktopPane) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) frame.dispose();

        JInternalFrame internalFrame = new JInternalFrame("Análise de Mentores Pendentes", false, true, false, false);
        internalFrame.setSize(1000, 400);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setBorder(new LineBorder(EstilosPadrao.verdeUni, 2));



        EntityManager em = org.scam.model.repository.CustomizerFactory.getEntityManager();
        MentorAprovacaoController controller = new MentorAprovacaoController(em);
        List<MentorEntity> mentores = controller.listarMentoresPendentes();

        String[] colunas = {
                "ID", "Nome", "Email", "Telefone", "Área", "Experiência", "Vínculo", "Ações"
        };

        Object[][] dados = new Object[mentores.size()][colunas.length];

        for (int i = 0; i < mentores.size(); i++) {
            MentorEntity m = mentores.get(i);
            dados[i][0] = m.getIdMentor();
            dados[i][1] = m.getNome();
            dados[i][2] = m.getEmail();
            dados[i][3] = m.getTelefone();
            dados[i][4] = m.getAreaDeAtuacao() != null ? m.getAreaDeAtuacao().name() : "-";
            dados[i][5] = m.getTempoDeExperiencia();
            dados[i][6] = m.getTipoDeVinculo();
            dados[i][7] = m; // Guarda o objeto mentor para ação posterior
        }

        String[] colunasVisuais = {
                "ID", "Nome", "Email", "Telefone", "Área", "Experiência", "Vínculo"
        };

        JTable tabela = new JTable(new javax.swing.table.AbstractTableModel() {
            @Override
            public int getRowCount() {
                return dados.length;
            }

            @Override
            public int getColumnCount() {
                return colunasVisuais.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return dados[rowIndex][columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                return colunasVisuais[column];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });

        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabela.getTableHeader().setBackground(EstilosPadrao.verdeUni);
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.setFont(EstilosPadrao.fontePadrao);
        tabela.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(tabela);

        JPanel painelAcoes = new JPanel();
        painelAcoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
        painelAcoes.setBackground(EstilosPadrao.cinzaClaro);
        painelAcoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAprovar = new JButton("Aprovar Selecionado");
        JButton btnNegar = new JButton("Negar Selecionado");

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
                MentorEntity mentor = (MentorEntity) dados[linha][7];
                controller.aprovarMentor(mentor);
                JOptionPane.showMessageDialog(internalFrame, "Mentor aprovado com sucesso!");
                internalFrame.dispose();
                mostrarMentoresPendentes(desktopPane);
            } else {
                JOptionPane.showMessageDialog(internalFrame, "Selecione um mentor para aprovar.");
            }
        });

        btnNegar.addActionListener(ev -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                MentorEntity mentor = (MentorEntity) dados[linha][7];
                String motivo = JOptionPane.showInputDialog(internalFrame, "Motivo da negação:");
                if (motivo != null && !motivo.trim().isEmpty()) {
                    controller.negarMentor(mentor, motivo);
                    JOptionPane.showMessageDialog(internalFrame, "Mentor negado com sucesso!");
                    internalFrame.dispose();
                    mostrarMentoresPendentes(desktopPane);
                } else {
                    JOptionPane.showMessageDialog(internalFrame, "Motivo é obrigatório para negação.");
                }
            } else {
                JOptionPane.showMessageDialog(internalFrame, "Selecione um mentor para negar.");
            }
        });

        internalFrame.add(scroll, BorderLayout.CENTER);
        internalFrame.add(painelAcoes, BorderLayout.SOUTH);
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        internalFrame.setLocation(
                (desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                (desktopPane.getHeight() - internalFrame.getHeight()) / 2
        );
        internalFrame.moveToFront();
    }
}
