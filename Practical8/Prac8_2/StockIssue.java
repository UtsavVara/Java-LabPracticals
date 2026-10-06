package Practical8.Prac8_2;

import java.util.*;

// Custom checked exception for insufficient stock
class OutOfStockException extends Exception
{
    private int shortfall;

    public OutOfStockException(int shortfall)
    {
        super("Insufficient stock. Shortfall = " + shortfall);
        this.shortfall = shortfall;
    }

    public int getShortfall()
    {
        return shortfall;
    }
}

// Custom checked exception for invalid quantity
class InvalidQuantityException extends Exception
{
    public InvalidQuantityException(String message)
    {
        super(message);
    }
}

// Warehouse class
class Warehouse
{
    private Map<String, Integer> stock = new HashMap<>();

    // Add stock
    public void addStock(String item, int qty)
    {
        stock.put(item, qty);
    }

    // Issue items
    public void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException
    {
        // Check quantity
        if(qty <= 0)
        {
            throw new InvalidQuantityException("Quantity must be greater than 0.");
        }

        int available = stock.getOrDefault(item, 0);

        // Check stock
        if (qty > available)
        {
            int shortfall = qty - available;
            throw new OutOfStockException(shortfall);
        }

        // Reduce stock
        stock.put(item, available - qty);

        System.out.println("Issued " + qty + " " + item + "(s) successfully.");
    }
}

// Main class
public class StockIssue
{
    public static void main(String[] args)
    {
        Warehouse warehouse = new Warehouse();

        // Initial stock
        warehouse.addStock("Laptop", 10);
        warehouse.addStock("Mouse", 5);
        warehouse.addStock("Keyboard", 8);

        // List of requests
        String[][] requests = {
                {"Laptop", "3"},
                {"Mouse", "10"},
                {"Keyboard", "0"},
                {"Laptop", "4"},
                {"Monitor", "2"},
                {"Mouse", "2"}
        };

        // Process every request
        for (String[] request : requests)
        {
            String item = request[0];
            int qty = Integer.parseInt(request[1]);

            System.out.println("Request: " + item + " - " + qty);

            try
            {
                warehouse.issue(item, qty);
            }

            catch(OutOfStockException e)
            {
                System.out.println("Out of stock! Shortfall = " + e.getShortfall());
            }
            catch(InvalidQuantityException e)
            {
                System.out.println("Invalid quantity: " + e.getMessage());
            }

            // The loop continues even if an exception occurs
            System.out.println("----------------------");
        }

        System.out.println("All requests processed.");
    }
}
