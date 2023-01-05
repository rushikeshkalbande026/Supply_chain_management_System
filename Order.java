package com.example.supplychainrushi;

public class Order {
    public static boolean placeOrder(String customerEmail, Product product){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = String.format("INSERT INTO order (customer_id, product_id) VALUES ((SELECT customer_id FROM customer WHERE email = '%s' ), %s)", customerEmail, product.getId());
        int rowCount = 0;
        try{
            rowCount = databaseConnection.executeUpdateQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rowCount != 0;
    }
}
