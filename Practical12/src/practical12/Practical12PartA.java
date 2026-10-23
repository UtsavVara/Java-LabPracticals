package practical12;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Practical 12 - Part A (Hour 1): collection practice programs.
 * Run this class and choose an exercise from the console menu.
 */
public class Practical12PartA {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = readLine("Choose option: ");
            switch (choice) {
                case "1" -> wordFrequency();
                case "2" -> uniqueVisitors();
                case "3" -> leaderboard();
                case "0" -> running = false;
                default -> System.out.println("Invalid option. Enter 0 to 3.");
            }
            System.out.println();
        }
        System.out.println("Practical 12 Part A completed.");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("======================================");
        System.out.println("  PRACTICAL 12 - PART A (HOUR 1)");
        System.out.println("======================================");
        System.out.println("1. Word frequency (HashMap + Comparator)");
        System.out.println("2. Unique visitors (HashSet + LinkedHashSet)");
        System.out.println("3. Player leaderboard (Map + sorting)");
        System.out.println("0. Exit");
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Sheet task 1: count each word and sort by frequency, highest first.
    private static void wordFrequency() {
        String paragraph = readLine("Enter a paragraph: ");
        Map<String, Integer> counts = new HashMap<>();
        for (String word : paragraph.toLowerCase(Locale.ROOT).split("[^\\p{L}\\p{N}]+")) {
            if (!word.isBlank()) counts.merge(word, 1, Integer::sum);
        }
        if (counts.isEmpty()) {
            System.out.println("No words to count.");
            return;
        }
        counts.entrySet().stream()
            .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .reversed().thenComparing(Map.Entry::getKey))
            .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
    }

    // Sheet task 2: count unique names and preserve order of first appearance.
    private static void uniqueVisitors() {
        String input = readLine("Enter login names separated by commas: ");
        List<String> names = Arrays.stream(input.split(","))
            .map(String::trim).filter(s -> !s.isEmpty()).toList();
        Set<String> unique = new HashSet<>(names);
        Set<String> ordered = new LinkedHashSet<>(names);
        System.out.println("Total names entered: " + names.size());
        System.out.println("Unique visitors: " + unique.size());
        System.out.println("First-seen order: " + ordered);
        System.out.println("First three distinct names: " + ordered.stream().limit(3).toList());
    }

    // Advanced optional task: build a small leaderboard and sort by score.
    private static void leaderboard() {
        int n = readInt("How many players? ");
        if (n < 1 || n > 100) {
            System.out.println("Enter a player count between 1 and 100.");
            return;
        }
        Map<String, Integer> scores = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = readLine("Player " + (i + 1) + " name: ");
            int score = readInt("Score: ");
            if (name.isBlank()) {
                System.out.println("Name cannot be blank; this player was skipped.");
                continue;
            }
            scores.merge(name, score, Math::max);
        }
        int topN = readInt("Show top how many players? ");
        if (topN < 1) {
            System.out.println("Top count must be positive.");
            return;
        }
        System.out.println("Leaderboard:");
        scores.entrySet().stream()
            .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .reversed().thenComparing(Map.Entry::getKey))
            .limit(topN)
            .forEach(e -> System.out.println(e.getKey() + " — " + e.getValue()));
    }

    private static int readInt(String prompt) {
        while (true) {
            String value = readLine(prompt);
            try { return Integer.parseInt(value); }
            catch (NumberFormatException ex) { System.out.println("Please enter a whole number."); }
        }
    }
}
