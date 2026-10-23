package minibank;
import minibank.exception.AccountNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStore {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final List<String> transactionHistory = Collections.synchronizedList(new ArrayList<>());
    public void add(Account account) { accounts.put(account.getAccountNumber(), account); }
    public Account get(String number) throws AccountNotFoundException {
        Account a = accounts.get(number);
        if (a == null) throw new AccountNotFoundException(number);
        return a;
    }
    public Collection<Account> getAll() { return new ArrayList<>(accounts.values()); }
    public List<String> history() { synchronized(transactionHistory) { return new ArrayList<>(transactionHistory); } }
    public void log(String entry) { transactionHistory.add(java.time.LocalDateTime.now() + " | " + entry); }
    public List<Account> sortedByBalance() {
        List<Account> result = new ArrayList<>(accounts.values());
        result.sort(Comparator.comparingLong(Account::getBalance).reversed());
        return result;
    }
}
