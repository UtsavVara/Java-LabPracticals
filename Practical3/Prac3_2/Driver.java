package Practical3.Prac3_2;

public class Driver
{
    public static void main(String[] args)
    {
        Card[] cards = new Card[5];

        Card[] input = {
                new Card("Ace", "Spades"),
                new Card("King", "Hearts"),
                new Card("Queen", "Clubs"),
                new Card("Ace", "Spades"),
                new Card("10", "Diamonds")
        };

        int size = 0;

        for(int i = 0; i < input.length; i++)
        {
            boolean duplicate = false;

            for(int j = 0; j < size; j++)
            {
                if(input[i].equals(cards[j]))
                {
                    System.out.println("Duplicate found: " + input[i]);
                    duplicate = true;
                    break;
                }
            }

            if(!duplicate)
            {
                cards[size] = input[i];
                size++;
            }
        }
    }
}
