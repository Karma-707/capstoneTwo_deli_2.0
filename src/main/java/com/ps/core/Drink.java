package com.ps.core;

public class Drink implements Product{
    private String name;
    private double price;

    //constructor
    public Drink(String name, double price) {
        this.name = name;
    }

    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updatePrice(String breadSize) {

        switch (breadSize) {
            case "Small":
                this.price = 2;
                break;
            case "Medium":
                this.price = 2.5;
                break;
            case "Large":
                this.price = 3;
                break;
            default:
        }

    }

    //FIXME: do i need this?
    @Override
    public double calcPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
