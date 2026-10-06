package Practical6.Prac6_2;

@FunctionalInterface
interface Notifier
{
    void send(String message);
}

// Marker interface
interface Urgent {
}

class EmailSender implements Urgent {
    // Email sender is marked as Urgent
}

class SMSSender {
    // SMS sender is not urgent
}

public class NotificationSenders
{
    public static void main(String[] args)
    {

        // Email sender as a lambda
        Notifier email = message -> System.out.println("Email: " + message);

        // SMS sender as a lambda
        Notifier sms = message -> System.out.println("SMS: " + message);

        // Hold senders in an array
        Notifier[] senders = {
                email,
                sms
        };

        // Objects used to identify urgent senders
        EmailSender emailObj = new EmailSender();
        SMSSender smsObj = new SMSSender();

        Object[] senderTypes = {
                emailObj,
                smsObj
        };

        String message = "This is an important notification.";

        // Broadcast message
        for (int i = 0; i < senders.length; i++)
        {
            senders[i].send(message);

            // Send twice if sender is marked Urgent
            if (senderTypes[i] instanceof Urgent)
            {
                senders[i].send(message);
            }
        }
    }
}
