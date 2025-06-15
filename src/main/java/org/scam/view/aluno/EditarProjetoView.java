package org.scam.view.aluno;

import org.scam.controller.EditarProjetoController;
import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.cadastros.Curso;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EditarProjetoView extends JInternalFrame {

    //CONTROLLER QUE GERENCIA A LOGICA DA TELA
    private final EditarProjetoController controller;

    //MODELOS DE LISTAS PARA EXIBIR ALUNOS E MENTORE NA INTERFACE
    private final DefaultListModel<AlunoEntity> alunosListModel = new DefaultListModel<>();
    private final DefaultListModel<MentorEntity> mentoresListModel = new DefaultListModel<>();

    //COMPONENTES DA INTERFACE
    private JTextField nomeField, raField;
    private JTextArea descricaoArea;
    private JComboBox<AreaDeAtuacao> btnAtuacao;
    private JComboBox<Curso> btnCurso;
    private JComboBox<String> btnPeriodo;
    private JList<AlunoEntity> alunosList;
    private JList<MentorEntity> mentoresList;
    private JButton adicionarAlunoButton, cadastrarButton, voltarButton;
    private JComboBox<Integer> diaComboBox, mesComboBox, anoComboBox;
    private JComboBox<ProjetoEntity> projetoComboBox;


    //CONSTRUTOR DA VIEW, INICIALIZAR OS COMPONESTES E LISTENERS
    public EditarProjetoView() {
        controller = new EditarProjetoController(this);
        setTitle("Editar Projeto");
        setClosable(true);
        setResizable(false);
        setMaximizable(false);
        setIconifiable(true);
        initComponents();
        initListeners();
        controller.inicializar();
        pack();
        setSize(EstilosPadrao.tamanhoInternalFrame);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 3));
        ui.setNorthPane(null);
    }

    //METODO QUE CRIA E POSICIONA TODOS OS COMPONENTES NA INTERFACE
    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(EstilosPadrao.cinzaClaro);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int row = 0;

        //TITULO DA TELA
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel titulo = new JLabel("Editar cadastro de Projeto");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(EstilosPadrao.fonteTitulos);
        panel.add(titulo, gbc);



        //COMBOX PARA SELECAO D PROJETO A SER EDITADO
        gbc.gridy = row++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(createWhiteLabel("Selecione o Projeto:"), gbc);
        gbc.gridx = 1;
        projetoComboBox = new JComboBox<>();
        // Renderer para exibir nome do projeto na ComboBox
        projetoComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel();
            if (value != null) {
                ProjetoEntity projeto = (ProjetoEntity) value;
                label.setText(projeto.getNomeDoProjeto());
            }
            label.setOpaque(true);
            label.setForeground(Color.BLACK);
            label.setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
            return label;
        });
        panel.add(projetoComboBox, gbc);

        //CAMPO PARA EDITAR NOME DO PROJETO
        gbc.gridy = row++;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Nome do Projeto:"), gbc);
        gbc.gridx = 1;
        nomeField = new JTextField(30);
        panel.add(nomeField, gbc);

        gbc.gridy = row++;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        descricaoArea = new JTextArea(4, 20);
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(descricaoArea), gbc);

        gbc.gridy = row++;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Área de atuação:"), gbc);
        gbc.gridx = 1;
        btnAtuacao = new JComboBox<>(AreaDeAtuacao.values());
        panel.add(btnAtuacao, gbc);

        gbc.gridy = row++;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Curso:"), gbc);
        gbc.gridx = 1;
        btnCurso = new JComboBox<>(Curso.values());
        panel.add(btnCurso, gbc);

        gbc.gridy = row++;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Data de início:"), gbc);
        gbc.gridx = 1;
        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        dataPanel.setOpaque(false);
        diaComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) diaComboBox.addItem(i);
        mesComboBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) mesComboBox.addItem(i);
        anoComboBox = new JComboBox<>();
        int anoAtual = LocalDate.now().getYear();
        for (int i = anoAtual - 1; i <= anoAtual + 5; i++) anoComboBox.addItem(i);
        dataPanel.add(createWhiteLabel("Dia:"));
        dataPanel.add(diaComboBox);
        dataPanel.add(createWhiteLabel("Mês:"));
        dataPanel.add(mesComboBox);
        dataPanel.add(createWhiteLabel("Ano:"));
        dataPanel.add(anoComboBox);
        panel.add(dataPanel, gbc);

        gbc.gridy = row++;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Período:"), gbc);
        gbc.gridx = 1;
        btnPeriodo = new JComboBox<>(new String[]{
                "1º Período", "2º Período", "3º Período", "4º Período",
                "5º Período", "6º Período", "7º Período", "8º Período"
        });
        panel.add(btnPeriodo, gbc);

        gbc.gridy = row++;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Adicionar aluno (RA):"), gbc);
        gbc.gridx = 1;
        JPanel raPanel = new JPanel(new BorderLayout(5, 0));
        raField = new JTextField();
        adicionarAlunoButton = new JButton("Adicionar");
        raPanel.add(raField, BorderLayout.CENTER);
        raPanel.add(adicionarAlunoButton, BorderLayout.EAST);
        raPanel.setOpaque(false);
        panel.add(raPanel, gbc);

        gbc.gridy = row++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        alunosList = new JList<>(alunosListModel);
        alunosList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof AlunoEntity aluno) {
                    setText(aluno.getRa() + " - " + aluno.getNome());
                }
                return this;
            }
        });
        JScrollPane alunosScroll = new JScrollPane(alunosList);
        alunosScroll.setBorder(createTitledBorder("Alunos no Projeto"));
        alunosScroll.setPreferredSize(new Dimension(400, 100));
        panel.add(alunosScroll, gbc);

        gbc.gridy = row++;
        mentoresList = new JList<>(mentoresListModel);
        mentoresList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof MentorEntity mentor) {
                    setText(mentor.getNome());
                }
                return this;
            }
        });
        JScrollPane mentoresScroll = new JScrollPane(mentoresList);
        mentoresScroll.setBorder(createTitledBorder("Mentores"));
        mentoresScroll.setPreferredSize(new Dimension(400, 100));
        panel.add(mentoresScroll, gbc);

        gbc.gridy = row++;
        gbc.insets = new Insets(20, 10, 10, 10);
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        voltarButton = new JButton("Cancelar");
        cadastrarButton = new JButton("Atualizar Projeto");
        botoesPanel.add(voltarButton);
        botoesPanel.add(cadastrarButton);
        botoesPanel.setOpaque(false);
        panel.add(botoesPanel, gbc);

        setContentPane(panel);
    }

    //CONFIGURA OS EVENTOS PARA OS BOTOES E COMBO BOXE
    private void initListeners() {
        voltarButton.addActionListener(e -> dispose());
        btnAtuacao.addActionListener(e -> controller.carregarMentoresPorArea(getAreaSelecionada()));
        adicionarAlunoButton.addActionListener(e -> controller.adicionarAlunoPorRa(raField.getText()));
        cadastrarButton.addActionListener(e -> controller.atualizarProjeto());
        projetoComboBox.addActionListener(e -> controller.preencherCamposComProjeto(getProjetoSelecionado()));
    }

    private JLabel createWhiteLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private TitledBorder createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleColor(Color.LIGHT_GRAY);
        return border;
    }

    //RETORNA A LISTA DE ALUNOS ATUALMENTE ADICIONADOS AO PROJETO
    public List<AlunoEntity> getAlunosSelecionados() {
        List<AlunoEntity> lista = new ArrayList<>();
        for (int i = 0; i < alunosListModel.size(); i++) {
            lista.add(alunosListModel.get(i));
        }
        return lista;
    }

    public void limparCampoRa() {
        raField.setText("");
    }

    public void adicionarAlunoNaLista(AlunoEntity aluno) {
        alunosListModel.addElement(aluno);
    }



    //VERIFICA SE UM ALUNO JA ESTA NA LISTA PARA EVITAR DUPLICATAS
    public boolean alunoJaAdicionado(AlunoEntity aluno) {
        return alunosListModel.contains(aluno);
    }

    //PREENCHE O COMBO BOX DE PROJETOS COM A LISTA DE PROJETOS DISPONIVEIS
    public void setProjetos(List<ProjetoEntity> projetos) {
        projetoComboBox.removeAllItems();
        for (ProjetoEntity projeto : projetos) {
            projetoComboBox.addItem(projeto);
        }
    }

    //PREENCHE A LISTA DE MENTORES CONFORME A AREA SELECIONADA
    public void setMentores(List<MentorEntity> mentores) {
        mentoresListModel.clear();
        for (MentorEntity mentor : mentores) {
            mentoresListModel.addElement(mentor);
        }
    }

    //EXIBIR MENSAGEM PARA O ALUNO
    public void showMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    //Preenche os campos da interface com os dados do projeto selecionado
    //TREENCHE OS CAMPOS DA INTERFACE COM OS DADOS DO PROJETOS SELECIONADO
    public void setDadosProjetoSelecionado(ProjetoEntity projeto) {
        nomeField.setText(projeto.getNomeDoProjeto());
        descricaoArea.setText(projeto.getDescricao());
        btnAtuacao.setSelectedItem(projeto.getAreaDeAtuacao());
        btnCurso.setSelectedItem(projeto.getCurso());
        btnPeriodo.setSelectedItem(projeto.getPeriodo());

        diaComboBox.setSelectedItem(projeto.getDataInicioProjeto().getDayOfMonth());
        mesComboBox.setSelectedItem(projeto.getDataInicioProjeto().getMonthValue());
        anoComboBox.setSelectedItem(projeto.getDataInicioProjeto().getYear());

        alunosListModel.clear();
        for (AlunoEntity aluno : projeto.getAlunos()) {
            alunosListModel.addElement(aluno);
        }

        carregarMentoresDaArea();
        mentoresList.setSelectedValue(projeto.getMentor(), true);
    }

    //METODO AUXILIAR PARA CARREGAR MENTORES CONFORME A AREA SELECIONADA
    private void carregarMentoresDaArea() {
        controller.carregarMentoresPorArea(getAreaSelecionada());
    }

    //RETORNA A AREA DE ATUACAO SELECIONADA NO COMBO BOX
    public AreaDeAtuacao getAreaSelecionada() {
        return (AreaDeAtuacao) btnAtuacao.getSelectedItem();
    }

    //RETORNA O PROJETO SELECIONADO NO COMBO BOX
    public ProjetoEntity getProjetoSelecionado() {
        return (ProjetoEntity) projetoComboBox.getSelectedItem();
    }

    //RETORNA A DESCRICAO DIGITADSA NO CAMPO DE TEXTO
    public String getDescricaoProjeto() {
        return descricaoArea.getText().trim();
    }

    // Na classe EditarProjetoView
    public LocalDate getDataSelecionada() {
        int dia = (int) diaComboBox.getSelectedItem();
        int mes = (int) mesComboBox.getSelectedItem();
        int ano = (int) anoComboBox.getSelectedItem();
        return LocalDate.of(ano, mes, dia);
    }

    public String getNomeProjeto() {
        return nomeField.getText().trim();
    }

    public Curso getCursoSelecionado() {
        return (Curso) btnCurso.getSelectedItem();
    }

    public String getPeriodoSelecionado() {
        return (String) btnPeriodo.getSelectedItem();
    }


}
