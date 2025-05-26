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
            "Mayonnaise", "Mustard", "Ketchup",
            "Ranch", "Thousand Island", "Vinaigrette"
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
            new PremiumTopping("Steak"),
            new PremiumTopping("Ham"),
            new PremiumTopping("Salami"),
            new PremiumTopping("Roast Beef"),
            new PremiumTopping("Chicken"),
            new PremiumTopping("Bacon"),
            new PremiumTopping("American"),
            new PremiumTopping("Provolone"),
            new PremiumTopping("Cheddar"),
            new PremiumTopping("Swiss")
    );
    private boolean isToasted;

    //constructor
    public Sandwich() {
    }



    //TODO: maybe add on, see as it goes
    private void viewAllToppings() {}

    private void viewAllSauces() {}

    @Override
    public double calcPrice() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n🥪 Sandwich Details:");
        sb.append("\n\tBread: ").append(selectedBread);
        sb.append("\n\tSize: ").append(selectedSize).append(" inches");
        sb.append("\n\tToppings:\n");
        if(selectedToppings != null && !selectedToppings.isEmpty()) {
            for(Topping topping: selectedToppings) {
                sb.append("\t\t").append(topping.getName()).append("\n"); //maybe consider HashMap for key value count?
            }
        }
        else {
            sb.append(" None\n");
        }

        sb.append("\tSauces:\n");
        if(selectedSauces != null && !selectedSauces.isEmpty()) {
            for(String sauce: selectedSauces) {
                sb.append("\t\t").append(sauce).append("\n");
            }
        }
        else {
            sb.append(" None\n");
        }

        sb.append("\tToasted: ").append(isToasted ? "Yes" : "No");

        return sb.toString();
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

}
