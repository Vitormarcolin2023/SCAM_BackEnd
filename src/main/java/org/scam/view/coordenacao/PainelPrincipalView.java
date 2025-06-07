package org.scam.view.coordenacao;

import org.scam.controller.MentorController;
import org.scam.controller.ProjetoController;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.StatusMentor;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.util.List;

public class PainelPrincipalView {

    // Cores e fontes padrão
    public static final Color verdeUni = new Color(0, 200, 100);
    public static final Color cinzaFundo = new Color(50, 50, 50);
    public static final Color cinzaClaro = new Color(90, 90, 90);
    public static final Font tituloSAM = new Font("SansSerif", Font.BOLD, 21);
    public static final Font fontePadrao = new Font("SansSerif", Font.PLAIN, 13);
    public static final Font fonteTitulos = new Font("SansSerif", Font.PLAIN, 16);
    public static final Dimension tamanhoBotao = new Dimension(165, 30);

    public static void painelCoordenacao() {
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 128, 66));
        topo.setPreferredSize(new Dimension(frame.getWidth(), 60));
        topo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        titulo.setFont(tituloSAM);
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(cinzaFundo);
        frame.add(painelCentral, BorderLayout.CENTER);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(80, 80, 80));
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(cinzaClaro);
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        String[] statusConta = {"Litar Mentor", "Ativo", "Inativo"};
        JComboBox<String> comboBox = new JComboBox<>(statusConta);
        comboBox.setMaximumSize(new Dimension(200, 30));
        comboBox.setFont(fontePadrao);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnDesativarMentor = new JButton("Desativar Mentor");
        JButton btnListarProjetos = new JButton("Listar Projetos");

        for (JComponent btn : new JComponent[]{comboBox, btnDesativarMentor, btnListarProjetos}) {
            btn.setMaximumSize(tamanhoBotao);
            btn.setPreferredSize(tamanhoBotao);
            btn.setFont(fontePadrao);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelBotoes.add(btn);
            painelBotoes.add(Box.createVerticalStrut(15));
        }

        painelCentral.add(painelBotoes, BorderLayout.WEST);

        // CONTROLLERS MVC
        EntityManager em = CustomizerFactory.getEntityManager();
        MentorController mentorController = new MentorController(em);
        ProjetoController projetoController = new ProjetoController(em);

        comboBox.addActionListener(e -> {
            String statusSelecionado = (String) comboBox.getSelectedItem();
            if ("Ativo".equals(statusSelecionado)) {
                List<MentorEntity> mentores = mentorController.listarMentoresPorStatus(StatusMentor.ATIVO);
                mostrarMentoresNaTela(mentores, "Mentores Ativos", desktopPane);
            } else if ("Inativo".equals(statusSelecionado)) {
                List<MentorEntity> mentores = mentorController.listarMentoresPorStatus(StatusMentor.DESATIVO);
                mostrarMentoresNaTela(mentores, "Mentores Inativos", desktopPane);
            }
        });

        btnListarProjetos.addActionListener(ev -> {
            List<ProjetoEntity> projetos = projetoController.listarTodosProjetos();
            mostrarProjetosNaTela(projetos, "Lista de Projetos", desktopPane);
        });

        btnDesativarMentor.addActionListener(ev -> {
            JInternalFrame internalFrame = new JInternalFrame();
            internalFrame.setSize(1055, 585);
            internalFrame.setLayout(new BorderLayout());
            BasicInternalFrameUI ui = (BasicInternalFrameUI) internalFrame.getUI();
            ui.setNorthPane(null);

            JPanel painelDialog = new JPanel();
            painelDialog.setBackground(cinzaFundo);
            painelDialog.setLayout(new BoxLayout(painelDialog, BoxLayout.Y_AXIS));
            painelDialog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel lblTitulo = new JLabel("Desativação de Conta");
            lblTitulo.setFont(fonteTitulos);
            lblTitulo.setForeground(verdeUni);
            painelDialog.add(lblTitulo);
            painelDialog.add(Box.createVerticalStrut(20));

            JLabel lblConfirma = new JLabel("Tem certeza que deseja desativar sua conta?");
            lblConfirma.setForeground(Color.WHITE);
            lblConfirma.setFont(fonteTitulos);
            painelDialog.add(lblConfirma);
            painelDialog.add(Box.createVerticalStrut(10));

            JRadioButton btnSim = new JRadioButton("Sim");
            JRadioButton btnNao = new JRadioButton("Não");
            btnSim.setBackground(cinzaFundo);
            btnNao.setBackground(cinzaFundo);
            btnSim.setForeground(Color.WHITE);
            btnNao.setForeground(Color.WHITE);
            ButtonGroup grupo = new ButtonGroup();
            grupo.add(btnSim);
            grupo.add(btnNao);

            JPanel opcoes = new JPanel();
            opcoes.setBackground(cinzaFundo);
            opcoes.setLayout(new BoxLayout(opcoes, BoxLayout.Y_AXIS));
            opcoes.add(btnSim);
            opcoes.add(btnNao);
            painelDialog.add(opcoes);
            painelDialog.add(Box.createVerticalStrut(20));

            JLabel lblMotivo = new JLabel("Digite o motivo da desativação da conta:");
            lblMotivo.setForeground(Color.WHITE);
            lblMotivo.setFont(fonteTitulos);
            painelDialog.add(lblMotivo);
            painelDialog.add(Box.createVerticalStrut(10));

            JTextArea areaTexto = new JTextArea(5, 30);
            areaTexto.setLineWrap(true);
            areaTexto.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane(areaTexto);
            painelDialog.add(scroll);
            painelDialog.add(Box.createVerticalStrut(20));

            JButton btnConfirmar = new JButton("Confirmar");
            btnConfirmar.setBackground(verdeUni);
            btnConfirmar.setForeground(Color.WHITE);
            btnConfirmar.setFocusPainted(false);
            btnConfirmar.setFont(fontePadrao);
            btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

            btnConfirmar.addActionListener(ev2 -> {
                if (!btnSim.isSelected() && !btnNao.isSelected()) {
                    JOptionPane.showMessageDialog(internalFrame, "Por favor, selecione uma opção: Sim ou Não.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (areaTexto.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(internalFrame, "Por favor, informe o motivo da desativação.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(frame, "Conta desativada com sucesso!");
                internalFrame.dispose();
            });

            painelDialog.add(btnConfirmar);
            internalFrame.add(painelDialog, BorderLayout.CENTER);
            internalFrame.setVisible(true);
            desktopPane.add(internalFrame);
            internalFrame.setLocation(
                    (desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                    (desktopPane.getHeight() - internalFrame.getHeight()) / 2
            );
            internalFrame.moveToFront();
        });

        frame.setVisible(true);
    }

    private static void mostrarMentoresNaTela(List<MentorEntity> mentores, String titulo, JDesktopPane desktopPane) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) frame.dispose();

        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(900, 400);
        internalFrame.setLayout(new BorderLayout());

        String[] colunas = {"ID", "Nome", "CPF", "Email", "Telefone", "Área de Atuação", "Status"};
        String[][] dados = new String[mentores.size()][colunas.length];

        for (int i = 0; i < mentores.size(); i++) {
            MentorEntity m = mentores.get(i);
            dados[i][0] = String.valueOf(m.getIdMentor());
            dados[i][1] = m.getNome();
            dados[i][2] = m.getCpf();
            dados[i][3] = m.getEmail();
            dados[i][4] = m.getTelefone();
            dados[i][5] = m.getAreaDeAtuacao().name();
            dados[i][6] = m.getStatus().name();
        }

        JTable tabela = new JTable(dados, colunas);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabela.getTableHeader().setBackground(verdeUni);
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.setFont(fontePadrao);
        tabela.setRowHeight(22);

        internalFrame.add(new JScrollPane(tabela), BorderLayout.CENTER);
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        internalFrame.setLocation(
                (desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                (desktopPane.getHeight() - internalFrame.getHeight()) / 2
        );
        internalFrame.moveToFront();
    }

    private static void mostrarProjetosNaTela(List<ProjetoEntity> projetos, String titulo, JDesktopPane desktopPane) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) frame.dispose();

        JInternalFrame internalFrame = new JInternalFrame(titulo, true, true, true, true);
        internalFrame.setSize(1000, 400);
        internalFrame.setLayout(new BorderLayout());

        String[] colunas = {
                "ID", "Nome", "Área", "Curso", "Período",
                "Início", "Fim", "Grupo", "Mentor"
        };
        String[][] dados = new String[projetos.size()][colunas.length];

        for (int i = 0; i < projetos.size(); i++) {
            ProjetoEntity p = projetos.get(i);
            dados[i][0] = String.valueOf(p.getId());
            dados[i][1] = p.getNomeDoProjeto();
            dados[i][2] = p.getAreaDeAtuacao() != null ? p.getAreaDeAtuacao().name() : "-";
            dados[i][3] = p.getCurso() != null ? p.getCurso().name() : "-";
            dados[i][4] = p.getPeriodo();
            dados[i][5] = p.getDataInicioProjeto().toString();
            dados[i][6] = p.getDataFinalProjeto().toString();
            dados[i][7] = String.valueOf(p.getTamanhoDoGrupo());
            dados[i][8] = p.getMentor() != null ? p.getMentor().getNome() : "-";
        }

        JTable tabela = new JTable(dados, colunas);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabela.getTableHeader().setBackground(verdeUni);
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.setFont(fontePadrao);
        tabela.setRowHeight(22);

        internalFrame.add(new JScrollPane(tabela), BorderLayout.CENTER);
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        internalFrame.setLocation(
                (desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                (desktopPane.getHeight() - internalFrame.getHeight()) / 2
        );
        internalFrame.moveToFront();
    }
}