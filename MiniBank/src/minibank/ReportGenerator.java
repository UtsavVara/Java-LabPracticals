package minibank;
import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
public final class ReportGenerator {
    private ReportGenerator() {}
    public static String build(Collection<Account> accounts) {
        long total = 0;
        StringBuilder b = new StringBuilder("MINIBANK END-OF-DAY REPORT\n");
        b.append("=========================\n");
        for (Account a : accounts) { b.append(a).append('\n'); total += a.getBalance(); }
        b.append("-------------------------\nAccounts: ").append(accounts.size())
         .append("\nTotal funds (net): ₹").append(total).append('\n');
        return b.toString();
    }
    public static void write(String report, Path file) throws IOException {
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) Files.createDirectories(parent);
        Files.writeString(file, report, StandardCharsets.UTF_8);
    }
}
