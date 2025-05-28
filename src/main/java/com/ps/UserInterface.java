package com.ps;

import com.ps.core.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static Order order;

    //start to create an order
    public static void init() {
        order = new Order();
        display();
    }

    //deli main functions
    private static void display() {
        int homeScreenCommand;

        do {
            printHomeScreenMenu();
            System.out.print("👉 Enter your command: ");

            homeScreenCommand = checkValidatedMenuSelection(1);

            switch (homeScreenCommand) {
                case 1: //new order
                    orderScreen();
                    break;
                case 0: //exits from program
                    System.out.println("👋 Thanks for stopping by! Enjoy your meal!");
                    break;
                default:
                    System.out.println("⚠️ Invalid input, please try again");
            }

        } while(homeScreenCommand != 0);

    }


    //order screen with menu options
    private static void orderScreen() {
        int orderMenuCommand;

        do {
            orderMenu();
            System.out.print("👉 Enter your command: ");
            orderMenuCommand = checkValidatedMenuSelection(4);

            switch (orderMenuCommand) {
                case 1: //add sandwich
                    addSandwichProcess();
                    break;
                case 2: //add drink
                    addDrinkProcess();
                    break;
                case 3: //add chips
                    addChipProcess();
                    break;
                case 4: //checkout
                    checkoutProcess();
                    break;
                case 0: //cancel order
                    order = new Order();
                    System.out.println("🔙 Order canceled. Returning to the home screen...");
                    break;
                default:
                    System.out.println("⚠️ Invalid input, please try again");
            }

        } while(orderMenuCommand != 0);

    }

    //checkout user order
    private static void checkoutProcess() {
        //print list of products user ordered
        if(order.getProducts() == null || order.getProducts().isEmpty()) {
            System.out.println("⚠️ Your order is empty. Add something first.");
            return;
        }

        System.out.println(); //just for easier to read
        System.out.println(order.generateReceipt());

        System.out.println("\n📦 Confirm and place your order?");
        System.out.println("✅ Press [1] ➤ Yes, confirm order");
        System.out.println("🔁 Press [2] ➤ No, still want to shop");
        System.out.println("❌ Press [3] ➤ No, delete order");
        System.out.print("👉 Your choice: ");
        int orderCommand = checkValidatedMenuSelection(3);

        do {
            switch (orderCommand) {
                case 1: //confirm order
                    FileManager.writeReceipt(order);
                    System.out.println("🎉 Your receipt has been saved. Thank you for your order! 🥪");
                    order = new Order();
                    break;
                case 2: //continue to shop
                    System.out.println("🛒 Checkout cancelled. You're back to shopping.");
                    break;
                case 3: //delete order
                    System.out.println("🗑️ Order deleted. Let's start fresh!");
                    order = new Order();
                    break;
                default: //wrong input
                    System.out.print("❌ Invalid selection, try again: ");
                    orderCommand = checkIntInput();
            }
        } while(orderCommand != 1 && orderCommand != 2 && orderCommand != 3);

    }

    //add chip to order
    private static void addChipProcess() {
        List<Chip> allChips = order.getChipList();
        List<Chip> chosenChips = new ArrayList<>();
        int chipSelected;
        do {
            System.out.println("\n━━━━━━━━━━━━━━━━━");
            System.out.println("🍟 Want A Snack?");
            System.out.println("━━━━━━━━━━━━━━━━━");
            for (int i = 0; i < allChips.size(); i++) {
                System.out.printf("🍟 Press [%d] ➤ %-15s\n", i + 1, allChips.get(i).getName());
            }
            System.out.println("🔚 Press [0] ➤ Done");

            System.out.print("👉 Select your chip: ");
            chipSelected = checkValidatedMenuSelection(allChips.size());
            if(chipSelected >= 1 && chipSelected <= allChips.size()) {
                chosenChips.add(allChips.get(chipSelected - 1));
                System.out.println(allChips.get(chipSelected - 1).getName() + " added.");
            }
            else if (chipSelected == 0) {
                break;
            }
            else {
                System.out.println("❌ Invalid selection.");
            }
        } while(chipSelected != 0);

        //add chips to products
        for(Chip chip: chosenChips) {
            order.addProduct(chip);
        }
    }

    //add drink to order
    private static void addDrinkProcess() {
        List<Drink> allDrinks = order.getDrinkList();
        List<Drink> chosenDrinks = new ArrayList<>();

        int drinkSelected;
        do {
            System.out.println("\n━━━━━━━━━━━━━━━━━");
            System.out.println("🥤 Want a drink?");
            System.out.println("━━━━━━━━━━━━━━━━━");
            for (int i = 0; i < allDrinks.size(); i++) {
                System.out.printf("🥤 Press [%d] ➤ %-15s\n", i + 1, allDrinks.get(i).getName());
            }
            System.out.println("🔚 Press [0] ➤ Done");

            System.out.print("👉 Select your beverage: ");
            drinkSelected = checkValidatedMenuSelection(allDrinks.size());
            if(drinkSelected >= 1 && drinkSelected <= allDrinks.size()) {
                Drink chosenDrink = allDrinks.get(drinkSelected - 1);
                chosenDrinks.add(chosenDrink);

                System.out.println("📏 What size would you like?");
                System.out.println("🟢 Press [1] ➤ Small");
                System.out.println("🟡 Press [2] ➤ Medium");
                System.out.println("🔴 Press [3] ➤ Large");
                System.out.print("👉 Your choice: ");
                int drinkSizeSelected = checkValidatedMenuSelection(3);
                String chosenDrinkSize;
                if(drinkSizeSelected == 1) {
                    chosenDrinkSize = "Small";
                }
                else if(drinkSizeSelected == 2) {
                    chosenDrinkSize = "Medium";
                }
                else {
                    chosenDrinkSize = "Large";
                }
                chosenDrink.updatePrice(chosenDrinkSize); //set up price with size of drink
                System.out.println(chosenDrink.getName() + " added.");

            }
            else if (drinkSelected == 0) {
                break;
            }
            else {
                System.out.println("❌ Invalid selection.");
            }
        } while(drinkSelected != 0);

        //add drinks to products
        for(Drink drink: chosenDrinks) {
            order.addProduct(drink);
        }
    }

    //add sandwich to order
    private static void addSandwichProcess() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🥪 Let's make your perfect sandwich!");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🔨 Press [1] ➤ Build your own sandwich");
            System.out.println("🌟 Press [2] ➤ Choose a signature sandwich (quick & tasty)");
            System.out.println("🔙 Press [0] ➤ Go back");
            System.out.print("👉 Your choice: ");
            int sandwichCommand = checkValidatedMenuSelection(2);

            switch (sandwichCommand) {
                case 1: //build your own sandwich
                    buildCustomSandwich();
                    break;
                case 2: //choose signature sandwich
                    addSignatureSandwich();
                    break;
                case 0: //go back to main menu
                    System.out.println("🔙 Going back!");
                    return;
                default:
            }
    }

    //add signature sandwich
    private static void addSignatureSandwich() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🚀 Choose a Signature Sandwich:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🥓 Press [1] ➤ BLT");
        System.out.println("🧀 Press [2] ➤ Philly Cheese Steak");
        System.out.println("🔙 Press [0] ➤ Go back");
        System.out.print("👉 Your choice: ");
        int sigCommand = checkValidatedMenuSelection(2);

        Sandwich sandwich = null;

        switch (sigCommand) {
            case 1: //BLT
                sandwich = new BLT();
                break;
            case 2: //Philly cheese steak
                sandwich = new PhillyCheeseSteak();
                break;
            case 0: //go back to sandwich options
                System.out.println("🔙 Returning to sandwich options...");
                return;
            default:
        }

        //select side
        System.out.println("\n🍲 Would you like to add a free side of Au Jus sauce?");
        System.out.println("✅ Press [1] ➤ Yes, add Au Jus");
        System.out.println("❌ Press [0] ➤ No, thanks");
        System.out.print("👉 Your choice: ");
        int sideSelected = checkValidatedMenuSelection(1); //grab user choice of side
        sandwich.setHasAuJus(sideSelected == 1);

        //print out the sandwich so far
        System.out.println("\n🥪 You've chosen a Signature Sandwich!");
        System.out.println(sandwich);
        System.out.printf("💰 Total: $%.2f\n", sandwich.calcPrice());
        System.out.println("✅ Sandwich successfully added to your order!");

        //final sandwich add to products
        order.addProduct(sandwich);
    }

    //build custom sandwich
    private static void buildCustomSandwich() {
        Sandwich sandwich = new Sandwich();

        System.out.println("\n🍞 Ready to start building your sandwich? Let's go!");
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🥪 Sandwich Creation Station 🛠️");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        //select bread type
        System.out.println("\n🥖 Available Bread Types:");
        List<String> allBreads = sandwich.getOfferedBread();
        //print all bread types
        for (int i = 0; i < allBreads.size(); i++) {
            System.out.printf("🍞 Press [%d] ➤ %-7s\n", i + 1, allBreads.get(i));
        }
        System.out.print("👉 Select your bread: ");
        int breadSelected = checkValidatedMenuSelection(allBreads.size()); //grab user choice of bread
        String chosenBread = allBreads.get(breadSelected - 1); //name of bread they selected
        sandwich.setSelectedBread(chosenBread); //put bread in sandwich

        //select size
        System.out.println("\n📏 Choose your sandwich size:");
        List<Integer> allSize = sandwich.getOfferedSizes();
        //print all bread size
        for (int i = 0; i < allSize.size(); i++) {
            System.out.printf("🥖 Press [%d] ➤ %2s inches\n", i + 1, allSize.get(i));
        }
        System.out.print("👉 Select your size: ");
        int sizeSelected = checkValidatedMenuSelection(allSize.size()); //grab user choice of bread size
        int chosenSize = allSize.get(sizeSelected - 1); //size of bread they selected
        sandwich.setSelectedSize(chosenSize); //put size in sandwich


        //select toppings
        int toppingSelected;
        List<Topping> allRegularToppings = sandwich.getOfferedRegularToppings();
        List<Topping> allMeatToppings = sandwich.getOfferedMeatToppings();
        List<Topping> allCheeseToppings = sandwich.getOfferedCheeseToppings();

        List<Topping> chosenToppings = new ArrayList<>();

        do {
            System.out.println("\n🥬 Add Toppings!");

            //print all toppings
            for (int i = 0; i < allRegularToppings.size(); i++) {
                System.out.printf("🥬 Press [%d] ➤ %-7s\n", i + 1, allRegularToppings.get(i));
            }
            System.out.println("🔚 Press [0] ➤ Done");
            System.out.print("👉 Select your topping: ");
            toppingSelected = checkValidatedMenuSelection(allRegularToppings.size()); //grab user choice of topping

            if (toppingSelected == 0) {
                break;
            }

            Topping chosenTopping = allRegularToppings.get(toppingSelected - 1); //topping selected
            String chosenToppingName = chosenTopping.getName(); //name of topping they selected

            int count = 0;
            //TODO: check toppings making sure only add x2
            //check num times topping appears in chosenToppings list
            for (int i = 0; i < chosenToppings.size(); i++) {
                String currentToppingName = chosenToppings.get(i).getName();
                if (currentToppingName.equalsIgnoreCase(chosenToppingName)) {
                    count++;
                }
            }

            //allow to add topping if less than 2 toppings on sandwich, else reject + loop till exit
            if (count < 2) {
                chosenToppings.add(chosenTopping);
                System.out.println("➕ " + chosenTopping.getName() + " added to your sandwich!");
            } else {
                System.out.println("🚫 You can only add up to 2 of the same topping. No more extras allowed!");
            }


        } while (toppingSelected != 0);

        //add meat
        System.out.println("\n🍖 Special Meat Toppings!");

        //print all premium meat toppings
        for (int i = 0; i < allMeatToppings.size(); i++) {
            System.out.printf("🍖 Press [%d] ➤ %-7s\n", i + 1, allMeatToppings.get(i));
        }
        System.out.println("🔚 Press [0] ➤ Done");
        System.out.print("👉 Select your topping: ");
        toppingSelected = checkValidatedMenuSelection(allMeatToppings.size()); //grab user choice of topping

        if (toppingSelected != 0) {
            Topping chosenTopping = allMeatToppings.get(toppingSelected - 1); //topping selected
            chosenToppings.add(chosenTopping);

            System.out.println("\n✨ Add Extras?");
            System.out.println("✅ Press [1] ➤ Yes, love extras!");
            System.out.println("❌ Press [0] ➤ No, no more");
            System.out.print("👉 Your choice: ");
            int extraMeat = checkValidatedMenuSelection(1);

            if (extraMeat == 1) {
                sandwich.setExtraMeat(true);
            }
        }

        //add cheese
        System.out.println("\n🧀 Special Cheese Toppings!");

        //print all premium cheese toppings
        for (int i = 0; i < allCheeseToppings.size(); i++) {
            if (allCheeseToppings.get(i).getName().contains("Cheese")) {
                System.out.printf("🧀 Press [%d] ➤ %-7s\n", i + 1, allCheeseToppings.get(i));
            }
        }
        System.out.println("🔚 Press [0] ➤ Done");
        System.out.print("👉 Select your topping: ");

        toppingSelected = checkValidatedMenuSelection(allCheeseToppings.size()); //grab user choice of topping
        if (toppingSelected != 0) {
            Topping chosenTopping = allCheeseToppings.get(toppingSelected - 1); //topping selected
            chosenToppings.add(chosenTopping);

            System.out.println("\n✨ Add Extras?");
            System.out.println("✅ Press [1] ➤ Yes, love extras!");
            System.out.println("❌ Press [0] ➤ No, no more");
            System.out.print("👉 Your choice: ");
            int extraCheese = checkValidatedMenuSelection(1);

            if (extraCheese == 1) {
                sandwich.setExtraCheese(true);
            }
        }

        //add all toppings to sandwich
        sandwich.setSelectedToppings(chosenToppings);

        //select sauces
        List<String> chosenSauces = new ArrayList<>();
        System.out.println("\n🍯 Add sauces");
        List<String> allSauces = sandwich.getOfferedSauces(); //get all sauces
        for (int i = 0; i < allSauces.size(); i++) {
            System.out.printf("🍯 Press [%d] ➤ %-10s\n", i + 1, allSauces.get(i));
        }
        System.out.println("🔚 Press [0] ➤ Done");

        System.out.print("👉 Select your sauce: ");
        int sauceSelected = checkValidatedMenuSelection(allSauces.size()); //grab user choice of sauce
        if (sauceSelected != 0) {
            chosenSauces.add(allSauces.get(sauceSelected - 1)); //name of sauce selected added to list
            sandwich.setSelectedSauces(chosenSauces); //put sauce in sandwich
        }


        //select toasted
        System.out.println("\n🔥 Would you like your sandwich toasted?");
        System.out.println("🔥 Press [1] ➤ Yes, toast it");
        System.out.println("❄️ Press [0] ➤ No, leave it untoasted");
        System.out.print("👉 Your choice: ");

        int toastSelected = checkValidatedMenuSelection(1); //grab user choice to toast
        boolean chosenToast = (toastSelected == 1); //toasted of bread
        sandwich.setToasted(chosenToast); //put size in sandwich

        //select side
        System.out.println("\n🍲 Would you like to add a free side of Au Jus sauce?");
        System.out.println("✅ Press [1] ➤ Yes, add Au Jus");
        System.out.println("❌ Press [0] ➤ No, thanks");
        System.out.print("👉 Your choice: ");
        int sideSelected = checkValidatedMenuSelection(1); //grab user choice of side
        sandwich.setHasAuJus(sideSelected == 1);

        //print out the sandwich so far
        System.out.println();
        System.out.println(sandwich);
        System.out.printf("💰 Total: $%.2f\n", sandwich.calcPrice());
        System.out.println("✅ Sandwich successfully added to your order!\n");

        //final sandwich add to products
        order.addProduct(sandwich);
    }


    //order menu options
    private static void orderMenu() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━");
        System.out.println("🛒 Build Your Order");
        System.out.println("━━━━━━━━━━━━━━━━━━━");

        System.out.println("🥪 Press [1] ➤ Add Sandwich");
        System.out.println("🥤 Press [2] ➤ Add Drink");
        System.out.println("🍟 Press [3] ➤ Add Chips");
        System.out.println("💳 Press [4] ➤ Checkout");
        System.out.println("🔁 Press [0] ➤ Start Over");
    }

    //print home screen menu options
    private static void printHomeScreenMenu() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("👋 Welcome to The Case of the Missing Sandwich!");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("What would you like to do?");
        System.out.println("🛒 Press [1] ➤ New Order");
        System.out.println("🚪 Press [0] ➤ Exit");
    }


    /* Check Validation Inputs */

    //validate int input - data type
    private static int checkIntInput() {
        while(true) {
            String userInput = scanner.nextLine().trim();

            //don't allow -0 as an option
            if(userInput.equalsIgnoreCase("-0")) {
                System.out.print("🚫 You can't enter 0 as a value... Try again: ");
                continue; //go back to top of loop
            }

            try {
                if(Integer.parseInt(userInput) < 0) {
                    System.out.print("⚠️ You can't put a negative value... Try again!: ");
                }
                else {
                    return Integer.parseInt(userInput);
                }
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Not a valid number! Please try again: ");
                writeErrorsToLogsFile(e);
            }

        }
    }


    private static int checkValidatedMenuSelection(int maxOption) {
        int selection;

        do {
           selection = checkIntInput();
           if(selection < 0 || selection > maxOption) {
               System.out.print("❌ Invalid selection, try again: ");
           }
        } while(selection < 0 || selection > maxOption);

        return selection;
    }


    /* Logs Methods */

    //write to log file of error/crashes
    public static void writeErrorsToLogsFile(Exception e) {
        try {
            LocalDateTime timeStamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatedTimeStamp = timeStamp.format(formatter);

            //write to logs the error
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("exceptions.log",true));
            bufferedWriter.write("Time of occurrence: " + formatedTimeStamp + "\t" + e.getMessage() + "\n");
            bufferedWriter.close();
        } catch (IOException ex) {
            writeErrorsToLogsFile(e);
        }
    }

    //make the logs empty
    private static void clearLogsFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("exceptions.log", false));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
