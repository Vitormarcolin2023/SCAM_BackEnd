package org.scam.view.mentor;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.classes.Endereco;
import org.scam.controller.classes.TipoMentor;
import org.scam.model.entities.EnderecoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.services.BuscarCEP;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.*;
import java.awt.*;

public class CadastroMentorPasso2View {

    // Campos para armazenar os dados do passo 1
    private static String nome, cpf, email, senha, telefone, tempoExperiencia, tipoVinculo;
    private static AreaDeAtuacao areaDeAtuacao;
    private static TipoMentor tipoMentor;

    public static void exibirTelaCadastroPasso2(
            String nome, String cpf, String email, String senha, String telefone,
            String tempoExperiencia, String tipoVinculo, AreaDeAtuacao areaDeAtuacao, TipoMentor tipoMentor) {

        // Armazena os dados recebidos do Passo 1
        CadastroMentorPasso2View.nome = nome;
        CadastroMentorPasso2View.cpf = cpf;
        CadastroMentorPasso2View.email = email;
        CadastroMentorPasso2View.senha = senha;
        CadastroMentorPasso2View.telefone = telefone;
        CadastroMentorPasso2View.tempoExperiencia = tempoExperiencia;
        CadastroMentorPasso2View.tipoVinculo = tipoVinculo;
        CadastroMentorPasso2View.areaDeAtuacao = areaDeAtuacao;
        CadastroMentorPasso2View.tipoMentor = tipoMentor;


        JFrame telaCadastro = new JFrame("Cadastro de Mentor - Passo 2 de 2");
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

        // Título
        JLabel titulo = new JLabel("CADASTRO - ENDEREÇO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Aumentado para 3 por causa do botão Buscar
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        int y = 1;

        // CEP
        panelFormulario.add(createLabel("CEP:"), createGbc(0, y));
        JTextField cepField = new JTextField(15);
        panelFormulario.add(cepField, createGbc(1, y));
        JButton buscarCepButton = new JButton("Buscar");
        panelFormulario.add(buscarCepButton, createGbc(2, y++));

        // Rua
        panelFormulario.add(createLabel("Rua:"), createGbc(0, y));
        JTextField ruaField = new JTextField(15);
        panelFormulario.add(ruaField, createGbc(1, y++, 2));

        // Número
        panelFormulario.add(createLabel("Número:"), createGbc(0, y));
        JTextField numeroField = new JTextField(15);
        panelFormulario.add(numeroField, createGbc(1, y++, 2));

        // Bairro
        panelFormulario.add(createLabel("Bairro:"), createGbc(0, y));
        JTextField bairroField = new JTextField(15);
        panelFormulario.add(bairroField, createGbc(1, y++, 2));

        // Cidade
        panelFormulario.add(createLabel("Cidade:"), createGbc(0, y));
        JTextField cidadeField = new JTextField(15);
        panelFormulario.add(cidadeField, createGbc(1, y++, 2));

        // Estado
        panelFormulario.add(createLabel("Estado:"), createGbc(0, y));
        JTextField estadoField = new JTextField(15);
        panelFormulario.add(estadoField, createGbc(1, y++, 2));


        // --- Botões ---
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotoes.setBackground(new Color(45, 45, 45));
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelFormulario.add(panelBotoes, gbc);

        JButton voltarButton = new JButton("Voltar");
        JButton salvarButton = new JButton("Salvar Cadastro");
        salvarButton.setBackground(new Color(0, 200, 100));

        panelBotoes.add(voltarButton);
        panelBotoes.add(salvarButton);

        // --- Ações ---
        buscarCepButton.addActionListener(e -> {
            BuscarCEP buscarCEP = new BuscarCEP();
            Endereco endereco = buscarCEP.getEndereco(cepField.getText());
            if (endereco != null && endereco.getLogradouro() != null) {
                ruaField.setText(endereco.getLogradouro());
                bairroField.setText(endereco.getBairro());
                cidadeField.setText(endereco.getLocalidade());
                estadoField.setText(endereco.getEstado());
                numeroField.requestFocus(); // Foco no campo de número
            } else {
                JOptionPane.showMessageDialog(telaCadastro, "CEP não encontrado ou inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        voltarButton.addActionListener(e -> {
            telaCadastro.dispose();
            CadastroMentorPasso1View.exibirTelaCadastroPasso1();
        });

        salvarButton.addActionListener(e -> {
            // Lógica para salvar, replicando o método cadastrarMentor
            if(numeroField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro, "O campo 'Número' é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            EntityManager em = CustomizerFactory.getEntityManager();
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                // 1. Criar e persistir endereço
                EnderecoEntity enderecoEntity = new EnderecoEntity();
                enderecoEntity.setRua(ruaField.getText());
                enderecoEntity.setNumero(Integer.parseInt(numeroField.getText()));
                enderecoEntity.setBairro(bairroField.getText());
                enderecoEntity.setCidade(cidadeField.getText());
                enderecoEntity.setEstado(estadoField.getText());
                enderecoEntity.setCep(cepField.getText());
                em.persist(enderecoEntity);

                // 2. Criar e persistir mentor com os dados do passo 1 e 2
                MentorEntity mentor = new MentorEntity();
                mentor.setNome(nome);
                mentor.setCpf(cpf);
                mentor.setEmail(email);
                mentor.setSenha(senha);
                mentor.setTelefone(telefone);
                mentor.setTempoExperiencia(tempoExperiencia);
                mentor.setTipoDeVinculo(tipoVinculo);
                mentor.setAreaDeAtuacao(areaDeAtuacao);
                mentor.setTipoDeUsuario(tipoMentor); // O enum do backend é 'TipoMentor'
                mentor.setEndereco(enderecoEntity);
                em.persist(mentor);

                tx.commit();

                JOptionPane.showMessageDialog(telaCadastro,
                        "✅ Mentor cadastrado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                telaCadastro.dispose();
                LoginOneMentorView.loginOne(); // Volta para a tela inicial

            } catch (Exception ex) {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
                JOptionPane.showMessageDialog(telaCadastro,
                        "❌ Erro ao cadastrar mentor: " + ex.getMessage(),
                        "Erro de Persistência",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } finally {
                if (em != null && em.isOpen()) {
                    em.close();
                }
            }
        });

        telaCadastro.setVisible(true);
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        return label;
    }

    private static GridBagConstraints createGbc(int x, int y, int gridwidth) {
        GridBagConstraints gbc = createGbc(x, y);
        gbc.gridwidth = gridwidth;
        return gbc;
    }

    private static GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        if (x > 0) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
        }
        return gbc;
    }
}