package com.ps.core;

import com.ps.core.sandwiches.Snack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private List<Product> products = new ArrayList<>();
    private List<Drink> drinkList = Arrays.asList(
            new Drink("Coca-Cola"),
            new Drink("Diet Coke"),
            new Drink("Sprite"),
            new Drink("Dr Pepper"),
            new Drink("Root Beer"),
            new Drink("Bottled Water"),
            new Drink("Lemonade"),
            new Drink("Iced Tea")
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
    public List<Snack> snackList = Arrays.asList(
            new Snack("Nutter Buddy"),
            new Snack("Chocolate Bar"),
            new Snack("Powdered Donut"),
            new Snack("Sunflower Seeds"),
            new Snack("Hubba Bubba")
    );

    //constructor
    public Order() {
    }

    //add a product
    public void addProduct(Product product) {
        products.add(product);
    }

    //generate receipt of products order
    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        double total = 0;
        receipt.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        receipt.append("🕵️ Case File: Order Summary");
        receipt.append("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        for(Product product: products) {
            double price = product.calcPrice();

            //product details
            receipt.append(product);

            //price of product line
            receipt.append(String.format("\n💵 Subtotal: $%.2f\n", price));
            receipt.append("-".repeat(35)).append("\n");

            total += price; //add to total of order
        }
        receipt.append(String.format("🧾 Total Amount Due: $%.2f\n", total));
        receipt.append("=".repeat(35)).append("\n");

        return receipt.toString();
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

}
