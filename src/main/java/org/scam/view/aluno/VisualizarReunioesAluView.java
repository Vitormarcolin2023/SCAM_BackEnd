package org.scam.view.aluno;

import org.scam.model.entities.ReuniaoEntity;
import org.scam.model.services.ReuniaoService;
import org.scam.view.EstilosPadrao;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisualizarReunioesAluView {

    public static JInternalFrame visualizarReunioes() {

        ReuniaoService reuniaoService = new ReuniaoService();
        List<ReuniaoEntity> reunioes = reuniaoService.getReunioes(80554);

        JInternalFrame internalFrame = new JInternalFrame();
        internalFrame.setSize(1055, 585);
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

            if(reunioes.isEmpty()){
                JLabel msgSemReunioes = new JLabel("Sem reuniões");
                msgSemReunioes.setForeground(Color.white);
                msgSemReunioes.setFont(EstilosPadrao.fontePadrao);
                msgSemReunioes.setAlignmentX(Component.CENTER_ALIGNMENT);
                msgSemReunioes.setAlignmentY(Component.CENTER_ALIGNMENT);
                painelLista.add(msgSemReunioes);
            }

            else {
                for (ReuniaoEntity r : reunioes) {
                    if (r.getStatusReuniao().toString().equalsIgnoreCase(filtro)) {
                        JButton btn = new JButton(r.toString());
                        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
                        btn.setFocusPainted(false);
                        btn.setBackground(EstilosPadrao.cinzaClaro);
                        btn.setForeground(Color.WHITE);
                        btn.setFont(EstilosPadrao.fontePadrao);

                        // btn.addActionListener(e -> mostrarDetalhes(r, painelLista));
                        painelLista.add(btn);
                        painelLista.add(Box.createVerticalStrut(10));
                    }
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
