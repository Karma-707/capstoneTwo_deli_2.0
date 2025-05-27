package com.ps.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private List<Product> products = new ArrayList<>();
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
            new Chip("Lays Classic"),
            new Chip("Lays BBQ"),
            new Chip("Lays Sour Cream & Onion"),
            new Chip("Doritos Nacho Cheese"),
            new Chip("Doritos Cool Ranch"),
            new Chip("Cheetos Crunchy"),
            new Chip("Ruffles Original"),
            new Chip("SunChips Garden Salsa")
    );

    //constructor
    public Order() {
    }

    //add a product
    public void addProduct(Product product) {
        products.add(product);
    }

    //getters & setters
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Drink> getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(List<Drink> drinkList) {
        this.drinkList = drinkList;
    }

    public List<Chip> getChipList() {
        return chipList;
    }

    public void setChipList(List<Chip> chipList) {
        this.chipList = chipList;
    }



    //TODO: generate receipt of products order
    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        double total = 0;
        receipt.append("\n🧾 Here’s your order summary:\n");
        for(Product product: products) {
            receipt.append(product);
            double price = product.calcPrice();
            receipt.append(String.format(" $%.2f\n", price));
            total += price; //add to total of order
        }

        receipt.append(String.format("Total: $%.2f\n", total));
        return receipt.toString();
    }

    //TODO: calculate all product prices
    public void calcPrice() {}
}
