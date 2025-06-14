package org.scam.view.coordenacao;

import org.scam.model.entities.ProjetoEntity;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static org.scam.view.coordenacao.PainelPrincipalView.*;

public class ProjetoListView {

    public static void mostrarProjetosNaTela(List<ProjetoEntity> projetos, String titulo, JDesktopPane desktopPane) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) frame.dispose();

        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(1000, 400);
        internalFrame.setLayout(new BorderLayout());
        javax.swing.plaf.InternalFrameUI ui = internalFrame.getUI();
        if (ui instanceof javax.swing.plaf.basic.BasicInternalFrameUI basicUI) {
            basicUI.setNorthPane(null);
        }
        //borda
        internalFrame.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 3));

        String[] colunas = {
                "ID", "Nome", "Área", "Curso", "Período",
                "Início", "Fim", "Grupo", "Mentor"
        };
        String[][] dados = new String[projetos.size()][colunas.length];

        for (int i = 0; i < projetos.size(); i++) {
            ProjetoEntity p = projetos.get(i);
            dados[i][0] = String.valueOf(p.getId());
            dados[i][1] = p.getNomeDoProjeto();
            dados[i][2] = p.getAreaDeAtuacao() != null ? p.getAreaDeAtuacao().name() : "-";
            dados[i][3] = p.getCurso() != null ? p.getCurso().name() : "-";
            dados[i][4] = p.getPeriodo();
            dados[i][5] = p.getDataInicioProjeto().toString();
            dados[i][6] = p.getDataFinalProjeto().toString();
            dados[i][7] = String.valueOf(p.getTamanhoDoGrupo());
            dados[i][8] = p.getMentor() != null ? p.getMentor().getNome() : "-";
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
