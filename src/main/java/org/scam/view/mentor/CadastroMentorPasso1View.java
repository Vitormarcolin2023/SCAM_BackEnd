package org.scam.view.mentor;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.classes.TipoMentor;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;

public class CadastroMentorPasso1View {

    public static void exibirTelaCadastroPasso1() {
        JFrame telaCadastro = new JFrame("Cadastro de Mentor - Passo 1 de 2");
        telaCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaCadastro.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaCadastro.setLayout(new BorderLayout());
        telaCadastro.getContentPane().setBackground(new Color(30, 30, 30));

        // Topo
        JPanel topo = new JPanel();
        topo.setBackground(new Color(0, 200, 100));
        topo.setPreferredSize(new Dimension(telaCadastro.getWidth(), 50));
        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(new Font("SansSerif", Font.BOLD, 21));
        topo.add(tituloTopo);
        telaCadastro.add(topo, BorderLayout.NORTH);

        // Container Central
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(new Color(30, 30, 30));
        telaCadastro.add(containerCentro, BorderLayout.CENTER);

        // Painel do Formulário
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(45, 45, 45));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        containerCentro.add(panelFormulario);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título do Painel
        JLabel titulo = new JLabel("CADASTRO - DADOS PESSOAIS E PROFISSIONAIS");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(titulo, gbc);

        // Reset GridBagConstraints
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // --- Campos do Formulário ---
        int y = 1; // Linha atual do grid

        // Nome
        panelFormulario.add(createLabel("Nome Completo:"), createGbc(0, y, GridBagConstraints.WEST));
        JTextField nomeField = new JTextField(20);
        panelFormulario.add(nomeField, createGbc(1, y++, GridBagConstraints.EAST));

        // CPF
        panelFormulario.add(createLabel("CPF (só números):"), createGbc(0, y, GridBagConstraints.WEST));
        JTextField cpfField = new JTextField(20);
        panelFormulario.add(cpfField, createGbc(1, y++, GridBagConstraints.EAST));

        // E-mail
        panelFormulario.add(createLabel("E-mail:"), createGbc(0, y, GridBagConstraints.WEST));
        JTextField emailField = new JTextField(20);
        panelFormulario.add(emailField, createGbc(1, y++, GridBagConstraints.EAST));

        // Senha
        panelFormulario.add(createLabel("Senha:"), createGbc(0, y, GridBagConstraints.WEST));
        JPasswordField senhaField = new JPasswordField(20);
        panelFormulario.add(senhaField, createGbc(1, y++, GridBagConstraints.EAST));

        // Telefone
        panelFormulario.add(createLabel("Telefone:"), createGbc(0, y, GridBagConstraints.WEST));
        JTextField telefoneField = new JTextField(20);
        panelFormulario.add(telefoneField, createGbc(1, y++, GridBagConstraints.EAST));

        // Tempo de Experiência
        panelFormulario.add(createLabel("Tempo de Experiência:"), createGbc(0, y, GridBagConstraints.WEST));
        JTextField experienciaField = new JTextField(20);
        panelFormulario.add(experienciaField, createGbc(1, y++, GridBagConstraints.EAST));

        // Tipo de Vínculo
        panelFormulario.add(createLabel("Tipo de Vínculo (CLT, PJ...):"), createGbc(0, y, GridBagConstraints.WEST));
        JTextField vinculoField = new JTextField(20);
        panelFormulario.add(vinculoField, createGbc(1, y++, GridBagConstraints.EAST));

        // Área de Atuação
        panelFormulario.add(createLabel("Área de Atuação:"), createGbc(0, y, GridBagConstraints.WEST));
        JComboBox<AreaDeAtuacao> areaAtuacaoBox = new JComboBox<>(AreaDeAtuacao.values());
        panelFormulario.add(areaAtuacaoBox, createGbc(1, y++, GridBagConstraints.EAST));

        // Tipo de Mentor
        panelFormulario.add(createLabel("Tipo de Mentor:"), createGbc(0, y, GridBagConstraints.WEST));
        JComboBox<TipoMentor> tipoMentorBox = new JComboBox<>(TipoMentor.values());
        panelFormulario.add(tipoMentorBox, createGbc(1, y++, GridBagConstraints.EAST));

        // --- Botões ---
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotoes.setBackground(new Color(45, 45, 45));
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelFormulario.add(panelBotoes, gbc);

        JButton voltarButton = new JButton("Voltar");
        JButton proximoButton = new JButton("Próximo");
        proximoButton.setBackground(new Color(0, 200, 100));

        panelBotoes.add(voltarButton);
        panelBotoes.add(proximoButton);

        // --- Ações dos Botões ---
        voltarButton.addActionListener(e -> {
            telaCadastro.dispose();
            LoginOneMentorView.loginOne(); // Volta para a tela de login/cadastro inicial
        });

        proximoButton.addActionListener(e -> {
            // Validações do backend antes de prosseguir
            EntityManager em = CustomizerFactory.getEntityManager();
            MentorRepository mentorRepo = new MentorRepository(em);

            String cpf = cpfField.getText();
            String email = emailField.getText();
            String telefone = telefoneField.getText();

            if (cpf.isEmpty() || email.isEmpty() || nomeField.getText().isEmpty() || new String(senhaField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (mentorRepo.existePorCpf(cpf)) {
                JOptionPane.showMessageDialog(telaCadastro, "Já existe um mentor cadastrado com este CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
                em.close();
                return;
            }
            if (mentorRepo.existePorEmail(email)) {
                JOptionPane.showMessageDialog(telaCadastro, "Já existe um mentor cadastrado com este E-mail.", "Erro", JOptionPane.ERROR_MESSAGE);
                em.close();
                return;
            }
            if (mentorRepo.existePorTelefone(telefone)) {
                JOptionPane.showMessageDialog(telaCadastro, "Já existe um mentor cadastrado com este Telefone.", "Erro", JOptionPane.ERROR_MESSAGE);
                em.close();
                return;
            }

            em.close();

            // Se tudo estiver OK, avança para o próximo passo
            telaCadastro.dispose();
            CadastroMentorPasso2View.exibirTelaCadastroPasso2(
                    nomeField.getText(),
                    cpf,
                    email,
                    new String(senhaField.getPassword()),
                    telefone,
                    experienciaField.getText(),
                    vinculoField.getText(),
                    (AreaDeAtuacao) areaAtuacaoBox.getSelectedItem(),
                    (TipoMentor) tipoMentorBox.getSelectedItem()
            );
        });

        telaCadastro.setVisible(true);
    }

    // Helper para criar JLabels com a formatação padrão
    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        return label;
    }

    // Helper para criar GridBagConstraints de forma mais limpa
    private static GridBagConstraints createGbc(int x, int y, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
        gbc.insets = new Insets(5, 5, 5, 5);
        if (x == 1) { // Componente de input
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
        }
        return gbc;
    }
}