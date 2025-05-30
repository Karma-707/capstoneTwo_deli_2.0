package com.ps.core.sandwiches;

import com.ps.core.Product;
import com.ps.core.sandwiches.toppings.PremiumTopping;
import com.ps.core.sandwiches.toppings.RegularTopping;
import com.ps.core.sandwiches.toppings.Topping;

import java.util.Arrays;
import java.util.List;

public class Sandwich implements Product {
    private String selectedBread;
    private int selectedSize;
    private List<Topping> selectedToppings;
    private List<String> selectedSauces;
    private boolean extraMeat = false;
    private boolean extraCheese = false;

    private List<Integer> offeredSizes = Arrays.asList(4, 8, 12);
    private List<String> offeredBread = Arrays.asList("White", "Wheat", "Rye", "Wrap");
    private List<String> offeredSauces = Arrays.asList(
            "Mayonnaise", "Mustard", "Ketchup",
            "Ranch", "Thousand Island", "Vinaigrette"
    );
    private List<Topping> offeredRegularToppings = Arrays.asList(
            new RegularTopping("Lettuce"),
            new RegularTopping("Peppers"),
            new RegularTopping("Onion"),
            new RegularTopping("Tomato"),
            new RegularTopping("Jalapeños"),
            new RegularTopping("Cucumber"),
            new RegularTopping("Pickles"),
            new RegularTopping("Guacamole"),
            new RegularTopping("Mushrooms")
    );
    private List<Topping> offeredMeatToppings = Arrays.asList(
            new PremiumTopping("Steak"),
            new PremiumTopping("Ham"),
            new PremiumTopping("Salami"),
            new PremiumTopping("Roast Beef"),
            new PremiumTopping("Chicken"),
            new PremiumTopping("Bacon")
    );
    private List<Topping> offeredCheeseToppings = Arrays.asList(
            new PremiumTopping("American Cheese"),
            new PremiumTopping("Provolone Cheese"),
            new PremiumTopping("Cheddar Cheese"),
            new PremiumTopping("Swiss Cheese")
    );
    private boolean isToasted;
    private boolean hasAuJus = false;


    //constructor
    public Sandwich() {
    }

    //helper methods
    private double getBreadPrice(int size) {
        if(size == 4) {
            return 5.50;
        }
        else if(size == 8) {
            return 7.00;
        }
        else if(size == 12) {
            return 8.50;
        }
        return 0;
    }

    private double getExtraCheeseFee(int size) {
        if(size == 4) {
            return 0.30;
        }
        else if(size == 8) {
            return 0.60;
        }
        else if(size == 12) {
            return 0.90;
        }
        return 0;
    }

    private double getExtraMeatFee(int size) {
        if(size == 4) {
            return 0.50;
        }
        else if(size == 8) {
            return 1.00;
        }
        else if(size == 12) {
            return 1.50;
        }
        return 0;
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

    public List<Topping> getOfferedRegularToppings() {
        return offeredRegularToppings;
    }

    public void setOfferedRegularToppings(List<Topping> offeredRegularToppings) {
        this.offeredRegularToppings = offeredRegularToppings;
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

    public List<Topping> getOfferedMeatToppings() {
        return offeredMeatToppings;
    }

    public void setOfferedMeatToppings(List<Topping> offeredMeatToppings) {
        this.offeredMeatToppings = offeredMeatToppings;
    }

    public List<Topping> getOfferedCheeseToppings() {
        return offeredCheeseToppings;
    }

    public void setOfferedCheeseToppings(List<Topping> offeredCheeseToppings) {
        this.offeredCheeseToppings = offeredCheeseToppings;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    public boolean isHasAuJus() {
        return hasAuJus;
    }

    public void setHasAuJus(boolean hasAuJus) {
        this.hasAuJus = hasAuJus;
    }


    @Override
    public double calcPrice() {
        double totalPrice = 0;
        double breadPrice = getBreadPrice(selectedSize);
        double cheesePrice = 0;
        double extraCheesePrice = 0;
        double meatPrice = 0;
        double extraMeatPrice = 0;

        for(Topping topping: selectedToppings) {
            if(topping instanceof PremiumTopping premiumTopping) {
                boolean isCheese = premiumTopping.getName().toLowerCase().contains("cheese");
                //cheese
                if(isCheese) {
                    cheesePrice = premiumTopping.getPrice(selectedSize);
                    if(extraCheese) {
                        extraCheesePrice += getExtraCheeseFee(selectedSize);
                    }
                }
                //meat
                else {
                    meatPrice = premiumTopping.getPrice(selectedSize);
                    if(extraMeat) {
                        extraMeatPrice += getExtraMeatFee(selectedSize);
                    }
                }
            }
        }

        //calculate total of sandwich
        totalPrice = breadPrice + cheesePrice + extraCheesePrice + meatPrice + extraMeatPrice;

        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("📂 Sandwich Case: Custom Creation\n");
        sb.append("🥪 Sandwich Details:");
        sb.append("\n\t🫓 Bread: ").append(selectedBread);
        sb.append("\n\t📏 Size: ").append(selectedSize).append(" inches");

        if(selectedToppings != null && !selectedToppings.isEmpty()) {
            sb.append("\n\t🥬 Toppings:\n");
            for(Topping topping: selectedToppings) {
                String toppingName = topping.getName();
                boolean isCheese = toppingName.toLowerCase().contains("cheese");
                boolean isMeat = !isCheese && (topping instanceof PremiumTopping);

                if(isCheese && extraCheese) {
                    toppingName += " (extra)";
                }
                else if (isMeat && extraMeat) {
                    toppingName += " (extra)";
                }

                sb.append("\t\t• ").append(toppingName).append("\n"); //maybe consider HashMap for key value count?
            }
        }
        else {
            sb.append("\n\t🥬 Toppings:");
            sb.append(" None\n");
        }

        if(selectedSauces != null && !selectedSauces.isEmpty()) {
            sb.append("\t🧂 Sauces:\n");
            for(String sauce: selectedSauces) {
                sb.append("\t\t• ").append(sauce).append("\n");
            }
        }
        else {
            sb.append("\t🧂 Sauces:");
            sb.append(" None\n");
        }

        if(hasAuJus) {
            sb.append("\t🍽️ Sides: Au Jus Sauce\n");
        }
        else {
            sb.append("\t🍽️ Sides: None\n");
        }

        sb.append("\t🔥 Toasted: ").append(isToasted ? "Yes ✅" : "No ❌");

        return sb.toString();
    }
}
