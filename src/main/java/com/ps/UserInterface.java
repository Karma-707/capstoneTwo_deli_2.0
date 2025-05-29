package com.ps;

import com.ps.core.*;
import com.ps.core.sandwiches.BLT;
import com.ps.core.sandwiches.PhillyCheeseSteak;
import com.ps.core.sandwiches.Sandwich;
import com.ps.core.sandwiches.toppings.Topping;

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
    private static boolean isFirstTime = false;


    //start to create an order
    public static void init() {
        order = new Order();
        display();
    }

    //deli main functions
    private static void display() {
        int homeScreenCommand;

        //TODO: dont forget to uncomment
//        introMessage();

        do {
            printHomeScreenMenu();
            System.out.print("👉 Your choice: ");

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

    private static void introMessage() {
        System.out.println();
        dotTypewriterInLineAnimationKeepDots("🌧️ The rain fell hard over Yokohama that night",3);
//        printTypeWriter("\n🌧️ The rain fell hard over Yokohama that night...\n", 50);

        //"A sandwich — no, THE sandwich — vanished without a trace.\n"
        printTypeWriter("\nA sandwich ", 50);
        spinnerInline(2000);

        printTypeWriter("no...", 200);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            writeErrorsToLogsFile(e);
        }
        eraseTypeWriter("A sandwich no...", 100);
        printTypeWriter("THE sandwich — vanished without a trace.\n", 50);

        //"No signs of forced entry. Just silence... and hunger.\n"
        dotTypewriterInLineAnimationKeepDots("No signs of forced entry. Just silence", 3); //include ...
        printTypeWriter(" and hunger.\n", 50);


        printTypeWriter("Time to investigate.\n", 50);
    }


    //order screen with menu options
    private static void orderScreen() {
        int orderMenuCommand;

        do {
            orderMenu();
            System.out.print("👉 Your choice: ");
            orderMenuCommand = checkValidatedMenuSelection(4);

            switch (orderMenuCommand) {
                case 1: //add sandwich
                    printTypeWriter("\n🔍 The sandwich... that's where it all began. Maybe rebuilding it will reveal something.\n", 50);
                    addSandwichProcess();
                    break;
                case 2: //add drink
                    printTypeWriter("\n🧃 Witnesses say the thief was sipping something. Could the drink hold a clue?\n", 50);
                    addDrinkProcess();
                    break;
                case 3: //add chips
                    printTypeWriter("\n🍟 They say no one grabs chips without leaving fingerprints. Time to dust for prints.\n", 50);
                    addChipProcess();
                    break;
                case 4: //checkout
                    printTypeWriter("\n📜 All evidence gathered. Time to file the report—or go deeper.\n", 50);
                    checkoutProcess();
                    break;
                case 0: //cancel order
                    printTypeWriter("\n🗃️ You close the case file—for now. The trail’s gone cold... back to HQ.\n", 50);
                    order = new Order();
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
            System.out.println("⚠️ Your case file is still blank. Add some clues (or food) first.");
            return;
        }

        System.out.println(); //for spacing
        System.out.println(order.generateReceipt());

        System.out.println("\n🧾 The evidence is all laid out... the final decision is yours.");
        System.out.println("📦 Do you close the case with this order?"); //📦 Confirm and place your order?
        System.out.println("✅ Press [1] ➤ Yes, confirm order — case closed."); //✅ Press [1] ➤ Yes, confirm order
        System.out.println("🔁 Press [2] ➤ Not yet — there’s more to uncover."); //🔁 Press [2] ➤ No, still want to shop
        System.out.println("❌ Press [3] ➤ Scrap the file — start over."); //❌ Press [3] ➤ No, delete order
        System.out.print("👉 Your choice: ");
        int orderCommand = checkValidatedMenuSelection(3);

        do {
            switch (orderCommand) {
                case 1: //confirm order
                    FileManager.writeReceipt(order);
                    System.out.println("🎉 Case closed. Your receipt is filed, and justice (and hunger) is served.");
                    order = new Order();
                    break;
                case 2: //continue to shop
                    System.out.println("🔍 You tuck the case file under your arm and keep investigating.");
                    break;
                case 3: //delete order
                    System.out.println("🗑️ You shred the file. Sometimes, a fresh start is the only way.");
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
            System.out.println("\n🍟 The snack drawer creaks open — time to pick your crunchy accomplice.");

            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("🍟 Fancy a quick snack to crack the case?"); //🍟 Want A Snack?
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            for (int i = 0; i < allChips.size(); i++) {
                System.out.printf("🍟 Press [%d] ➤ %-15s\n", i + 1, allChips.get(i).getName());
            }
            System.out.println("🔚 Press [0] ➤ No more distractions.");

            System.out.print("👉 Select your chip: ");
            chipSelected = checkValidatedMenuSelection(allChips.size());
            if(chipSelected >= 1 && chipSelected <= allChips.size()) {
                chosenChips.add(allChips.get(chipSelected - 1));
                System.out.println("🥔 " + allChips.get(chipSelected - 1).getName() + " joined the lineup.");
            }
            else if (chipSelected == 0) {
                System.out.println("🚪 You close the snack drawer. Back to business.");
                break;
            }
            else {
                System.out.println("❌ That’s not on the list. Try again.");
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
            System.out.println("\n🍹 You step up to the cooler—condensation trails like clues down the glass.");

            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("🥤 Need a drink to cool off or stay sharp?"); //🥤 Want a drink?
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            for (int i = 0; i < allDrinks.size(); i++) {
                System.out.printf("🥤 Press [%d] ➤ %-15s\n", i + 1, allDrinks.get(i).getName());
            }
            System.out.println("🔚 Press [0] ➤ You've seen enough.");

            System.out.print("👉 Select your beverage: ");
            drinkSelected = checkValidatedMenuSelection(allDrinks.size());
            if(drinkSelected >= 1 && drinkSelected <= allDrinks.size()) {
                Drink chosenDrink = allDrinks.get(drinkSelected - 1);
                chosenDrinks.add(chosenDrink);

                System.out.println("\n📏 How strong do you want this lead to be?"); //📏 What size would you like?
                System.out.println("🟢 Press [1] ➤ Small — just a taste.");
                System.out.println("🟡 Press [2] ➤ Medium — steady sipper.");
                System.out.println("🔴 Press [3] ➤ Large — go all in.");
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
                System.out.println("💧 " + chosenDrink.getName() + " (" + chosenDrinkSize + ") added to your case file.");

            }
            else if (drinkSelected == 0) {
                System.out.println("🚪 You step away from the cooler. No more drinks—for now.");
                break;
            }
            else {
                System.out.println("❌ Invalid selection. This lead's a dead end.");
            }
        } while(drinkSelected != 0);

        //add drinks to products
        for(Drink drink: chosenDrinks) {
            order.addProduct(drink);
        }
    }

    //add sandwich to order
    private static void addSandwichProcess() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🥪 Suspect Profile: The Missing Sandwich");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🔨 Press [1] ➤ Reconstruct the sandwich piece by piece"); //build your own sandwich
        System.out.println("🌟 Press [2] ➤ Use a prebuilt 'signature sandwich' from evidence logs"); //prebuilt sandwich
        System.out.println("🔙 Press [0] ➤ Step away from the counter"); //go back
        System.out.print("👉 Your choice: ");
        int sandwichCommand = checkValidatedMenuSelection(2);

        switch (sandwichCommand) {
            case 1: //build your own sandwich
                printTypeWriter("\n🧩 You gather the clues. Time to reconstruct the scene from scratch...\n", 50);
                buildCustomSandwich();
                break;
            case 2: //choose signature sandwich
                printTypeWriter("\n📁 You pull up the deli's records. A signature sandwich might match the crime.\n", 50);
                addSignatureSandwich();
                break;
            case 0: //go back to main menu
                printTypeWriter("🔙 You back away, the scent of toasted bread still lingering in the air.\n", 50);
                return;
            default:
        }
    }

    //add signature sandwich
    private static void addSignatureSandwich() {
        System.out.println("\n📁 A new lead appears—two sandwiches known to stir up trouble...");
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🚀 Choose a Signature Sandwich:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🥓 Press [1] ➤ BLT — Crisp. Clean. But never innocent.");
        System.out.println("🧀 Press [2] ➤ Philly Cheese Steak — Hot, heavy, and full of secrets.");
        System.out.println("🔙 Press [0] ➤ Back out. Too risky... for now.");
        System.out.print("👉 Your choice: ");
        int sigCommand = checkValidatedMenuSelection(2);

        Sandwich sandwich = null;

        switch (sigCommand) {
            case 1: //BLT
                System.out.println("\n🥓 You chose the BLT. A classic case—bacon, lettuce, tomato. But sometimes... simplicity hides intent.");
                sandwich = new BLT();
                break;
            case 2: //Philly cheese steak
                System.out.println("\n🧀 You picked the Philly Cheese Steak. Hearty, hot, and likely to leave a mess at the scene.");
                sandwich = new PhillyCheeseSteak();
                break;
            case 0: //go back to sandwich options
                System.out.println("🔙 You turn away. Something didn't feel right... returning to the sandwich board."); //🔙 Returning to sandwich options...
                return;
            default:
        }

        //select side
        System.out.println("\n🍲 One final detail—Au Jus. Some say it’s just a dip... others know better.");
        System.out.println("✅ Press [1] ➤ Yes, add Au Jus—every clue counts.");
        System.out.println("❌ Press [0] ➤ No, we’ve got enough to go on.");
        System.out.print("👉 Your choice: ");
        int sideSelected = checkValidatedMenuSelection(1); //grab user choice of side
        sandwich.setHasAuJus(sideSelected == 1);

        //print out the sandwich so far
        System.out.println("\n📝 Case File: Signature Sandwich Identified.");
        System.out.println(sandwich);
        System.out.printf("💰 Total: $%.2f\n", sandwich.calcPrice());
        System.out.println("✅ Added to your growing investigation... stay sharp.\n");

        //final sandwich add to products
        order.addProduct(sandwich);
    }

    //build custom sandwich
    private static void buildCustomSandwich() {
        Sandwich sandwich = new Sandwich();

        printTypeWriter("\n📂 You open the evidence locker—time to reconstruct the sandwich.\n", 50);
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🛠️ Crime Scene: Sandwich Assembly Station"); //🥪 Sandwich Creation Station 🛠️
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        //select bread type
        printTypeWriter("\n🥖 First: The foundation. What bread did the culprit favor?\n", 50); //🥖 Available Bread Types:
        List<String> allBreads = sandwich.getOfferedBread();
        //print all bread types
        for (int i = 0; i < allBreads.size(); i++) {
            System.out.printf("🍞 Press [%d] ➤ %-7s\n", i + 1, allBreads.get(i));
        }
        System.out.print("👉 Your choice: ");
        int breadSelected = checkValidatedMenuSelection(allBreads.size()); //grab user choice of bread
        String chosenBread = allBreads.get(breadSelected - 1); //name of bread they selected
        sandwich.setSelectedBread(chosenBread); //put bread in sandwich

        //select size
        System.out.println("\n📏 How big was the crime? Choose your sandwich size:"); //📏 Choose your sandwich size:
        List<Integer> allSize = sandwich.getOfferedSizes();
        //print all bread size
        for (int i = 0; i < allSize.size(); i++) {
            System.out.printf("📐 Press [%d] ➤ %2s inches\n", i + 1, allSize.get(i));
        }
        System.out.print("👉 Your choice: ");
        int sizeSelected = checkValidatedMenuSelection(allSize.size()); //grab user choice of bread size
        int chosenSize = allSize.get(sizeSelected - 1); //size of bread they selected
        sandwich.setSelectedSize(chosenSize); //put size in sandwich


        //select toppings
        System.out.println("\n🔎 Toppings... the scene was messy. What clues were left behind?");
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
            System.out.println("🔚 Press [0] ➤ Done selecting toppings");
            System.out.print("👉 Your choice: ");
            toppingSelected = checkValidatedMenuSelection(allRegularToppings.size()); //grab user choice of topping

            if (toppingSelected == 0) {
                break;
            }

            Topping chosenTopping = allRegularToppings.get(toppingSelected - 1); //topping selected
            String chosenToppingName = chosenTopping.getName(); //name of topping they selected

            int count = 0;
            //check toppings making sure only add x2
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
                System.out.println("➕ " + chosenTopping.getName() + " added. The mystery deepens...");
            } else {
                System.out.println("🚫 No more than 2 of the same topping—the evidence must stay consistent.");
            }


        } while (toppingSelected != 0);

        //add meat
        System.out.println("\n🍖 A bold move—was meat involved?"); //🍖 Special Meat Toppings!

        //print all premium meat toppings
        for (int i = 0; i < allMeatToppings.size(); i++) {
            System.out.printf("🍖 Press [%d] ➤ %-7s\n", i + 1, allMeatToppings.get(i));
        }
        System.out.println("🔚 Press [0] ➤ Skip the meat");
        System.out.print("👉 Your choice: ");
        toppingSelected = checkValidatedMenuSelection(allMeatToppings.size()); //grab user choice of topping

        if (toppingSelected != 0) {
            Topping chosenTopping = allMeatToppings.get(toppingSelected - 1); //topping selected
            chosenToppings.add(chosenTopping);
            System.out.println("🕶️ Premium meat added. This thief had expensive taste...");

            System.out.println("\n✨ Add extra meat?");
            System.out.println("✅ Press [1] ➤ Yes, double the damage");
            System.out.println("❌ Press [0] ➤ No, that’s enough");
            System.out.print("👉 Your choice: ");
            int extraMeat = checkValidatedMenuSelection(1);

            if (extraMeat == 1) {
                sandwich.setExtraMeat(true);
            }
        }

        //add cheese
        System.out.println("\n🧀 The cheddar trail—cheese, always a classic motive."); //🧀 Special Cheese Toppings!

        //print all premium cheese toppings
        for (int i = 0; i < allCheeseToppings.size(); i++) {
            if (allCheeseToppings.get(i).getName().contains("Cheese")) {
                System.out.printf("🧀 Press [%d] ➤ %-7s\n", i + 1, allCheeseToppings.get(i));
            }
        }
        System.out.println("🔚 Press [0] ➤ Skip the cheese");
        System.out.print("👉 Your choice: ");

        toppingSelected = checkValidatedMenuSelection(allCheeseToppings.size()); //grab user choice of topping
        if (toppingSelected != 0) {
            Topping chosenTopping = allCheeseToppings.get(toppingSelected - 1); //topping selected
            chosenToppings.add(chosenTopping);
            System.out.println("🧩 Cheese locked in. That’s one more piece of the puzzle...");

            System.out.println("\n✨ Add extra cheese?");
            System.out.println("✅ Press [1] ➤ Yes, cheese it up");
            System.out.println("❌ Press [0] ➤ No extras");
            System.out.print("👉 Your choice: ");
            int extraCheese = checkValidatedMenuSelection(1);

            if (extraCheese == 1) {
                sandwich.setExtraCheese(true);
            }
        }

        //add all toppings to sandwich
        sandwich.setSelectedToppings(chosenToppings);

        //select sauces
        System.out.println("\n🍯 The final smear—what sauce ties it all together?"); //🍯 Add sauces

        List<String> chosenSauces = new ArrayList<>();
        List<String> allSauces = sandwich.getOfferedSauces(); //get all sauces
        for (int i = 0; i < allSauces.size(); i++) {
            System.out.printf("🍯 Press [%d] ➤ %-10s\n", i + 1, allSauces.get(i));
        }
        System.out.println("🔚 Press [0] ➤ Skip the sauce");

        System.out.print("👉 Your choice: ");
        int sauceSelected = checkValidatedMenuSelection(allSauces.size()); //grab user choice of sauce
        if (sauceSelected != 0) {
            chosenSauces.add(allSauces.get(sauceSelected - 1)); //name of sauce selected added to list
            sandwich.setSelectedSauces(chosenSauces); //put sauce in sandwich
        }


        //select toasted
        System.out.println("\n🔥 Toasted or not? Some like their clues crispy."); //🔥 Would you like your sandwich toasted?
        System.out.println("🔥 Press [1] ➤ Yes, toast it");
        System.out.println("❄️ Press [0] ➤ No, keep it cool");
        System.out.print("👉 Your choice: ");
        int toastSelected = checkValidatedMenuSelection(1); //grab user choice to toast
        sandwich.setToasted(toastSelected == 1); //put size in sandwich

        //select side
        System.out.println("\n🍲 Final question—Au Jus on the side?");
        System.out.println("✅ Press [1] ➤ Add the dip");
        System.out.println("❌ Press [0] ➤ Skip it");
        System.out.print("👉 Your choice: ");
        int sideSelected = checkValidatedMenuSelection(1); //grab user choice of side
        sandwich.setHasAuJus(sideSelected == 1);

        //print out the sandwich so far
        System.out.println("\n📝 Case report filed:");

        System.out.println(sandwich);
        System.out.printf("💰 Total: $%.2f\n", sandwich.calcPrice());
        System.out.println("✅ Sandwich added to your active investigation.\n");

        //final sandwich add to products
        order.addProduct(sandwich);
    }


    //order menu options
    private static void orderMenu() {
        System.out.println("\n🕵️ The counter is yours to search. What clue... or craving... will lead you closer?");

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
        if(!isFirstTime) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
//            System.out.println("👋 Welcome to The Case of the Missing Sandwich!");

            dotTypewriterInLineAnimation("👋 Welcome to ", 5); //welcome ...
            printTypeWriter("The Case of the Missing Sandwich!\n", 50);

            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
        else {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("👋 Welcome to The Case of the Missing Sandwich!");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
        printTypeWriter("👣 You stand at the deli entrance. Where will your investigation begin?\n", 50);
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

    /* User interface - Touch Up Methods*/

    //print text in typewriter style
    private static void printTypeWriter(String text, int delay) {
        for(char c: text.toCharArray()) { //convert string into char array
            System.out.print(c); //print each character
            try {
                Thread.sleep(delay); //delay between characters
            } catch (InterruptedException e) {
                writeErrorsToLogsFile(e);
            }

        }
    }

    private static void eraseTypeWriter(String text, int delay) {
        for (int i = text.length() - 1; i >= 0; i--) {
            System.out.print("\b \b"); // backspace, space, backspace (to fully erase the char)
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void spinner(int duration) {
        String[] spinnerChars = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};

        long end = System.currentTimeMillis() + duration;
        int i = 0;

        while (System.currentTimeMillis() < end) {
            System.out.print("\r" + spinnerChars[i++ % spinnerChars.length]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print("\r"); // Clear spinner
    }

    private static void spinnerInline(int duration) {
        String[] spinnerChars = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};
        long end = System.currentTimeMillis() + duration;
        int i = 0;

        while (System.currentTimeMillis() < end) {
            System.out.print(spinnerChars[i++ % spinnerChars.length]);
            System.out.flush();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                writeErrorsToLogsFile(e);
               }
            System.out.print("\b"); // backspace to overwrite just the spinner character
        }
    }

    private static void inlineDotLoader(String message, int repeatCount, int delay) {
        for (int r = 0; r < repeatCount; r++) {
            for (int i = 1; i <= 3; i++) {
                System.out.print("\r" + message + ".".repeat(i) + "   "); // spaces to overwrite
                System.out.flush();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    writeErrorsToLogsFile(e);
                }
            }
            // Clear just the dots after one cycle
            System.out.print("\r" + message + "      ");
        }
        System.out.println(); // move to next line after done
    }

    private static void dotTypewriterInLineAnimation(String message, int repeatCount) {
        printTypeWriter(message, 50); // Type out the message once

        for (int i = 0; i < repeatCount; i++) {
            for (int dots = 1; dots <= 3; dots++) {
                System.out.print("\r" + message + ".".repeat(dots));
                System.out.flush();

                try {
                    Thread.sleep(300); // Speed between dot steps
                } catch (InterruptedException e) {
                    writeErrorsToLogsFile(e);
                }
            }

            // After 3 dots, erase the dots only (keep message)
            System.out.print("\r" + message + "   \r" + message);
            System.out.flush();
        }

        // Move to the next line when done
//        System.out.println();
    }

    private static void dotTypewriterInLineAnimationKeepDots(String message, int repeatCount) {
        printTypeWriter(message, 50); // Type out the message once

        for (int i = 0; i < repeatCount; i++) {
            for (int dots = 1; dots <= 3; dots++) {
                System.out.print("\r" + message + ".".repeat(dots));
                System.out.flush();

                try {
                    Thread.sleep(300); // Speed between dot steps
                } catch (InterruptedException e) {
                    writeErrorsToLogsFile(e);
                }
            }
        }

        // After all repeats, keep the message with 3 dots permanently
        System.out.print("\r" + message + "...");
        System.out.flush();

        // Optionally, move to the next line when done
        // System.out.println();
    }






}
