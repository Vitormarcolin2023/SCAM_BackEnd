package org.scam.view.mentor;

import org.scam.controller.MentorController;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;

public class DesativarContaMentorView {

    /**
     * Cria e retorna um JInternalFrame para o processo de desativação de conta do mentor.
     * @param parentFrame A JFrame principal da aplicação, usada para fechar o app em caso de sucesso.
     * @return Um JInternalFrame contendo o formulário de desativação.
     */
    public static JInternalFrame exibirTelaDesativarConta(JFrame parentFrame) {
        JInternalFrame internalFrame = new JInternalFrame("Desativar Conta", false, false, false, false);
        internalFrame.setSize(1055, 585);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 2));

        // Remove a barra de título da janela interna
        if (internalFrame.getUI() instanceof javax.swing.plaf.basic.BasicInternalFrameUI ui) {
            ui.setNorthPane(null);
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

        // ActionListener para confirmar a desativação
        btnConfirmar.addActionListener(ev -> {
            String motivo = areaTexto.getText().trim();
            if (motivo.isEmpty()) {
                JOptionPane.showMessageDialog(internalFrame,
                        "Por favor, informe o motivo da desativação.",
                        "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String mentorLogadoEmail = Sessao.getMentorLogado().getEmail();

            if (mentorLogadoEmail == null) {
                JOptionPane.showMessageDialog(internalFrame,
                        "Erro: Nenhum mentor logado.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            EntityManager em = CustomizerFactory.getEntityManager();
            try {
                MentorController controller = new MentorController(em);
                boolean desativado = controller.desativarMentorPorEmail(mentorLogadoEmail, motivo);

                if (desativado) {
                    JOptionPane.showMessageDialog(internalFrame,
                            "Conta desativada com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    parentFrame.dispose(); // Fecha a janela principal da aplicação
                } else {
                    JOptionPane.showMessageDialog(internalFrame,
                            "Erro ao desativar conta.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
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

        painelDialog.add(btnConfirmar);
        internalFrame.add(painelDialog);
        internalFrame.setVisible(true);

        return internalFrame;
    }
}