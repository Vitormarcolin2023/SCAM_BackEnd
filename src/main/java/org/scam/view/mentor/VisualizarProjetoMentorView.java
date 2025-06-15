package org.scam.view.mentor;

import org.scam.controller.ProjetoController;
import org.scam.controller.classes.Mentor; // Usado para obter o objeto da Sessão
import org.scam.model.entities.AlunoEntity;
import org.scam.model.entities.MentorEntity; // Usado para a consulta ao banco
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class VisualizarProjetoMentorView {

    public static void exibir(JFrame parentFrame, JDesktopPane desktopPane) {
        EntityManager em = null;
        try {
            em = CustomizerFactory.getEntityManager();
            ProjetoController projetoController = new ProjetoController(em);

            Mentor mentorDaSessao = Sessao.getMentorLogado();
            if (mentorDaSessao == null) {
                JOptionPane.showMessageDialog(parentFrame, "Nenhum mentor logado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // CORREÇÃO: Busca a MentorEntity a partir do ID do mentor da sessão
            // Isso garante que estamos usando a entidade correta para a consulta JPQL
            MentorEntity mentorEntity = em.find(MentorEntity.class, mentorDaSessao.getId());
            if (mentorEntity == null) {
                JOptionPane.showMessageDialog(parentFrame, "Erro ao carregar dados do mentor.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<ProjetoEntity> projetosDoMentor = projetoController.listarProjetosPorMentor(mentorEntity);

            if (projetosDoMentor.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Você não está vinculado a nenhum projeto.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            ProjetoEntity[] projetosArray = projetosDoMentor.toArray(new ProjetoEntity[0]);

            ProjetoEntity projetoSelecionado = (ProjetoEntity) JOptionPane.showInputDialog(
                    parentFrame, "Selecione um projeto para ver os detalhes:", "Meus Projetos",
                    JOptionPane.PLAIN_MESSAGE, null, projetosArray, projetosArray[0]);

            if (projetoSelecionado != null) {
                JInternalFrame internalFrame = new JInternalFrame("Detalhes do Projeto", true, true, true, true);
                internalFrame.setSize(1055, 585);
                internalFrame.setBorder(BorderFactory.createLineBorder(EstilosPadrao.cinzaFundo, 2));
                internalFrame.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

                if (internalFrame.getUI() instanceof javax.swing.plaf.basic.BasicInternalFrameUI ui) {
                    ui.setNorthPane(null);
                }

                JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
                painelConteudo.setBackground(EstilosPadrao.cinzaFundo);
                painelConteudo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                // CORREÇÃO: Usando getNomeDoProjeto()
                JLabel tituloProjeto = new JLabel(projetoSelecionado.getNomeDoProjeto(), SwingConstants.CENTER);
                tituloProjeto.setFont(EstilosPadrao.fonteTitulos);
                tituloProjeto.setForeground(Color.WHITE);
                painelConteudo.add(tituloProjeto, BorderLayout.NORTH);

                JPanel formulario = new JPanel(new GridBagLayout());
                formulario.setBackground(EstilosPadrao.cinzaFundo);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.anchor = GridBagConstraints.WEST;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Área de Atuação
                gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
                formulario.add(new JLabel("Área de Atuação:") {{ setForeground(Color.WHITE); setFont(EstilosPadrao.fonteBotao); }}, gbc);
                gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
                // CORREÇÃO: Usando getAreaDeAtuacao().toString() para converter o Enum para String
                formulario.add(new JTextField(projetoSelecionado.getAreaDeAtuacao().toString(), 30) {{ setEditable(false); setFont(EstilosPadrao.fontePadrao); }}, gbc);

                // REMOÇÃO: O campo 'Status' foi removido pois não existe na entidade.

                // Descrição
                gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.NORTHWEST;
                formulario.add(new JLabel("Descrição:") {{ setForeground(Color.WHITE); setFont(EstilosPadrao.fonteBotao); }}, gbc);
                gbc.gridy++; gbc.anchor = GridBagConstraints.WEST;
                JTextArea txtDescricao = new JTextArea(projetoSelecionado.getDescricao(), 5, 40);
                txtDescricao.setEditable(false);
                txtDescricao.setFont(EstilosPadrao.fontePadrao);
                txtDescricao.setLineWrap(true);
                txtDescricao.setWrapStyleWord(true);
                formulario.add(new JScrollPane(txtDescricao), gbc);

                // Alunos
                gbc.gridy++; gbc.anchor = GridBagConstraints.NORTHWEST;
                formulario.add(new JLabel("Alunos vinculados:") {{ setForeground(Color.WHITE); setFont(EstilosPadrao.fonteBotao); }}, gbc);
                gbc.gridy++; gbc.anchor = GridBagConstraints.WEST;
                String nomesAlunos = projetoSelecionado.getAlunos().stream()
                        .map(AlunoEntity::getNome) // Mantido, assumindo que AlunoEntity tem getNome()
                        .collect(Collectors.joining("\n"));
                JTextArea txtAlunos = new JTextArea(nomesAlunos, 5, 40);
                txtAlunos.setEditable(false);
                txtAlunos.setFont(EstilosPadrao.fontePadrao);
                formulario.add(new JScrollPane(txtAlunos), gbc);


                painelConteudo.add(formulario, BorderLayout.CENTER);
                internalFrame.add(painelConteudo);

                Dimension desktopSize = desktopPane.getSize();
                Dimension frameSize = internalFrame.getSize();
                internalFrame.setLocation((desktopSize.width - frameSize.width) / 2, (desktopSize.height - frameSize.height) / 2);

                internalFrame.setVisible(true);
                desktopPane.add(internalFrame);
                internalFrame.setSelected(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentFrame, "Erro ao carregar os projetos: " + ex.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}