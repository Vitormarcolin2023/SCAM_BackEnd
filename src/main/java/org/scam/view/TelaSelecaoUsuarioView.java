package org.scam.view;

import org.scam.view.aluno.LoginAlunoView;
import org.scam.view.coordenacao.LoginCoordenacaoView;
import org.scam.view.mentor.LoginOneMentorView;
import org.scam.view.mentor.LoginTwoMentorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSelecaoUsuarioView {

    public static void exibirTelaSelecao() {
        // Janela principal
        JFrame frame = new JFrame("Bem-vindo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null); // Centraliza
        frame.setLayout(new BorderLayout());

        // Painel central
        JPanel painelCentral = new JPanel();
        painelCentral.setBackground(new Color(45, 45, 45));
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titulo = new JLabel("BOAS VINDAS AO SAM");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));

        String[] opcoes = {"Aluno", "Mentor", "Coordenação"};
        JComboBox<String> comboBox = new JComboBox<>(opcoes);
        comboBox.setMaximumSize(new Dimension(200, 30));
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setBackground(new Color(0, 200, 100));
        btnContinuar.setForeground(Color.BLACK);
        btnContinuar.setFont(new Font("SansSerif", Font.BOLD, 14));

        painelCentral.add(Box.createVerticalGlue());
        painelCentral.add(titulo);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        painelCentral.add(comboBox);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        painelCentral.add(btnContinuar);
        painelCentral.add(Box.createVerticalGlue());

        frame.add(painelCentral, BorderLayout.CENTER);
        frame.setVisible(true);

        // Ação do botão
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecionado = (String) comboBox.getSelectedItem();

                frame.dispose(); // fecha esta tela

                if ("Aluno".equals(selecionado)) {
                    LoginAlunoView.loginAluno();
                } else if ("Mentor".equals(selecionado)) {
                    LoginOneMentorView.loginOne(); // você ainda vai implementar essa
                } else if ("Coordenação".equals(selecionado)) {
                    LoginCoordenacaoView.loginCoordenacao();
                }


            }
        });
    }
}