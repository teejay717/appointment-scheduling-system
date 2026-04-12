package appointmentScheduler;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandler {
    public static final String FILENAME = "schedule.txt";

    public void saveSchedule(Appointment[] appointments, int count) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME))) {

            pw.println("==========================================");
            pw.println("      APPOINTMENT SCHEDULE");
            pw.println("==========================================");
            pw.println("Generated: " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            pw.println();

            if (count == 0) {
                pw.println("No Appointments Scheduled!");
            } else {
                pw.printf("%-4s %-22s %-12s %-13s %-20s%n",
                        "#", "Name", "Day", "Time Slot", "Purpose");
                pw.println("-".repeat(75));

                for (int i = 0; i < count; i++) {
                    pw.printf("%-4d %-22s %-12s %-13s %-20s%n",
                            i + 1,
                            appointments[i].getName(),
                            appointments[i].getDay(),
                            appointments[i].getTimeSlot(),
                            appointments[i].getPurpose());
                }
                pw.println("-".repeat(75));
                pw.println("Total appointments: " + count);
            }

            pw.println();
            pw.println("==========================================");

            System.out.println("Schedule saved to " + FILENAME);

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}