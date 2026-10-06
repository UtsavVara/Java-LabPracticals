package Practical7.Prac7_2;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Run annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

// Class containing test methods
class MyTests {

    @Run
    public void testAddition() {
        System.out.println("Running testAddition");
    }

    @Run
    public void testSubtraction() {
        System.out.println("Running testSubtraction");
    }

    public void normalMethod() {
        System.out.println("This method should not run");
    }

    @Run
    public void testMultiplication() {
        System.out.println("Running testMultiplication");
    }
}

// Mini Test Runner
class TestRunner {

    public static void runTests(Object obj) {

        int count = 0;

        // Get all methods
        Method[] methods = obj.getClass().getDeclaredMethods();

        for (Method method : methods) {

            // Check if method has @Run
            if (method.isAnnotationPresent(Run.class)) {

                // Check that method has no parameters
                if (method.getParameterCount() == 0) {

                    try {
                        method.setAccessible(true);

                        // Invoke the method
                        method.invoke(obj);

                        count++;

                    } catch (Exception e) {
                        System.out.println(
                                "Error running: " + method.getName()
                        );
                    }
                }
            }
        }

        System.out.println("Total tests ran: " + count);
    }
}

// Main class
public class MiniTestRunnerDemo {

    public static void main(String[] args) {

        MyTests tests = new MyTests();

        TestRunner.runTests(tests);
    }
}