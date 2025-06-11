package org.scam.view.mentor;

import org.scam.controller.MentorController;
import org.scam.controller.classes.Mentor;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;

public class TelaInicialMentor {

    public static void telaMentor() {
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeUni);
        topo.setPreferredSize(new Dimension(0, 60));
        topo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        titulo.setFont(EstilosPadrao.tituloSAM);
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(EstilosPadrao.cinzaFundo);
        frame.add(painelCentral, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(EstilosPadrao.cinzaFundo);
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnVisuProjetos = new JButton("Visualizar Projetos");
        JButton btnAtualizarConta = new JButton("Atualizar Conta");
        JButton btnDesativarConta = new JButton("Desativar Conta");
        JButton btnVoltar = new JButton("Voltar");

        for (JButton btn : new JButton[]{btnVisuProjetos, btnAtualizarConta, btnDesativarConta}) {
            btn.setMaximumSize(EstilosPadrao.tamanhoBotao);
            btn.setPreferredSize(EstilosPadrao.tamanhoBotao);
            btn.setFont(EstilosPadrao.fonteBtnAcaoLateral);
            btn.setBackground(EstilosPadrao.cinzaClaro);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelBotoes.add(btn);
            painelBotoes.add(Box.createVerticalStrut(15));
        }

        String[] opcoesReuniao = {"Reuniões", "Visualizar Reuniões", "Agendar Reunião"};
        JComboBox<String> btnReuniao = new JComboBox<>(opcoesReuniao);
        btnReuniao.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        btnReuniao.setMaximumSize(EstilosPadrao.tamanhoBotao);
        btnReuniao.setPreferredSize(EstilosPadrao.tamanhoBotao);
        btnReuniao.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelBotoes.add(btnReuniao);
        painelBotoes.add(Box.createVerticalStrut(15));

        btnReuniao.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });

        btnVoltar.setMaximumSize(EstilosPadrao.tamanhoBotao);
        btnVoltar.setPreferredSize(EstilosPadrao.tamanhoBotao);
        btnVoltar.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        btnVoltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnVoltar.setBackground(EstilosPadrao.verdeBotaoVoltar);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        painelBotoes.add(btnVoltar);
        painelBotoes.add(Box.createVerticalStrut(15));

        painelCentral.add(painelBotoes, BorderLayout.WEST);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(EstilosPadrao.cinzaClaro);
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        btnVisuProjetos.addActionListener(e -> {
            Object[][] projetos = {
                    {"Projeto Alpha", "IA", "Em andamento", "Algoritmos de IA.", new String[]{"João Silva", "Carlos Alberto"}},
                    {"Projeto Beta", "Web", "Concluído", "App web de tarefas.", new String[]{"Maria Souza", "Bruno Rocha"}},
            };

            String[] nomesProjetos = new String[projetos.length];
            for (int i = 0; i < projetos.length; i++) {
                nomesProjetos[i] = (String) projetos[i][0];
            }

            String projetoSelecionado = (String) JOptionPane.showInputDialog(
                    frame, "Selecione um projeto para ver os detalhes:", "Projetos",
                    JOptionPane.PLAIN_MESSAGE, null, nomesProjetos, nomesProjetos[0]);

            if (projetoSelecionado != null) {
                for (Object[] projeto : projetos) {
                    if (projeto[0].equals(projetoSelecionado)) {
                        String nome = (String) projeto[0];
                        String area = (String) projeto[1];
                        String status = (String) projeto[2];
                        String descricao = (String) projeto[3];
                        String[] alunos = (String[]) projeto[4];

                        JInternalFrame internalFrame = new JInternalFrame("Detalhes do Projeto", true, true, true, true);
                        internalFrame.setSize(1055, 585);
                        internalFrame.setVisible(true);
                        internalFrame.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 2));
                        internalFrame.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

                        if (internalFrame.getUI() instanceof javax.swing.plaf.basic.BasicInternalFrameUI ui) {
                            ui.setNorthPane(null);
                        }

                        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
                        painelConteudo.setBackground(EstilosPadrao.cinzaFundo);
                        painelConteudo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                        JLabel tituloProjeto = new JLabel(nome, SwingConstants.CENTER);
                        tituloProjeto.setFont(EstilosPadrao.fonteTitulos);
                        tituloProjeto.setForeground(Color.WHITE);
                        painelConteudo.add(tituloProjeto, BorderLayout.NORTH);

                        JPanel formulario = new JPanel(new GridBagLayout());
                        formulario.setBackground(EstilosPadrao.cinzaFundo);
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.insets = new Insets(5, 5, 5, 5);
                        gbc.anchor = GridBagConstraints.WEST;

                        gbc.gridx = 0; gbc.gridy = 0;
                        JLabel lblArea = new JLabel("Área:");
                        lblArea.setForeground(Color.WHITE);
                        lblArea.setFont(EstilosPadrao.fonteBotao);
                        formulario.add(lblArea, gbc);

                        gbc.gridx = 1;
                        JTextField txtArea = new JTextField(area, 30);
                        txtArea.setEditable(false);
                        txtArea.setFont(EstilosPadrao.fontePadrao);
                        formulario.add(txtArea, gbc);

                        gbc.gridx = 0; gbc.gridy++;
                        JLabel lblStatus = new JLabel("Status:");
                        lblStatus.setForeground(Color.WHITE);
                        lblStatus.setFont(EstilosPadrao.fonteBotao);
                        formulario.add(lblStatus, gbc);

                        gbc.gridx = 1;
                        JTextField txtStatus = new JTextField(status, 30);
                        txtStatus.setEditable(false);
                        txtStatus.setFont(EstilosPadrao.fontePadrao);
                        formulario.add(txtStatus, gbc);

                        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
                        JLabel lblDescricao = new JLabel("Descrição:");
                        lblDescricao.setForeground(Color.WHITE);
                        lblDescricao.setFont(EstilosPadrao.fonteBotao);
                        formulario.add(lblDescricao, gbc);

                        gbc.gridy++;
                        JTextArea txtDescricao = new JTextArea(descricao, 5, 40);
                        txtDescricao.setEditable(false);
                        txtDescricao.setFont(EstilosPadrao.fontePadrao);
                        formulario.add(new JScrollPane(txtDescricao), gbc);

                        gbc.gridy++;
                        JLabel lblAlunos = new JLabel("Alunos vinculados:");
                        lblAlunos.setForeground(Color.WHITE);
                        lblAlunos.setFont(EstilosPadrao.fonteBotao);
                        formulario.add(lblAlunos, gbc);

                        gbc.gridy++;
                        JTextArea txtAlunos = new JTextArea(String.join("\n", alunos), 5, 40);
                        txtAlunos.setEditable(false);
                        txtAlunos.setFont(EstilosPadrao.fontePadrao);
                        formulario.add(new JScrollPane(txtAlunos), gbc);

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

        btnAtualizarConta.addActionListener(e -> EdicaoMentorPasso1View.exibirTelaEdicaoPasso1());

        //btn desativar conta
        btnDesativarConta.addActionListener(e -> {
            JInternalFrame internalFrame = new JInternalFrame("Desativar Conta", false, false, false, false);
            internalFrame.setSize(1055, 585);
            internalFrame.setLayout(new BorderLayout());
            internalFrame.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 2));

            javax.swing.plaf.InternalFrameUI ui = internalFrame.getUI();
            if (ui instanceof javax.swing.plaf.basic.BasicInternalFrameUI basicUI) {
                basicUI.setNorthPane(null);
            }

            JPanel painelDialog = new JPanel();
            painelDialog.setBackground(EstilosPadrao.cinzaFundo);
            painelDialog.setLayout(new BoxLayout(painelDialog, BoxLayout.Y_AXIS));
            painelDialog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel lblTitulo = new JLabel("Desativação de Conta");
            lblTitulo.setFont(EstilosPadrao.fonteTitulos);
            lblTitulo.setForeground(EstilosPadrao.verdeUni);
            lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelDialog.add(lblTitulo);
            painelDialog.add(Box.createVerticalStrut(20));


            JPanel opcoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            opcoes.setBackground(EstilosPadrao.cinzaFundo);
            opcoes.setAlignmentX(Component.LEFT_ALIGNMENT);

            painelDialog.add(opcoes);
            painelDialog.add(Box.createVerticalStrut(20));

            JLabel lblMotivo = new JLabel("Digite o motivo da desativação da conta:");
            lblMotivo.setForeground(Color.WHITE);
            lblMotivo.setFont(EstilosPadrao.fontePadrao);
            lblMotivo.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelDialog.add(lblMotivo);
            painelDialog.add(Box.createVerticalStrut(10));

            JTextArea areaTexto = new JTextArea(5, 30);
            areaTexto.setLineWrap(true);
            areaTexto.setWrapStyleWord(true);
            areaTexto.setFont(EstilosPadrao.fontePadrao);
            JScrollPane scroll = new JScrollPane(areaTexto);
            scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelDialog.add(scroll);
            painelDialog.add(Box.createVerticalStrut(20));

            JButton btnConfirmar = new JButton("Confirmar");
            btnConfirmar.setBackground(EstilosPadrao.verdeUni);
            btnConfirmar.setForeground(Color.WHITE);
            btnConfirmar.setFocusPainted(false);
            btnConfirmar.setFont(EstilosPadrao.fonteBotao);
            btnConfirmar.setAlignmentX(Component.LEFT_ALIGNMENT);

            //btn para confirmar a desativação

            btnConfirmar.addActionListener(ev -> {

                String motivo = areaTexto.getText().trim();
                if (motivo.isEmpty()) {
                    JOptionPane.showMessageDialog(internalFrame,
                            "Por favor, informe o motivo da desativação.",
                            "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String emailMentor = Sessao.getEmail(); // CERTO

                if (emailMentor == null) {
                    JOptionPane.showMessageDialog(internalFrame,
                            "Erro: Nenhum mentor logado.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                EntityManager em = CustomizerFactory.getEntityManager();

                try {
                    MentorController controller = new MentorController(em);

                    boolean desativado = controller.desativarMentorPorEmail(emailMentor, motivo);

                    if (desativado) {
                        JOptionPane.showMessageDialog(internalFrame,
                                "Conta desativada com sucesso!",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(internalFrame,
                                "Erro ao desativar conta.",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                        // Não fecha a janela para o usuário tentar novamente
                    }
                } catch (Exception ex) { // nome diferente para evitar conflito
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(internalFrame,
                            "Erro inesperado: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (em.isOpen()) {
                        em.close();
                    }
                }
            });
            //fim do action event do btnConfirmar

            painelDialog.add(btnConfirmar);
            internalFrame.add(painelDialog);
            internalFrame.setVisible(true);
            desktopPane.add(internalFrame);
            try {
                internalFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException pve) {
                pve.printStackTrace();
            }
        }); //fim do action event do btnDesativarConta


        btnVoltar.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(frame,
                    "Tem certeza que deseja voltar para a tela de login?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                frame.dispose();
                LoginOneMentorView.loginOne();
            }
        });

        btnReuniao.addActionListener(e -> {
            JInternalFrame internalFrame = null;
            int posicaoBtnReuniao = btnReuniao.getSelectedIndex();
            if (posicaoBtnReuniao == 1) {
                internalFrame = VisualizarReunioesMtView.visualizarReunioesMentor();
            } else if (posicaoBtnReuniao == 2) {
                internalFrame = AgendarReuniaoMtView.agendarReuniaoMentor();
            }

            if (internalFrame != null) {
                desktopPane.add(internalFrame);
                internalFrame.setLocation((desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                        (desktopPane.getHeight() - internalFrame.getHeight()) / 2);
                internalFrame.moveToFront();
                internalFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }
}