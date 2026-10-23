package minibank;
public record Command(TransactionType type, String accountNumber, long amount) {
    public enum TransactionType { DEPOSIT, WITHDRAW, TRANSFER }
}
