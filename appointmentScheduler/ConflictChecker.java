package appointmentScheduler;

public class ConflictChecker {

    public boolean hasConflict(Appointment[] appointments,
            int count,
            String day,
            String timeSlot) {
        for (int i = 0; i < count; i++) {
            if (appointments[i].getDay().equalsIgnoreCase(day)
                    && appointments[i].getTimeSlot().equalsIgnoreCase(timeSlot)) {
                return true;
            }
        }
        return false;
    }
}