package Practical2.Prac2_2;

public class CinemaShow
{
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked;

    CinemaShow(String title, int capacity)
    {
        this.title = title;
        this.capacity = capacity;
        seatsAvailable = capacity;
    }

    CinemaShow(String title)
    {
        this.title = title;
        this(title, 100);
    }

    boolean book(int n)
    {
        if(n <= seatsAvailable)
        {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        else
        {
            return false;
        }
    }

    void cancel(int n)
    {
        seatsAvailable += n;

        if(seatsAvailable > capacity)
        {
            seatsAvailable = capacity;
        }
    }

    int getSeatsAvailable()
    {
        return seatsAvailable;
    }

    static int getTotalBooked()
    {
        return totalBooked;
    }

    public static void main(String[] args)
    {
        CinemaShow show = new CinemaShow("Avengers", 50);

        System.out.println("Book 20: " + show.book(20));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Book 25: " + show.book(25));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Book 10: " + show.book(10));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        show.cancel(5);
        System.out.println("After cancelling 5 seats:");
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Book 10: " + show.book(10));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Total Booked: " + CinemaShow.getTotalBooked());
    }
}
