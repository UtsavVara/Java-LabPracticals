package minibank;

import minibank.exception.*;
import java.io.IOException;
import java.util.*;

public class MiniBank {
    private final BankService bank = new BankService();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new MiniBank().run();
    }
    private void run() {
        System.out.println("=================================");
        System.out.println("       MINIBANK CONSOLE");
        System.out.println("=================================");
        boolean running = true;
        while (running) {
            showMenu();
            String choice = readLine("Choose an option: ");
            try {
                switch (choice) {
                    case "1" -> openAccount();
                    case "2" -> deposit();
                    case "3" -> withdraw();
                    case "4" -> transfer();
                    case "5" -> listAccounts();
                    case "6" -> showHistory();
                    case "7" -> System.out.println(bank.report());
                    case "8" -> showInterest();
                    case "0" -> running = false;
                    default -> System.out.println("Invalid choice. Enter a number from 0 to 8.");
                }
            } catch (BankException | IllegalArgumentException ex) {
                System.out.println("Operation failed: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Unexpected error handled safely: " + ex.getMessage());
            }
            System.out.println();
        }
        try { bank.save(); } catch (IOException ex) { System.out.println("Could not save data: " + ex.getMessage()); }
        System.out.println("Thank you for using MiniBank. Goodbye!");
        scanner.close();
    }
    private void showMenu() {
        System.out.println("1. Open account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. List accounts");
        System.out.println("6. Transaction history");
        System.out.println("7. End-of-day report");
        System.out.println("8. Interest estimate");
        System.out.println("0. Exit");
    }
    private void openAccount() {
        String name = readLine("Customer name: ");
        long opening = readLong("Opening balance (whole rupees): ");
        String type = readLine("Type [S= Savings, C=Current, F=Fixed Deposit]: ");
        Account a = bank.openAccount(name, opening, type);
        System.out.println("Account created: " + a);
    }
    private void deposit() throws BankException {
        String number = readLine("Account number: ");
        long amount = readLong("Deposit amount: ");
        bank.deposit(number, amount);
        System.out.println("Deposit successful. New balance: ₹" + bank.find(number).getBalance());
    }
    private void withdraw() throws BankException {
        String number = readLine("Account number: ");
        long amount = readLong("Withdrawal amount: ");
        bank.withdraw(number, amount);
        System.out.println("Withdrawal successful. New balance: ₹" + bank.find(number).getBalance());
    }
    private void transfer() throws BankException {
        String from = readLine("From account: ");
        String to = readLine("To account: ");
        long amount = readLong("Transfer amount: ");
        bank.transfer(from, to, amount);
        System.out.println("Transfer successful.");
    }
    private void listAccounts() {
        List<Account> accounts = new ArrayList<>(bank.sortedAccounts());
        if (accounts.isEmpty()) { System.out.println("No accounts available."); return; }
        accounts.forEach(System.out::println);
    }
    private void showHistory() {
        List<String> history = bank.history();
        if (history.isEmpty()) { System.out.println("No transactions in this session."); return; }
        history.forEach(System.out::println);
    }
    private void showInterest() throws BankException {
        String number = readLine("Account number: ");
        Account a = bank.find(number);
        System.out.printf(Locale.ROOT, "Rate: %.1f%% | Estimated yearly interest: ₹%.2f%n",
            a.interestRate(), bank.yearlyInterest(a));
    }
    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    private long readLong(String prompt) {
        while (true) {
            String value = readLine(prompt);
            try { return Long.parseLong(value); }
            catch (NumberFormatException ex) { System.out.println("Please enter a whole number, e.g. 1500."); }
        }
    }
}
