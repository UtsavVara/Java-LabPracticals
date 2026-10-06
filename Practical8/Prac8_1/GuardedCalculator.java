package Practical8.Prac8_1;

import java.util.Scanner;

// Custom exception for division by zero
class DivideByZeroException extends Exception
{
    public DivideByZeroException(String message)
    {
        super(message);
    }
}

public class GuardedCalculator
{
    // Calculator method
    public static double calculate(double num1, double num2, char operator) throws DivideByZeroException
    {
        switch (operator)
        {
            case '+':
                return num1 + num2;

            case '-':
                return num1 - num2;

            case '*':
                return num1 * num2;

            case '/':
                if (num2 == 0)
                {
                    throw new DivideByZeroException("Cannot divide by zero.");
                }
                return num1 / num2;

            default:
                throw new IllegalArgumentException("Invalid operator.");
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        boolean success = false;

        while (!success)
        {
            try
            {
                System.out.print("Enter first number: ");
                double num1 = Double.parseDouble(sc.nextLine());

                System.out.print("Enter second number: ");
                double num2 = Double.parseDouble(sc.nextLine());

                System.out.print("Enter operator (+, -, *, /): ");
                char operator = sc.nextLine().charAt(0);

                double result = calculate(num1, num2, operator);

                System.out.println("Result = " + result);

                // Calculation succeeded
                success = true;
            }

            catch(NumberFormatException e)
            {
                // Invalid number input
                System.out.println("Invalid number! Please enter valid numbers.");

            }
            catch(DivideByZeroException e)
            {
                // Division by zero
                System.out.println("Error: " + e.getMessage());

            }
            catch(IllegalArgumentException e)
            {
                // Invalid operator
                System.out.println("Error: " + e.getMessage());

            }

            finally
            {
                // Runs after every attempt
                System.out.println("Attempt logged.");
                System.out.println("----------------------");
            }
        }

        sc.close();
        System.out.println("Calculation completed successfully.");
    }
}

