package practical11;

import java.util.*;

/**
 * Practical 11 - Part A (Hour 1)
 * Demonstrates collections, comparators, bounded generics and a generic stack.
 */
public class Practical11PartA {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = readLine("Choose option: ");
            try {
                switch (choice) {
                    case "1" -> wordFrequency();
                    case "2" -> uniqueVisitors();
                    case "3" -> genericContainers();
                    case "4" -> boundedAverage();
                    case "5" -> genericStackDemo();
                    case "0" -> running = false;
                    default -> System.out.println("Invalid choice. Enter 0 to 5.");
                }
            } catch (RuntimeException ex) {
                System.out.println("Could not complete operation: " + ex.getMessage());
            }
            System.out.println();
        }
        System.out.println("Practical 11 Part A completed.");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("======================================");
        System.out.println("  PRACTICAL 11 - PART A (HOUR 1)");
        System.out.println("======================================");
        System.out.println("1. Word frequency (HashMap + Comparator)");
        System.out.println("2. Unique visitors (HashSet + LinkedHashSet)");
        System.out.println("3. Generic Box<T> and Pair<A,B>");
        System.out.println("4. Bounded generic average");
        System.out.println("5. Generic stack using ArrayList");
        System.out.println("0. Exit");
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Program 1: Count words in a paragraph and sort by frequency, highest first.
    private static void wordFrequency() {
        String paragraph = readLine("Enter a paragraph: ");
        Map<String, Integer> frequency = new HashMap<>();

        for (String word : paragraph.toLowerCase(Locale.ROOT).split("[^\\p{L}\\p{N}]+")) {
            if (!word.isBlank()) {
                frequency.merge(word, 1, Integer::sum);
            }
        }

        if (frequency.isEmpty()) {
            System.out.println("No words were entered.");
            return;
        }

        frequency.entrySet().stream()
            .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey))
            .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }

    // Program 2: Count distinct names and preserve the order of their first appearance.
    private static void uniqueVisitors() {
        String input = readLine("Enter visitor names separated by commas: ");
        List<String> visitors = Arrays.stream(input.split(","))
            .map(String::trim)
            .filter(name -> !name.isEmpty())
            .toList();

        Set<String> unique = new HashSet<>(visitors);
        Set<String> firstSeenOrder = new LinkedHashSet<>(visitors);

        System.out.println("Total visits: " + visitors.size());
        System.out.println("Unique visitors: " + unique.size());
        System.out.println("Unique names in first-seen order: " + firstSeenOrder);
        System.out.println("First three distinct visitors: " +
            firstSeenOrder.stream().limit(3).toList());
    }

    // Program 3: Demonstrate generic type safety without casts.
    private static void genericContainers() {
        Box<String> textBox = new Box<>("Java");
        Box<Integer> numberBox = new Box<>(2026);
        Pair<String, Integer> pair = new Pair<>("Marks", 95);
        Pair<Integer, String> anotherPair = new Pair<>(7, "Practical");

        System.out.println("Box<String>: " + textBox.get());
        System.out.println("Box<Integer>: " + numberBox.get());
        System.out.println("Pair<String, Integer>: (" + pair.first() + ", " + pair.second() + ")");
        System.out.println("Pair<Integer, String>: (" + anotherPair.first() + ", " + anotherPair.second() + ")");
        System.out.println("First element: " + pair.first());
    }

    // Program 4: The bound N extends Number accepts Integer, Double, Long, etc.
    private static void boundedAverage() {
        Integer[] integers = {10, 20, 30, 40};
        Double[] doubles = {1.5, 2.5, 4.0};
        Long[] longs = {100L, 200L, 300L};

        System.out.println("Average of Integer[]: " + average(integers));
        System.out.println("Average of Double[]: " + average(doubles));
        System.out.println("Average of Long[]: " + average(longs));
    }

    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        double sum = 0;
        for (N value : values) {
            if (value == null) {
                throw new IllegalArgumentException("Array must not contain null values.");
            }
            sum += value.doubleValue();
        }
        return sum / values.length;
    }

    // Program 5: Optional advanced exercise - a small generic stack.
    private static void genericStackDemo() {
        GenericStack<String> stack = new GenericStack<>();
        stack.push("First");
        stack.push("Second");
        System.out.println("Stack size: " + stack.size());
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop on empty stack: " + stack.pop());
    }

    public static class Box<T> {
        private T value;
        public Box(T value) { this.value = value; }
        public T get() { return value; }
        public void set(T value) { this.value = value; }
    }

    public record Pair<A, B>(A first, B second) {}

    public static class GenericStack<T> {
        private final List<T> items = new ArrayList<>();
        public void push(T item) { items.add(item); }
        public T peek() { return items.isEmpty() ? null : items.get(items.size() - 1); }
        public T pop() { return items.isEmpty() ? null : items.remove(items.size() - 1); }
        public int size() { return items.size(); }
        public boolean isEmpty() { return items.isEmpty(); }
    }
}
