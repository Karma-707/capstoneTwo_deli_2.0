package com.ps.core;

import java.util.Arrays;
import java.util.List;

public class Sandwich implements Product{
    private String selectedBread;
    private int selectedSize;
    private List<Topping> selectedToppings;
    private List<String> selectedSauces;

    private List<Integer> offeredSizes = Arrays.asList(4, 8, 12);
    private List<String> offeredBread = Arrays.asList("White", "Wheat", "Rye", "Wrap");
    private List<String> offeredSauces = Arrays.asList(
            "Mayonnaise", "Mustard", "Ranch", "Ketchup",
            "Sriracha", "Chipotle Mayo",
            "Olive Oil", "Red Wine Vinegar", "Vinaigrette",
            "Pesto", "Garlic Aioli", "Thousand Island"
    );
    private List<Topping> offeredRegularTopping = Arrays.asList(
            new RegularTopping("Lettuce"),
            new RegularTopping("Bell Peppers"),
            new RegularTopping("Onion"),
            new RegularTopping("Tomato"),
            new RegularTopping("Jalapeños"),
            new RegularTopping("Cucumber"),
            new RegularTopping("Pickles"),
            new RegularTopping("Guacamole"),
            new RegularTopping("Mushrooms")
    );
    private List<Topping> offeredPremiumTopping = Arrays.asList(
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

    //constructor
    public Sandwich() {
    }

    //getters & setters
    public List<Integer> getOfferedSizes() {
        return offeredSizes;
    }

    public void setOfferedSizes(List<Integer> offeredSizes) {
        this.offeredSizes = offeredSizes;
    }

    public List<String> getOfferedBread() {
        return offeredBread;
    }

    public void setOfferedBread(List<String> offeredBread) {
        this.offeredBread = offeredBread;
    }

    public List<String> getOfferedSauces() {
        return offeredSauces;
    }

    public void setOfferedSauces(List<String> offeredSauces) {
        this.offeredSauces = offeredSauces;
    }

    public List<Topping> getOfferedRegularTopping() {
        return offeredRegularTopping;
    }

    public void setOfferedRegularTopping(List<Topping> offeredRegularTopping) {
        this.offeredRegularTopping = offeredRegularTopping;
    }

    public List<Topping> getOfferedPremiumTopping() {
        return offeredPremiumTopping;
    }

    public void setOfferedPremiumTopping(List<Topping> offeredPremiumTopping) {
        this.offeredPremiumTopping = offeredPremiumTopping;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public String getSelectedBread() {
        return selectedBread;
    }

    public void setSelectedBread(String selectedBread) {
        this.selectedBread = selectedBread;
    }

    public int getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(int selectedSize) {
        this.selectedSize = selectedSize;
    }

    public List<Topping> getSelectedToppings() {
        return selectedToppings;
    }

    public void setSelectedToppings(List<Topping> selectedToppings) {
        this.selectedToppings = selectedToppings;
    }

    public List<String> getSelectedSauces() {
        return selectedSauces;
    }

    public void setSelectedSauces(List<String> selectedSauces) {
        this.selectedSauces = selectedSauces;
    }



    //TODO: maybe add on, see as it goes
    private void viewAllToppings() {}

    private void viewAllSauces() {}

    @Override
    public double calcPrice() {
        return 0;
    }
}
