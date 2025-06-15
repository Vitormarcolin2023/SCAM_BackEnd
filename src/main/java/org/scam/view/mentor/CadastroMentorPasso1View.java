package org.scam.view.mentor;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.classes.TipoMentor;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.view.EstilosPadrao;
import org.scam.view.mentor.LoginOneMentorView;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class CadastroMentorPasso1View {

    public static void exibirTelaCadastroPasso1() {
        final JFrame telaCadastro = new JFrame("Cadastro de Mentor - Passo 1 de 2");
        telaCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaCadastro.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaCadastro.setLayout(new BorderLayout());
        telaCadastro.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

        // Topo
        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeSAM);
        topo.setPreferredSize(new Dimension(telaCadastro.getWidth(), 50));
        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(EstilosPadrao.tituloSAM);
        topo.add(tituloTopo);
        telaCadastro.add(topo, BorderLayout.NORTH);

        // Container Central
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(EstilosPadrao.cinzaFundo);
        telaCadastro.add(containerCentro, BorderLayout.CENTER);

        // Painel do Formulário
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(EstilosPadrao.cinzaClaro);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        containerCentro.add(panelFormulario);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título do Painel
        JLabel titulo = new JLabel("CADASTRO - DADOS PESSOAIS E PROFISSIONAIS");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(EstilosPadrao.fonteTitulos);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // --- Campos do Formulário ---
        int y = 1;

        panelFormulario.add(createLabel("Nome Completo:"), createGbc(0, y, GridBagConstraints.WEST));
        final JTextField nomeField = new JTextField(20);
        panelFormulario.add(nomeField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("CPF:"), createGbc(0, y, GridBagConstraints.WEST));
        final JFormattedTextField cpfField = createCpfField();
        panelFormulario.add(cpfField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("E-mail:"), createGbc(0, y, GridBagConstraints.WEST));
        final JTextField emailField = new JTextField(20);
        panelFormulario.add(emailField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Senha:"), createGbc(0, y, GridBagConstraints.WEST));
        final JPasswordField senhaField = new JPasswordField(20);
        panelFormulario.add(senhaField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Telefone:"), createGbc(0, y, GridBagConstraints.WEST));
        final JTextField telefoneField = new JTextField(20);
        panelFormulario.add(telefoneField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Tempo de Experiência:"), createGbc(0, y, GridBagConstraints.WEST));
        final JTextField experienciaField = new JTextField(20);
        panelFormulario.add(experienciaField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Tipo de Vínculo (CLT, PJ...):"), createGbc(0, y, GridBagConstraints.WEST));
        final JTextField vinculoField = new JTextField(20);
        panelFormulario.add(vinculoField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Área de Atuação:"), createGbc(0, y, GridBagConstraints.WEST));
        final JComboBox<AreaDeAtuacao> areaAtuacaoBox = new JComboBox<>(AreaDeAtuacao.values());
        panelFormulario.add(areaAtuacaoBox, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Tipo de Mentor:"), createGbc(0, y, GridBagConstraints.WEST));
        final JComboBox<TipoMentor> tipoMentorBox = new JComboBox<>(TipoMentor.values());
        panelFormulario.add(tipoMentorBox, createGbc(1, y++, GridBagConstraints.EAST));

        // --- Botões ---
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotoes.setBackground(EstilosPadrao.cinzaClaro);
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelFormulario.add(panelBotoes, gbc);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(EstilosPadrao.verdeBotaoVoltar);
        voltarButton.setFont(EstilosPadrao.fonteBotao);
        voltarButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        voltarButton.setForeground(Color.WHITE);

        JButton proximoButton = new JButton("Próximo");
        proximoButton.setBackground(EstilosPadrao.verdeUni);
        proximoButton.setFont(EstilosPadrao.fonteBotao);
        proximoButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        proximoButton.setForeground(Color.WHITE);

        panelBotoes.add(voltarButton);
        panelBotoes.add(proximoButton);

        // --- Ações dos Botões ---
        voltarButton.addActionListener(e -> {
            telaCadastro.dispose();
            LoginOneMentorView.loginOne();
        });

        proximoButton.addActionListener(e -> {
            EntityManager em = CustomizerFactory.getEntityManager();
            MentorRepository mentorRepo = new MentorRepository(em);

            String cpf = cpfField.getText().replaceAll("[^0-9]", "");
            String email = emailField.getText();
            String telefone = telefoneField.getText();

            if (nomeField.getText().trim().isEmpty() || email.trim().isEmpty() || new String(senhaField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                em.close();
                return;
            }
            if (cpf.length() != 11) {
                JOptionPane.showMessageDialog(telaCadastro, "Por favor, preencha o CPF completamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                em.close();
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

            telaCadastro.dispose();
            CadastroMentorPasso2View.exibirTelaCadastroPasso2(
                    nomeField.getText(), cpf, email, new String(senhaField.getPassword()),
                    telefone, experienciaField.getText(), vinculoField.getText(),
                    (AreaDeAtuacao) areaAtuacaoBox.getSelectedItem(),
                    (TipoMentor) tipoMentorBox.getSelectedItem()
            );
        });

        telaCadastro.setVisible(true);
    }

    private static JFormattedTextField createCpfField() {
        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            return new JFormattedTextField(cpfMask);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JFormattedTextField();
        }
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(EstilosPadrao.fontePadrao);
        return label;
    }

    private static GridBagConstraints createGbc(int x, int y, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
        gbc.insets = new Insets(5, 5, 5, 5);
        if (x == 1) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
        }
        return gbc;
    }
}