package minibank;
import java.util.Locale;
public final class CommandParser {
    private CommandParser() {}
    public static Command parse(String line) {
        if (line == null || line.isBlank()) throw new IllegalArgumentException("Command cannot be empty.");
        String[] p = line.trim().split("\\s+");
        if (p.length != 3) throw new IllegalArgumentException("Format: DEPOSIT|WITHDRAW accountNumber amount");
        Command.TransactionType type;
        try { type = Command.TransactionType.valueOf(p[0].toUpperCase(Locale.ROOT)); }
        catch (IllegalArgumentException ex) { throw new IllegalArgumentException("Supported commands: DEPOSIT, WITHDRAW."); }
        long amount;
        try { amount = Long.parseLong(p[2]); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("Amount must be a whole number."); }
        return new Command(type, p[1], amount);
    }
}
