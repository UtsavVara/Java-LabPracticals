package Practical4.Prac4_1;

public class PasswordChecker
{
    // Rule 1: Length >= 8
    public static boolean hasMinLength(String pw)
    {
        return pw.length() >= 8;
    }

    // Rule 2: Contains at least one uppercase letter
    public static boolean hasUppercase(String pw)
    {
        return pw.matches(".*[A-Z].*");
    }

    // Rule 3: Contains at least one digit
    public static boolean hasDigit(String pw)
    {
        return pw.matches(".*\\d.*");
    }

    // Rule 4: Contains at least one special character
    public static boolean hasSpecialCharacter(String pw)
    {
        return pw.matches(".*[^a-zA-Z0-9].*");
    }

    // Returns Weak, Medium, or Strong
    public static String strength(String pw)
    {
        int count = 0;

        if(hasMinLength(pw))
            count++;
        if(hasUppercase(pw))
            count++;
        if(hasDigit(pw))
            count++;
        if(hasSpecialCharacter(pw))
            count++;

        if(count <= 1)
            return "Weak";
        else if (count <= 3)
            return "Medium";
        else
            return "Strong";
    }
}
