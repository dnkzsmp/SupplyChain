package com.curs.database;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void startMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int value;
        boolean flag = true;
        DBaseAdmin dBaseAdmin = new DBaseAdmin();
        while (flag) {
            System.out.println("1) Добавить пользователя(ей) в бд");
            System.out.println("2) Просмотреть БД");
            System.out.println("3) Выйти");
            value = scanner.nextInt();
            if (value == 1) {
                System.out.println("Кол-во пользователей: ");
                int quantity = scanner.nextInt();
                dBaseAdmin.setNewUserInDB(quantity);
            }
            if (value == 2) {
                dBaseAdmin.showDbFull();
            }
            if (value != 1 && value != 2)
                flag = false;
        }
    }

    public static void main(String[] args) throws Exception {
        startMenu();
    }
}
