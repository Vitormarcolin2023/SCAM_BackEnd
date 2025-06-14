package org.scam.view.mentor;

import org.scam.controller.LoginMentorController;
import org.scam.model.entities.MentorEntity;
import org.scam.model.repository.CustomizerFactory;
import org.scam.model.repository.StatusMentor;
import org.scam.model.services.Sessao;
import org.scam.view.EstilosPadrao;
import org.scam.view.TelaSelecaoUsuarioView;
import org.scam.controller.classes.Mentor;
import org.scam.controller.dto.MentorDTO;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginTwoMentorView {
    public static void loginTwo() {
        JFrame telaLogin = new JFrame("Login - Mentor");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setLayout(new BorderLayout());
        telaLogin.getContentPane().setBackground(EstilosPadrao.cinzaFundo);

        JPanel topo = new JPanel();
        topo.setBackground(EstilosPadrao.verdeUni);
        topo.setPreferredSize(new Dimension(telaLogin.getWidth(), 50));

        JLabel tituloTopo = new JLabel("SISTEMA DE ACOMPANHAMENTO DE MENTORIAS");
        tituloTopo.setForeground(Color.WHITE);
        tituloTopo.setFont(EstilosPadrao.tituloSAM);
        topo.add(tituloTopo);
        telaLogin.add(topo, BorderLayout.NORTH);

        JPanel containerCentro = new JPanel(new GridBagLayout());
        containerCentro.setBackground(EstilosPadrao.cinzaFundo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(EstilosPadrao.cinzaClaro);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.insets = new Insets(5, 0, 10, 0);
        panelGbc.gridx = 0;
        panelGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tituloLogin = new JLabel("LOGIN - MENTOR");
        tituloLogin.setForeground(Color.WHITE);
        tituloLogin.setFont(EstilosPadrao.fonteTitulos);
        tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
        panelGbc.gridy = 0;
        panel.add(tituloLogin, panelGbc);
        panel.add(Box.createVerticalStrut(10), panelGbc);

        panelGbc.gridy++;
        JLabel email = new JLabel("Email");
        email.setForeground(Color.WHITE);
        email.setFont(EstilosPadrao.fontePadrao);
        panel.add(email, panelGbc);

        panelGbc.gridy++;
        JTextField userField = new JTextField();
        userField.setPreferredSize(new Dimension(250, 30));
        panel.add(userField, panelGbc);

        panelGbc.gridy++;
        JLabel senha = new JLabel("Senha");
        senha.setForeground(Color.WHITE);
        senha.setFont(EstilosPadrao.fontePadrao);
        panel.add(senha, panelGbc);

        panelGbc.gridy++;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 30));
        panel.add(passwordField, panelGbc);

        panelGbc.gridy++;
        panel.add(Box.createVerticalStrut(15), panelGbc);

        panelGbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(EstilosPadrao.verdeUni);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(EstilosPadrao.fonteBotao);
        loginButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        panel.add(loginButton, panelGbc);

        panelGbc.gridy++;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(EstilosPadrao.verdeBotaoVoltar);
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setFont(EstilosPadrao.fonteBotao);
        voltarButton.setPreferredSize(EstilosPadrao.tamanhoBotao);
        panel.add(voltarButton, panelGbc);

        voltarButton.addActionListener(e -> {
            telaLogin.dispose();
            TelaSelecaoUsuarioView.exibirTelaSelecao();
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = userField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                EntityManager em = CustomizerFactory.getEntityManager();
                LoginMentorController controller = new LoginMentorController(em);
                MentorEntity mentor = controller.autenticar(email, senha);

                if (mentor == null) {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StatusMentor status = controller.verificarStatus(mentor);

                switch (status) {
                    case PENDENTE:
                        JOptionPane.showMessageDialog(null, "Seu perfil ainda está em análise pela coordenação. Aguarde a aprovação.");
                        break;

                    case NEGADO:
                        String motivo = controller.obterMotivoNegacao(mentor);
                        JOptionPane.showMessageDialog(null, "Seu cadastro foi negado.\nMotivo: " + motivo + "\nVocê poderá reenviar seu cadastro agora.");
                        controller.reenviarCadastro(mentor);
                        break;

                    case DESATIVO:
                        int opcao = JOptionPane.showOptionDialog(
                                null,
                                "Sua conta está desativada. Deseja reativar?",
                                "Conta Desativada",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new String[]{"Ativar Conta", "Sair"},
                                "Sair"
                        );
                        if (opcao == JOptionPane.YES_OPTION) {
                            controller.reativarMentor(mentor);
                            mentor.setStatus(StatusMentor.PENDENTE); // Atualiza localmente também
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Solicitação de reativação enviada. A coordenação deverá aprovar sua reativação para ativar sua conta.",
                                    "Reativação Pendente",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            Sessao.setMentorLogado(mentor.toMentor());
                            telaLogin.dispose();
                            TelaSelecaoUsuarioView.exibirTelaSelecao();
                        } else {
                            telaLogin.dispose();
                            TelaSelecaoUsuarioView.exibirTelaSelecao();
                        }
                        break;



                    case ATIVO:
                        MentorDTO mentorDTO = new MentorDTO(
                                mentor.getIdMentor(), // tipo int compatível com o seu DTO
                                mentor.getNome(),
                                mentor.getEmail()
                        );

                        Mentor mentorObj = new Mentor(
                                mentorDTO.getId(),
                                mentorDTO.getNome(),
                                mentor.getCpf(),
                                mentorDTO.getEmail(),
                                mentor.getSenha(),
                                mentor.getTipoDeUsuario(),
                                mentor.getTelefone(),
                                mentor.getTempoDeExperiencia(),
                                mentor.getTipoDeVinculo(),
                                mentor.getAreaDeAtuacao(),
                                mentor.getEndereco()
                        );

                        Sessao.setMentorLogado(mentorObj);
                        JOptionPane.showMessageDialog(null, "Bem-vindo(a), " + mentorObj.getNome());
                        telaLogin.dispose();
                        TelaInicialMentor.telaMentor();
                        break;
                }

                em.close();
            }
        });

        containerCentro.add(panel, gbc);
        telaLogin.add(containerCentro, BorderLayout.CENTER);
        telaLogin.setVisible(true);
    }
}