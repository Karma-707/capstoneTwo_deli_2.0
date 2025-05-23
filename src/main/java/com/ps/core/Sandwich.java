package com.ps.core;

import java.util.Arrays;
import java.util.List;

public class Sandwich implements Product{
    private static List<Integer> sizes = Arrays.asList(4, 8, 12);
    private static List<String> bread = Arrays.asList("White", "Wheat", "Rye", "Wrap");
    private static List<String> sauces = Arrays.asList(
            "Mayonnaise", "Mustard", "Ranch",
            "Sriracha", "Chipotle Mayo",
            "Olive Oil", "Red Wine Vinegar",
            "Pesto", "Garlic Aioli", "Thousand Island"
    );
    private static List<Topping> regularTopping = Arrays.asList(
            new RegularTopping("Lettuce"),
            new RegularTopping("Tomato"),
            new RegularTopping("Cucumber"),
            new RegularTopping("Onion"),
            new RegularTopping("Pickles"),
            new RegularTopping("Spinach"),
            new RegularTopping("Bell Peppers"),
            new RegularTopping("Jalapeños"),
            new RegularTopping("Banana Peppers"),
            new RegularTopping("Olives")
    );
    private static List<Topping> premiumTopping = Arrays.asList(
            new PremiumTopping("Turkey", 1.50),
            new PremiumTopping("Ham", 1.25),
            new PremiumTopping("Roast Beef", 1.75),
            new PremiumTopping("Salami", 1.25),
            new PremiumTopping("Chicken Breast", 1.50),
            new PremiumTopping("Bacon", 1.00),
            new PremiumTopping("American Cheese", 0.75),
            new PremiumTopping("Swiss Cheese", 0.75),
            new PremiumTopping("Cheddar Cheese", 0.75),
            new PremiumTopping("Provolone Cheese", 0.75),
            new PremiumTopping("Mozzarella", 0.75),
            new PremiumTopping("Pepper Jack", 0.75)
    );
    private boolean isToasted;

    //TODO: maybe add on, see as it goes
    private void viewAllToppings() {}

    private void viewAllSauces() {}

    @Override
    public double calcPrice() {
        return 0;
    }
}
