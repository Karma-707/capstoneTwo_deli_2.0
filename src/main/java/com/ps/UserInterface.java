package com.ps;

import com.ps.core.*;

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

    //TODO: deli main functions
    private static void display() {
        int homeScreenCommand;

        do {
            printHomeScreenMenu();
            System.out.print("👉 Enter your command: ");

            homeScreenCommand = scanner.nextInt();

            switch (homeScreenCommand) {
                case 1: //new order
                    orderScreen();
                    break;
                case 0: //exits from program
                    System.out.println("Thanks for having us, enjoy!");
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
            orderMenuCommand = scanner.nextInt();

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
                case 0:
                    System.out.println("Going back to home screen");
                    break;
                default:
                    System.out.println("⚠️ Invalid input, please try again");
            }

        } while(orderMenuCommand != 0);

    }

    //checkout user order
    private static void checkoutProcess() {
        //print list of products user ordered
        if(order.getProducts().isEmpty()) {
            System.out.println("⚠️ Your order is empty. Add something first.");
            return;
        }

        System.out.println("\n🧾 Here’s your order summary:");
        System.out.println(order.generateReceipt());

        System.out.println("Confirm and place your order?");
        System.out.println("Press [1] ➤ Yes, confirm order");
        System.out.println("Press [2] ➤ No, still want to shop.");
        System.out.print("👉 Your choice: ");
        int orderCommand = scanner.nextInt();

        if(orderCommand == 1) {
            FileManager.writeReceipt(order);
            System.out.println("🧾 Your receipt has been saved. Thank you for your order!");
        }
        else {
            //requirement says to delete order?
            System.out.println("Checkout cancelled. Go back and edit your order");
        }

    }

    //add chip to order
    private static void addChipProcess() {
        List<Chip> allChips = order.getChipList();
        List<Chip> chosenChips = new ArrayList<>();
        int chipSelected;
        do {
            System.out.println("\nWant a drink?");
            for (int i = 0; i < allChips.size(); i++) {
                System.out.printf("Press [%d] ➤ %-15s\n", i + 1, allChips.get(i).getName());
            }
            System.out.println("Press [0] ➤ Done");

            System.out.print("👉 Select your beverage: ");
            chipSelected = scanner.nextInt();
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
            System.out.println("\nWant a drink?");
            for (int i = 0; i < allDrinks.size(); i++) {
                System.out.printf("Press [%d] ➤ %-15s\n", i + 1, allDrinks.get(i).getName());
            }
            System.out.println("Press [0] ➤ Done");

            System.out.print("👉 Select your beverage: ");
            drinkSelected = scanner.nextInt();
            if(drinkSelected >= 1 && drinkSelected <= allDrinks.size()) {
                chosenDrinks.add(allDrinks.get(drinkSelected - 1));
                System.out.println(allDrinks.get(drinkSelected - 1).getName() + " added.");
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
        Sandwich sandwich = new Sandwich();
        System.out.println("\nReady to start building your sandwich? Let's go!");

        //select bread type
        System.out.println("\nAvailable Bread Types");
        List<String> allBreads = sandwich.getOfferedBread();
        //print all bread types
        for(int i = 0; i < allBreads.size(); i++) {
            System.out.printf("Press [%d] ➤ %-7s\n", i+1, allBreads.get(i));
        }
        System.out.print("👉 Select your bread: ");
        int breadSelected = scanner.nextInt(); //grab user choice of bread
        String chosenBread = allBreads.get(breadSelected-1); //name of bread they selected
        sandwich.setSelectedBread(chosenBread); //put bread in sandwich

        //select size
        System.out.println("\nChoose sandwich size:");
        List<Integer> allSize = sandwich.getOfferedSizes();
        //print all bread size
        for(int i = 0; i < allSize.size(); i++) {
            System.out.printf("Press [%d] ➤ %-2s inches\n", i+1, allSize.get(i));
        }
        System.out.print("👉 Select your size: ");
        int sizeSelected = scanner.nextInt(); //grab user choice of bread
        int chosenSize = allSize.get(sizeSelected-1); //size of bread they selected
        sandwich.setSelectedSize(chosenSize); //put size in sandwich

        //select toppings
        int toppingSelected;
        List<Topping> allToppings = new ArrayList<>(sandwich.getOfferedRegularTopping());
        allToppings.addAll(sandwich.getOfferedPremiumTopping());

        List<Topping> chosenToppings = new ArrayList<>();

        do {
            System.out.println("\nAdd toppings!");

            //print all toppings
            for (int i = 0; i < allToppings.size(); i++) {
                System.out.printf("Press [%d] ➤ %-7s\n", i + 1, allToppings.get(i));
            }
            System.out.println("Press [0] ➤ Done");
            System.out.print("👉 Select your topping: ");

            toppingSelected = scanner.nextInt(); //grab user choice of topping
            if(toppingSelected == 0) {
                break;
            }
            Topping chosenTopping = allToppings.get(toppingSelected - 1); //topping selected
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
                System.out.println(chosenTopping.getName() + " added to your sandwich.");
            } else {
                System.out.println("❌ You can only add up to 2 of the same topping. No more extras!");
            }

        } while(toppingSelected != 0);
        sandwich.setSelectedToppings(chosenToppings);

        //select sauces
        List<String> chosenSauces = new ArrayList<>();
        System.out.println("\nAdd sauces");
        List<String> allSauces = sandwich.getOfferedSauces(); //get all sauces
        for(int i = 0; i < allSauces.size(); i++) {
            System.out.printf("Press [%d] ➤ %-10s\n", i+1, allSauces.get(i));
        }
        System.out.print("👉 Select your sauce: ");
        int sauceSelected = scanner.nextInt(); //grab user choice of sauce
        chosenSauces.add(allSauces.get(sauceSelected-1)); //name of sauce selected added to list
        sandwich.setSelectedSauces(chosenSauces); //put sauce in sandwich

        //select toasted
        System.out.println("\nWould you like your sandwich toasted?");
        System.out.println("Press [1] ➤ Yes, toast it");
        System.out.println("Press [2] ➤ No, leave it untoasted");
        System.out.print("👉 Your choice: ");

        int toastSelected = scanner.nextInt(); //grab user choice to toast
        boolean chosenToast = (toastSelected == 1); //toasted of bread
        //TODO: validate choice for other
        sandwich.setToasted(chosenToast); //put size in sandwich

        //print out the sandwich so far
        System.out.println(sandwich);
        //make the list editable if they want to change anything

        //final sandwich add to products
        order.addProduct(sandwich);
    }


    //order menu options
    private static void orderMenu() {
        System.out.println("\nReady to order?");
        System.out.println("Press [1] ➤ Add Sandwich");
        System.out.println("Press [2] ➤ Add Drink");
        System.out.println("Press [3] ➤ Add Chips");
        System.out.println("Press [4] ➤ Checkout");
        System.out.println("Press [0] ➤ Cancel Order");
    }

    //print home screen menu options
    private static void printHomeScreenMenu() {
        System.out.println("\nWelcome to The Case of the Missing Sandwich");
        System.out.println("What would you like to do?");
        System.out.println("Press [1] ➤ New Order");
        System.out.println("Press [0] ➤ Exit");
    }

    //TODO: add each order to user shopping
    private static void addProduct() {

    }

    //TODO: checkout user items in order
    private static void checkOut() {

    }

}
