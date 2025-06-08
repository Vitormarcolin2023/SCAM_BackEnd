package org.scam.view.coordenacao;

import org.scam.controller.MentorController;
import org.scam.model.entities.MentorEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.StatusMentor;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DesativarMentorView {

    public static void abrirTelaDesativacao(JDesktopPane desktopPane) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            frame.dispose();
        }

        EntityManager em = CustomizerFactory.getEntityManager();
        MentorController controller = new MentorController(em);
        List<MentorEntity> mentoresAtivos = controller.listarMentoresPorStatus(StatusMentor.ATIVO);

        JInternalFrame frame = new JInternalFrame("Desativar Mentor", true, true, true, true);
        frame.setSize(600, 350);
        frame.setLayout(new BorderLayout());

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(PainelPrincipalView.cinzaFundo);
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblSelecione = new JLabel("Selecione o mentor:");
        lblSelecione.setForeground(Color.WHITE);
        lblSelecione.setFont(PainelPrincipalView.fonteTitulos);
        painel.add(lblSelecione);

        JComboBox<MentorEntity> comboMentores = new JComboBox<>();
        for (MentorEntity mentor : mentoresAtivos) {
            comboMentores.addItem(mentor);
        }
        comboMentores.setFont(PainelPrincipalView.fontePadrao);
        comboMentores.setMaximumSize(new Dimension(400, 30));
        painel.add(comboMentores);
        painel.add(Box.createVerticalStrut(15));

        JLabel lblMotivo = new JLabel("Motivo da desativação:");
        lblMotivo.setForeground(Color.WHITE);
        lblMotivo.setFont(PainelPrincipalView.fonteTitulos);
        painel.add(lblMotivo);

        JTextArea areaMotivo = new JTextArea(4, 30);
        areaMotivo.setLineWrap(true);
        areaMotivo.setWrapStyleWord(true);
        JScrollPane scrollMotivo = new JScrollPane(areaMotivo);
        painel.add(scrollMotivo);
        painel.add(Box.createVerticalStrut(20));

        JButton btnDesativar = new JButton("Desativar");
        btnDesativar.setBackground(PainelPrincipalView.verdeUni);
        btnDesativar.setForeground(Color.WHITE);
        btnDesativar.setFont(PainelPrincipalView.fontePadrao);
        btnDesativar.setFocusPainted(false);
        btnDesativar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(btnDesativar);

        btnDesativar.addActionListener(e -> {
            MentorEntity selecionado = (MentorEntity) comboMentores.getSelectedItem();
            String motivo = areaMotivo.getText().trim();

            if (selecionado == null) {
                JOptionPane.showMessageDialog(frame, "Selecione um mentor.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (motivo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Informe o motivo da desativação.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.desativarMentor(selecionado.getIdMentor(), motivo);
            JOptionPane.showMessageDialog(frame, "Mentor desativado com sucesso.");
            frame.dispose();
        });

        frame.add(painel, BorderLayout.CENTER);
        frame.setVisible(true);
        desktopPane.add(frame);
        frame.setLocation(
                (desktopPane.getWidth() - frame.getWidth()) / 2,
                (desktopPane.getHeight() - frame.getHeight()) / 2
        );
        frame.moveToFront();
    }
}