package org.scam.view;

import org.scam.controller.classes.Coordenador;
import org.scam.controller.menus.MenuCoordenador;
import org.scam.model.entities.CoordenacaoEntity;
import org.scam.model.entities.UsuarioEntity;
import org.scam.model.repository.CoordenacaoRepository;
import org.scam.model.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginCoordenaçãoView {

    public static void loginCoordenacao() {
        JFrame telaLogin = new JFrame("Login - Coordenação");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setSize(300, 180);
        telaLogin.setLocationRelativeTo(null); // centraliza a janela

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Email:");
        JTextField userField = new JTextField(10);

        JLabel passwordLabel = new JLabel("Senha:");
        JPasswordField passwordField = new JPasswordField(10);

        JButton loginButton = new JButton("Login");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // espaço vazio
        panel.add(loginButton);

        telaLogin.getContentPane().add(panel);
        telaLogin.setVisible(true);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = userField.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();

                EntityManager em = CustomizerFactory.getEntityManager();
                CoordenacaoRepository coordenacaoRepository = new CoordenacaoRepository(em);

                UsuarioEntity usuario = coordenacaoRepository.login(email, senha);

                if (usuario != null && usuario instanceof CoordenacaoEntity) {
                    Coordenador coordenador = ((CoordenacaoEntity) usuario).toCoordenador();

                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                    telaLogin.dispose(); // fecha a janela de login

                    MenuCoordenador menu = new MenuCoordenador(coordenador);
                    menu.exibirMenu(); // chama menu da coordenação (CLI)
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                em.close();
            }
        });
    }
}