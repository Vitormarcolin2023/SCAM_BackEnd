package org.scam.view.aluno;

import org.scam.view.EstilosPadrao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;



public class VisualizarProjView {
    public static JInternalFrame ListProjeto() {

        String[] colunas = {"ID", "Nome do projeto", "Descrição"};
        Object[][] dados = {
                {"P001", "MonitorIA – Sistema de Monitoramento com IA", "App com IA para monitoramento de desempenho estudantil.", "",},
                {"P002", "EcoTrack – Rastreador de Pegada de Carbono", "Plataforma para calcular e reduzir pegada de carbono."},
                {"P003", "MentorLink – Rede de Apoio Acadêmico", "Sistema que conecta alunos a mentores acadêmicos."}
        };
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(40);

        //ESTILO DO CABECALHO
        JTableHeader header = tabela.getTableHeader();
        header.setBackground(EstilosPadrao.verdeUni);
        header.setFont(new Font("Arial", Font.BOLD,14));


        JScrollPane scrollPane = new JScrollPane(tabela);

        JInternalFrame frame = new JInternalFrame("Lista de Projetos",true,true,true,true);
        frame.setSize(800,400);
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane,BorderLayout.CENTER);
        frame.setVisible(true);

        return frame;
    }
}
