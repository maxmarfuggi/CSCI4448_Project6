package com.company;


// Helper Class - Handles menu printing
// Decorator Pattern
// Uses the decorator pattern by having the store object call methods with parameters to
// get the desired print menu

public class inventory_helper {

    // Print iterative menus with inventory content
    // Based on the type, prints corresponding menu
    public void print_menu_type(Store store, String type) {
        // Print edit inventory menu
        if(type.equals("qty")){
            System.out.println("|-------------------------------------|");
            System.out.println("| Snack                   |    Qty.   |");
            System.out.println("|-------------------------------------|");
            for (int i = 0; i < store.len; i++) {
                if(store.inventory[i].qty > -1) {
                    System.out.printf("| "+(i+1)+". %-15s       |", store.inventory[i].name);
                    System.out.printf("%5d    |%n", store.inventory[i].qty);
                }
                else {
                    System.out.println("|-------------------------------------|");
                }
            }
            System.out.println("|-------------------------------------|");
            System.out.println("|---- 0. To Return to Main Menu -----|");
            System.out.println("|-------------------------------------|");
        }

        // Print log sale menu
        else if(type.equals("price")){
            System.out.println("|------------------------------------------|");
            System.out.println("| Snack                     QTY. | Price   |");
            System.out.println("|------------------------------------------|");
            for (int i = 0; i < store.len; i++) {
                if(store.inventory[i].qty != 0) {
                    System.out.printf("| " + (i + 1) + ". %-15s         %2d  ", store.inventory[i].name, store.inventory[i].qty);
                    System.out.printf("| $%.2f   |%n", store.inventory[i].price);
                }
                else {
                    System.out.println("|------------------------------------------|");
                }
            }
            System.out.println("|-----------------------------------------|");
            System.out.println("|---- 20. To Return to Main Menu -----|");
            System.out.println("|-------------------------------------|");
        }

        // print sale history menu
        else if(type.equals("sale")) {
            System.out.println("|---------------------------------------------------|");
            System.out.println("| Item Sold              Qty. Sold  ||   Sale Total |");
            System.out.println("|---------------------------------------------------|");
            int total_sales = 0;
            for (int i = 0; i < store.daily_orders.size(); i++) {

                System.out.printf("| "+(i+1)+". %-15s           %2d   ", store.inventory[store.daily_orders.get(i).order[0]].name, store.daily_orders.get(i).order[1]);
                System.out.printf("||    $%.2f     |%n", store.daily_orders.get(i).total);
                System.out.println("|---------------------------------------------------|");
                total_sales += store.daily_orders.get(i).total;
            }
            System.out.println("|---------------  Total Sales   --------------------|");
            System.out.println("Total Sales: $" + total_sales + "\n\n");
        }
    }

    // Print section headers
    // Based on the type, prints corresponding menu
    public void print_header_type(String page) {
        // Switch statement to handle each possible type of menu header
        switch (page) {
            case "0":
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|    *    *    *          Main Menu           *    *    *     |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|~~ Type ID of the Desired Page and Click Enter to Proceed  ~~|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|    0. Return to Main Menu        2. Edit Inventory          |");
                System.out.println("|    1. Log Sale                   3. View Sales History      |");
                System.out.println("|                     4. Log Out                              |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");
                break;
            case "1":
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|    *    *    *          Log   Sale          *    *    *     |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");
                break;
            case "2":
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|    *    *    *        Edit Inventory        *    *    *     |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");
                break;
            case "3":
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|    *    *    *      View Sales History      *    *    *     |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");
                break;
            case "4":
                break;
            case "5":
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|    *    *    *     Welcome to Shopkeeper    *    *    *     |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|~~~ P O I N T ~~~~~~~~~~~~~ O F ~~~~~~~~~~~~~~~~ S A L E ~~~~|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");

                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|    *    *    *          Main Menu           *    *    *     |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|~~ Type ID of the Desired Page and Click Enter to Proceed  ~~|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|    0. Return to Main Menu        2. Edit Inventory          |");
                System.out.println("|    1. Log Sale                   3. View Sales History      |");
                System.out.println("|                     4. Log Out                              |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");
                break;
            default:
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|~~~~~~~~~~~~~~    Enter a Valid Page ID    ~~~~~~~~~~~~~~~~~~|");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
                System.out.println("|    0. Return to Main Menu        2. Edit Inventory          |");
                System.out.println("|    1. Log Sale                   3. View Sales History      |");
                System.out.println("|                     4. Log Out                              |");
                System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n");
                break;
        }
    }
}
