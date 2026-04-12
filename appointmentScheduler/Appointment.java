package appointmentScheduler;

public class Appointment {
    private String name;
    private String day;
    private String timeSlot;
    private String purpose;

    public Appointment(String name, String day, String timeSlot, String purpose) {
        this.name = name;
        this.day = day;
        this.timeSlot = timeSlot;
        this.purpose = purpose;
    }

    public String getName() {
        return name;
    };

    public String getDay() {
        return day;
    };

    public String getTimeSlot() {
        return timeSlot;
    };

    public String getPurpose() {
        return purpose;
    };

    public String toString() {
        return String.format("| %-20s | %-10s | %-11s | %-20s |",
                name, day, timeSlot, purpose);
    }
}
