package org.scam.view.aluno;

import org.scam.controller.classes.Aluno;
import org.scam.model.entities.ProjetoEntity;
import org.scam.model.entities.ReuniaoEntity;
import org.scam.model.repository.StatusReuniao;
import org.scam.model.services.ReuniaoService;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class VisualizarReunioesAluView {

    private static ReuniaoService reuniaoService = new ReuniaoService();

    public static JInternalFrame visualizarReunioes() {

        List<ProjetoEntity> projetos = reuniaoService.buscarProjetos(Sessao.getRaAluno());
        List<ReuniaoEntity> reunioes = reuniaoService.getReunioes(projetos);

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

            if(reunioes.isEmpty()){
                JLabel msgSemReunioes = new JLabel("Sem reuniões");
                msgSemReunioes.setForeground(Color.white);
                msgSemReunioes.setFont(EstilosPadrao.fontePadrao);
                msgSemReunioes.setAlignmentX(Component.CENTER_ALIGNMENT);
                msgSemReunioes.setAlignmentY(Component.CENTER_ALIGNMENT);
                painelLista.add(msgSemReunioes);
            }

            else {
                boolean encontrou = false;
                for (ReuniaoEntity r : reunioes) {
                    if (r.getStatusReuniao().equals(statusSelecionado)) {
                        encontrou = true;

                        JPanel painelReuniao = new JPanel();
                        painelReuniao.setLayout(new GridLayout(2, 1));
                        painelReuniao.setBackground(EstilosPadrao.cinzaClaro);
                        painelReuniao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
                        painelReuniao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                        JPanel linhaInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        linhaInfo.setBackground(EstilosPadrao.cinzaClaro);

                        JLabel lblId = new JLabel("ID: " + r.getId());
                        lblId.setForeground(Color.WHITE);
                        lblId.setFont(EstilosPadrao.fontePadrao);

                        JLabel lblMotivo = new JLabel("Motivo: " + r.getMotivoReuniao());
                        lblMotivo.setForeground(Color.WHITE);
                        lblMotivo.setFont(EstilosPadrao.fontePadrao);

                        linhaInfo.add(lblId);
                        linhaInfo.add(Box.createHorizontalStrut(20));
                        linhaInfo.add(lblMotivo);

                        JPanel linhaBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                        linhaBotoes.setBackground(EstilosPadrao.cinzaClaro);

                        JButton btnDetalhes = new JButton("Detalhes");
                        JButton btnRealizar = new JButton("Realizar");
                        JButton btnAceitar = new JButton("Aceitar");
                        JButton btnCancelar = new JButton("Cancelar");

                        JButton[] botoes = {btnDetalhes, btnRealizar, btnAceitar, btnCancelar};
                        for (JButton btn : botoes) {
                            btn.setBackground(EstilosPadrao.verdeUni);
                            btn.setForeground(Color.WHITE);
                            btn.setFocusPainted(false);
                            btn.setFont(EstilosPadrao.fontePadrao);
                            linhaBotoes.add(btn);
                        }

                        // Ação para abrir os detalhes
                        btnDetalhes.addActionListener(e -> {
                            String detalhes = """
                        ID: %d
                        Motivo: %s
                        Status: %s
                        Data: %s
                        Hora: %s
                        Formato: %s
                        """
                                    .formatted(
                                            r.getId(),
                                            r.getMotivoReuniao(),
                                            r.getStatusReuniao().name(),
                                            r.getDataReuniao(), // ajuste conforme seu campo real
                                            r.getHorarioReuniao(),
                                            r.getTipoReuniao().toString()
                                    );

                            JOptionPane.showMessageDialog(null, detalhes, "Detalhes da Reunião", JOptionPane.INFORMATION_MESSAGE);
                        });

                        // Exemplos de ação
                        btnRealizar.addActionListener(e -> {
                            JOptionPane.showMessageDialog(null, "Reunião " + r.getId() + " marcada como realizada!");
                            // reuniaoService.marcarComoRealizada(r);
                        });

                        btnAceitar.addActionListener(e -> {
                            JOptionPane.showMessageDialog(null, "Reunião " + r.getId() + " aceita!");
                            // reuniaoService.aceitarReuniao(r);
                        });

                        btnCancelar.addActionListener(e -> {
                            JOptionPane.showMessageDialog(null, "Reunião " + r.getId() + " cancelada!");
                            // reuniaoService.cancelarReuniao(r);
                        });

                        painelReuniao.add(linhaInfo);
                        painelReuniao.add(linhaBotoes);

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
}
