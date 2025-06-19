package org.scam.view.aluno;

import org.scam.controller.AlunoController;
import org.scam.model.entities.MentorEntity;
import org.scam.model.repository.StatusMentor;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class ListMentoresView {

    public static JInternalFrame ListMentores(JDesktopPane desktopPane) {
        // Cria o JInternalFrame
        JInternalFrame internalFrame = new JInternalFrame("Lista de Mentores", true, true, true, true);
        internalFrame.setSize(EstilosPadrao.tamanhoInternalFrame);
        internalFrame.setBackground(EstilosPadrao.cinzaFundo);
        internalFrame.setLayout(new BorderLayout());

        // Remove a barra de título padrão
        BasicInternalFrameUI ui = (BasicInternalFrameUI) internalFrame.getUI();
        ui.setNorthPane(null);

        // Obtém a lista de mentores ativos
        List<MentorEntity> mentores = AlunoController.listarMentoresPorStatus(StatusMentor.ATIVO);

        // Define as colunas da tabela
        String[] colunas = {"ID", "Nome", "Email", "Telefone", "Área de Atuação", "Tempo de Experiencia"};

        // Cria um DefaultTableModel para permitir personalização
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna a tabela não editável
            }
        };

        // Preenche a tabela com os dados dos mentores
        for (MentorEntity mentor : mentores) {
            Object[] rowData = {
                    mentor.getIdMentor(),
                    mentor.getNome(),
                    //mentor.getCpf(),
                    mentor.getEmail(),
                    mentor.getTelefone(),
                    mentor.getAreaDeAtuacao().name(),
                    mentor.getTempoDeExperiencia()
                    //mentor.getStatus().name()
            };
            model.addRow(rowData);
        }

        // Cria a JTable com o modelo personalizado
        JTable tabela = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                // Define cor de fundo para linhas pares e ímpares (opcional)
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? EstilosPadrao.cinzaClaro : EstilosPadrao.cinzaFundo);
                }
                return c;
            }
        };

// Configuração adicional de cores
        tabela.setBackground(EstilosPadrao.cinzaFundo); // Cor de fundo padrão
        tabela.setForeground(Color.WHITE); // Cor do texto
        tabela.setFont(EstilosPadrao.fontePadrao);
        tabela.setRowHeight(25);
        tabela.setSelectionBackground(EstilosPadrao.verdeUni.brighter());
        tabela.setSelectionForeground(Color.WHITE);
        tabela.setGridColor(Color.GRAY);

// Configura o cabeçalho da tabela
        JTableHeader header = tabela.getTableHeader();
        header.setFont(EstilosPadrao.fontePadrao.deriveFont(Font.BOLD));
        header.setBackground(EstilosPadrao.verdeUni);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        // Adiciona a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.getViewport().setBackground(EstilosPadrao.cinzaFundo);
        scrollPane.setBackground(EstilosPadrao.cinzaFundo);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Cria o botão "Fechar" estilizado
        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBackground(EstilosPadrao.verdeUni);
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setFont(EstilosPadrao.fonteBotao);
        btnFechar.setFocusPainted(false);
        btnFechar.setPreferredSize(EstilosPadrao.tamanhoBotao);
        btnFechar.addActionListener(e -> internalFrame.dispose());

        // Painel para o botão (alinhado à direita)
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelBotao.setBackground(EstilosPadrao.cinzaFundo);
        painelBotao.add(btnFechar);

        // Adiciona os componentes ao JInternalFrame
        internalFrame.add(scrollPane, BorderLayout.CENTER);
        internalFrame.add(painelBotao, BorderLayout.SOUTH);

        // Centraliza o JInternalFrame no JDesktopPane
        internalFrame.setLocation(
                (desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                (desktopPane.getHeight() - internalFrame.getHeight()) / 2
        );

        // Torna o frame visível e o adiciona ao desktopPane
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);

        return internalFrame;
    }
}