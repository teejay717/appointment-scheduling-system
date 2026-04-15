package appointmentScheduler;

// Group Coordinator: Terenz Jahred Dantes
// Members: 
// Brent Angelo Lambino
// Jude Robin Sumalinab

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    public static String getLiveClockTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy | hh:mm:ss a");
        return LocalDateTime.now().format(formatter);
    }

    public static String getFileTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}