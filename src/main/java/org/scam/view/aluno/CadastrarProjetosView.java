package org.scam.view.aluno;

import net.bytebuddy.asm.Advice;

import javax.swing.*;
import java.awt.*;

public class CadastrarProjetosView {

        public static void main(String[] args) {
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
            panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
            panel.setPreferredSize(new Dimension(500,600));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10,10,5,10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridx = 0;
            gbc.weightx = 1.0;
            int row = 0;


        // TITULO
            JLabel titulo = new JLabel("Cadastro de Projetos");
            titulo.setForeground(Color.WHITE);
            titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
            gbc.gridy = row++;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(titulo, gbc);


        //NOME PROJETO
            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridwidth = 2;
            gbc.gridy = row++;
            JLabel nomeLabel = new JLabel("Nome do Projeto:");
            nomeLabel.setForeground(Color.WHITE);
            panel.add(nomeLabel,gbc);

            gbc.gridy = row++;
            JTextField nomeField = new JTextField();
            nomeField.setPreferredSize(new Dimension(200,25));
            panel.add(nomeField, gbc);

            //DESCRICAO
            gbc.gridy = row++;
            JLabel descricaoLabel = new JLabel("Descrição:");
            descricaoLabel.setForeground(Color.WHITE);
            panel.add(descricaoLabel,gbc);

            gbc.gridy = row++;
            JTextArea descricaoField = new JTextArea(4, 20); // 5 linhas, 20 colunas
            descricaoField.setLineWrap(true);
            descricaoField.setWrapStyleWord(true);
            descricaoField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            panel.add(descricaoField, gbc);

            //AAREA DE ATUACAO
            gbc.gridy = row++;
            JLabel atuacaoLabel = new JLabel("Área de atuação:");
            atuacaoLabel.setForeground(Color.WHITE);
            panel.add(atuacaoLabel, gbc);

            gbc.gridy = row++;
            String [] opcoesAtuacao = {"AGRÁRIAS E BIOLOGIA", "EDUCAÇÃO", "ENGENHARIA E ARQUITETURA", "GESTÃO","SAÚDE","SOCIAIS","TECNOLOGIA"};
            JComboBox<String> btnAtuacao = new JComboBox<>(opcoesAtuacao);
            panel.add(btnAtuacao,gbc);

            //SELECAO DO CURSO
            gbc.gridy = row++;
            JLabel cursoLabel = new JLabel("Seleção do curso:");
            cursoLabel.setForeground(Color.WHITE);
            panel.add(cursoLabel, gbc);

            gbc.gridy = row++;
            String [] opcoesCurso = { "ADMINISTRADOR", "AGRONOMIA", "ANÁLISE E DESENVOLVIMENTO DE SISTEMAS", "ARQUITETURA E URBANISMO",
                    "BIOMEDICINA", "CIÊNCIAS BIOLÓGICAS", "CIÊNCIAS CONTÁBEIS","DESIGN GRÁFICA DIGITAL", "DIREITO", "EDUCAÇÃO FÍSICA", "ENFERMAGEM",
                    "ENGENHARIA CIVIL", "ENGENHARIA DE SOFTWARE", "ENGENHARIA ELÉTRICA", "ENGENHARIA MECÂNICA", "FARMÁCIA", "FISIOTERAPIA", "MEDICINA VETERINÁRIA",
                    "NUTRIÇÃO", "PEDAGOGIA", "PSICOLOGIA", "PUBLICIDADE E PROPAGANDA"};
            JComboBox<String> btnCurso = new JComboBox<>(opcoesCurso);
            panel.add(btnCurso,gbc);

            //BOTOES
            gbc.gridy = row++;
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

            containerCentro.add(panel);
            telaLogin.add(containerCentro,BorderLayout.CENTER);
            telaLogin.setVisible(true);

    }
}

