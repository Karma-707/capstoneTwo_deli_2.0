package com.ps.core;

public class Chip implements Product{
    private String name;
    private double price = 1.50;

    //constructor
    public Chip(String name) {
        this.name = name;
    }


    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return String.format("🍟%s ", name);
    }
}
