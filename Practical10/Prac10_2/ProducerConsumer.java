package Practical10.Prac10_2;

import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 5;
    private static final ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);
    private static volatile boolean running = true;
    private static volatile int producedCount = 0;
    private static volatile int consumedCount = 0;

    public static void main(String[] args) {
        // Create producer and consumer threads
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        // Start the producer and consumer threads
        producerThread.start();
        consumerThread.start();

        // Add a shutdown hook to gracefully stop the program when it's interrupted
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down...");
            running = false;
        }));

        try {
            // Keep the main thread alive for some time to allow producer and consumer threads to run
            while (producedCount < 10 && consumedCount < 10) {
                Thread.sleep(50); // Check every 50 milliseconds
            }
        } catch (InterruptedException e) {
            System.err.println("Main thread was interrupted.");
        }

        // Stop the threads gracefully
        running = false;
        producerThread.interrupt();
        consumerThread.interrupt();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            System.err.println("Error joining threads.");
        }

        if (producedCount == 10 && consumedCount == 10) {
            System.out.println("All items produced and consumed successfully.");
        } else {
            System.err.println("Not all items were processed. Produced: " + producedCount + ", Consumed: " + consumedCount);
        }
    }

    // Producer class
    static class Producer implements Runnable {
        @Override
        public void run() {
            int item = 1;
            while (running && producedCount < 10) {
                try {
                    buffer.put(item);
                    System.out.println("Produced: " + item);
                    producedCount++;
                    Thread.sleep(500); // Simulate time-consuming production process
                    item++;
                } catch (InterruptedException e) {
                    System.err.println("Producer was interrupted.");
                    return;
                }
            }
        }
    }

    // Consumer class
    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (running && consumedCount < 10) {
                try {
                    int item = buffer.take();
                    System.out.println("Consumed: " + item);
                    consumedCount++;
                    Thread.sleep(1000); // Simulate time-consuming consumption process
                } catch (InterruptedException e) {
                    System.err.println("Consumer was interrupted.");
                    return;
                }
            }
        }
    }
}
