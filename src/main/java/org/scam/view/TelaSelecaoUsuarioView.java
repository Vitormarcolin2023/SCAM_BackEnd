package org.scam.view;

import org.scam.view.EstilosPadrao;
import org.scam.view.aluno.LoginAlunoView;
import org.scam.view.coordenacao.LoginCoordenacaoView;
import org.scam.view.mentor.LoginOneMentorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSelecaoUsuarioView {

    public static void exibirTelaSelecao() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bem-vindo ao SAM");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

            JPanel topo = new JPanel();
            topo.setBackground(EstilosPadrao.verdeUni);
            topo.setPreferredSize(new Dimension(0, 50));
            JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
            tituloTopo.setForeground(Color.WHITE);
            tituloTopo.setFont(EstilosPadrao.tituloSAM);
            topo.add(tituloTopo);
            frame.add(topo, BorderLayout.NORTH);

            JPanel containerCentro = new JPanel(new GridBagLayout());
            containerCentro.setBackground(EstilosPadrao.cinzaFundo);
            frame.add(containerCentro, BorderLayout.CENTER);

            JPanel painelSelecao = new JPanel(new GridBagLayout());
            painelSelecao.setBackground(EstilosPadrao.cinzaClaro);
            painelSelecao.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
            containerCentro.add(painelSelecao);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.insets = new Insets(0, 0, 20, 0);

            JLabel titulo = new JLabel("BEM VINDO(A) AO SAM");
            titulo.setFont(EstilosPadrao.fonteTitulos);
            titulo.setForeground(Color.WHITE);
            painelSelecao.add(titulo, gbc);

            String[] opcoes = {"Aluno", "Mentor", "Coordenação"};
            JComboBox<String> comboBox = new JComboBox<>(opcoes);
            comboBox.setFont(EstilosPadrao.fonteBotao);
            comboBox.setPreferredSize(EstilosPadrao.tamanhoBotao);
            painelSelecao.add(comboBox, gbc);

            JButton btnContinuar = new JButton("Continuar");
            btnContinuar.setBackground(EstilosPadrao.verdeUni);
            // CORREÇÃO 1: Cor do texto igual aos outros botões.
            btnContinuar.setForeground(Color.WHITE);
            // CORREÇÃO 2: Fonte sem negrito, igual aos outros botões.
            btnContinuar.setFont(EstilosPadrao.fonteBotao);
            btnContinuar.setPreferredSize(EstilosPadrao.tamanhoBotao);
            gbc.insets = new Insets(10, 0, 0, 0);
            painelSelecao.add(btnContinuar, gbc);

            btnContinuar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selecionado = (String) comboBox.getSelectedItem();
                    frame.dispose();

                    if ("Aluno".equals(selecionado)) {
                        LoginAlunoView.loginAluno();
                    } else if ("Mentor".equals(selecionado)) {
                        LoginOneMentorView.loginOne();
                    } else if ("Coordenação".equals(selecionado)) {
                        LoginCoordenacaoView.loginCoordenacao();
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}