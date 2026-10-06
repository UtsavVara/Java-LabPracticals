package Practical6.Prac6_1;

interface Switchable
{
    void on();
    void off();

    // Default method
    default void toggle()
    {
        System.out.println("Toggling device");
    }
}

class Fan implements Switchable
{
    public void on()
    {
        System.out.println("Fan is ON");
    }

    public void off()
    {
        System.out.println("Fan is OFF");
    }

    @Override
    public void toggle()
    {
        System.out.println("Fan toggled");
    }
}

class Light implements Switchable
{
    public void on()
    {
        System.out.println("Light is ON");
    }

    public void off()
    {
        System.out.println("Light is OFF");
    }

    @Override
    public void toggle()
    {
        System.out.println("Light toggled");
    }
}

// Functional interface
@FunctionalInterface
interface SwitchPermission
{
    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControl
{
    public static void main(String[] args)
    {

        // Array of Switchable devices
        Switchable[] devices = {
                new Fan(),
                new Light()
        };

        // Toggle each device
        System.out.println("Toggling devices:");
        for (Switchable device : devices)
        {
            device.toggle();
        }

        // Using functional interface with anonymous class
        SwitchPermission permissionAnonymous = new SwitchPermission() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour)
            {
                return hour >= 6 && hour < 22;
            }
        };

        // Using functional interface with lambda
        SwitchPermission permissionLambda =
                (device, hour) -> hour >= 6 && hour < 22;

        // Test at different hours
        int hour = 20;

        System.out.println("\nUsing Anonymous Class:");
        for (Switchable device : devices)
        {
            System.out.println(device.getClass().getSimpleName() + " may switch on at " + hour + ":00? " + permissionAnonymous.maySwitchOn(device, hour));
        }

        System.out.println("\nUsing Lambda:");
        for (Switchable device : devices)
        {
            System.out.println(device.getClass().getSimpleName() + " may switch on at " + hour + ":00? " + permissionLambda.maySwitchOn(device, hour));
        }
    }
}