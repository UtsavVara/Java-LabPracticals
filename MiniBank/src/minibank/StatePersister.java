package minibank;
import java.io.*;
import java.nio.file.*;
import java.util.*;
public final class StatePersister {
    private StatePersister() {}
    public static void save(Collection<Account> accounts, Path file) throws IOException {
        Path parent = file.toAbsolutePath().getParent();
        if (parent != null) Files.createDirectories(parent);
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(file))) {
            out.writeObject(new ArrayList<>(accounts));
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Account> load(Path file) throws IOException {
        if (!Files.exists(file)) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file))) {
            Object obj = in.readObject();
            if (!(obj instanceof List<?> list)) throw new IOException("Invalid account data.");
            List<Account> accounts = new ArrayList<>();
            for (Object item : list) if (item instanceof Account a) accounts.add(a);
            return accounts;
        } catch (ClassNotFoundException | RuntimeException ex) { throw new IOException("Could not read saved bank data.", ex); }
    }
}
