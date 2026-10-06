package Practical4.Prac4_1;

public class Driver
{
    public static void main(String[] args)
    {

        String[] passwords = {
                "abc",
                "password",
                "Password",
                "Password1",
                "Abcd1234!"
        };

        for (String pw : passwords)
        {
            System.out.println("Password: " + pw);

            System.out.println("Length >= 8          : " +
                    (PasswordChecker.hasMinLength(pw) ? "Passed" : "Failed"));

            System.out.println("Contains Uppercase   : " +
                    (PasswordChecker.hasUppercase(pw) ? "Passed" : "Failed"));

            System.out.println("Contains Digit       : " +
                    (PasswordChecker.hasDigit(pw) ? "Passed" : "Failed"));

            System.out.println("Contains Special Char: " +
                    (PasswordChecker.hasSpecialCharacter(pw) ? "Passed" : "Failed"));

            System.out.println("Strength: " + PasswordChecker.strength(pw));

            System.out.println("----------------------------------------");
        }
    }
}
