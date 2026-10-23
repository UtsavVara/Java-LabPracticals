package minibank;
import java.util.regex.Pattern;
public final class Validator {
    private Validator() {}
    public static boolean isValidMobile(String s) { return s != null && Pattern.matches("[6-9][0-9]{9}", s); }
    public static boolean isValidEmail(String s) { return s != null && Pattern.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}", s); }
    public static boolean isValidPan(String s) { return s != null && Pattern.matches("[A-Z]{5}[0-9]{4}[A-Z]", s); }
    public static boolean isValidIfsc(String s) { return s != null && Pattern.matches("[A-Z]{4}0[A-Z0-9]{6}", s); }
}
