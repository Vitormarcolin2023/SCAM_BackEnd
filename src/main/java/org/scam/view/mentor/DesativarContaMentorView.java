package org.scam.view.mentor;

import org.scam.controller.MentorController;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;

public class DesativarContaMentorView {
    public static void desativarContaM() {
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
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelBotoes.add(btn);
            painelBotoes.add(Box.createVerticalStrut(15));
        }

        btnVoltar.setMaximumSize(EstilosPadrao.tamanhoBotao);
        btnVoltar.setPreferredSize(EstilosPadrao.tamanhoBotao);
        btnVoltar.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        btnVoltar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnVoltar.setBackground(EstilosPadrao.verdeBotaoVoltar);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        painelBotoes.add(Box.createVerticalStrut(30));
        painelBotoes.add(btnVoltar);

        painelCentral.add(painelBotoes, BorderLayout.WEST);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(EstilosPadrao.cinzaClaro);
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        // Corrigido: ação completa para btnVisuProjetos
        btnVisuProjetos.addActionListener(e -> {
            JInternalFrame internalFrame = new JInternalFrame("Detalhes do Projeto", true, true, true, true);
            internalFrame.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

            JLabel tituloProjeto = new JLabel("Nome do Projeto", SwingConstants.CENTER);
            tituloProjeto.setFont(EstilosPadrao.fonteTitulos);
            tituloProjeto.setForeground(Color.WHITE);
            internalFrame.add(tituloProjeto, BorderLayout.CENTER);

            internalFrame.setSize(600, 400);
            internalFrame.setVisible(true);
            desktopPane.add(internalFrame);
            try {
                internalFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException pve) {
                pve.printStackTrace();
            }
        });

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

            JLabel lblConfirma = new JLabel("Tem certeza que deseja desativar sua conta?");
            lblConfirma.setForeground(Color.WHITE);
            lblConfirma.setFont(EstilosPadrao.fontePadrao);
            lblConfirma.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelDialog.add(lblConfirma);
            painelDialog.add(Box.createVerticalStrut(10));

            JRadioButton btnSim = new JRadioButton("Sim");
            btnSim.setBackground(EstilosPadrao.cinzaFundo);
            btnSim.setFont(EstilosPadrao.fontePadrao);
            btnSim.setForeground(Color.WHITE);

            ButtonGroup grupo = new ButtonGroup();
            grupo.add(btnSim);

            JPanel opcoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            opcoes.setBackground(EstilosPadrao.cinzaFundo);
            opcoes.add(btnSim);
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

            btnConfirmar.addActionListener(ev -> {
                if (!btnSim.isSelected()) {
                    JOptionPane.showMessageDialog(internalFrame,
                            "Por favor, clique na opção 'Sim' para continuar.",
                            "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

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
                em.close();

            });


            painelDialog.add(btnConfirmar);
            internalFrame.add(painelDialog);
            internalFrame.setVisible(true);
            desktopPane.add(internalFrame);
            try {
                internalFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException pve) {
                pve.printStackTrace();
            }
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

        frame.setVisible(true);
    }
}
