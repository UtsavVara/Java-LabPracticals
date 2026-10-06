package Practical9.Prac9_1;

public class CounterRace {

    static int counter = 0;

    // Synchronized increment
    static synchronized void increment() {
        counter++;
    }

    static class CounterThread extends Thread {

        private int increments;

        CounterThread(int increments) {
            this.increments = increments;
        }

        @Override
        public void run() {
            for (int i = 0; i < increments; i++) {
                increment();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int numberOfThreads = 5;
        int incrementsPerThread = 100000;

        CounterThread[] threads = new CounterThread[numberOfThreads];

        // Create threads
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new CounterThread(incrementsPerThread);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        int expected = numberOfThreads * incrementsPerThread;

        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + counter);
    }
}
