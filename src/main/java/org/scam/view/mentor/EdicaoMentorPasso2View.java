package org.scam.view.mentor;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.cadastros.MentorCadastro;
import org.scam.controller.classes.Endereco;
import org.scam.controller.classes.Mentor;
import org.scam.model.entities.EnderecoEntity;
import org.scam.model.services.BuscarCEP;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import java.awt.*;

import static org.scam.view.mentor.EdicaoMentorPasso1View.exibirTelaEdicaoPasso1;

public class EdicaoMentorPasso2View {

    public static void exibirTelaEdicaoPasso2(
            // Dados recebidos do passo 1
            String nome, String senha, String telefone, String tempoExperiencia,
            String tipoVinculo, AreaDeAtuacao areaDeAtuacao
    ) {
        Mentor mentorLogado = Sessao.getMentorLogado();
        EnderecoEntity enderecoAtual = (mentorLogado != null) ? mentorLogado.getEndereco() : null;

        if (mentorLogado == null || enderecoAtual == null) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados do endereço.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFrame telaEdicao = new JFrame("Editar Perfil - Passo 2 de 2");
        telaEdicao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaEdicao.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaEdicao.setLayout(new BorderLayout());
        telaEdicao.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

        // --- Topo (reutilizado) ---
        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeUni);
        topo.setPreferredSize(new Dimension(telaEdicao.getWidth(), 50));
        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(EstilosPadrao.tituloSAM);
        topo.add(tituloTopo);
        telaEdicao.add(topo, BorderLayout.NORTH);

        // --- Formulário (reutilizado) ---
        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(EstilosPadrao.cinzaFundo);
        telaEdicao.add(containerCentro, BorderLayout.CENTER);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(EstilosPadrao.cinzaClaro);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        containerCentro.add(panelFormulario);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("EDITAR - ENDEREÇO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(EstilosPadrao.fonteTitulos);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(titulo, gbc);
        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;

        int y = 1;

        // --- Campos de endereço com dados pré-carregados ---
        panelFormulario.add(createLabel("CEP:"), createGbc(0, y));
        JTextField cepField = new JTextField(15);
        cepField.setText(enderecoAtual.getCep()); // Carrega dado
        panelFormulario.add(cepField, createGbc(1, y));
        JButton buscarCepButton = new JButton("Buscar");
        panelFormulario.add(buscarCepButton, createGbc(2, y++));

        panelFormulario.add(createLabel("Rua:"), createGbc(0, y));
        JTextField ruaField = new JTextField(15);
        ruaField.setText(enderecoAtual.getRua()); // Carrega dado
        panelFormulario.add(ruaField, createGbc(1, y++, 2));

        panelFormulario.add(createLabel("Número:"), createGbc(0, y));
        JTextField numeroField = new JTextField(15);
        numeroField.setText(String.valueOf(enderecoAtual.getNumero())); // Carrega dado
        panelFormulario.add(numeroField, createGbc(1, y++, 2));

        panelFormulario.add(createLabel("Bairro:"), createGbc(0, y));
        JTextField bairroField = new JTextField(15);
        bairroField.setText(enderecoAtual.getBairro()); // Carrega dado
        panelFormulario.add(bairroField, createGbc(1, y++, 2));

        panelFormulario.add(createLabel("Cidade:"), createGbc(0, y));
        JTextField cidadeField = new JTextField(15);
        cidadeField.setText(enderecoAtual.getCidade()); // Carrega dado
        panelFormulario.add(cidadeField, createGbc(1, y++, 2));

        panelFormulario.add(createLabel("Estado:"), createGbc(0, y));
        JTextField estadoField = new JTextField(15);
        estadoField.setText(enderecoAtual.getEstado()); // Carrega dado
        panelFormulario.add(estadoField, createGbc(1, y++, 2));

        // --- Botões ---
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotoes.setBackground(EstilosPadrao.cinzaClaro);
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(panelBotoes, gbc);

        JButton voltarButton = new JButton("Voltar");
        JButton salvarButton = new JButton("Salvar Alterações");

        // Estilos
        voltarButton.setBackground(EstilosPadrao.verdeBotaoVoltar);
        voltarButton.setFont(EstilosPadrao.fonteBotao);
        voltarButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        voltarButton.setForeground(Color.WHITE);

        salvarButton.setBackground(EstilosPadrao.verdeUni);
        salvarButton.setFont(EstilosPadrao.fonteBotao);
        salvarButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        salvarButton.setForeground(Color.WHITE);

        panelBotoes.add(voltarButton);
        panelBotoes.add(salvarButton);

        // --- Ações ---
        buscarCepButton.addActionListener(e -> { /* ...lógica de buscar cep... */ });

        voltarButton.addActionListener(e -> {
            telaEdicao.dispose();
            exibirTelaEdicaoPasso1(); // Volta para a tela anterior de edição
        });

        salvarButton.addActionListener(e -> {
            // LÓGICA FINAL: Chama o controller para salvar tudo
            if (numeroField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(telaEdicao, "O campo 'Número' é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                MentorCadastro controller = new MentorCadastro();
                controller.atualizarMentorPelaView(
                        nome, senha, telefone, tempoExperiencia, tipoVinculo, areaDeAtuacao, // Dados do passo 1
                        cepField.getText(), ruaField.getText(), Integer.parseInt(numeroField.getText()),
                        bairroField.getText(), cidadeField.getText(), estadoField.getText() // Dados do passo 2
                );

                JOptionPane.showMessageDialog(telaEdicao, "✅ Perfil atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                telaEdicao.dispose();
                // O ideal aqui é atualizar a tela principal, se ela estiver aberta
                // TelaPrincipalView.atualizarDados();

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(telaEdicao, "❌ O campo 'Número' deve ser um valor numérico.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(telaEdicao, "❌ Erro ao atualizar perfil: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        telaEdicao.setVisible(true);
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return label;
    }

    private static GridBagConstraints createGbc(int x, int y, int... gridwidth) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        if (gridwidth.length > 0) gbc.gridwidth = gridwidth[0];
        if (x > 0) { gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0; }
        return gbc;
    }
}