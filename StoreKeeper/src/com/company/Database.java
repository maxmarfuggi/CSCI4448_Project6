package com.company;

import java.sql.*;


// Database class - Responsible for making connections with database as well as updating it
// Uses the observer pattern to watch for state changes so that it can submit changes to the database
public class Database {
    // Connection object for database
    Connection db_connect = null;


    Database() {
        System.out.println("Successfully connected to Database.");
    }

    // Connect to database function
    public Connection connect() {
        // Database info
        final String url = "jdbc:postgresql://localhost/maxmarfuggi";
        final String user = "maxmarfuggi";
        final String password = "<12345>";

        // Try catch to attempt a connection, raise error if connection fails
        try {
            db_connect = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // return succesful connection
        return db_connect;
    }

    // Method to count the rows in the database table, returns # of rows
    public int getSnackCount() {
        String command = "SELECT count(*) FROM stock";
        int count = 0;

        // Try catch to attempt a connection, raise error if connection fails
        // If connection is succesful, send query to database
        try (Connection conn = connect();
             Statement query = conn.createStatement();
             ResultSet query_result = query.executeQuery(command)) {
            query_result.next();
            count = query_result.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    // Method to load the database values and pass them to helper function
    // so they can be sent to the inventory for updating
    public void getSnacks(Store store) {

        String command = "SELECT id, qty FROM stock";

        try (Connection conn = connect();
             Statement query = conn.createStatement();
             ResultSet query_result = query.executeQuery(command)) {
            displaySnacks(query_result, store);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Method to raise flag and update observer that the state has changed
    // Iterates through rows and updates each entry in inventory
    private void displaySnacks(ResultSet query_result, Store store) throws SQLException {
        while (query_result.next()) {
            int id = Integer.parseInt(query_result.getString("id"));
            id = id - 1;
            int qty = Integer.parseInt(query_result.getString("qty"));
            update(store,id,qty);
        }
    }

    // Method to update database based on inventory stock upon log out
    public void update_database(Store store){
        int rows = 0;

        // Iterates through rows and updates each entry in the database
        for (int i = 0; i < store.len; i++){
            String query = "UPDATE stock " + "SET qty = ? " + "WHERE id = ? ";

            try (Connection conn = connect();
                 PreparedStatement db = conn.prepareStatement(query)) {

                db.setInt(1, store.inventory[i].qty);
                db.setInt(2, i+1);

                rows += db.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("\nDB Rows Updated: "+ rows);
        System.out.println("System States Saved, Logging out...");
    }

    // Observer method to change the inventory quantity at load time
    public void update(Store store, int id, int qty){
        store.inventory[id].setQty(qty);
    }
}
