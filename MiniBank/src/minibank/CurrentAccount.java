package minibank;
public class CurrentAccount extends Account {
    private static final long serialVersionUID = 1L;
    private final long overdraftLimit;
    public CurrentAccount(String ownerName, long openingBalance) { super(ownerName, openingBalance); this.overdraftLimit = 5000; }
    @Override public double interestRate() { return 0.0; }
    @Override public synchronized boolean canWithdraw(long amount) {
        return amount > 0 && getBalance() - amount >= -overdraftLimit;
    }
    @Override protected synchronized void restoreBalance(long value) {
        if (value < -overdraftLimit) throw new IllegalArgumentException("Balance exceeds overdraft limit.");
        super.restoreBalance(Math.max(0, value));
    }
    public long getOverdraftLimit() { return overdraftLimit; }
}
