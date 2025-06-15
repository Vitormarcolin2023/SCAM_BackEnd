package org.scam.view.mentor;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.cadastros.MentorCadastro;
import org.scam.controller.classes.Endereco;
import org.scam.controller.classes.TipoMentor;
import org.scam.model.services.BuscarCEP;
import org.scam.view.EstilosPadrao;
import org.scam.view.mentor.LoginOneMentorView;

import javax.swing.*;
import java.awt.*;

public class CadastroMentorPasso2View {

    private static String nome, cpf, email, senha, telefone, tempoExperiencia, tipoVinculo;
    private static AreaDeAtuacao areaDeAtuacao;
    private static TipoMentor tipoMentor;

    public static void exibirTelaCadastroPasso2(
            String nome, String cpf, String email, String senha, String telefone,
            String tempoExperiencia, String tipoVinculo, AreaDeAtuacao areaDeAtuacao, TipoMentor tipoMentor) {

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
        telaCadastro.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeUni);
        topo.setPreferredSize(new Dimension(telaCadastro.getWidth(), 50));
        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(EstilosPadrao.tituloSAM);
        topo.add(tituloTopo);
        telaCadastro.add(topo, BorderLayout.NORTH);

        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(EstilosPadrao.cinzaFundo);
        telaCadastro.add(containerCentro, BorderLayout.CENTER);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(EstilosPadrao.cinzaClaro);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        containerCentro.add(panelFormulario);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("CADASTRO - ENDEREÇO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(EstilosPadrao.fonteTitulos);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        int y = 1;

        panelFormulario.add(createLabel("CEP:"), createGbc(0, y));
        JTextField cepField = new JTextField(15);
        panelFormulario.add(cepField, createGbc(1, y));
        JButton buscarCepButton = new JButton("Buscar");
        buscarCepButton.setBackground(EstilosPadrao.verdeBotaoVoltar);
        buscarCepButton.setFont(EstilosPadrao.fonteBtnAcaoLateral);
        buscarCepButton.setForeground(Color.WHITE);
        panelFormulario.add(buscarCepButton, createGbc(2, y++));

        panelFormulario.add(createLabel("Rua:"), createGbc(0, y));
        JTextField ruaField = new JTextField(15);
        panelFormulario.add(ruaField, createGbc(1, y++, 2));

        panelFormulario.add(createLabel("Número:"), createGbc(0, y));
        JTextField numeroField = new JTextField(15);
        panelFormulario.add(numeroField, createGbc(1, y++, 2));

        panelFormulario.add(createLabel("Bairro:"), createGbc(0, y));
        JTextField bairroField = new JTextField(15);
        panelFormulario.add(bairroField, createGbc(1, y++, 2));

        panelFormulario.add(createLabel("Cidade:"), createGbc(0, y));
        JTextField cidadeField = new JTextField(15);
        panelFormulario.add(cidadeField, createGbc(1, y++, 2));

        panelFormulario.add(createLabel("Estado:"), createGbc(0, y));
        JTextField estadoField = new JTextField(15);
        panelFormulario.add(estadoField, createGbc(1, y++, 2));

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotoes.setBackground(EstilosPadrao.cinzaClaro);
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 5, 5);
        panelFormulario.add(panelBotoes, gbc);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(EstilosPadrao.verdeBotaoVoltar);
        voltarButton.setFont(EstilosPadrao.fonteBotao);
        voltarButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        voltarButton.setForeground(Color.WHITE);

        JButton salvarButton = new JButton("Salvar Cadastro");
        salvarButton.setBackground(EstilosPadrao.verdeUni);
        salvarButton.setFont(EstilosPadrao.fonteBotao);
        salvarButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        salvarButton.setForeground(Color.WHITE);

        panelBotoes.add(voltarButton);
        panelBotoes.add(salvarButton);

        buscarCepButton.addActionListener(e -> {
            BuscarCEP buscarCEP = new BuscarCEP();
            Endereco endereco = buscarCEP.getEndereco(cepField.getText());
            if (endereco != null && endereco.getLogradouro() != null) {
                ruaField.setText(endereco.getLogradouro());
                bairroField.setText(endereco.getBairro());
                cidadeField.setText(endereco.getLocalidade());
                estadoField.setText(endereco.getEstado());
                numeroField.requestFocus();
            } else {
                JOptionPane.showMessageDialog(telaCadastro, "CEP não encontrado ou inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        voltarButton.addActionListener(e -> {
            telaCadastro.dispose();
            CadastroMentorPasso1View.exibirTelaCadastroPasso1();
        });

        salvarButton.addActionListener(e -> {
            if (numeroField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro, "O campo 'Número' é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                MentorCadastro mentorCadastroController = new MentorCadastro();

                mentorCadastroController.salvarMentorPelaView(
                        nome, cpf, email, senha, telefone, tempoExperiencia, tipoVinculo, areaDeAtuacao, tipoMentor,
                        cepField.getText(), ruaField.getText(), Integer.parseInt(numeroField.getText()),
                        bairroField.getText(), cidadeField.getText(), estadoField.getText()
                );

                JOptionPane.showMessageDialog(telaCadastro, "✅ Mentor cadastrado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                telaCadastro.dispose();
                LoginOneMentorView.loginOne();

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(telaCadastro, "❌ O campo 'Número' deve conter apenas dígitos.",
                        "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(telaCadastro, "❌ Erro ao cadastrar mentor: " + ex.getMessage(),
                        "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        telaCadastro.setVisible(true);
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(EstilosPadrao.fontePadrao);
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