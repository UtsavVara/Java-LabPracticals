package Practical1.Prac1_1;

import java.util.Scanner;

public class VendingMachine
{
    enum Coin
    {
        ONE, TWO, FIVE, TEN;
    }

    public static void main(String[] args)
    {
        final int price = 15;
        int total = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Total Price : "+price);
        System.out.print("Enter Coin (ONE, TWO, FIVE, TEN) : ");

        while(total < price)
        {
            String input = sc.next().toUpperCase();

            try
            {
                Coin c = Coin.valueOf(input);

                int result = switch(c)
                {
                    case ONE -> 1;
                    case TWO -> 2;
                    case FIVE -> 5;
                    case TEN -> 10;
                };

                total += result;
            }

            catch(IllegalArgumentException e)
            {
                System.out.println("Invalid Coin!!");
                System.out.print("Again Enter Coin (ONE, TWO, FIVE, TEN) : ");
                continue;
            }
            System.out.println("Total Paid : "+total);
            System.out.print("Again Enter Coin (ONE, TWO, FIVE, TEN) : ");
        }
        System.out.println("Payment Successful!");
        System.out.println("Change : "+(total-price));
    }
}
