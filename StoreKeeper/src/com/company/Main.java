package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean esc = true;

        // Instantiating the store and database objects
        // Connecting to DB
        Store store = new Store();
        Database stock_db = new Database();
        stock_db.connect();

        // Loading snacks from DB into inventory, open the POS interface
        System.out.println(stock_db.getSnackCount() + " rows of data have been updated.");
        stock_db.getSnacks(store);

        store.open();

        Scanner input = new Scanner(System.in);
        String page = input.nextLine();

        System.out.println("Chosen Page: ");

        // Loop to continue menu navigation until the escape key is hit
        // Each respective store method is run for the corresponding cases
        while(esc){
            switch (page){
                // Main Menu Screen
                // Store object runs menu script
                case "0":
                    store.menu();

                    System.out.println("Chosen Page: ");
                    page = input.nextLine();
                    break;

                // Log Sale Screen
                // Escape condition if escape key is hit
                case "1":
                    store.log_sale();

                    if(!store.flag2){
                        page = "0";
                        store.flag2 = true;
                        break;
                    }

                    System.out.println("Chosen Page: ");
                    page = input.nextLine();
                    break;

                // Edit inventory screen
                // Escape key condition
                case "2":
                    store.edit_inventory();

                    if(!store.flag){
                        page = "0";
                        store.flag = true;
                        break;
                    }
                    System.out.println("Chosen Page: ");
                    page = input.nextLine();
                    break;

                // Sales History Screen
                case "3":
                    store.sales_history();

                    page = "0";
                    break;

                // Log out
                // Observer Pattern
                // When the escape key is hit, the flag is changed to update the database
                // based on the inventory
                case "4":
                    esc = false;
                    stock_db.update_database(store);
                    break;

                // Invalid key is hit, ask for valid entry
                default:
                    store.error();

                    System.out.println("Chosen Page: ");
                    page = input.nextLine();
                    break;
            }
        }
    }
}
