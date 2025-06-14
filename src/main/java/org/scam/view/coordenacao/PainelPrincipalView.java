package org.scam.view.coordenacao;

import org.scam.controller.MentorController;
import org.scam.controller.ProjetoController;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.StatusMentor;
import org.scam.view.EstilosPadrao;
import org.scam.view.TelaSelecaoUsuarioView;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelPrincipalView {

    public static void painelCoordenacao() {
        JFrame frame = new JFrame("Sistema de Acompanhamento de Mentorias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // TOPO
        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeSAM);
        topo.setPreferredSize(new Dimension(frame.getWidth(), 60));
        topo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        JLabel titulo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        titulo.setFont(EstilosPadrao.tituloSAM);
        titulo.setForeground(Color.WHITE);
        topo.add(titulo);
        frame.add(topo, BorderLayout.NORTH);

        // PAINEL CENTRAL
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(EstilosPadrao.cinzaFundo);
        frame.add(painelCentral, BorderLayout.CENTER);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(80, 80, 80));
        painelCentral.add(desktopPane, BorderLayout.CENTER);

        // PAINEL DE BOTÕES
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(EstilosPadrao.cinzaClaro);
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        String[] statusConta = {"Litar Mentor", "Ativo", "Inativo"};
        JComboBox<String> comboBox = new JComboBox<>(statusConta);
        comboBox.setMaximumSize(new Dimension(200, 30));
        comboBox.setFont(EstilosPadrao.fontePadrao);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnDesativarMentor = new JButton("Desativar Mentor");
        JButton btnListarProjetos = new JButton("Listar Projetos");
        //JButton btnTelaAprvarMentor = new JButton("Aprovar Mentor"); - A criar
        JButton btnLogof = new JButton("Sair");

        for (JComponent btn : new JComponent[]{comboBox, btnDesativarMentor, btnListarProjetos}) {
            btn.setMaximumSize(EstilosPadrao.tamanhoBotao);
            btn.setPreferredSize(EstilosPadrao.tamanhoBotao);
            btn.setFont(EstilosPadrao.fontePadrao);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelBotoes.add(btn);
            painelBotoes.add(Box.createVerticalStrut(15));
        }


        btnLogof.setMaximumSize(EstilosPadrao.tamanhoBotao);
        btnLogof.setPreferredSize(EstilosPadrao.tamanhoBotao);
        btnLogof.setFont(EstilosPadrao.fontePadrao);
        btnLogof.setBackground(EstilosPadrao.verdeBotaoVoltar);
        btnLogof.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelBotoes.add(btnLogof);
        painelBotoes.add(Box.createVerticalStrut(15));


        painelCentral.add(painelBotoes, BorderLayout.WEST);

        // CONTROLLERS
        EntityManager em = CustomizerFactory.getEntityManager();
        MentorController mentorController = new MentorController(em);
        ProjetoController projetoController = new ProjetoController(em);

        // AÇÕES

        comboBox.addActionListener(e -> {
            String statusSelecionado = (String) comboBox.getSelectedItem();
            if ("Ativo".equals(statusSelecionado)) {
                List<MentorEntity> mentores = mentorController.listarMentoresPorStatus(StatusMentor.ATIVO);
                MentorListView.mostrarMentoresNaTela(mentores, "Mentores Ativos", desktopPane);
            } else if ("Inativo".equals(statusSelecionado)) {
                List<MentorEntity> mentores = mentorController.listarMentoresPorStatus(StatusMentor.DESATIVO);
                MentorListView.mostrarMentoresNaTela(mentores, "Mentores Inativos", desktopPane);
            }
        });

        btnListarProjetos.addActionListener(ev -> {
            List<ProjetoEntity> projetos = projetoController.listarTodosProjetos();
            ProjetoListView.mostrarProjetosNaTela(projetos, "Lista de Projetos", desktopPane);
        });

        btnDesativarMentor.addActionListener(ev -> {
            DesativarMentorView.abrirTelaDesativacao(desktopPane);
        });

        btnLogof.addActionListener(ev ->{
            TelaSelecaoUsuarioView.exibirTelaSelecao();
            frame.dispose();
        });

        frame.setVisible(true);
    }
}