package com.curs.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        super("Авторизация");
        createGUI();
    }

    private void createGUI() {
        this.setSize(130, 220);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Авторизуйтесь");
        label1.setFont(Font.getFont("Bold"));
        label1.setSize(35, 35);

        JLabel labelLogin = new JLabel("Логин: ");

        JLabel labelPassword = new JLabel("Пароль: ");

        final JLabel labelCorrect = new JLabel("");

        final JTextField textFieldLogin = new JTextField();
        textFieldLogin.setBackground(Color.WHITE);
        textFieldLogin.setColumns(14);

        final JPasswordField textFieldPassword = new JPasswordField();
        textFieldPassword.setBackground(Color.WHITE);
        textFieldPassword.setColumns(14);
        textFieldPassword.setEchoChar('*');

        JButton button = new JButton("Войти");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (textFieldLogin.getText()) {
                    case "user":
                        if (textFieldPassword.getText().equals("user")) {
                            setVisible(false);
                            new UserFrame().setVisible(true);
                        } else {
                            labelCorrect.setText("Неверный пароль");
                            labelCorrect.setForeground(Color.red);
                        }
                        break;
                    case "admin":
                        if (textFieldPassword.getText().equals("admin")) {
                            setVisible(false);
                            new AdminFrame().setVisible(true);
                        } else {
                            labelCorrect.setText("Неверный пароль");
                            labelCorrect.setForeground(Color.red);
                        }
                        break;
                    case "provider":
                        if (textFieldPassword.getText().equals("provider")) {
                            setVisible(false);
                            new ProviderFrame().setVisible(true);
                        } else {
                            labelCorrect.setText("Неверный пароль");
                            labelCorrect.setForeground(Color.red);
                        }
                        break;
                    default:
                        labelCorrect.setText("Неверный логин");
                        labelCorrect.setForeground(Color.red);
                        break;
                }
            }
        });

        JPanel panel = new JPanel(new VerticalLayout());
        panel.add(label1);
        panel.add(labelLogin);
        panel.add(textFieldLogin);
        panel.add(labelPassword);
        panel.add(textFieldPassword);
        panel.add(button);
        panel.add(labelCorrect);
        setContentPane(panel);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
