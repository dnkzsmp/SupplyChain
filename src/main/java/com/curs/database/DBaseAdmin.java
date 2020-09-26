package com.curs.database;

import java.sql.*;
import java.util.Scanner;

public class DBaseAdmin {
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

    public void setNewUserInDB(int quantity) throws SQLException {
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            User user = new User();
            Scanner scanner = new Scanner(System.in);
            int counter = 0;
            do {
                if (quantity <= 0) {
                    System.out.println("Кол-во пользователей должно быть >= 1");
                    break;
                }
                System.out.println("Имя пользователя: ");
                user.setName(scanner.next());
                System.out.println("Фамилия пользователя: ");
                user.setSurname(scanner.next());
                System.out.println("Возраст пользователя: ");
                user.setAge(scanner.nextInt());
                System.out.println("Почта пользователя: ");
                user.setEmail(scanner.next());
                System.out.println("Предпочитаемый брэнд: ");
                user.setBrand(scanner.next());
                saveData(user.getName(), user.getSurname(), user.getAge(), user.getEmail(), user.getBrand());
                counter++;
            } while (counter < quantity);
        } catch (Exception ex) {
            System.out.println("Ошибка подключения к БД (DBaseAdmin.setNewUserInDB)");
            ex.printStackTrace();
        } finally {
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

    public void showDbNames() throws SQLException {
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
                value = resultSet.getString("id");
                System.out.print(value + ") ");
                value = resultSet.getString("name");
                System.out.println(value);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }

    public void showDbNames(final int quantity) throws SQLException {
        Statement statement;
        ResultSet resultSet;
        String value;
        String SQL = "select * from users;";
        short counter = 0;
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next() && counter < quantity) {
                value = resultSet.getString("id");
                System.out.print(value + ") ");
                value = resultSet.getString("name");
                System.out.println(value);
                counter++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }

    public void showDbSurnames() throws SQLException {
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
                value = resultSet.getString("id");
                System.out.print(value + ") ");
                value = resultSet.getString("surname");
                System.out.println(value);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }

    public void showDbSurnames(final int quantity) throws SQLException {
        Statement statement;
        ResultSet resultSet;
        String value;
        String SQL = "select * from users;";
        short counter = 0;
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next() && counter < quantity) {
                value = resultSet.getString("id");
                System.out.print(value + ") ");
                value = resultSet.getString("surname");
                System.out.println(value);
                counter++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }
}
