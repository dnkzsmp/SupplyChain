package com.curs.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DataBaseUser extends JFrame {
    public DataBaseUser() throws SQLException {
        super("База данных запросов");
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("Имя");
        model.addColumn("Фамилия");
        model.addColumn("Возраст");
        model.addColumn("Почта");
        model.addColumn("Цена");
        pack();
        Connection connection = null;
        try {
            System.out.println("Устанавливаем соединение с БД");
            String URL = "jdbc:mysql://localhost:3306/curs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String USERNAME = "danil";
            String PASSWORD = "root";
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                });
            }
            JTable table = new JTable(model);
            table.setColumnSelectionAllowed(false);
            table.setRowSelectionAllowed(false);
            JScrollPane scrollPane = new JScrollPane(table);
            Container container = this.getContentPane();
            container.add(scrollPane);
            this.pack();
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setResizable(true);
        } catch (Exception ex) {
            System.out.println("Ошибка подключения к БД (DataBaseUser.constructor)");
            ex.printStackTrace();
        } finally {
            assert connection != null;
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }
}
