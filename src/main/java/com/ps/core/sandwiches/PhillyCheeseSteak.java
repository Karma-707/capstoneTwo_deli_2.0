package com.ps.core.sandwiches;

import com.ps.core.sandwiches.toppings.PremiumTopping;
import com.ps.core.sandwiches.toppings.RegularTopping;
import com.ps.core.sandwiches.toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class PhillyCheeseSteak extends Sandwich{

    public PhillyCheeseSteak() {
        super();

        //set PhillyCheeseSteak sandwich
        setSelectedBread("White");
        setSelectedSize(8);

        List<Topping> toppings = new ArrayList<>();
        toppings.add(new PremiumTopping("Steak"));
        toppings.add(new PremiumTopping("American Cheese"));
        toppings.add(new RegularTopping("Peppers"));
        setSelectedToppings(toppings);

        List<String> sauces = new ArrayList<>();
        sauces.add("Mayonnaise");
        setSelectedSauces(sauces);

        setToasted(true);

    }

    @Override
    public String toString() {
        return "📌 Signature Sandwich: Philly Cheese Steak\n" + super.toString();
    }
}
