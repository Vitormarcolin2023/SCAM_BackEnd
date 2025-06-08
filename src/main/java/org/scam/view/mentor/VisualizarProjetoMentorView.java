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

        // Painel superior
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 128, 66));
        topo.setPreferredSize(new Dimension(frame.getWidth(), 60));
        topo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        // Painel central
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

        // Área de trabalho (Desktop Pane) para JInternalFrames
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(80, 80, 80));
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        // AÇÃO DO BOTÃO "Desativar Conta"
        btnDesativarConta.addActionListener(e -> {
            JInternalFrame internalFrame = new JInternalFrame();
            internalFrame.setSize(1055, 585);
            internalFrame.setLayout(new BorderLayout());
            internalFrame.setBorder(BorderFactory.createLineBorder(new Color(37, 36, 36), 2));
            javax.swing.plaf.InternalFrameUI ui = internalFrame.getUI();
            if (ui instanceof javax.swing.plaf.basic.BasicInternalFrameUI basicUI) {
                basicUI.setNorthPane(null); // remove a barra de título
            }

            JPanel painelDialog = new JPanel();
            painelDialog.setBackground(new Color(50, 50, 50));
            painelDialog.setLayout(new BoxLayout(painelDialog, BoxLayout.Y_AXIS));
            painelDialog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel lblTitulo = new JLabel("Desativação de Conta");
            lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
            lblTitulo.setForeground(new Color(0, 200, 100));
            lblTitulo.setAlignmentX(0.0f);
            painelDialog.add(lblTitulo);
            painelDialog.add(Box.createVerticalStrut(20));

            JLabel lblConfirma = new JLabel("Tem certeza que deseja desativar sua conta?");
            lblConfirma.setForeground(Color.WHITE);
            lblConfirma.setFont(new Font("SansSerif", Font.PLAIN, 20));
            lblConfirma.setAlignmentX(0.0f);
            painelDialog.add(lblConfirma);
            painelDialog.add(Box.createVerticalStrut(10));

            JRadioButton btnSim = new JRadioButton("Sim");
            btnSim.setBackground(new Color(50, 50, 50));
            btnSim.setFont(new Font("SansSerif", Font.PLAIN, 18));
            btnSim.setForeground(Color.WHITE);


            ButtonGroup grupo = new ButtonGroup();
            grupo.add(btnSim);


            JPanel opcoes = new JPanel();
            opcoes.setBackground(new Color(50, 50, 50));
            opcoes.setLayout(new BoxLayout(opcoes, BoxLayout.Y_AXIS));
            opcoes.add(btnSim);

            painelDialog.add(opcoes);
            painelDialog.add(Box.createVerticalStrut(20));

            JLabel lblMotivo = new JLabel("Digite o motivo da desativação da conta:");
            lblMotivo.setForeground(Color.WHITE);
            lblMotivo.setFont(new Font("SansSerif", Font.PLAIN, 20));
            painelDialog.add(lblMotivo);
            painelDialog.add(Box.createVerticalStrut(10));
            lblMotivo.setAlignmentX(0.0f);

            JTextArea areaTexto = new JTextArea(5, 30);
            areaTexto.setLineWrap(true);
            areaTexto.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane(areaTexto);
            painelDialog.add(scroll);
            painelDialog.add(Box.createVerticalStrut(20));

            JButton btnConfirmar = new JButton("Confirmar");
            btnConfirmar.setBackground(new Color(0, 200, 100));
            btnConfirmar.setForeground(Color.WHITE);
            btnConfirmar.setFocusPainted(false);
            btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 14));
            btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

            btnConfirmar.addActionListener(ev -> {
                // Validação antes de confirmar
                if (!btnSim.isSelected()) {
                    JOptionPane.showMessageDialog(internalFrame,
                            "Por favor, clique na opção Sim para continuar",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (areaTexto.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(internalFrame,
                            "Por favor, informe o motivo da desativação.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(frame, "Conta desativada com sucesso!");
                internalFrame.dispose();
            });

            painelDialog.add(btnConfirmar);
            internalFrame.add(painelDialog, BorderLayout.CENTER);
            internalFrame.setVisible(true);
            desktopPane.add(internalFrame);
            internalFrame.setLocation((desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                    (desktopPane.getHeight() - internalFrame.getHeight()) / 2);
            internalFrame.moveToFront();
        });


        btnVoltar.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(frame,
                    "Tem certeza que deseja voltar para a tela de login?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                frame.dispose();
                LoginOneMentorView.loginOne();
            }
        });

        // Dados dos projetos com descrição e alunos
        Object[][] projetos = {
                {
                        "Projeto Alpha",
                        "IA",
                        "Em andamento",
                        "Projeto focado no desenvolvimento de algoritmos de inteligência artificial para reconhecimento de padrões.",
                        new String[]{"João Silva", "Carlos Alberto", "Fernanda Lima"}
                },
                {
                        "Projeto Beta",
                        "Web",
                        "Concluído",
                        "Desenvolvimento de uma aplicação web para gerenciamento de tarefas e colaboração em equipe.",
                        new String[]{"Maria Souza", "Bruno Rocha"}
                },
                {
                        "Projeto Gama",
                        "Mobile",
                        "Em planejamento",
                        "Aplicativo móvel para monitoramento de saúde com integração a dispositivos wearable.",
                        new String[]{"Lucas Lima"}
                }
        };

        btnVisuProjetos.addActionListener(e -> {
            String[] nomesProjetos = new String[projetos.length];
            for (int i = 0; i < projetos.length; i++) {
                nomesProjetos[i] = (String) projetos[i][0];
            }

            String projetoSelecionado = (String) JOptionPane.showInputDialog(
                    frame,
                    "Selecione um projeto para ver os detalhes:",
                    "Projetos",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nomesProjetos,
                    nomesProjetos[0]);

            if (projetoSelecionado != null) {
                for (Object[] projeto : projetos) {
                    if (projeto[0].equals(projetoSelecionado)) {
                        String nome = (String) projeto[0];
                        String area = (String) projeto[1];
                        String status = (String) projeto[2];
                        String descricao = (String) projeto[3];
                        String[] alunos = (String[]) projeto[4];

                        // Criar JInternalFrame com detalhes do projeto
                        JInternalFrame internalFrame = new JInternalFrame("Detalhes do Projeto - " + nome, true, true, true, true);
                        internalFrame.setSize(1055, 585);
                        internalFrame.setVisible(true);
                        internalFrame.setLayout(new BorderLayout());
                        internalFrame.setBorder(BorderFactory.createLineBorder(new Color(37, 36, 36), 2));
                        javax.swing.plaf.InternalFrameUI ui = internalFrame.getUI();
                        if (ui instanceof javax.swing.plaf.basic.BasicInternalFrameUI basicUI) {
                            basicUI.setNorthPane(null); // remove a barra de título
                        }
                        internalFrame.getContentPane().setBackground(new Color(60, 60, 60));

                        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
                        painelConteudo.setBackground(new Color(60, 60, 60));
                        painelConteudo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                        JLabel tituloProjeto = new JLabel(nome, SwingConstants.CENTER);
                        tituloProjeto.setFont(new Font("SansSerif", Font.BOLD, 18));
                        tituloProjeto.setForeground(Color.WHITE);
                        painelConteudo.add(tituloProjeto, BorderLayout.NORTH);

                        StringBuilder info = new StringBuilder();
                        info.append("Área: ").append(area).append("\n");
                        info.append("Status: ").append(status).append("\n\n");
                        info.append("Descrição:\n").append(descricao).append("\n\n");
                        info.append("Alunos vinculados:\n");
                        for (String aluno : alunos) {
                            info.append("- ").append(aluno).append("\n");
                        }

                        JTextArea detalhes = new JTextArea(info.toString());
                        detalhes.setFont(new Font("SansSerif", Font.PLAIN, 14));
                        detalhes.setEditable(false);
                        detalhes.setLineWrap(true);
                        detalhes.setWrapStyleWord(true);
                        detalhes.setBackground(new Color(80, 80, 80));
                        detalhes.setForeground(Color.WHITE);
                        detalhes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                        JScrollPane scroll = new JScrollPane(detalhes);
                        scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
                        painelConteudo.add(scroll, BorderLayout.CENTER);

                        internalFrame.add(painelConteudo, BorderLayout.CENTER);

                        desktopPane.add(internalFrame);
                        try {
                            internalFrame.setSelected(true);
                        } catch (java.beans.PropertyVetoException ex) {
                            ex.printStackTrace();
                        }

                        break;
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}