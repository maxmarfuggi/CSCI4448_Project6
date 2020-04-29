package com.company;

import java.util.Scanner;
import java.util.Vector;

public class Store {
    // Class attributes
    // Lists of Food and Drink Items
    // Vector of orders that have taken place during the session
    // length of inventory
    // Snack array that represents store inventory
    private String[] foods = {"Cheetos", "Hot Cheetos", "Taki's", "Goldfish", "Pretzels",
                        "Popcorn", "Lays", "Ruffles", "Nature Valley", "Fruit Snacks"};

    private String[] drinks = {"Water", "Blue Gatorade", "Red Gatorade", "Yellow Gatorade"};
    protected Vector<Orders> daily_orders = new Vector<>();
    protected int len = foods.length + drinks.length;
    protected Snack[] inventory = new Snack[len];
    public boolean flag = true;
    public boolean flag2 = true;

    // Constructor method
    // Loads the snacks into the inventory
    // Uses strategy pattern to instantiate the snack objects
    public Store(){
        int count = 0;
        for (int i = 0; i < foods.length; i++){
            Snack item = new Food(foods[i]);
            inventory[i] = item;
            count = count +1;
        }
        for(int i = count; i < len; i++){
            Snack item = new Drink(drinks[i-count]);
            inventory[i] = item;
        }
    }

    // Method to run store opening protocol
    // Opens store interface with inventory_helper object
    public void open() {
        inventory_helper helper = new inventory_helper();
        helper.print_header_type("5");
    }

    // Method to run store menu protocol
    // Opens menu interface with inventory_helper object
    public void menu(){
        inventory_helper helper = new inventory_helper();
        helper.print_header_type("0");
    }

    // Method to run store error protocol
    // Opens error message with inventory_helper object
    public void error() {
        inventory_helper helper = new inventory_helper();
        helper.print_header_type("-5");
    }

    // Method to run Sales History Procedures
    // Opens menu interface with inventory_helper object
    // Uses helper object to print all of the sales in the daily_orders vector
    public void sales_history() {
        inventory_helper helper = new inventory_helper();
        helper.print_header_type("3");
        helper.print_menu_type(this,"sale");
    }

    // Log Sale method
    // Responsible for registering sales and creating order objects
    // Uses helper object methods to print header and menu
    public void log_sale() {
        Orders order = new Orders();
        inventory_helper helper = new inventory_helper();
        helper.print_header_type("1");
        helper.print_menu_type(this,"price");

        // Ask for the desired item id

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Item ID you would like to add: ");
        String choice = input.nextLine();
        int selection = Integer.parseInt(choice) - 1;

        // Check that the input is valid
        if(selection >= 0 && selection < len){
            System.out.println("How Many Units of " + inventory[selection].name + "?");
            String qt = input.nextLine();
            int q = Integer.parseInt(qt);

            // If id is valid check that quantity is valid
            // If valid, confirm purchase, use order object method to send order to order archive
            if(q > 0 && q <= inventory[selection].qty) {
                System.out.println("Confirm Purchase? - " + q + " units of " + inventory[selection].name);
                System.out.println("Type '1' for Yes, '0' for No: ");
                String conf = input.nextLine();

                if(conf.equals("0")){
                    System.out.println("\nCart Emptied.\n\n");
                }
                else if (conf.equals("1")) {
                    order.add_to_cart(this,selection,q);
                    order.send_order(this);
                }
            }
            else {
                System.out.println("\nEnter a Valid Item Quantity. \n\n");
            }
            log_sale();
        }

        // Escape key
        else if(selection == 19){
            flag2 = false;
        }
        else {
            System.out.println("\n\nEnter a Valid Item ID. \n");
        }
    }

    // Edit inventory method
    // Responsible for updating inventory totals
    // Uses helper object to print menus
    public void edit_inventory() {
        inventory_helper helper = new inventory_helper();
        helper.print_header_type("2");
        helper.print_menu_type(this,"qty");

        // Asking for id of edit item
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Item ID you would like to edit: ");
        int ind = -1;
        boolean valid = false;

        // Loop until the escape key is entered
        while(!valid){
            String item = input.nextLine();

            // Ensure input is a number, capture input if valid
            try
            {
                int index = Integer.parseInt(item);
                ind = index;
                if(index <= len && index > 0){
                    valid = true;
                }
                else if(index == 0){
                    flag = false;
                    return;
                }
                else{
                    System.out.println("\nEnter a Valid Item ID. ");
                }
            }
            catch(NumberFormatException ex)
            {
                System.out.println("\nEnter a Valid Item ID. ");
            }
        }

        // Ask for new quantity value

        System.out.println("Enter the new Item Quantity: ");
        String q = input.nextLine();
        int qty = Integer.parseInt(q);

        boolean stop = false;

        // Loop until escape key is hit,
        while(!stop){
            System.out.println("Confirm change? '1' for yes, '0' for no.");
            String confirm = input.nextLine();
            if(confirm.equals("1")){
                inventory[ind - 1].qty = qty;
                stop = true;
            }
            else if(confirm.equals("0")){
                flag = false;
                return;
            }
            else{
                System.out.println("Enter a Valid Response. ");
            }
        }

        // Run method again to give the option to go back to main menu
        edit_inventory();
    }
}