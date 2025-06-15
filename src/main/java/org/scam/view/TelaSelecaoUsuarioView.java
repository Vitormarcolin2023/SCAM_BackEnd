package org.scam.view;

import org.scam.view.aluno.LoginAlunoView;
import org.scam.view.coordenacao.LoginCoordenacaoView;
import org.scam.view.mentor.LoginOneMentorView;

import javax.swing.*;
import java.awt.*;

public class TelaSelecaoUsuarioView {

    public static void exibirTelaSelecao() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bem-vindo ao SAM");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

            JPanel topo = new JPanel();
            topo.setBackground(EstilosPadrao.verdeSAM);
            topo.setPreferredSize(new Dimension(0, 50));
            JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
            tituloTopo.setForeground(Color.WHITE);
            tituloTopo.setFont(EstilosPadrao.tituloSAM);
            topo.add(tituloTopo);
            frame.add(topo, BorderLayout.NORTH);

            JPanel containerCentro = new JPanel(new GridBagLayout());
            containerCentro.setBackground(EstilosPadrao.cinzaFundo);
            GridBagConstraints gbc = new GridBagConstraints();
            frame.add(containerCentro, BorderLayout.CENTER);

            JPanel painelSelecao = new JPanel(new GridBagLayout());
            painelSelecao.setBackground(EstilosPadrao.cinzaClaro);
            painelSelecao.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
            containerCentro.add(painelSelecao, gbc);

            GridBagConstraints panelGbc = new GridBagConstraints();
            panelGbc.gridx = 0;
            panelGbc.gridy = 0;
            panelGbc.insets = new Insets(5, 0, 10, 0);

            JLabel titulo = new JLabel("BEM-VINDO(A) AO SAM");
            titulo.setFont(EstilosPadrao.fonteTitulos);
            titulo.setForeground(Color.WHITE);
            painelSelecao.add(titulo, panelGbc);

            panelGbc.gridy++;
            painelSelecao.add(Box.createVerticalStrut(15), panelGbc);

            panelGbc.gridy++;
            String[] opcoes = {"Aluno", "Mentor", "Coordenação"};
            JComboBox<String> comboBox = new JComboBox<>(opcoes);
            comboBox.setFont(EstilosPadrao.fontePadrao);
            comboBox.setPreferredSize(new Dimension(250, 35));
            painelSelecao.add(comboBox, panelGbc);

            panelGbc.gridy++;
            JButton btnContinuar = new JButton("Continuar");
            btnContinuar.setBackground(EstilosPadrao.verdeUni);
            btnContinuar.setForeground(Color.BLACK);
            btnContinuar.setFont(EstilosPadrao.fonteBotao);
            btnContinuar.setPreferredSize(EstilosPadrao.tamanhoBotao);
            painelSelecao.add(btnContinuar, panelGbc);

            btnContinuar.addActionListener(e -> {
                String selecionado = (String) comboBox.getSelectedItem();
                frame.dispose();

                if ("Aluno".equals(selecionado)) {
                    LoginAlunoView.loginAluno();
                } else if ("Mentor".equals(selecionado)) {
                    LoginOneMentorView.loginOne();
                } else if ("Coordenação".equals(selecionado)) {
                    LoginCoordenacaoView.loginCoordenacao();
                }
            });

            frame.setVisible(true);
        });
    }
}