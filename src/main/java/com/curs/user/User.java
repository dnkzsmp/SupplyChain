package com.curs.user;

public class User {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String brand;

    public User(String name, String surname, int age, String email, String brand) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getBrand() {
        return brand;
    }
}
