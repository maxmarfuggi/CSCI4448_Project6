package com.company;


// Class to represent each order of a sale
// Uses the observer pattern to send the order to the store when sale confirmation is granted
// Subtracts the quantity of the sale from the inventory when sale is confirmed
// Holds a size 2 array to hold item index and quantity, as well as order total

public class Orders {
    protected int[] order = new int[2];
    protected double total = 0;

    public void add_to_cart(Store store,int selection, int quantity){
        order[0] = selection;
        order[1] = quantity;
        total = store.inventory[selection].price * quantity;
    }

    public void send_order(Store store) {
        store.inventory[order[0]].qty -= order[1];
        store.daily_orders.add(this);
    }
}
