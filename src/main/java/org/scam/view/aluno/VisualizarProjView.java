package org.scam.view.aluno;

import org.scam.controller.AlunoController;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;


public class VisualizarProjView {

        public static JInternalFrame ListProjeto() {

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
            scroll.setBorder(null); // Remove a borda do scroll se desejar
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

                    // Exemplo de ação ao clicar no botão
                    btnDetalhes.addActionListener(e -> {
                        JOptionPane.showMessageDialog(null,
                                "Detalhes do projeto:\n" +
                                        "Nome: " + projeto.getNomeDoProjeto() + "\n" +
                                        "Descrição: " + projeto.getDescricao(), // supondo que exista
                                "Detalhes do Projeto",
                                JOptionPane.INFORMATION_MESSAGE);
                    });

                    painelProjetos.add(painelLabels, BorderLayout.WEST);
                    painelProjetos.add(btnDetalhes, BorderLayout.EAST);

                    painelLista.add(painelProjetos);
                    painelLista.add(Box.createVerticalStrut(10));
                }
            }

            internalFrame.add(painelPrincipal, BorderLayout.CENTER);
            return internalFrame;
        }
    }

