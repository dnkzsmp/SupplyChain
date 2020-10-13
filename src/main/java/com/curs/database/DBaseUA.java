package com.curs.database;

import com.curs.user.User;

import java.sql.*;

public class DBaseUA implements Connector {
    private Connection connection = null;
    private final String URL = "jdbc:mysql://localhost:3306/curs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USERNAME = "danil";
    private final String PASSWORD = "root";

    private void saveData(String name, String surname, int age, String email, int price) {
        String sql = "insert into users (name, surname, age, email, price) values (?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, age);
            statement.setString(4, email);
            statement.setInt(5, price);
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
            saveData(
                    user.getName(),
                    user.getSurname(),
                    user.getAge(),
                    user.getEmail(),
                    user.getPrice()
            );
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
                    "', age = " + Integer.parseInt(arr[2]) +
                    ", email = '" + arr[3] +
                    "', price = " + Integer.parseInt(arr[4]) +
                    " WHERE name = '" + user.getName() +
                    "' AND surname = '" + user.getSurname() +
                    "' AND age = " + user.getAge() +
                    " AND price = " + user.getPrice();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }

    public void deleteUserById(String id) throws SQLException {
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Ошибка подключения к БД (DBaseUA.ChangeDataInDB)");
            ex.printStackTrace();
        } finally {
            String sql = "DELETE FROM users WHERE id = " + Integer.parseInt(id);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }

    public void updateTable() throws SQLException {
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Ошибка подключения к БД (DBaseUA.ChangeDataInDB)");
            ex.printStackTrace();
        } finally {
            String sql1 = "SET @num := 0";
            String sql2 = "UPDATE users SET id = @num := (@num + 1)";
            DBaseProvider.updating(sql1, sql2, connection);
        }
    }
}