package com.ps.core;

import java.util.Arrays;
import java.util.List;

public class Order {
    private List<Product> products;
    private List<Drink> drinkList = Arrays.asList(
            new Drink("Coca-Cola", 1.50),
            new Drink("Diet Coke", 1.50),
            new Drink("Sprite", 1.50),
            new Drink("Dr Pepper", 1.50),
            new Drink("Root Beer", 1.50),
            new Drink("Bottled Water", 1.00),
            new Drink("Lemonade", 1.75),
            new Drink("Iced Tea", 1.75)
    );
    private List<Chip> chipList = Arrays.asList(
            new Chip("Lays Classic", 1.25),
            new Chip("Lays BBQ", 1.25),
            new Chip("Lays Sour Cream & Onion", 1.25),
            new Chip("Doritos Nacho Cheese", 1.50),
            new Chip("Doritos Cool Ranch", 1.50),
            new Chip("Cheetos Crunchy", 1.50),
            new Chip("Ruffles Original", 1.25),
            new Chip("SunChips Garden Salsa", 1.50)
    );

    //add product
    //get all products

    //TODO: generate receipt of products order
    public void generateReceipt() {}

    //TODO: calculate all product prices
    public void calcPrice() {}
}
