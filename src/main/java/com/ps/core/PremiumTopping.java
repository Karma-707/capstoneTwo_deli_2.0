package com.ps.core;

public class PremiumTopping extends Topping{
    private double price;

    //constructor
    public PremiumTopping(String name) {
        super(name);
    }

    //getters & setters
    public double getPrice(int breadSize) {
        //cheese prices
        if(super.getName().toLowerCase().contains("cheese")) {
            if(breadSize == 4) {
                this.price = 0.75;
            }
            else if (breadSize == 8) {
                this.price = 1.50;
            }
            else {
                this.price = 2.25;
            }
        }
        else { //meat prices
            if(breadSize == 4) {
                this.price = 1.00;
            }
            else if (breadSize == 8) {
                this.price = 2.00;
            }
            else {
                this.price = 3.00;
            }
        }

        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



}
