package Practical4.Prac4_2;

public class ChatFilter {

    public static String filterMessages(String[] logs, String keyword) {

        StringBuilder report = new StringBuilder();
        int count = 0;

        for (String line : logs) {

            // Split into at most 3 parts: time, user, message
            String[] parts = line.split(" ", 3);

            // Skip malformed lines
            if (parts.length < 3) {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            // Case-insensitive keyword search
            if (message.toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                report.append(time)
                        .append(" ")
                        .append(user)
                        .append(": ")
                        .append(message)
                        .append("\n");
            }
        }

        return "Matches: " + count + "\n" + report.toString();
    }
}
