package minibank;

import minibank.annotation.Id;
import minibank.annotation.Positive;
import minibank.exception.*;
import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable, Comparable<Account> {
    private static final long serialVersionUID = 1L;
    @Id @minibank.annotation.MaxLength(20)
    private final String accountNumber;
    private final String ownerName;
    @Positive private long balance;
    private boolean active = true;

    public Account(String ownerName, long openingBalance) {
        this("AC" + String.format("%04d", AccountNumbers.next()), ownerName, openingBalance);
    }
    public Account(String accountNumber, String ownerName, long openingBalance) {
        if (accountNumber == null || accountNumber.isBlank()) throw new IllegalArgumentException("Account number is required.");
        if (ownerName == null || ownerName.isBlank()) throw new IllegalArgumentException("Owner name is required.");
        if (openingBalance < 0) throw new IllegalArgumentException("Opening balance cannot be negative.");
        this.accountNumber = accountNumber.trim();
        this.ownerName = ownerName.trim();
        this.balance = openingBalance;
    }
    public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be greater than zero.");
        if (Long.MAX_VALUE - balance < amount) throw new InvalidAmountException("Deposit exceeds supported balance.");
        balance += amount;
    }
    public synchronized boolean withdraw(long amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be greater than zero.");
        if (!canWithdraw(amount)) return false;
        balance -= amount;
        return true;
    }
    public boolean canWithdraw(long amount) { return amount > 0 && balance >= amount; }
    public double interestRate() { return 0.0; }
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public synchronized long getBalance() { return balance; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    @Override public String toString() {
        return String.format("%s | %-20s | Balance: ₹%d | %s", accountNumber, ownerName, getBalance(), active ? "ACTIVE" : "INACTIVE");
    }
    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account other)) return false;
        return accountNumber.equals(other.accountNumber);
    }
    @Override public int hashCode() { return Objects.hash(accountNumber); }
    @Override public int compareTo(Account other) { return accountNumber.compareTo(other.accountNumber); }
    protected synchronized void restoreBalance(long value) {
        if (value < 0) throw new IllegalArgumentException("Restored balance cannot be negative.");
        balance = value;
    }
    static void reserveAccountNumber(String existingNumber) {
        if (existingNumber == null || !existingNumber.matches("AC[0-9]+")) return;
        try { AccountNumbers.reserve(Integer.parseInt(existingNumber.substring(2)) + 1); }
        catch (NumberFormatException ignored) { /* Ignore nonstandard imported account numbers. */ }
    }
    private static final class AccountNumbers {
        private static int counter = 1;
        static synchronized int next() { return counter++; }
        static synchronized void reserve(int nextValue) { counter = Math.max(counter, nextValue); }
    }
}
