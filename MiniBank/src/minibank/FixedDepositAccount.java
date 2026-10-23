package minibank;
import minibank.exception.InvalidAmountException;
public class FixedDepositAccount extends Account {
    private static final long serialVersionUID = 1L;
    public FixedDepositAccount(String ownerName, long openingBalance) { super(ownerName, openingBalance); }
    @Override public double interestRate() { return 7.0; }
    @Override public synchronized boolean withdraw(long amount) throws InvalidAmountException {
        throw new InvalidAmountException("Fixed deposits are locked and cannot be withdrawn in this demo.");
    }
    @Override public synchronized boolean canWithdraw(long amount) { return false; }
}
