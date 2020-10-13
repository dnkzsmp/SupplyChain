package com.curs.database;

import com.curs.provider.Provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBaseProvider implements Connector {
    private Connection connection = null;
    private final String URL = "jdbc:mysql://localhost:3306/curs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USERNAME = "danil";
    private final String PASSWORD = "root";

    private void saveData(String company, String model, int yearOfProduction, int priceOfPhone) {
        String sql = "insert into provider (company, model, year, price) values (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, company);
            statement.setString(2, model);
            statement.setInt(3, yearOfProduction);
            statement.setInt(4, priceOfPhone);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setNewProviderInDB(Provider pr) throws SQLException {
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Ошибка подключения к БД (DBaseProvider.setNewUserInProvider)");
            ex.printStackTrace();
        } finally {
            saveData(pr.getCompany(), pr.getModel(), pr.getYearOfProduction(), pr.getPriceOfPhone());
            connection.close();
            System.out.println("Закрыли соединение с БД");
        }
    }

    public void deleteProviderById(String id) throws SQLException {
        try {
            System.out.println("Устанавливаем соединение с БД");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Ошибка подключения к БД (DBaseProvider.deleteProviderById)");
            ex.printStackTrace();
        } finally {
            String sql = "DELETE FROM provider WHERE id = " + Integer.parseInt(id);
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
            String sql2 = "UPDATE provider SET id = @num := (@num + 1)";
            updating(sql1, sql2, connection);
        }
    }

    static void updating(String sql1, String sql2, Connection connection) throws SQLException {
        String sql3 = "ALTER TABLE users AUTO_INCREMENT = 1";
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql3)) {
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connection.close();
        System.out.println("Закрыли соединение с БД");
    }
}
