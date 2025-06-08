package org.scam.view.mentor;

import javax.swing.*;
import java.awt.*;

public class VisualizarProjetoMentorView {
    public static void visualizarProjeto() {

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

        // Botões
        JButton btnVisuProjetos = new JButton("Visualizar Projetos");
        JButton btnAtualizarConta = new JButton("Atualizar Conta");
        JButton btnDesativarConta = new JButton("Desativar Conta");
        JButton btnVoltar = new JButton("Voltar");

        Dimension botaoTamanho = new Dimension(165, 30);
        Font fonteBotao = new Font("SansSerif", Font.PLAIN, 14);

        for (JButton btn : new JButton[]{btnVisuProjetos, btnAtualizarConta, btnDesativarConta}) {
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

        // Área de trabalho (Desktop Pane) para janelas internas
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(80, 80, 80));
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        // Ação do botão "Desativar Conta"
        btnDesativarConta.addActionListener(e -> {
            DesativarContaMentorView.abrirFormularioDesativacao(desktopPane);
        });

        // Ação do botão "Voltar" para retornar à tela de login
        btnVoltar.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(frame,
                    "Tem certeza que deseja voltar para a tela de login?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                frame.dispose(); // Fecha a janela atual
                LoginOneMentorView.loginOne(); // Abre a tela de login
            }
        });

        // Ação do botão "Visualizar Projetos"
        btnVisuProjetos.addActionListener(e -> {
            // Simulação de projetos com nome e descrição
            String[][] projetos = {
                    {"Projeto Alpha", "Mentorado: João Silva\nÁrea: IA\nStatus: Em andamento"},
                    {"Projeto Beta", "Mentorado: Maria Souza\nÁrea: Web\nStatus: Concluído"},
                    {"Projeto Gama", "Mentorado: Lucas Lima\nÁrea: Mobile\nStatus: Em planejamento"}
            };

            String[] nomesProjetos = new String[projetos.length];
            for (int i = 0; i < projetos.length; i++) {
                nomesProjetos[i] = projetos[i][0];
            }

            // Mostra a lista de nomes em um JOptionPane com opção de seleção
            String projetoSelecionado = (String) JOptionPane.showInputDialog(
                    frame,
                    "Selecione um projeto para ver os detalhes:",
                    "Projetos",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nomesProjetos,
                    nomesProjetos[0]);

            // Se o usuário escolheu um projeto (não clicou em Cancelar)
            if (projetoSelecionado != null) {
                for (String[] projeto : projetos) {
                    if (projeto[0].equals(projetoSelecionado)) {
                        // Criar o pop-up personalizado
                        JDialog dialog = new JDialog(frame, "Detalhes do Projeto - " + projetoSelecionado, true);
                        dialog.setSize(400, 250);
                        dialog.setLocationRelativeTo(frame);
                        dialog.setLayout(new BorderLayout());

                        // Painel estilizado
                        JPanel painelDialogo = new JPanel();
                        painelDialogo.setBackground(new Color(60, 60, 60));
                        painelDialogo.setLayout(new BorderLayout(10, 10));
                        painelDialogo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                        JLabel tituloDialog = new JLabel(projeto[0], SwingConstants.CENTER);
                        tituloDialog.setFont(new Font("SansSerif", Font.BOLD, 18));
                        tituloDialog.setForeground(Color.WHITE);
                        painelDialogo.add(tituloDialog, BorderLayout.NORTH);

                        JTextArea detalhes = new JTextArea(projeto[1]);
                        detalhes.setFont(new Font("SansSerif", Font.PLAIN, 14));
                        detalhes.setEditable(false);
                        detalhes.setLineWrap(true);
                        detalhes.setWrapStyleWord(true);
                        detalhes.setBackground(new Color(80, 80, 80));
                        detalhes.setForeground(Color.WHITE);
                        detalhes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                        JScrollPane scroll = new JScrollPane(detalhes);
                        scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
                        painelDialogo.add(scroll, BorderLayout.CENTER);

                        JButton fechar = new JButton("Fechar");
                        fechar.setBackground(new Color(0, 128, 66));
                        fechar.setForeground(Color.WHITE);
                        fechar.setFocusPainted(false);
                        fechar.addActionListener(ev -> dialog.dispose());

                        JPanel painelBotao = new JPanel();
                        painelBotao.setBackground(new Color(60, 60, 60));
                        painelBotao.add(fechar);

                        painelDialogo.add(painelBotao, BorderLayout.SOUTH);
                        dialog.add(painelDialogo);

                        dialog.setVisible(true);
                        break;
                    }
                }
            }
        });

        // Exibir a janela
        frame.setVisible(true);
    }
}
