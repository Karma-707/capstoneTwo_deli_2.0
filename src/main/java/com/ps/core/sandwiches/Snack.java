package com.ps.core.sandwiches;

import com.ps.core.Product;

public class Snack implements Product{
    private String name;
    private double price = 1.00;

    public Snack(String name) {
        this.name = name;

    }

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

    @Override
    public String toString() {
        return String.format("\uD83C\uDF6B%s", name);
    }
}
