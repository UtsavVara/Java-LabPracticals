package minibank;
import java.util.concurrent.*;
import java.util.*;
public class TransactionProcessor implements AutoCloseable {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final List<Future<?>> tasks = Collections.synchronizedList(new ArrayList<>());
    public void submit(Runnable task) { tasks.add(executor.submit(task)); }
    public void stop() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) executor.shutdownNow();
        } catch (InterruptedException ex) { executor.shutdownNow(); Thread.currentThread().interrupt(); }
    }
    @Override public void close() { stop(); }
}
