package com.ps.core;

public class Drink implements Product{
    private String name;
    private double price;

    //constructor
    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    @Override
    public double calcPrice() {
        return this.price;
    }
}
