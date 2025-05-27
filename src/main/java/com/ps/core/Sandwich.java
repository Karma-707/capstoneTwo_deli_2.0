package com.ps.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sandwich implements Product{
    private String selectedBread;
    private int selectedSize;
    private List<Topping> selectedToppings;
    private List<String> selectedSauces;
    private boolean extraMeat = false;
    private boolean extraCheese = false;
    private Map<String, Integer> toppingsCountMap = new HashMap<>();

    private List<Integer> offeredSizes = Arrays.asList(4, 8, 12);
    private List<String> offeredBread = Arrays.asList("White", "Wheat", "Rye", "Wrap");
    private List<String> offeredSauces = Arrays.asList(
            "Mayonnaise", "Mustard", "Ketchup",
            "Ranch", "Thousand Island", "Vinaigrette"
    );
    private List<Topping> offeredRegularToppings = Arrays.asList(
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




    //constructor
    public Sandwich() {
    }



    //TODO: maybe add on, see as it goes
    private void viewAllToppings() {}

    private void viewAllSauces() {}

    //FIXME: i wonder if i should set name of everything to get price
    @Override
    public double calcPrice() {
        double totalPrice = 0;
        double breadPrice = 0;
        double cheesePrice = 0;
        double extraCheesePrice = 0;
        double meatPrice = 0;
        double extraMeatPrice = 0;

        switch (selectedSize) {
            case 4:
                breadPrice = 5.50;
                for(Topping topping: selectedToppings) {
                    if(topping instanceof PremiumTopping premiumTopping) {
                        //cheese
                        if(premiumTopping.getName().toLowerCase().contains("cheese")) {
                            cheesePrice = premiumTopping.getPrice(selectedSize);
                            if(extraCheese) {
                                extraCheesePrice += 0.30;
                            }
                        }
                        //meat
                        else {
                            meatPrice = premiumTopping.getPrice(selectedSize);
                            if(extraMeat) {
                                extraMeatPrice += 0.50;
                            }
                        }
                    }
                }

                break;
            case 8:
                breadPrice = 7.00;
                for(Topping topping: selectedToppings) {
                    if(topping instanceof PremiumTopping premiumTopping) {
                        //cheese
                        if(premiumTopping.getName().toLowerCase().contains("cheese")) {
                            cheesePrice = premiumTopping.getPrice(selectedSize);
                            if(extraCheese) {
                                extraCheesePrice += 0.60;
                            }
                        }
                        //meat
                        else {
                            meatPrice = premiumTopping.getPrice(selectedSize);
                            if(extraMeat) {
                                extraMeatPrice += 1.00;
                            }
                        }
                    }
                }

                break;
            case 12:
                breadPrice = 8.50;
                for(Topping topping: selectedToppings) {
                    if(topping instanceof PremiumTopping premiumTopping) {
                        //cheese
                        if(premiumTopping.getName().toLowerCase().contains("cheese")) {
                            cheesePrice = premiumTopping.getPrice(selectedSize);
                            if(extraCheese) {
                                extraCheesePrice += 0.90;
                            }
                        }
                        //meat
                        else {
                            meatPrice = premiumTopping.getPrice(selectedSize);
                            if(extraMeat) {
                                extraMeatPrice += 1.50;
                            }
                        }
                    }
                }

                break;
            default:
                break;
        }

        //calculate total of sandwich
        totalPrice = breadPrice + cheesePrice + extraCheesePrice + meatPrice + extraMeatPrice;

        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n🥪 Sandwich Details:");
        sb.append("\n\tBread: ").append(selectedBread);
        sb.append("\n\tSize: ").append(selectedSize).append(" inches");

        if(selectedToppings != null && !selectedToppings.isEmpty()) {
            sb.append("\n\tToppings:\n");
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

                sb.append("\t\t").append(toppingName).append("\n"); //maybe consider HashMap for key value count?
            }
        }
        else {
            sb.append("\n\tToppings:");
            sb.append(" None\n");
        }

        if(selectedSauces != null && !selectedSauces.isEmpty()) {
            sb.append("\tSauces:\n");
            for(String sauce: selectedSauces) {
                sb.append("\t\t").append(sauce).append("\n");
            }
        }
        else {
            sb.append("\tSauces:");
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
}
