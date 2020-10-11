package com.curs.database;

import com.curs.gui.DataBaseUser;
import com.curs.user.User;

import java.sql.*;

public class DBaseUA {
    private Connection connection = null;
    private final String URL = "jdbc:mysql://localhost:3306/curs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USERNAME = "danil";
    private final String PASSWORD = "root";

    private void saveData(String name, String surname, int age, String email, String brand) {
        String sql = "insert into users (name, surname, age, email, brand) values (?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, age);
            statement.setString(4, email);
            statement.setString(5, brand);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setNewUserInDB(User user) throws SQLException {
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Ошибка подключения к БД (DBaseUA.setNewUserInDB)");
            ex.printStackTrace();
        } finally {
            saveData(user.getName(), user.getSurname(), user.getAge(), user.getEmail(), user.getBrand());
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }

    public void changeDataInDB(User user, String [] arr) throws SQLException {
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Ошибка подключения к БД (DBaseUA.ChangeDataInDB)");
            ex.printStackTrace();
        } finally {
            String sql = "UPDATE users SET name = '" + arr[0] +
                    "', surname = '" + arr[1] +
                    "', age = " + arr[2] +
                    ", email = '" + arr[3] +
                    "', brand = '" + arr[4] +
                    "' WHERE name = '" + user.getName() +
                    "' AND surname = '" + user.getSurname() +
                    "' AND age = " + user.getAge() +
                    " AND email = '" + user.getEmail() + "'";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }

    public void showDataBaseUser() throws SQLException {
        new DataBaseUser().setVisible(true);
    }
}