package com.curs.gui;

import com.curs.comboclasses.Company;
import com.curs.comboclasses.Year;
import com.curs.database.DBaseProvider;
import com.curs.database.DBaseUA;
import com.curs.provider.Provider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProviderFrame extends JFrame {
    private JPanel panelMain;
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JLabel labelHello;
    private JLabel labelCompany;
    private JComboBox<Company> comboCompany;
    private JLabel labelModel;
    private JLabel labelYear;
    private JComboBox<Year> comboYear;
    private JLabel labelPrice;
    private JTextField textPrice;
    private JButton buttonUsers;
    private JButton buttonProvider;
    private JButton buttonAdd;
    private JLabel label_id;
    private JTextField text_id;
    private JButton buttonDeleteUser;
    private JTextField textModel;
    private JLabel labelAlert;
    private JLabel labelAlertPrice;
    private JButton buttonDeleteProvider;
    private JButton buttonUpdate;

    public ProviderFrame() {
        super("Окно поставок");
        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        updateComboCompany(comboCompany);
        updateComboYear(comboYear);
        buttonUsers.addActionListener(new ActionListener() {
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
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNumber(textPrice.getText()) || !textPrice.getText().isEmpty()) {
                    if (!textModel.getText().isEmpty()) {
                        Provider provider = new Provider(
                                String.valueOf(comboCompany.getSelectedItem()),
                                textModel.getText(),
                                Integer.parseInt(String.valueOf(comboYear.getSelectedItem())),
                                Integer.parseInt(textPrice.getText())
                        );
                        labelAlertPrice.setText("");
                        textPrice.setText("");
                        textModel.setText("");
                        try {
                            new DBaseProvider().setNewProviderInDB(provider);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        labelAlertPrice.setText("Заполните поля");
                        labelAlertPrice.setForeground(Color.red);
                    }
                } else {
                    labelAlertPrice.setText("Введите целое число");
                    labelAlertPrice.setForeground(Color.red);
                }
            }
        });
        buttonDeleteUser.addActionListener(new ActionListener() {
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
        buttonDeleteProvider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!text_id.getText().isEmpty() && isNumber(text_id.getText())) {
                    try {
                        new DBaseProvider().deleteProviderById(text_id.getText());
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
                    new DBaseProvider().updateTable();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void updateComboCompany(JComboBox<Company> combo) {
        combo.addItem(new Company("", 1));
        combo.addItem(new Company("Samsung", 2));
        combo.addItem(new Company("Apple", 3));
        combo.addItem(new Company("Xiaomi", 4));
        combo.addItem(new Company("Huawei", 5));
        combo.addItem(new Company("Nokia", 6));
        combo.addItem(new Company("Honor", 7));
        combo.addItem(new Company("Meizu", 8));
    }

    private void updateComboYear(JComboBox<Year> combo) {
        combo.addItem(new Year("", 1));
        combo.addItem(new Year("2015", 2));
        combo.addItem(new Year("2016", 3));
        combo.addItem(new Year("2017", 4));
        combo.addItem(new Year("2018", 5));
        combo.addItem(new Year("2019", 6));
        combo.addItem(new Year("2020", 7));
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty())
            return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }
}
