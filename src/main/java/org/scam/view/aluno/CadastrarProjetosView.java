package org.scam.view.aluno;

import org.scam.controller.cadastros.AreaDeAtuacao;
import org.scam.controller.cadastros.Curso;
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.MentorEntity;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.MentorRepository;
import org.scam.model.repository.ProjetoRepository;
import org.scam.model.services.Sessao;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CadastrarProjetosView extends JInternalFrame {

    private final EntityManager em;
    private final MentorRepository mentorRepository;
    private final ProjetoRepository projetoRepository;

    private final DefaultListModel<AlunoEntity> alunosListModel;
    private final DefaultListModel<MentorEntity> mentoresListModel;

    private JTextField nomeField, raField;
    private JTextArea descricaoArea;
    private JComboBox<AreaDeAtuacao> btnAtuacao;
    private JComboBox<Curso> btnCurso;
    private JComboBox<String> btnPeriodo;
    private JList<AlunoEntity> alunosList;
    private JList<MentorEntity> mentoresList;
    private JButton adicionarAlunoButton, cadastrarButton, voltarButton;
    private JComboBox<Integer> diaComboBox, mesComboBox, anoComboBox;


    public CadastrarProjetosView() {
        this.em = CustomizerFactory.getEntityManager();
        this.mentorRepository = new MentorRepository(em);
        this.projetoRepository = new ProjetoRepository(em);
        this.alunosListModel = new DefaultListModel<>();
        this.mentoresListModel = new DefaultListModel<>();

        setTitle("Cadastro de Novo Projeto");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        initComponents();
        initListeners();

        adicionarAlunoDaSessao();
        carregarMentores();

        pack();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(45, 45, 45));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int row = 0;

        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel titulo = new JLabel("Cadastro de Novos Projetos");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(titulo, gbc);
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Nome do Projeto:"), gbc);
        gbc.gridx = 1;
        nomeField = new JTextField(30);
        nomeField.setPreferredSize(new Dimension(20, 30));
        panel.add(nomeField, gbc);

        gbc.gridy = ++row;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        descricaoArea = new JTextArea(4, 20);
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);
        JScrollPane descricaoScrollPane = new JScrollPane(descricaoArea);
        panel.add(descricaoScrollPane, gbc);

        gbc.gridy = ++row;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Área de atuação:"), gbc);
        gbc.gridx = 1;
        btnAtuacao = new JComboBox<>(AreaDeAtuacao.values());
        panel.add(btnAtuacao, gbc);

        gbc.gridy = ++row;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Seleção do curso:"), gbc);
        gbc.gridx = 1;
        btnCurso = new JComboBox<>(Curso.values());
        panel.add(btnCurso, gbc);

        gbc.gridy = ++row;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Data de início:"), gbc);
        gbc.gridx = 1;
        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        dataPanel.setOpaque(false);
        diaComboBox = new JComboBox<>(); for (int i = 1; i <= 31; i++) diaComboBox.addItem(i);
        mesComboBox = new JComboBox<>(); for (int i = 1; i <= 12; i++) mesComboBox.addItem(i);
        anoComboBox = new JComboBox<>();
        int anoAtual = LocalDate.now().getYear();
        for (int i = anoAtual - 1; i <= anoAtual + 5; i++) anoComboBox.addItem(i);
        LocalDate hoje = LocalDate.now();
        diaComboBox.setSelectedItem(hoje.getDayOfMonth());
        mesComboBox.setSelectedItem(hoje.getMonthValue());
        anoComboBox.setSelectedItem(hoje.getYear());
        dataPanel.add(new JLabel("Dia:")); dataPanel.add(diaComboBox);
        dataPanel.add(new JLabel("Mês:")); dataPanel.add(mesComboBox);
        dataPanel.add(new JLabel("Ano:")); dataPanel.add(anoComboBox);
        panel.add(dataPanel, gbc);

        gbc.gridy = ++row;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Período:"), gbc);
        gbc.gridx = 1;
        String[] opcoesPeriodo = {"1º Período", "2º Período", "3º Período", "4º Período", "5º Período", "6º Período", "7º Período", "8º Período", "9º Período", "10º Período"};
        btnPeriodo = new JComboBox<>(opcoesPeriodo);
        panel.add(btnPeriodo, gbc);

        gbc.gridy = ++row;
        gbc.gridx = 0;
        panel.add(createWhiteLabel("Adicionar aluno por RA:"), gbc);
        gbc.gridx = 1;
        JPanel raPanel = new JPanel(new BorderLayout(5, 0));
        raPanel.setOpaque(false);
        raField = new JTextField();
        raField.setPreferredSize(new Dimension(20, 30));
        adicionarAlunoButton = new JButton("Adicionar");
        raPanel.add(raField, BorderLayout.CENTER);
        raPanel.add(adicionarAlunoButton, BorderLayout.EAST);
        panel.add(raPanel, gbc);

        gbc.gridy = ++row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        alunosList = new JList<>(alunosListModel);
        // MUDANÇA: Estilo e renderizador da lista
        alunosList.setBackground(new Color(60, 63, 65));
        alunosList.setForeground(Color.WHITE);
        alunosList.setCellRenderer(new AlunoMentorCellRenderer());
        JScrollPane alunosScrollPane = new JScrollPane(alunosList);
        alunosScrollPane.setBorder(createTitledBorder("Alunos no Projeto"));
        alunosScrollPane.setPreferredSize(new Dimension(400, 100));
        panel.add(alunosScrollPane, gbc);

        gbc.gridy = ++row;
        mentoresList = new JList<>(mentoresListModel);
        mentoresList.setBackground(new Color(60, 63, 65));
        mentoresList.setForeground(Color.WHITE);
        mentoresList.setCellRenderer(new AlunoMentorCellRenderer());
        JScrollPane mentoresScrollPane = new JScrollPane(mentoresList);
        mentoresScrollPane.setBorder(createTitledBorder("Mentores Disponíveis (Selecione um)"));
        mentoresScrollPane.setPreferredSize(new Dimension(400, 120));
        panel.add(mentoresScrollPane, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        gbc.gridy = ++row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesPanel.setBackground(new Color(45, 45, 45));
        voltarButton = new JButton("Cancelar");
        cadastrarButton = new JButton("Cadastrar Projeto");
        cadastrarButton.setPreferredSize(new Dimension(180, 30));
        voltarButton.setPreferredSize(new Dimension(150, 30));
        botoesPanel.add(voltarButton);
        botoesPanel.add(cadastrarButton);
        panel.add(botoesPanel, gbc);

        setContentPane(panel);
    }

    private void initListeners() {
        voltarButton.addActionListener(e -> this.dispose());
        btnAtuacao.addActionListener(e -> carregarMentores());
        adicionarAlunoButton.addActionListener(e -> adicionarAluno());
        cadastrarButton.addActionListener(e -> cadastrarProjeto());
    }

    private void adicionarAlunoDaSessao() {
        try {
            int raAlunoLogado = Sessao.getRaAluno();
            AlunoEntity alunoPrincipal = em.find(AlunoEntity.class, raAlunoLogado);
            if (alunoPrincipal != null && !alunosListModel.contains(alunoPrincipal)) {
                alunosListModel.addElement(alunoPrincipal);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar aluno da sessão: " + e.getMessage(), "Erro de Sessão", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarMentores() {
        AreaDeAtuacao areaSelecionada = (AreaDeAtuacao) btnAtuacao.getSelectedItem();
        mentoresListModel.clear();
        if (areaSelecionada != null) {
            List<MentorEntity> mentores = mentorRepository.buscarMentoresPorAreaDeAtuacao(areaSelecionada);
            if (!mentores.isEmpty()) {
                mentores.forEach(mentoresListModel::addElement);
            }
        }
    }

    private void adicionarAluno() {
        String raText = raField.getText().trim();
        if (raText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite o RA do aluno.", "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int ra = Integer.parseInt(raText);
            AlunoEntity aluno = em.find(AlunoEntity.class, ra);
            if (aluno != null) {
                if (!alunosListModel.contains(aluno)) {
                    alunosListModel.addElement(aluno);
                    raField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Este aluno já foi adicionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Aluno com RA " + ra + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O RA deve ser um número válido.", "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cadastrarProjeto() {
        if (nomeField.getText().trim().isEmpty() || descricaoArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }
        MentorEntity mentorSelecionado = mentoresList.getSelectedValue();
        if (mentorSelecionado == null) {
            JOptionPane.showMessageDialog(this, "É obrigatório selecionar um mentor para o projeto!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate dataInicio;
        try {
            int dia = (int) diaComboBox.getSelectedItem();
            int mes = (int) mesComboBox.getSelectedItem();
            int ano = (int) anoComboBox.getSelectedItem();
            dataInicio = LocalDate.of(ano, mes, dia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "A data selecionada é inválida!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProjetoEntity novoProjeto = new ProjetoEntity();
        novoProjeto.setNomeDoProjeto(nomeField.getText().trim());
        novoProjeto.setDescricao(descricaoArea.getText().trim());
        novoProjeto.setAreaDeAtuacao((AreaDeAtuacao) btnAtuacao.getSelectedItem());
        novoProjeto.setCurso((Curso) btnCurso.getSelectedItem());
        novoProjeto.setDataInicioProjeto(dataInicio);
        novoProjeto.setDataFinalProjeto(dataInicio.plusMonths(5));
        novoProjeto.setPeriodo((String) btnPeriodo.getSelectedItem());
        novoProjeto.setMentor(mentorSelecionado);

        List<AlunoEntity> alunosParaAdicionar = new ArrayList<>();
        for (int i = 0; i < alunosListModel.getSize(); i++) {
            alunosParaAdicionar.add(alunosListModel.getElementAt(i));
        }
        novoProjeto.setAlunos(alunosParaAdicionar);
        novoProjeto.setTamanhoDoGrupo(alunosListModel.getSize());

        if (projetoRepository.salvar(novoProjeto)) {
            JOptionPane.showMessageDialog(this, "Projeto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao cadastrar o projeto. Verifique o console para mais detalhes.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
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

    class AlunoMentorCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof AlunoEntity) {
                AlunoEntity aluno = (AlunoEntity) value;
                setText(aluno.getRa() + " - " + aluno.getNome());
            } else if (value instanceof MentorEntity) {
                MentorEntity mentor = (MentorEntity) value;
                setText(mentor.getNome());
            }
            return c;
        }
    }
}