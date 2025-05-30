package com.ps.core;

public class Drink implements Product{
    private String name;
    private double price;
    private String drinkSize;

    //constructor
    public Drink(String name) {
        this.name = name;
    }

    //update drink price by size
    public void updatePrice(String drinkSize) {
        switch (drinkSize) {
            case "Small":
                this.price = 2;
                this.drinkSize = drinkSize;
                break;
            case "Medium":
                this.price = 2.5;
                this.drinkSize = drinkSize;
                break;
            case "Large":
                this.price = 3;
                this.drinkSize = drinkSize;
                break;
            default:
        }
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

    public String getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
    }



    @Override
    public double calcPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("🥤%s (%s)", name, drinkSize);
    }
}
