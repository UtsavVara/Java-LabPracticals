package minibank;
import minibank.exception.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class BankService implements InterestBearing {
    private final AccountStore store = new AccountStore();
    private final Path dataFile = Path.of("data", "accounts.bin");
    private final Path logFile = Path.of("data", "transactions.log");
    private final ReentrantLock transferLock = new ReentrantLock();

    public BankService() {
        try {
            for (Account a : StatePersister.load(dataFile)) {
                Account.reserveAccountNumber(a.getAccountNumber());
                store.add(a);
            }
        }
        catch (IOException ex) { System.err.println("Warning: saved data could not be loaded: " + ex.getMessage()); }
        if (store.getAll().isEmpty()) {
            store.add(new SavingsAccount("Aarav Patel", 5000));
            store.add(new CurrentAccount("Diya Shah", 10000));
            store.add(new FixedDepositAccount("Riya Desai", 15000));
        }
    }
    public Collection<Account> listAccounts() { return store.getAll(); }
    public List<Account> sortedAccounts() { return store.sortedByBalance(); }
    public Account find(String number) throws AccountNotFoundException { return store.get(number); }
    public Account openAccount(String name, long opening, String type) {
        Account a = switch (type.toUpperCase(Locale.ROOT)) {
            case "S", "SAVINGS" -> new SavingsAccount(name, opening);
            case "C", "CURRENT" -> new CurrentAccount(name, opening);
            case "F", "FIXED" -> new FixedDepositAccount(name, opening);
            default -> throw new IllegalArgumentException("Choose S (Savings), C (Current), or F (Fixed Deposit).");
        };
        store.add(a); store.log("OPEN " + a.getAccountNumber() + " ₹" + opening); persistQuietly(); return a;
    }
    public void deposit(String number, long amount) throws BankException {
        Account a = store.get(number); a.deposit(amount);
        log("DEPOSIT " + number + " ₹" + amount); persistQuietly();
    }
    public void withdraw(String number, long amount) throws BankException {
        Account a = store.get(number);
        if (amount <= 0) throw new InvalidAmountException("Amount must be greater than zero.");
        if (!a.withdraw(amount)) throw new InsufficientFundsException(Math.max(0, amount - a.getBalance()));
        log("WITHDRAW " + number + " ₹" + amount); persistQuietly();
    }
    public void transfer(String from, String to, long amount) throws BankException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be greater than zero.");
        transferLock.lock();
        try {
            Account source = store.get(from), destination = store.get(to);
            if (source.equals(destination)) throw new InvalidAmountException("Source and destination must differ.");
            synchronized (source) {
                synchronized (destination) {
                    if (!source.canWithdraw(amount)) throw new InsufficientFundsException(Math.max(0, amount-source.getBalance()));
                    if (source instanceof FixedDepositAccount) throw new InvalidAmountException("Fixed deposit accounts are locked.");
                    source.withdraw(amount);
                    try { destination.deposit(amount); }
                    catch (BankException ex) { source.deposit(amount); throw ex; }
                }
            }
            log("TRANSFER " + from + " -> " + to + " ₹" + amount); persistQuietly();
        } finally { transferLock.unlock(); }
    }
    public List<String> history() { return store.history(); }
    public String report() {
        String report = ReportGenerator.build(store.getAll());
        try { ReportGenerator.write(report, Path.of("data", "daily-report.txt")); }
        catch (IOException ex) { return report + "\nCould not write report: " + ex.getMessage(); }
        return report;
    }
    public void save() throws IOException { StatePersister.save(store.getAll(), dataFile); }
    private void log(String s) {
        store.log(s);
        try { TransactionLog.append(logFile, s); }
        catch (IOException ex) { System.err.println("Warning: transaction log unavailable: " + ex.getMessage()); }
    }
    private void persistQuietly() {
        try { save(); } catch (IOException ex) { System.err.println("Warning: data could not be saved: " + ex.getMessage()); }
    }
}
