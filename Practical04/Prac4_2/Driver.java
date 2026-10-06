package Practical4.Prac4_2;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        // Chat log lines
        String[] logs = {
                "10:05 alice Hello there",
                "10:10 bob Good morning",
                "10:15",                    // Malformed line
                "10:20 charlie How are you?"
        };

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        // Display filtered result
        System.out.println();
        System.out.println(ChatFilter.filterMessages(logs, keyword));

        sc.close();
    }
}