package minibank;
public class SavingsAccount extends Account {
    private static final long serialVersionUID = 1L;
    private final long minBalance;
    public SavingsAccount(String ownerName, long openingBalance) { super(ownerName, openingBalance); this.minBalance = 500; }
    @Override public double interestRate() { return 4.0; }
    @Override public synchronized boolean canWithdraw(long amount) {
        return amount > 0 && getBalance() - amount >= minBalance;
    }
    public long getMinBalance() { return minBalance; }
}
