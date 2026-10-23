package minibank;
import minibank.exception.*;
public interface Transactable {
    void deposit(long amount) throws InvalidAmountException;
    boolean withdraw(long amount) throws InvalidAmountException;
}
