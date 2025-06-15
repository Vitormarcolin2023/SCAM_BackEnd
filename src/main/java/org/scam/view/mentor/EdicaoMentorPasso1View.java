package org.scam.view.mentor;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.classes.Mentor;
import org.scam.controller.classes.TipoMentor;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import java.awt.*;

public class EdicaoMentorPasso1View {

    public static void exibirTelaEdicaoPasso1() {
        // LÓGICA PRINCIPAL: Pega o mentor da sessão ANTES de criar a tela
        Mentor mentorLogado = Sessao.getMentorLogado();
        if (mentorLogado == null) {
            JOptionPane.showMessageDialog(null, "Não há um mentor logado para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final JFrame telaEdicao = new JFrame("Editar Perfil - Passo 1 de 2");
        telaEdicao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE para não fechar o app todo
        telaEdicao.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaEdicao.setLayout(new BorderLayout());
        telaEdicao.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

        // --- Topo (igual ao do cadastro) ---
        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeSAM);
        topo.setPreferredSize(new Dimension(telaEdicao.getWidth(), 50));
        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(EstilosPadrao.tituloSAM);
        topo.add(tituloTopo);
        telaEdicao.add(topo, BorderLayout.NORTH);

        // --- Formulário Central ---
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

        JLabel titulo = new JLabel("EDITAR - DADOS PESSOAIS E PROFISSIONAIS");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(EstilosPadrao.fonteTitulos);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(titulo, gbc);
        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;

        int y = 1;

        // --- Campos do Formulário COM DADOS PRÉ-CARREGADOS ---

        // CPF e E-mail não podem ser editados, apenas exibidos
        panelFormulario.add(createLabel("CPF:"), createGbc(0, y));
        panelFormulario.add(createDisabledField(mentorLogado.getCpf()), createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("E-mail:"), createGbc(0, y));
        panelFormulario.add(createDisabledField(mentorLogado.getEmail()), createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Nome Completo:"), createGbc(0, y));
        final JTextField nomeField = new JTextField(20);
        nomeField.setText(mentorLogado.getNome()); // Carrega dado
        panelFormulario.add(nomeField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Nova Senha (deixe em branco para não alterar):"), createGbc(0, y));
        final JPasswordField senhaField = new JPasswordField(20);
        panelFormulario.add(senhaField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Telefone:"), createGbc(0, y));
        final JTextField telefoneField = new JTextField(20);
        telefoneField.setText(mentorLogado.getTelefone()); // Carrega dado
        panelFormulario.add(telefoneField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Tempo de Experiência:"), createGbc(0, y));
        final JTextField experienciaField = new JTextField(20);
        experienciaField.setText(mentorLogado.getTempoDeExperiencia()); // Carrega dado
        panelFormulario.add(experienciaField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Tipo de Vínculo (CLT, PJ...):"), createGbc(0, y));
        final JTextField vinculoField = new JTextField(20);
        vinculoField.setText(mentorLogado.getTipoDeVinculo()); // Carrega dado
        panelFormulario.add(vinculoField, createGbc(1, y++, GridBagConstraints.EAST));

        panelFormulario.add(createLabel("Área de Atuação:"), createGbc(0, y));
        final JComboBox<AreaDeAtuacao> areaAtuacaoBox = new JComboBox<>(AreaDeAtuacao.values());
        areaAtuacaoBox.setSelectedItem(mentorLogado.getAreaDeAtuacao()); // Carrega dado
        panelFormulario.add(areaAtuacaoBox, createGbc(1, y++, GridBagConstraints.EAST));

        // Tipo de mentor também não deve ser editado
        panelFormulario.add(createLabel("Tipo de Mentor:"), createGbc(0, y));
        panelFormulario.add(createDisabledField(mentorLogado.getTipoDeUsuario().name()), createGbc(1, y++, GridBagConstraints.EAST));

        // --- Botões ---
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotoes.setBackground(EstilosPadrao.cinzaClaro);
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(panelBotoes, gbc);

        JButton cancelarButton = new JButton("Cancelar");
        JButton proximoButton = new JButton("Próximo");

        // Estilos...
        cancelarButton.setBackground(Color.GRAY);
        cancelarButton.setForeground(Color.WHITE);
        cancelarButton.setFont(EstilosPadrao.fonteBotao);
        cancelarButton.setPreferredSize(EstilosPadrao.tamanhoBotao);

        proximoButton.setBackground(EstilosPadrao.verdeUni);
        proximoButton.setFont(EstilosPadrao.fonteBotao);
        proximoButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        proximoButton.setForeground(Color.WHITE);

        panelBotoes.add(cancelarButton);
        panelBotoes.add(proximoButton);

        // --- Ações dos Botões ---
        cancelarButton.addActionListener(e -> telaEdicao.dispose());

        proximoButton.addActionListener(e -> {
            // Apenas passa os dados para a próxima tela
            telaEdicao.dispose();
            EdicaoMentorPasso2View.exibirTelaEdicaoPasso2(
                    nomeField.getText(),
                    new String(senhaField.getPassword()),
                    telefoneField.getText(),
                    experienciaField.getText(),
                    vinculoField.getText(),
                    (AreaDeAtuacao) areaAtuacaoBox.getSelectedItem()
            );
        });

        telaEdicao.setVisible(true);
    }

    // --- Métodos auxiliares ---
    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16)); // Fonte um pouco menor para caber mais texto
        return label;
    }

    private static JTextField createDisabledField(String text) {
        JTextField field = new JTextField(text);
        field.setEditable(false);
        field.setBackground(Color.DARK_GRAY);
        field.setForeground(Color.LIGHT_GRAY);
        field.setBorder(BorderFactory.createEtchedBorder());
        return field;
    }

    private static GridBagConstraints createGbc(int x, int y, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y; gbc.anchor = anchor;
        gbc.insets = new Insets(5, 5, 5, 5);
        if (x == 1) { gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0; }
        return gbc;
    }

    private static GridBagConstraints createGbc(int x, int y) {
        return createGbc(x, y, GridBagConstraints.WEST); // Chama a outra versão com um valor padrão
    }
}