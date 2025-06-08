package org.scam.view.mentor;

import javax.swing.*;
import java.awt.*;

public class VisualizarProjetoMentorView {
    public static void visualizarProjeto() {

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
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
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

        JButton btnVisuProjetos = new JButton("Visualizar Projetos");
        JButton btnAtualizarConta = new JButton("Atualizar Conta");
        JButton btnDesativarConta = new JButton("Desativar Conta");
        JButton btnVoltar = new JButton("Voltar");

        Dimension botaoTamanho = new Dimension(180, 35);
        Font fonteBotao = new Font("SansSerif", Font.PLAIN, 16);

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

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(80, 80, 80));
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        btnDesativarConta.addActionListener(e -> {
            DesativarContaMentorView.desativarContaM();
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

        Object[][] projetos = {
                {"Projeto Alpha", "IA", "Em andamento", "Algoritmos de IA para reconhecimento de padrões.", new String[]{"João Silva", "Carlos Alberto", "Fernanda Lima"}},
                {"Projeto Beta", "Web", "Concluído", "App web para gerenciamento de tarefas.", new String[]{"Maria Souza", "Bruno Rocha"}},
                {"Projeto Gama", "Mobile", "Em planejamento", "App de saúde com wearables.", new String[]{"Lucas Lima"}}
        };

        Font fonteCampos = new Font("SansSerif", Font.PLAIN, 16);
        Font fonteLabel = new Font("SansSerif", Font.BOLD, 16);

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

                        JInternalFrame internalFrame = new JInternalFrame("Detalhes do Projeto - " + nome, true, true, true, true);
                        internalFrame.setSize(1055, 585);
                        internalFrame.setVisible(true);
                        internalFrame.setBorder(BorderFactory.createLineBorder(new Color(37, 36, 36), 2));
                        internalFrame.getContentPane().setBackground(new Color(60, 60, 60));

                        javax.swing.plaf.InternalFrameUI ui = internalFrame.getUI();
                        if (ui instanceof javax.swing.plaf.basic.BasicInternalFrameUI basicUI) {
                            basicUI.setNorthPane(null);
                        }

                        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
                        painelConteudo.setBackground(new Color(60, 60, 60));
                        painelConteudo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                        JLabel tituloProjeto = new JLabel(nome, SwingConstants.CENTER);
                        tituloProjeto.setFont(new Font("SansSerif", Font.BOLD, 24));
                        tituloProjeto.setForeground(Color.WHITE);
                        painelConteudo.add(tituloProjeto, BorderLayout.NORTH);

                        JPanel formulario = new JPanel(new GridBagLayout());
                        formulario.setBackground(new Color(60, 60, 60));
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.insets = new Insets(5, 5, 5, 5);
                        gbc.anchor = GridBagConstraints.WEST;

                        // Área
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        JLabel lblArea = new JLabel("Área:");
                        lblArea.setForeground(Color.WHITE);
                        lblArea.setFont(fonteLabel);
                        formulario.add(lblArea, gbc);

                        gbc.gridx = 1;
                        JTextField txtArea = new JTextField(area, 30);
                        txtArea.setEditable(false);
                        txtArea.setFont(fonteCampos);
                        formulario.add(txtArea, gbc);

                        // Status
                        gbc.gridx = 0;
                        gbc.gridy++;
                        JLabel lblStatus = new JLabel("Status:");
                        lblStatus.setForeground(Color.WHITE);
                        lblStatus.setFont(fonteLabel);
                        formulario.add(lblStatus, gbc);

                        gbc.gridx = 1;
                        JTextField txtStatus = new JTextField(status, 30);
                        txtStatus.setEditable(false);
                        txtStatus.setFont(fonteCampos);
                        formulario.add(txtStatus, gbc);

                        // Descrição
                        gbc.gridx = 0;
                        gbc.gridy++;
                        gbc.gridwidth = 2;
                        JLabel lblDescricao = new JLabel("Descrição:");
                        lblDescricao.setForeground(Color.WHITE);
                        lblDescricao.setFont(fonteLabel);
                        formulario.add(lblDescricao, gbc);

                        gbc.gridy++;
                        JTextArea txtDescricao = new JTextArea(descricao, 5, 40);
                        txtDescricao.setLineWrap(true);
                        txtDescricao.setWrapStyleWord(true);
                        txtDescricao.setEditable(false);
                        txtDescricao.setFont(fonteCampos);
                        JScrollPane scrollDesc = new JScrollPane(txtDescricao);
                        formulario.add(scrollDesc, gbc);

                        // Alunos
                        gbc.gridy++;
                        JLabel lblAlunos = new JLabel("Alunos vinculados:");
                        lblAlunos.setForeground(Color.WHITE);
                        lblAlunos.setFont(fonteLabel);
                        formulario.add(lblAlunos, gbc);

                        gbc.gridy++;
                        JTextArea txtAlunos = new JTextArea(String.join("\n", alunos), 5, 40);
                        txtAlunos.setLineWrap(true);
                        txtAlunos.setWrapStyleWord(true);
                        txtAlunos.setEditable(false);
                        txtAlunos.setFont(fonteCampos);
                        JScrollPane scrollAlunos = new JScrollPane(txtAlunos);
                        formulario.add(scrollAlunos, gbc);

                        painelConteudo.add(formulario, BorderLayout.CENTER);
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
