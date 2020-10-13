package com.curs.gui;

import com.curs.database.DBaseUA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminFrame extends JFrame {
    private JButton buttonProvider;
    private JButton buttonUser;
    private JTextField text_id;
    private JButton buttonDeleteId;
    private JPanel panelMain;
    private JLabel label_id;
    private JLabel labelAlert;
    private JButton buttonUpdate;

    public AdminFrame() {
        super("Окно администратора");
        labelAlert.setForeground(Color.gray);
        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        buttonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DataBaseUser().setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        buttonProvider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DataBaseProvider().setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        buttonDeleteId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!text_id.getText().isEmpty() && isNumber(text_id.getText())) {
                    try {
                        new DBaseUA().deleteUserById(text_id.getText());
                        labelAlert.setForeground(Color.gray);
                        labelAlert.setText("*вводить можно только целое число");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    labelAlert.setForeground(Color.red);
                    labelAlert.setText("ошибка: введите целое число");
                }
            }
        });
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DBaseUA().updateTable();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}
