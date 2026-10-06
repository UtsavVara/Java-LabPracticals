package Practical7.Prac7_1;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Annotation for checking blank fields
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

// Annotation for checking maximum length
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// Signup Form
class SignupForm {

    @NotBlank
    @MaxLength(20)
    String username;

    @NotBlank
    @MaxLength(30)
    String email;

    @NotBlank
    @MaxLength(15)
    String password;

    SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

// Validator
class FormValidator {

    public static List<String> validate(Object obj) {

        List<String> errors = new ArrayList<>();

        // Get all fields of the object
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            try {
                // Allow access to private fields if required
                field.setAccessible(true);

                Object value = field.get(obj);

                // Check @NotBlank
                if (field.isAnnotationPresent(NotBlank.class)) {

                    if (value == null || value.toString().trim().isEmpty()) {
                        errors.add(field.getName() + " must not be blank");
                    }
                }

                // Check @MaxLength
                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength annotation =
                            field.getAnnotation(MaxLength.class);

                    int maxLength = annotation.value();

                    if (value != null &&
                            value.toString().length() > maxLength) {

                        errors.add(field.getName()
                                + " must not exceed "
                                + maxLength
                                + " characters");
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Cannot access field: " + field.getName());
            }
        }

        return errors;
    }
}

// Main class
public class FormValidatorDemo {

    public static void main(String[] args) {

        SignupForm form = new SignupForm(
                "",
                "verylongemailaddress@example.com",
                "password123456789"
        );

        List<String> errors = FormValidator.validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid.");
        } else {
            System.out.println("Validation Errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}