package com.ps.core.sandwiches.toppings;

public abstract class Topping {
    private String name;

    //constructor
    public Topping(String name) {
        this.name = name;
    }

    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
