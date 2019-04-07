package com.ifpb.Projeto.Vision;

import javax.swing.*;

/**
 * Tela de acesso inicial (Login)
 */
public class TelaDeLogin extends JFrame{
    private JTextField userField;
    private JButton loginButton;
    private JPanel tellog;
    private JLabel loginLabel;
    private JLabel labelImage;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField1;
    private String login;
    private String senha;

    public TelaDeLogin() {
        super("Tela de Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setContentPane(tellog);
        ImageIcon logIcon = new ImageIcon("imagens/lock.png");
        setIconImage(logIcon.getImage());
        login = "Admin";
        senha = "admin";

            loginButton.addActionListener(e -> {
                //abre Janela Menu
                if ((userField.getText().equals("")) || (passwordField1.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos");
                } else if ((login.equals(userField.getText())) && (senha.equals(passwordField1.getText()))) {
                    TelaMenu menu = new TelaMenu();
                    menu.setVisible(true);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Usúario ou senha incorretos!");
                }
            });
        }
    private void createUIComponents() {

        ImageIcon controlaPedido = new ImageIcon("imagens/loginIcon.png");
        labelImage = new JLabel(controlaPedido);
        ImageIcon log = new ImageIcon("imagens/KEY.png");
        loginButton = new JButton(log);

    }

}
