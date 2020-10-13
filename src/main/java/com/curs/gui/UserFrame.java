package com.curs.gui;

import com.curs.database.DBaseUA;
import com.curs.user.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserFrame extends JFrame {
    private JList listOfUsers;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JTextField textFieldAge;
    private JTextField textFieldEmail;
    private JTextField textFieldPrice;
    private JButton buttonSend;
    private JPanel panelMain;
    private JButton buttonSave;
    private JButton buttonExit;
    private JLabel labelAlert;
    private JButton buttonShow;
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JLabel labelName;
    private JLabel labelSurname;
    private JLabel labelAge;
    private JLabel labelEmail;
    private JLabel labelPrice;
    private final ArrayList<User> people;
    private final String [] arrOfTextFields = new String[5];
    private final DefaultListModel listPeopleModel;

    public UserFrame() {
        super("Поставка бренда");
        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        people = new ArrayList<>();
        final DBaseUA baseAdmin = new DBaseUA();
        listPeopleModel = new DefaultListModel();
        listOfUsers.setModel(listPeopleModel);
        buttonSave.setEnabled(false);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userNumber = listOfUsers.getSelectedIndex();
                if (userNumber >= 0) {
                    if (!textFieldAge.getText().isEmpty() &&
                            !textFieldPrice.getText().isEmpty() &&
                            !textFieldEmail.getText().isEmpty() &&
                            !textFieldName.getText().isEmpty() &&
                            !textFieldSurname.getText().isEmpty()) {
                        if (isNumber(textFieldAge.getText()) && isNumber(textFieldPrice.getText())) {
                            arrOfTextFields[0] = textFieldName.getText();
                            arrOfTextFields[1] = textFieldSurname.getText();
                            arrOfTextFields[2] = textFieldAge.getText();
                            arrOfTextFields[3] = textFieldEmail.getText();
                            arrOfTextFields[4] = textFieldPrice.getText();
                            User user = people.get(userNumber);
                            refreshUser();
                            try {
                                baseAdmin.changeDataInDB(user, arrOfTextFields);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            buttonSave.setEnabled(false);
                            labelAlert.setText("Ошибка: введите число в поле 'Возраст'");
                            labelAlert.setForeground(Color.red);
                        }
                    } else {
                        buttonSave.setEnabled(false);
                        labelAlert.setText("Ошибка: заполните все поля");
                        labelAlert.setForeground(Color.red);
                    }
                }
            }
        });

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user;
                if (!textFieldAge.getText().isEmpty() &&
                        !textFieldPrice.getText().isEmpty() &&
                        !textFieldEmail.getText().isEmpty() &&
                        !textFieldName.getText().isEmpty() &&
                        !textFieldSurname.getText().isEmpty()) {
                    if (isNumber(textFieldAge.getText())) {
                        user = new User(
                                textFieldName.getText(),
                                textFieldSurname.getText(),
                                Integer.parseInt(textFieldAge.getText()),
                                textFieldEmail.getText(),
                                Integer.parseInt(textFieldPrice.getText())
                        );
                        try {
                            baseAdmin.setNewUserInDB(user);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        addUser(user);
                    } else {
                        labelAlert.setText("Ошибка: ведите число в поле 'Возраст'");
                        labelAlert.setForeground(Color.red);
                    }
                } else {
                    labelAlert.setText("Ошибка: заполните все поля");
                    labelAlert.setForeground(Color.red);
                }
            }
        });
        buttonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DataBaseUser().setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        listOfUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int userNumber = listOfUsers.getSelectedIndex();
                if (userNumber >= 0) {
                            User u = people.get(userNumber);
                            textFieldName.setText(u.getName());
                            textFieldSurname.setText(u.getSurname());
                            textFieldAge.setText(Integer.toString(u.getAge()));
                            textFieldEmail.setText(u.getEmail());
                            textFieldPrice.setText(Integer.toString(u.getPrice()));
                            buttonSave.setEnabled(true);
                        } else {
                    buttonSave.setEnabled(false);
                }
            }
        });
        setLocationRelativeTo(null);
    }

    private void refreshUser() {
        listPeopleModel.removeAllElements();
        System.out.println("Очистил лист пользователей");
        for (User u : people) {
            System.out.println("Добавил пользователя в лист: " + u.getName());
            listPeopleModel.addElement(u.getName());
        }
    }

    private void addUser(User user) {
        people.add(user);
        refreshUser();
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}
