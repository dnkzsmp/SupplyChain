package com.curs.comboclasses;

public class Year {
    private final String year;
    private final int id;

    public Year(String year, int id) {
        this.year = year;
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return year;
    }
}
