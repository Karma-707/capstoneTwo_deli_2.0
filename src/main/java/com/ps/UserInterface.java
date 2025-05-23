package com.ps;

import com.ps.core.Order;
import com.ps.core.Product;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static Order order;
    private static List<Product> shoppingCart;

    //start to create a deli
    public static void init() {
        order = new Order();
        display();
    }

    //TODO: deli main functions
    private static void display() {

    }

    //TODO: add each order to user shopping
    private static void addOrder() {

    }

    //TODO: checkout user items in shoppingCart
    private static void checkOut() {

    }

}
