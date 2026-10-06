package Practical9.Prac9_2;

public class SeatBookingRace {
    static int seatsLeft = 5;
    static int successfulBookings = 0;

    // Synchronized booking method
    static synchronized boolean book() {

        if (seatsLeft > 0) {
            seatsLeft--;
            successfulBookings++;
            return true;
        }

        return false;
    }

    static class BookingThread extends Thread {

        @Override
        public void run() {
            if (book()) {
                System.out.println(
                        Thread.currentThread().getName() + " booked a seat."
                );
            } else {
                System.out.println(
                        Thread.currentThread().getName() + " failed - no seats left."
                );
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int numberOfThreads = 10;

        BookingThread[] threads = new BookingThread[numberOfThreads];

        // Create and start 10 booking threads
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new BookingThread();
            threads[i].setName("Customer-" + (i + 1));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        System.out.println("\n----- Final Result -----");
        System.out.println("Seats available: " + seatsLeft);
        System.out.println("Successful bookings: " + successfulBookings);
        System.out.println("Expected successful bookings: 5");
    }
}
