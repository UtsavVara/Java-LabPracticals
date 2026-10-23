package practical13;

import java.util.*;

/**
 * Practical 13 - Part A (Hour 1): Java Generics practice.
 * Includes generic Box, Pair, bounded average, and a generic stack.
 */
public class Practical13PartA {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = readLine("Choose option: ");
            try {
                switch (choice) {
                    case "1" -> genericBoxPairDemo();
                    case "2" -> boundedAverageDemo();
                    case "3" -> genericStackDemo();
                    case "0" -> running = false;
                    default -> System.out.println("Invalid choice. Enter 0 to 3.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid operation: " + ex.getMessage());
            } catch (RuntimeException ex) {
                System.out.println("Operation failed safely: " + ex.getMessage());
            }
            System.out.println();
        }
        System.out.println("Practical 13 Part A completed.");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("======================================");
        System.out.println("  PRACTICAL 13 - PART A (HOUR 1)");
        System.out.println("======================================");
        System.out.println("1. Generic Box<T> and Pair<A,B>");
        System.out.println("2. Bounded generic average");
        System.out.println("3. Generic stack using ArrayList");
        System.out.println("0. Exit");
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Program 1: demonstrate generic type safety using different data types.
    private static void genericBoxPairDemo() {
        Box<String> stringBox = new Box<>("Hello Generics");
        Box<Integer> integerBox = new Box<>(42);
        Box<Double> doubleBox = new Box<>(3.14);
        Pair<String, Integer> marks = new Pair<>("Java", 95);
        Pair<Integer, String> practical = new Pair<>(13, "Generics");

        System.out.println("Box<String>: " + stringBox.get());
        System.out.println("Box<Integer>: " + integerBox.get());
        System.out.println("Box<Double>: " + doubleBox.get());
        System.out.println("Pair<String, Integer>: (" + marks.first() + ", " + marks.second() + ")");
        System.out.println("Pair<Integer, String>: (" + practical.first() + ", " + practical.second() + ")");
        System.out.println("First element of marks pair: " + marks.first());
    }

    // Program 2: bounded generic method accepts only Number subclasses.
    private static void boundedAverageDemo() {
        Integer[] integers = {10, 20, 30, 40};
        Double[] doubles = {1.5, 2.5, 4.0};
        Long[] longs = {100L, 200L, 300L};

        System.out.println("Average(Integer[]): " + average(integers));
        System.out.println("Average(Double[]): " + average(doubles));
        System.out.println("Average(Long[]): " + average(longs));
    }

    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        double sum = 0;
        for (N value : values) {
            if (value == null) {
                throw new IllegalArgumentException("Array must not contain null.");
            }
            sum += value.doubleValue();
        }
        return sum / values.length;
    }

    // Program 3: optional advanced generic stack, with safe empty behavior.
    private static void genericStackDemo() {
        GenericStack<Integer> stack = new GenericStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Stack size: " + stack.size());
        System.out.println("Top item: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
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
        private final List<T> values = new ArrayList<>();
        public void push(T value) { values.add(value); }
        public T peek() { return values.isEmpty() ? null : values.get(values.size() - 1); }
        public T pop() { return values.isEmpty() ? null : values.remove(values.size() - 1); }
        public int size() { return values.size(); }
        public boolean isEmpty() { return values.isEmpty(); }
    }
}
