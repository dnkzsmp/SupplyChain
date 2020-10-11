package com.curs.database;

import com.curs.user.User;
import java.sql.*;

public class DBaseUser {
    private Connection connection = null;
    private final String URL = "jdbc:mysql://localhost:3306/curs";
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
            System.out.println("Ошибка подключения к БД (DBaseUser.setNewUserInDB)");
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
            System.out.println("Ошибка подключения к БД (DBaseUser.ChangeDataInDB)");
            ex.printStackTrace();
        } finally {
            String sql = "update users set name = '" + arr[0] +
                    "', surname = '" + arr[1] +
                    "', age = " + arr[2] +
                    ", email = '" + arr[3] +
                    "', brand = '" + arr[4] +
                    "' where name = '" + user.getName() +
                    "' and surname = '" + user.getSurname() +
                    "' and age = " + user.getAge() +
                    " and email = '" + user.getEmail() + "';";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }

    public void showDbFull() throws SQLException {
        Statement statement;
        ResultSet resultSet;
        String value;
        String SQL = "select * from users;";
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                value = resultSet.getString("name");
                System.out.print(value + "\t");
                value = resultSet.getString("surname");
                System.out.print(value + "\t");
                value = resultSet.getString("age");
                System.out.print(value + "\t");
                value = resultSet.getString("email");
                System.out.print(value + "\t");
                value = resultSet.getString("brand");
                System.out.println(value);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }
}
