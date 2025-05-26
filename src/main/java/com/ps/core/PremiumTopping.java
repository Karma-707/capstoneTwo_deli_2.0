package com.ps.core;

public class PremiumTopping extends Topping{
    private double price;

    //constructor
    public PremiumTopping(String name) {
        super(name);
    }

    //getters & setters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



}
