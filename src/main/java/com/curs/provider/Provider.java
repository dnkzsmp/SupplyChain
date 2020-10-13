package com.curs.provider;

public class Provider {
    private final String company;
    private final String model;
    private final int yearOfProduction;
    private final int priceOfPhone;

    public Provider(String company, String model, int yearOfProduction, int priceOfPhone) {
        this.company = company;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.priceOfPhone = priceOfPhone;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public int getPriceOfPhone() {
        return priceOfPhone;
    }
}
