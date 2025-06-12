package org.scam.view.aluno;

import org.scam.view.EstilosPadrao;
import org.scam.view.TelaSelecaoUsuarioView;
import org.scam.view.mentor.LoginOneMentorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalAlunoView {

    public static void principal() {
        // Garante que a UI seja criada na Thread de Despacho de Eventos (EDT) - Boa prática do Swing
        SwingUtilities.invokeLater(() -> {
            // 1. CRIAÇÃO DA JANELA PRINCIPAL
            JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLayout(new BorderLayout());

            // 2. PAINEL SUPERIOR (TÍTULO)
            JPanel topo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
            topo.setBackground(EstilosPadrao.verdeSAM);
            topo.setPreferredSize(new Dimension(0, 60)); // A largura é gerenciada pelo BorderLayout

            JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
            titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
            titulo.setForeground(Color.WHITE);
            topo.add(titulo);

            // 3. PAINEL CENTRAL E DESKTOP PANE PARA JANELAS INTERNAS
            JPanel painelCentral = new JPanel(new BorderLayout());
            painelCentral.setBackground(EstilosPadrao.cinzaFundo);

            JDesktopPane desktopPane = new JDesktopPane();
            desktopPane.setBackground(EstilosPadrao.cinzaClaro);
            painelCentral.add(desktopPane, BorderLayout.CENTER);

            // 4. PAINEL LATERAL DE BOTÕES
            JPanel painelBotoes = new JPanel();
            painelBotoes.setBackground(EstilosPadrao.cinzaFundo);
            painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
            painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            // --- CRIAÇÃO DOS BOTÕES ---
            JButton cadastrarProjetoBtn = new JButton("Cadastrar Projeto");
            JButton gerenciarProjetoBtn = new JButton("Gerenciar Projeto");
            JButton listarMentoresBtn = new JButton("Listar Mentores");

            String[] opcoesReuniao = {"Reuniões", "Visualizar Reuniões", "Agendar Reunião"};
            JComboBox<String> reuniaoCombo = new JComboBox<>(opcoesReuniao);

            JButton voltarBtn = new JButton("Voltar");

            // --- ESTILIZAÇÃO E ADIÇÃO DOS BOTÕES DE AÇÃO ---
            // Loop para estilizar e adicionar os botões principais de forma consistente
            for (JButton btn : new JButton[]{cadastrarProjetoBtn, gerenciarProjetoBtn, listarMentoresBtn}) {
                btn.setFont(EstilosPadrao.fonteBtnAcaoLateral);
                btn.setMaximumSize(EstilosPadrao.tamanhoBotao);
                btn.setPreferredSize(EstilosPadrao.tamanhoBotao);
                btn.setForeground(Color.BLACK);
                btn.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinha todos à esquerda
                painelBotoes.add(btn);
                painelBotoes.add(Box.createVerticalStrut(15)); // Espaçamento
            }

            // botão de reuniões - criar os demais aqui e depois adicionar no for para adicionar na barra lateralMore actions
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
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    label.setHorizontalAlignment(SwingConstants.CENTER); // centraliza itens da lista
                    return label;
                }
            });

            // Estilização especial para o botão "Voltar"
            voltarBtn.setMaximumSize(EstilosPadrao.tamanhoBotao);
            voltarBtn.setPreferredSize(EstilosPadrao.tamanhoBotao);
            voltarBtn.setFont(EstilosPadrao.fonteBtnAcaoLateral);
            voltarBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
            voltarBtn.setBackground(EstilosPadrao.verdeBotaoVoltar);
            voltarBtn.setForeground(Color.WHITE);
            voltarBtn.setFocusPainted(false);
            // Adicionar mais espaço antes do botão voltar para separá-lo dos outros
            //painelBotoes.add(Box.createVerticalGlue()); // Empurra o botão "Voltar" para baixo - melhor para cima
            painelBotoes.add(voltarBtn);


            // 5. AÇÕES DOS BOTÕES (ACTION LISTENERS)
            cadastrarProjetoBtn.addActionListener(e -> {
                // Coloque o código para abrir a tela de cadastro de projetos aqui
                // Ex: CadastrarProjetosView cadastrarProjetosView = new CadastrarProjetosView();
                //     desktopPane.add(cadastrarProjetosView);
                //     cadastrarProjetosView.setVisible(true);
                JOptionPane.showMessageDialog(frame, "Funcionalidade 'Cadastrar Projeto' a ser implementada.");
            });

            gerenciarProjetoBtn.addActionListener(e -> {
                // A sua lógica original abria um JFrame, o que não é ideal com JDesktopPane.
                // O correto é abrir um JInternalFrame.
                GerenciarProjetosView gerenciarProjetosView = new GerenciarProjetosView();
                desktopPane.add(gerenciarProjetosView);
                // Centralizar a janela interna
                gerenciarProjetosView.setLocation(
                        (desktopPane.getWidth() - gerenciarProjetosView.getWidth()) /2,
                        (desktopPane.getHeight() - gerenciarProjetosView.getHeight()) /2
                );
                gerenciarProjetosView.setVisible(true);
            });

            listarMentoresBtn.addActionListener(e -> {
                JInternalFrame mentorFrame = ListMentoresView.ListMentores();
                desktopPane.add(mentorFrame);
                // Centralizar a janela interna
                mentorFrame.setLocation(
                        (desktopPane.getWidth() - mentorFrame.getWidth()) / 2,
                        (desktopPane.getHeight() - mentorFrame.getHeight()) / 2
                );
                try {
                    mentorFrame.setSelected(true); // Traz a janela para frente
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
            });

            btnReuniao.addActionListener(e -> {
                int selectedIndex = btnReuniao.getSelectedIndex();
                JInternalFrame internalFrame = null; // Inicia como nulo

                // Verifica qual opção foi selecionada para criar a janela correspondente
                if (selectedIndex == 1) { // "Visualizar Reuniões"
                    internalFrame = VisualizarReunioesAluView.visualizarReunioes();
                } else if (selectedIndex == 2) { // "Agendar Reunião"
                    internalFrame = AgendaReuniaoAluView.cadastrarReuniao();
                }

                // Só adiciona ao desktop se uma janela válida foi criada
                if (internalFrame != null) {
                    desktopPane.add(internalFrame);
                    internalFrame.setLocation(
                            (desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                            (desktopPane.getHeight() - internalFrame.getHeight()) / 2
                    );
                    internalFrame.setVisible(true);
                    internalFrame.moveToFront(); // Garante que a nova janela fique na frente
                }
            });

            voltarBtn.addActionListener(e -> {
                int confirmar = JOptionPane.showConfirmDialog(frame,
                        "Tem certeza que deseja voltar para a tela de login?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION);

                if (confirmar == JOptionPane.YES_OPTION) {
                    frame.dispose(); // Fecha a janela atual
                    TelaSelecaoUsuarioView.exibirTelaSelecao(); // Abre a tela de login
                }
            });

            // 6. MONTAGEM FINAL DA JANELA
            frame.add(topo, BorderLayout.NORTH);
            frame.add(painelCentral, BorderLayout.CENTER);
            frame.add(painelBotoes, BorderLayout.WEST);

            // Exibir a Janela
            frame.setVisible(true);
        });
    }
}