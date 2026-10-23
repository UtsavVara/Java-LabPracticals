package minibank;
import minibank.annotation.*;
import java.lang.reflect.*;
import java.util.*;
public final class AnnotationValidator {
    private AnnotationValidator() {}
    public static List<String> validate(Object object) {
        List<String> errors = new ArrayList<>();
        if (object == null) { errors.add("Object cannot be null."); return errors; }
        for (Field f : object.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                Object value = f.get(object);
                if (f.isAnnotationPresent(Id.class) && (value == null || value.toString().isBlank()))
                    errors.add(f.getName() + " is required.");
                if (f.isAnnotationPresent(Positive.class) && value instanceof Number n && n.longValue() <= 0)
                    errors.add(f.getName() + ": " + f.getAnnotation(Positive.class).message());
                MaxLength max = f.getAnnotation(MaxLength.class);
                if (max != null && value != null && value.toString().length() > max.value())
                    errors.add(f.getName() + " must be at most " + max.value() + " characters.");
            } catch (IllegalAccessException | RuntimeException ex) {
                errors.add("Could not validate field " + f.getName() + ".");
            }
        }
        return errors;
    }
}
