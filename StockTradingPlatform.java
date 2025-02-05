package com.shristi.internship;

import java.util.*;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class User {
    String username;
    double balance;
    Map<String, Integer> portfolio;

    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }
}

public class StockTradingPlatform {
    private static final Map<String, Stock> market = new HashMap<>();
    private static final Map<String, User> users = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        initializeMarket();
        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void initializeMarket() {
        market.put("AAPL", new Stock("AAPL", 150.00));
        market.put("GOOGL", new Stock("GOOGL", 2800.00));
        market.put("TSLA", new Stock("TSLA", 700.00));
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }
        users.put(username, new User(username, 5000.00));
        System.out.println("Registration successful!");
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (!users.containsKey(username)) {
            System.out.println("User not found!");
            return;
        }
        currentUser = users.get(username);
        System.out.println("Login successful!");
        userMenu();
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n1. View Market\n2. Buy Stock\n3. Sell Stock\n4. View Portfolio\n5. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> viewMarket();
                case 2 -> buyStock();
                case 3 -> sellStock();
                case 4 -> viewPortfolio();
                case 5 -> {
                    currentUser = null;
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void viewMarket() {
        System.out.println("\nMarket Prices:");
        for (Stock stock : market.values()) {
            stock.price += (Math.random() - 0.5) * 10;  // Simulate price change
            System.out.printf("%s: $%.2f%n", stock.symbol, stock.price);
        }
    }

    private static void buyStock() {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();
        if (!market.containsKey(symbol)) {
            System.out.println("Stock not found!");
            return;
        }
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        double totalCost = market.get(symbol).price * quantity;
        if (currentUser.balance < totalCost) {
            System.out.println("Insufficient balance!");
            return;
        }
        currentUser.balance -= totalCost;
        currentUser.portfolio.put(symbol, currentUser.portfolio.getOrDefault(symbol, 0) + quantity);
        System.out.println("Stock purchased successfully!");
    }

    private static void sellStock() {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();
        if (!currentUser.portfolio.containsKey(symbol) || currentUser.portfolio.get(symbol) == 0) {
            System.out.println("You don't own this stock!");
            return;
        }
        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (quantity > currentUser.portfolio.get(symbol)) {
            System.out.println("Insufficient stocks to sell!");
            return;
        }
        double totalGain = market.get(symbol).price * quantity;
        currentUser.balance += totalGain;
        currentUser.portfolio.put(symbol, currentUser.portfolio.get(symbol) - quantity);
        System.out.println("Stock sold successfully!");
    }

    private static void viewPortfolio() {
        System.out.println("\nYour Portfolio:");
        for (Map.Entry<String, Integer> entry : currentUser.portfolio.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.printf("%s: %d shares%n", entry.getKey(), entry.getValue());
            }
        }
        System.out.printf("Balance: $%.2f%n", currentUser.balance);
    }
}
