package org.scam.view.aluno;

import javax.swing.*;
import java.awt.*;

public class CadastrarProjetosView {
    public static void cadastrarProjeto (){
        JFrame telaLogin = new JFrame("Cadastro de Projetos");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setLayout(new BorderLayout());
        telaLogin.getContentPane().setBackground(new Color(30, 30, 30));

        // FAIXA SUPEIOR VERDE
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 200, 100));
        topo.setPreferredSize(new Dimension(telaLogin.getWidth(), 50));

        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(new Font("SansSerif", Font.BOLD, 21));
        topo.add(tituloTopo);
        telaLogin.add(topo, BorderLayout.NORTH);

        // CONTAINER CENTRAL
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(new Color(30, 30, 30)); // fundo escuro total

            // PAINEL DO FORMULARIO
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(new Color(45, 45, 45));
            panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
           // panel.setPreferredSize(new Dimension(1000,900));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10,10,5,10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            int row = 0;


        // TITULO
            gbc.gridx = 0;
            gbc.gridy = row++;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            JLabel titulo = new JLabel("Cadastro de Projetos");
            titulo.setForeground(Color.WHITE);
            titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
            panel.add(titulo, gbc);

            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.WEST;

           //NOME PROJETO

            gbc.gridy = row++;
            gbc.gridx = 0;
            JLabel nomeLabel = new JLabel("Nome do Projeto:");
            nomeLabel.setForeground(Color.WHITE);
            panel.add(nomeLabel,gbc);

            gbc.gridx = 1;
            JTextField nomeField = new JTextField();
            nomeField.setPreferredSize(new Dimension(20,30));
            panel.add(nomeField,gbc);

            //DESCRICAO
            gbc.gridy = row++;
            JLabel descricaoLabel = new JLabel("Descrição:");
            descricaoLabel.setForeground(Color.WHITE);
            panel.add(descricaoLabel,gbc);

            gbc.gridx = 1;
            JTextArea descricaoField = new JTextArea(4, 20); // 5 linhas, 20 colunas
            descricaoField.setLineWrap(true);
            descricaoField.setWrapStyleWord(true);
            descricaoField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            panel.add(descricaoField, gbc);

            //AAREA DE ATUACAO
            gbc.gridy = row++;
            gbc.gridx = 0;
            JLabel atuacaoLabel = new JLabel("Área de atuação:");
            atuacaoLabel.setForeground(Color.WHITE);
            panel.add(atuacaoLabel, gbc);

            gbc.gridx = 1;
            String [] opcoesAtuacao = {"AGRÁRIAS E BIOLOGIA", "EDUCAÇÃO", "ENGENHARIA E ARQUITETURA", "GESTÃO","SAÚDE","SOCIAIS","TECNOLOGIA"};
            JComboBox<String> btnAtuacao = new JComboBox<>(opcoesAtuacao);
            panel.add(btnAtuacao,gbc);

            //SELECAO DO CURSO
            gbc.gridy = row++;
            gbc.gridx = 0;
            JLabel cursoLabel = new JLabel("Seleção do curso:");
            cursoLabel.setForeground(Color.WHITE);
            panel.add(cursoLabel, gbc);

            gbc.gridx = 1;
            String [] opcoesCurso = { "ADMINISTRADOR", "AGRONOMIA", "ANÁLISE E DESENVOLVIMENTO DE SISTEMAS", "ARQUITETURA E URBANISMO",
                    "BIOMEDICINA", "CIÊNCIAS BIOLÓGICAS", "CIÊNCIAS CONTÁBEIS","DESIGN GRÁFICA DIGITAL", "DIREITO", "EDUCAÇÃO FÍSICA", "ENFERMAGEM",
                    "ENGENHARIA CIVIL", "ENGENHARIA DE SOFTWARE", "ENGENHARIA ELÉTRICA", "ENGENHARIA MECÂNICA", "FARMÁCIA", "FISIOTERAPIA", "MEDICINA VETERINÁRIA",
                    "NUTRIÇÃO", "PEDAGOGIA", "PSICOLOGIA", "PUBLICIDADE E PROPAGANDA"};
            JComboBox<String> btnCurso = new JComboBox<>(opcoesCurso);
            panel.add(btnCurso,gbc);

            gbc.gridy = row++;
            gbc.gridx = 0;
            JLabel dataInicioLabel = new JLabel("Data de início do projeto:");
            dataInicioLabel.setForeground(Color.WHITE);
            panel.add(dataInicioLabel,gbc);

           gbc.gridx = 1;
           JTextField dataInicioField = new JTextField();
           dataInicioField.setPreferredSize(new Dimension(20,30));
           panel.add(dataInicioField,gbc);

           //PERIODO
           gbc.gridy = row++;
           gbc.gridx = 0;
           JLabel periodoLabel = new JLabel("Período:");
           periodoLabel.setForeground(Color.WHITE);
           panel.add(periodoLabel,gbc);

           gbc.gridx = 1;
           String[] opcoesPeriodo = {
                   "1º Período", "2º Período", "3º Período", "4º Período", "5º Período",
                   "6º Período", "7º Período", "8º Período", "9º Período", "10º Período"
           };
           JComboBox<String> btnPeriodo = new JComboBox<>(opcoesPeriodo);
            panel.add(btnPeriodo,gbc);

            //RA ds Alunos
            gbc.gridy = row++;
            gbc.gridx = 0;
            JLabel raLabel = new JLabel("RA dos alunos:");
            raLabel.setForeground(Color.WHITE);
            panel.add(raLabel,gbc);

            gbc.gridx = 1;
            JTextField raField = new JTextField();
            raField.setPreferredSize(new Dimension(20,30));
            panel.add(raField,gbc);

            //BOTOES
            gbc.gridy = row++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(20,10,10,10);

            JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            botoesPanel.setBackground(new Color(45,45,45));

            JButton voltarButton = new JButton("Voltar");
            JButton cadastroButton = new JButton("Cadastre-se");


            cadastroButton.setPreferredSize(new Dimension(150,30));
            voltarButton.setPreferredSize(new Dimension(150,30));

            botoesPanel.add(voltarButton);
            botoesPanel.add(cadastroButton);

            panel.add(botoesPanel,gbc);

            // Adicionar o panel ao containerCentro com constraints para expandir
            GridBagConstraints gbcContainer = new GridBagConstraints();
            gbcContainer.gridx = 0;
            gbcContainer.gridy = 0;
            gbcContainer.weightx = 1.0;
            gbcContainer.weighty = 1.0;
            gbcContainer.fill = GridBagConstraints.BOTH;

        containerCentro.add(panel, gbcContainer);

            containerCentro.add(panel);
            telaLogin.add(containerCentro,BorderLayout.CENTER);
            telaLogin.setVisible(true);

    }


}

