package org.scam.view.coordenacao;

import org.scam.model.entities.MentorEntity;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MentorListView {

    public static void mostrarMentoresNaTela(List<MentorEntity> mentores, String titulo, JDesktopPane desktopPane) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) frame.dispose();

        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(900, 400);
        internalFrame.setLayout(new BorderLayout());
        javax.swing.plaf.InternalFrameUI ui = internalFrame.getUI();
        if (ui instanceof javax.swing.plaf.basic.BasicInternalFrameUI basicUI) {
            basicUI.setNorthPane(null);
        }
        //borda
        internalFrame.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 3));

        String[] colunas = {"ID", "Nome", "CPF", "Email", "Telefone", "Área de Atuação", "Status"};
        String[][] dados = new String[mentores.size()][colunas.length];

        for (int i = 0; i < mentores.size(); i++) {
            MentorEntity m = mentores.get(i);
            dados[i][0] = String.valueOf(m.getIdMentor());
            dados[i][1] = m.getNome();
            dados[i][2] = m.getCpf();
            dados[i][3] = m.getEmail();
            dados[i][4] = m.getTelefone();
            dados[i][5] = m.getAreaDeAtuacao().name();
            dados[i][6] = m.getStatus().name();
        }

        JTable tabela = new JTable(dados, colunas);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabela.getTableHeader().setBackground(EstilosPadrao.verdeUni);
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.setFont(EstilosPadrao.fontePadrao);
        tabela.setRowHeight(22);

        internalFrame.add(new JScrollPane(tabela), BorderLayout.CENTER);
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        internalFrame.setLocation(
                (desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                (desktopPane.getHeight() - internalFrame.getHeight()) / 2
        );
        internalFrame.moveToFront();
    }
}
