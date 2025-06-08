package org.scam.view.aluno;

import org.scam.view.EstilosPadrao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;



public class ListMentoresView {
    public static JInternalFrame ListMentores() {

        String[] colunas = {"Nome", "Tempo como Mentor", "Experiência Profissional", "E-mail"};
        Object[][] dados = {
                {"Amanda Ribeiro Silva", "3 anos", "8 anos", "amanda.silva@mentorhub.com"},
                {"Bruno Carvalho Mendes", "5 anos", "12 anos", "bruno.mendes@mentorhub.com"},
                {"Camila Torres Azevedo", "2 anos", "6 anos", "camila.azevedo@mentorhub.com"},
                {"Diego Martins Souza", "4 anos", "10 anos", "diego.souza@mentorhub.com"},
                {"Elaine Costa Barreto", "1 ano", "5 anos", "elaine.barreto@mentorhub.com"},
                {"Felipe Lima Cardoso", "6 anos", "15 anos", "felipe.cardoso@mentorhub.com"},
                {"Gabriela Rocha Tavares", "3 anos", "9 anos", "gabriela.tavares@mentorhub.com"},
                {"Henrique Dias Oliveira", "2 anos", "7 anos", "henrique.oliveira@mentorhub.com"},
                {"Isabella Nunes Ferreira", "4 anos", "11 anos", "isabella.ferreira@mentorhub.com"},
                {"João Pedro Almeida Leite", "5 anos", "13 anos", "joao.leite@mentorhub.com"}

        };
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(30);

        //ESTILO DO CABECALHO
        JTableHeader header = tabela.getTableHeader();
        header.setBackground(EstilosPadrao.verdeUni);
        header.setFont(new Font("Arial", Font.BOLD,14));


        JScrollPane scrollPane = new JScrollPane(tabela);

        JInternalFrame frame = new JInternalFrame("Lista de Mentores",true,true,true,true);
        frame.setSize(800,400);
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane,BorderLayout.CENTER);
        frame.setVisible(true);

        return frame;
    }
}
