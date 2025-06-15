package org.scam.view.Reuniao;

import org.scam.model.entities.ProjetoEntity;
import org.scam.model.entities.ReuniaoEntity;
import org.scam.model.repository.StatusReuniao;
import org.scam.model.repository.TipoUsuario;
import org.scam.controller.ReuniaoController;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VisualizarReunioesView {

    private static ReuniaoController reuniaoController = new ReuniaoController();

    public static JInternalFrame visualizarReunioes(JDesktopPane desktop, TipoUsuario tipoUsuario) {

        List<ProjetoEntity> projetosTemp = new ArrayList<>();
        if (tipoUsuario.equals(TipoUsuario.ALUNO)) {
            projetosTemp = ReuniaoController.buscarProjetosAluno(Sessao.getAlunoLogado().getRa());
        } else if (tipoUsuario.equals(TipoUsuario.MENTOR)) {
            projetosTemp = ReuniaoController.buscarProjetosMentor(Sessao.getMentorLogado().getId());
        }

        final List<ProjetoEntity> projetos = projetosTemp; // efetivamente final agora

        List<ReuniaoEntity> reunioes = reuniaoController.getReunioes(projetos);

        JInternalFrame internalFrame = new JInternalFrame();
        internalFrame.setSize(EstilosPadrao.tamanhoInternalFrame);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) internalFrame.getUI();
        ui.setNorthPane(null); // remove a barra de título

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(EstilosPadrao.cinzaFundo);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel lblTitulo = new JLabel("Reuniões");
        lblTitulo.setFont(EstilosPadrao.fonteTitulos);
        lblTitulo.setForeground(EstilosPadrao.verdeUni);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Painel Central com Filtro e Lista
        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BorderLayout(10, 10));
        painelCentro.setBackground(EstilosPadrao.cinzaFundo);

        // Filtro
        JPanel painelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelFiltro.setBackground(EstilosPadrao.cinzaFundo);
        JLabel labelFiltro = new JLabel("Filtrar por:");
        labelFiltro.setFont(EstilosPadrao.fontePadrao);
        labelFiltro.setForeground(Color.WHITE);

        JComboBox<String> filtroReuniao = new JComboBox<>(new String[]{"Agendada", "Aguardando Confirmação", "Realizada", "Cancelada"});
        filtroReuniao.setFont(EstilosPadrao.fonteBotao);
        painelFiltro.add(labelFiltro);
        painelFiltro.add(filtroReuniao);

        // HashMap para associar as opções do comboBox com o Enum
        HashMap<String, StatusReuniao> mapStatus = new HashMap<>();
        mapStatus.put("Agendada", StatusReuniao.AGENDADA);
        mapStatus.put("Aguardando Confirmação", StatusReuniao.AGUARDANDO_CONFIRMACAO);
        mapStatus.put("Realizada", StatusReuniao.REALIZADA);
        mapStatus.put("Cancelada", StatusReuniao.CANCELADA);

        painelCentro.add(painelFiltro, BorderLayout.NORTH);

        // Painel de lista de reuniões
        JPanel painelLista = new JPanel();
        painelLista.setLayout(new BoxLayout(painelLista, BoxLayout.Y_AXIS));
        painelLista.setBackground(EstilosPadrao.cinzaFundo);

        JScrollPane scroll = new JScrollPane(painelLista);
        painelCentro.add(scroll, BorderLayout.CENTER);

        // Função para atualizar a lista com base no filtro
        Runnable atualizarLista = () -> {
            painelLista.removeAll();
            String filtro = (String) filtroReuniao.getSelectedItem();
            StatusReuniao statusSelecionado = mapStatus.get(filtro);

            if (reunioes.isEmpty()) {
                JLabel msgSemReunioes = new JLabel("Sem reuniões");
                msgSemReunioes.setForeground(Color.white);
                msgSemReunioes.setFont(EstilosPadrao.fontePadrao);
                msgSemReunioes.setAlignmentX(Component.CENTER_ALIGNMENT);
                painelLista.add(msgSemReunioes);
            } else {
                boolean encontrou = false;
                for (ReuniaoEntity r : reunioes) {
                    if (r.getStatusReuniao().equals(statusSelecionado)) {
                        encontrou = true;

                        JPanel painelReuniao = new JPanel(new BorderLayout());
                        painelReuniao.setBackground(EstilosPadrao.cinzaClaro);
                        painelReuniao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
                        painelReuniao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                        JPanel linhaInfo = new JPanel(new BorderLayout());
                        linhaInfo.setBackground(EstilosPadrao.cinzaClaro);

                        JPanel painelLabels = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        painelLabels.setBackground(EstilosPadrao.cinzaClaro);

                        JLabel lblId = new JLabel("Solicitante: " + r.getSolicitante());
                        lblId.setForeground(Color.WHITE);
                        lblId.setFont(EstilosPadrao.fontePadrao);

                        JLabel lblMotivo = new JLabel("Motivo: " + r.getMotivoReuniao());
                        lblMotivo.setForeground(Color.WHITE);
                        lblMotivo.setFont(EstilosPadrao.fontePadrao);

                        painelLabels.add(lblId);
                        painelLabels.add(Box.createHorizontalStrut(20));
                        painelLabels.add(lblMotivo);

                        JButton btnDetalhes = new JButton("Detalhes");
                        btnDetalhes.setBackground(EstilosPadrao.verdeUni);
                        btnDetalhes.setForeground(Color.WHITE);
                        btnDetalhes.setFocusPainted(false);
                        btnDetalhes.setFont(EstilosPadrao.fonteBotao);
                        btnDetalhes.setPreferredSize(EstilosPadrao.tamanhoBotao);

                        btnDetalhes.addActionListener(e -> mostrarDetalhesReuniao(r, desktop, tipoUsuario));

                        linhaInfo.add(painelLabels, BorderLayout.WEST);
                        linhaInfo.add(btnDetalhes, BorderLayout.EAST);

                        painelReuniao.add(linhaInfo, BorderLayout.CENTER);

                        painelLista.add(painelReuniao);
                        painelLista.add(Box.createVerticalStrut(10));
                    }
                }

                if (!encontrou) {
                    JLabel msgSemReunioes = new JLabel("Sem reuniões com esse status");
                    msgSemReunioes.setForeground(Color.white);
                    msgSemReunioes.setFont(EstilosPadrao.fontePadrao);
                    msgSemReunioes.setAlignmentX(Component.CENTER_ALIGNMENT);
                    painelLista.add(msgSemReunioes);
                }
            }

            painelLista.revalidate();
            painelLista.repaint();
        };

        filtroReuniao.addActionListener(e -> atualizarLista.run());

        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        // Carrega inicialmente com Agendadas
        filtroReuniao.setSelectedItem("Agendada");
        atualizarLista.run();

        JPanel painelFechar = new JPanel(new BorderLayout());
        painelFechar.setBackground(EstilosPadrao.cinzaFundo);
        painelFechar.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        JButton fechar = new JButton("Fechar");
        fechar.setSize(EstilosPadrao.tamanhoBotao);
        fechar.setBackground(EstilosPadrao.verdeUni);
        fechar.setForeground(Color.white);
        painelFechar.add(fechar, BorderLayout.WEST);

        fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internalFrame.dispose();
            }
        });

        painelPrincipal.add(painelFechar, BorderLayout.SOUTH);

        internalFrame.add(painelPrincipal, BorderLayout.CENTER);
        internalFrame.setVisible(true);
        return internalFrame;
    }

    private static void mostrarDetalhesReuniao(ReuniaoEntity r, JDesktopPane desktop, TipoUsuario tipoUsuario) {
        JInternalFrame detalhesFrame = new JInternalFrame("Detalhes da Reunião", true, true, true, true);
        detalhesFrame.setSize(700, 250);
        detalhesFrame.setLayout(new BorderLayout());
        detalhesFrame.setLocation(120, 120);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) detalhesFrame.getUI();
        ui.setNorthPane(null); // remove a barra de título

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.setBackground(EstilosPadrao.cinzaFundo);

        Font fonte = EstilosPadrao.fontePadrao;
        Color cor = Color.WHITE;

        LocalDate data = r.getDataReuniao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);

        painel.add(criarLabel("Projeto: " + r.getProjeto(), fonte, cor));
        painel.add(criarLabel("Solicitante: " + r.getSolicitante(), fonte, cor));
        painel.add(criarLabel("Motivo: " + r.getMotivoReuniao(), fonte, cor));
        painel.add(criarLabel("Status: " + r.getStatusReuniao().name(), fonte, cor));
        painel.add(criarLabel("Data: " + dataFormatada, fonte, cor));
        painel.add(criarLabel("Hora: " + r.getHorarioReuniao(), fonte, cor));
        painel.add(criarLabel("Formato: " + r.getTipoReuniao(), fonte, cor));
        if(r.getStatusReuniao() == StatusReuniao.CANCELADA){
            painel.add(criarLabel("Motivo Cancelamento: " + r.getMotivoCancelamento(), fonte, cor));
        }

        // PAINEL DE BOTÕES
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotoes.setBackground(EstilosPadrao.cinzaFundo);
        painelBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);
        Dimension tamanho = new Dimension(110, 25);

        // Botão Fechar
        JButton fechar = new JButton("Fechar");
        fechar.setBackground(EstilosPadrao.verdeUni);
        fechar.setForeground(Color.white);
        fechar.setFont(EstilosPadrao.fonteBotao);
        fechar.setPreferredSize(tamanho);
        fechar.addActionListener(e -> detalhesFrame.dispose());

        // Botão Cancelar
        JButton cancelar = new JButton("Cancelar");
        cancelar.setBackground(EstilosPadrao.verdeUni);
        cancelar.setForeground(Color.white);
        cancelar.setFont(EstilosPadrao.fonteBotao);
        cancelar.setPreferredSize(tamanho);

        cancelar.addActionListener(e -> {
            String motivoCancelamento = JOptionPane.showInputDialog(
                    detalhesFrame,
                    "Informe o motivo do cancelamento:",
                    "Cancelar Reunião",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (motivoCancelamento != null && !motivoCancelamento.trim().isEmpty()) {
                boolean sucesso = ReuniaoController.cancelarReuniao(r.getId(), motivoCancelamento.trim());

                if (sucesso) {
                    JOptionPane.showMessageDialog(
                            detalhesFrame,
                            "Reunião cancelada com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    atualizaTela(detalhesFrame, desktop, tipoUsuario);
                } else {
                    JOptionPane.showMessageDialog(
                            detalhesFrame,
                            "Erro ao cancelar a reunião.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        detalhesFrame,
                        "Cancelamento abortado: é necessário informar um motivo.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // Verifica se deve mostrar "Confirmar" ou "Realizada"
        if (r.getStatusReuniao() == StatusReuniao.AGUARDANDO_CONFIRMACAO) {
            JButton confirmar = new JButton("Confirmar");
            confirmar.setBackground(EstilosPadrao.verdeUni);
            confirmar.setForeground(Color.white);
            confirmar.setFont(EstilosPadrao.fonteBotao);
            confirmar.setPreferredSize(tamanho);
            confirmar.addActionListener(e -> {
                if (ReuniaoController.alterarStatus(r.getId(), StatusReuniao.AGENDADA)) {
                    JOptionPane.showMessageDialog(null, "Reunião confirmada!");
                    atualizaTela(detalhesFrame, desktop, tipoUsuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Algo deu errado, tente novamente");
                }
            });
            painelBotoes.add(confirmar);
            painelBotoes.add(cancelar);
        } else if (r.getStatusReuniao() == StatusReuniao.AGENDADA) {
            JButton realizada = new JButton("Realizada");
            realizada.setBackground(EstilosPadrao.verdeUni);
            realizada.setForeground(Color.white);
            realizada.setFont(EstilosPadrao.fonteBotao);
            realizada.setPreferredSize(tamanho);
            realizada.addActionListener(e -> {
                if (ReuniaoController.alterarStatus(r.getId(), StatusReuniao.REALIZADA)) {
                    JOptionPane.showMessageDialog(null, "Reunião marcada como realizada!");


                } else {
                    JOptionPane.showMessageDialog(null, "Algo deu errado, tente novamente");
                }
            });
            painelBotoes.add(realizada);
            painelBotoes.add(cancelar);
        }

        // Desabilita "Cancelar" se a reunião já foi realizada
        if (r.getStatusReuniao() == StatusReuniao.REALIZADA) {
            cancelar.setEnabled(false);
            cancelar.setToolTipText("Não é possível cancelar uma reunião já realizada.");
            atualizaTela(detalhesFrame, desktop, tipoUsuario);
        }

        //painelBotoes.add(cancelar);*/
        painelBotoes.add(fechar);

        painel.add(Box.createVerticalStrut(20));
        painel.add(painelBotoes);

        detalhesFrame.add(painel, BorderLayout.CENTER);
        detalhesFrame.setVisible(true);
        desktop.add(detalhesFrame);
        try {
            detalhesFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }



    private static JLabel criarLabel(String texto, Font fonte, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(fonte);
        label.setForeground(cor);
        label.setAlignmentX(Component.LEFT_ALIGNMENT); // Essencial para alinhar no BoxLayout
        return label;
    }

    private static void atualizaTela(JInternalFrame detalhesFrame, JDesktopPane desktop, TipoUsuario tipoUsuario){
        detalhesFrame.dispose();
        desktop.removeAll();

        // Adiciona o novo frame
        JInternalFrame internalFrame = VisualizarReunioesView.visualizarReunioes(desktop, tipoUsuario);
        desktop.add(internalFrame);
        desktop.repaint();

        // Centraliza o novo frame
        int x = (desktop.getWidth() - internalFrame.getWidth()) / 2;
        int y = (desktop.getHeight() - internalFrame.getHeight()) / 2;
        internalFrame.setLocation(x, y);
    }
}
