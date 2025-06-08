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

        // Faixa superior verde
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 200, 100));
        topo.setPreferredSize(new Dimension(telaLogin.getWidth(), 50));

        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(new Font("SansSerif", Font.BOLD, 21));
        topo.add(tituloTopo);
        telaLogin.add(topo, BorderLayout.NORTH);

        // Container central com GridBagLayout para centralizar
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(new Color(30, 30, 30)); // fundo escuro total

        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.insets = new Insets(8, 10, 8, 10);
        panelGbc.fill = GridBagConstraints.BOTH;
        panelGbc.anchor = GridBagConstraints.CENTER;

        // Painel cinza com botões
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45, 45, 45));
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setPreferredSize(new Dimension(400, 600));

        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(10,10,10,10);
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.weightx = 1.0;
        formGbc.gridx = 0;
        formGbc.gridwidth = 2;


        // Título
            JPanel tituloPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tituloPanel.setBackground(new Color(45,45,45));

            JLabel titulo = new JLabel("Cadastro de Projetos");
            titulo.setForeground(Color.WHITE);
            titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
            tituloPanel.add(titulo);
        formGbc.gridx = 0;
        formGbc.gridy = 0;
            formGbc.gridwidth = 2;
        formGbc.insets = new Insets(0,0,20,0);
        panel.add(titulo, formGbc);

        formGbc.insets = new Insets(5,10,5,10);
        formGbc.anchor = GridBagConstraints.WEST;


        //Nome do Projeto
            formGbc.gridy++;
            formGbc.gridx = 0;
            JLabel nomeLabel = new JLabel("Nome do Projeto:");
            nomeLabel.setForeground(Color.WHITE);
            panel.add(nomeLabel,formGbc);

            formGbc.gridx++;
            formGbc.gridx = 0;
            JTextField nomeField = new JTextField();
            panel.add(nomeField, formGbc);

            //Descricao
            formGbc.gridy++;
            formGbc.gridx = 0;
            JLabel descricaoLabel = new JLabel("Descrição:");
            descricaoLabel.setForeground(Color.WHITE);
            panel.add(descricaoLabel,formGbc);

            formGbc.gridx++;
            formGbc.gridx = 0;
            JTextField descricaoField = new JTextField();
            panel.add(descricaoField, formGbc);

            //Area de atuacao
            formGbc.gridy++;

            JLabel atuacaoLabel = new JLabel("Área de atuação:");
            atuacaoLabel.setForeground(Color.WHITE);
            panel.add(atuacaoLabel, formGbc);

            formGbc.gridy++;
            String [] opcoesAtuacao = {"AGRÁRIAS E BIOLOGIA", "EDUCAÇÃO", "ENGENHARIA E ARQUITETURA", "GESTÃO","SAÚDE","SOCIAIS","TECNOLOGIA"};
            JComboBox<String> btnAtuacao = new JComboBox<>(opcoesAtuacao);
            panel.add(btnAtuacao,formGbc);

            //Botoes
            formGbc.gridy++;
            formGbc.insets = new Insets(20,10,10,10);
            JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            botoesPanel.setBackground(new Color(45,45,45));

            JButton cadastroButton = new JButton("Cadastre-se");
            JButton voltarButton = new JButton("Voltar");

            cadastroButton.setPreferredSize(new Dimension(150,30));
            voltarButton.setPreferredSize(new Dimension(150,30));

            botoesPanel.add(voltarButton);
            botoesPanel.add(cadastroButton);
            panel.add(botoesPanel,formGbc);

            containerCentro.add(panel,panelGbc);
            telaLogin.add(containerCentro,BorderLayout.CENTER);
            telaLogin.setVisible(true);

    }
}

