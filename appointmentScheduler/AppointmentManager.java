package appointmentScheduler;

// Group Coordinator: Terenz Jahred Dantes
// Members: 
// Brent Angelo Lambino
// Jude Robin Sumalinab

public class AppointmentManager {

    public static final int MAX = 100;

    public static final String[] DAYS = {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
    };

    public static final String[] TIME_SLOTS = {
            "8AM-9AM", "9AM-10AM", "10AM-11AM", "11AM-12PM",
            "12PM-1PM", "1PM-2PM", "2PM-3PM", "3PM-4PM",
            "4PM-5PM", "5PM-6PM"
    };

    // initialize
    private Appointment[] appointments;
    private int count;
    private ConflictChecker conflictChecker;

    public AppointmentManager() {
        appointments = new Appointment[MAX];
        count = 0;
        conflictChecker = new ConflictChecker();
    }

    // ADD -------------------------------------------------------

    public boolean addAppointment(String name, String day, String timeSlot, String purpose) {
        if (name.isEmpty() || day.isEmpty() || timeSlot.isEmpty() || purpose.isEmpty()) {
            return false;
        }
        if (conflictChecker.hasConflict(appointments, count, day, timeSlot)) {
            return false;
        }
        appointments[count++] = new Appointment(name, day, timeSlot, purpose);

        return true;
    }

    // VIEW --------------------------------------------------

    public void viewAppointments() {
        if (count == 0) {
            System.out.println("\nNo Appointments scheduled.");
        }
        for (int i = 0; i < count; i++) {
            printHeader();
            System.out.printf("| %-4d %s%n", i + 1, appointments[i]);
            printFooter();
        }
    }

    // CANCEL -----------------------------------------------

    public boolean cancelAppointment(int index) {

        if (index < 1 || index > count) {
            System.out.println("Invalid selection.");
            return false;
        }
        // String cancelledName = appointments[index - 1].getName();
        for (int i = index - 1; i < count - 1; i++) {
            appointments[i] = appointments[i + 1];
        }

        appointments[--count] = null;

        return true;
    }

    // SORT ---------------

    public void sortAppointments() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                int dayA = getDayIndex(appointments[j].getDay());
                int dayB = getDayIndex(appointments[j + 1].getDay());
                int timeA = getTimeIndex(appointments[j].getTimeSlot());
                int timeB = getTimeIndex(appointments[j + 1].getTimeSlot());

                if (dayA > dayB || (dayA == dayB && timeA > timeB)) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }

        }
    }

    // helper functions
    private void printHeader() {
        System.out.println(
                "\n+------+----------------------+------------+"
                        + "-------------+----------------------+");
        System.out.printf(
                "| %-4s | %-20s | %-10s | %-11s | %-20s |%n",
                "#", "Name", "Day", "Time Slot", "Purpose");
        System.out.println(
                "+------+----------------------+------------+"
                        + "-------------+----------------------+");
    }

    private void printFooter() {
        System.out.println(
                "+------+----------------------+------------+"
                        + "-------------+----------------------+");
    }

    public int getDayIndex(String day) {
        for (int i = 0; i < DAYS.length; i++) {
            if (DAYS[i].equalsIgnoreCase(day)) {
                return i;
            }
        }
        return -1;
    }

    public int getTimeIndex(String timeSlot) {
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            if (TIME_SLOTS[i].equalsIgnoreCase(timeSlot)) {
                return i;
            }
        }
        return -1;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public int getCount() {
        return count;
    }
}
