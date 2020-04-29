package com.company;


// Abstract Snack Class
// Strategy Pattern - each inherited class sets their own price
// Has name, quantity, and price attributes, as well as a set quantity method
public abstract class Snack {
    String name;
    int qty;
    double price;

    public Snack(String name1){
        name = name1;
        qty = 1;
    }

    public void setQty(int n){
        qty = n;
    }
}

// Food inherited class
// Uses inherited constructor, and updates price
class Food extends Snack {
    public Food(String name1) {
        super(name1);
        price = 1.00;
    }
}

// Drink inherited class
// Uses inherited constructor, and updates price
class Drink extends Snack {
    public Drink(String name1) {
        super(name1);
        price = 2.00;
    }
}
