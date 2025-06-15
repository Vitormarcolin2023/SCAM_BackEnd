package org.scam.view.aluno;

import javax.swing.*;
import java.awt.*;

public class GerenciarProjetosView extends JFrame {
    public static void GerenciarProjeto() {

        // Janela principal
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // Painel superior (barra verde com título)
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 128, 66));
        topo.setPreferredSize(new Dimension(frame.getWidth(), 60));
        topo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        // Painel central (conteúdo principal)
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(new Color(60, 60, 60));
        frame.add(painelCentral, BorderLayout.CENTER);

        // Painel lateral com botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(45, 45, 45));
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Janelas Internas
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(60, 60, 60));
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        // Botões
        JButton btnVisuProjetos = new JButton("Visualizar Projetos");
        JButton btnEditar = new JButton("Editar Projetos");
        JButton btnVoltar = new JButton("Voltar");

        Dimension botaoTamanho = new Dimension(165, 30);
        Font fonteBotao = new Font("SansSerif", Font.PLAIN, 14);

        for (JButton btn : new JButton[]{btnVisuProjetos, btnEditar, btnVoltar}) {
            btn.setMaximumSize(botaoTamanho);
            btn.setPreferredSize(botaoTamanho);
            btn.setFont(fonteBotao);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelBotoes.add(btn);
            painelBotoes.add(Box.createVerticalStrut(15));
        }
        // Botão "Voltar" com estilo especial
        btnVoltar.setMaximumSize(botaoTamanho);
        btnVoltar.setPreferredSize(botaoTamanho);
        btnVoltar.setFont(fonteBotao);
        btnVoltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnVoltar.setBackground(new Color(0, 128, 66));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        painelBotoes.add(btnVoltar);
        painelBotoes.add(Box.createVerticalStrut(15));

        painelCentral.add(painelBotoes, BorderLayout.WEST);

       /* btnVisuProjetos.addActionListener(e -> {
            JInternalFrame mentorFrame = VisualizarProjView.ListProjeto();
            desktopPane.add(mentorFrame);
            mentorFrame.setLocation(
                    (desktopPane.getWidth() - mentorFrame.getWidth()) / 2,
                    (desktopPane.getHeight() - mentorFrame.getHeight()) / 2
            );
            try {
                mentorFrame.setSelected(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });*/

        btnEditar.addActionListener(e -> {
            JInternalFrame editarFrame = EditarCadProjetoView.EditarProjeto();
            desktopPane.add(editarFrame);
            editarFrame.setLocation(
                    (desktopPane.getWidth() - editarFrame.getWidth()) / 2,
                    (desktopPane.getHeight() - editarFrame.getHeight()) / 2
            );
            try {
                editarFrame.setSelected(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });




        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> GerenciarProjetosView.GerenciarProjeto());
    }
}