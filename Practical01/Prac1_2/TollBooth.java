package Practical1.Prac1_2;

import java.util.Scanner;

public class TollBooth
{
    record Vehicle(String number, String type) {}

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        int totalToll = 0;
        int bikes = 0;
        int cars = 0;
        int trucks = 0;

        while(true)
        {
            System.out.print("Enter vehicle number (or 'done' to stop) : ");
            String number = sc.nextLine();

            if(number.equalsIgnoreCase("done"))
            {
                break;
            }

            System.out.print("Enter the vehicle type (Bike/Car/Truck) : ");
            String type = sc.nextLine().toLowerCase();

            Vehicle vehicle = new Vehicle(number, type);

            int toll = switch (vehicle.type())
            {
                case "bike" -> { bikes++; yield 20; }
                case "car" -> { cars++; yield 50; }
                case "truck" -> { trucks++; yield 150; }
                default -> { System.out.println("Invalid vehicle type!"); yield 0; }
            };

            totalToll = totalToll + toll;
        }

        String mostFrequent;

        if(bikes >= cars && bikes >= trucks)
        {
            mostFrequent = "bike";
        }
        else if (cars >= bikes && cars >= trucks)
        {
            mostFrequent = "car";
        }
        else
        {
            mostFrequent = "truck";
        }

        System.out.println("\nTotal toll: " + totalToll);
        System.out.println("Most frequent: " + mostFrequent);

        sc.close();
    }
}
