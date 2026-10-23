package minibank;
public interface InterestBearing {
    default double yearlyInterest(Account account) { return account.getBalance() * account.interestRate() / 100.0; }
}
