package com.ps.core;

import java.util.ArrayList;
import java.util.List;

public class BLT extends Sandwich{

    public BLT() {
        super();

        //set BLT sandwich
        setSelectedBread("White");
        setSelectedSize(8);

        List<Topping> toppings = new ArrayList<>();
        toppings.add(new PremiumTopping("Bacon"));
        toppings.add(new PremiumTopping("Cheddar Cheese"));
        toppings.add(new RegularTopping("Lettuce"));
        toppings.add(new RegularTopping("Tomato"));
        setSelectedToppings(toppings);

        List<String> sauces = new ArrayList<>();
        sauces.add("Ranch");
        setSelectedSauces(sauces);

        setToasted(true);
    }

    @Override
    public String toString() {
        return "📌 Signature Sandwich: BLT\n" + super.toString();
    }
}
