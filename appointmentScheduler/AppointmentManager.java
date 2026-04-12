package appointmentScheduler;

import java.util.Scanner;

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

    public void addAppointment(Scanner scanner) {

        // name

        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine().trim();

        // day

        System.out.println("Select day: ");
        // display DAYS
        for (int i = 0; i < DAYS.length; i++) {
            System.out.printf(" %d. %s\n", i + 1, DAYS[i]);
        }
        System.out.print("Choose day (1-7): ");

        int dayChoice;

        // try scanning dayChoice
        try {
            dayChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            return;
        }
        // invalid day checker
        if (dayChoice < 1 || dayChoice > 7) {
            System.out.println("Invalid day selection.");
            return;
        }

        String day = DAYS[dayChoice - 1];

        // timeSlot
        System.out.println("Select time slot: ");
        // displayTimeSlots
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            System.out.printf(" %d. %s\n", i + 1, TIME_SLOTS[i]);
        }

        System.out.print("Choose time (1-10): ");

        int timeSlotChoice;

        // try scanning timeSlotChoice
        try {
            timeSlotChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            return;
        }
        // invalid timeSlot checker
        if (timeSlotChoice < 1 || timeSlotChoice > 10) {
            System.out.println("Invalid day selection.");
            return;
        }

        String timeSlot = TIME_SLOTS[timeSlotChoice - 1];

        if (conflictChecker.hasConflict(appointments, count, day, timeSlot)) {
            System.out.println("Conflict! " + day + " " + timeSlot + " is already taken. Please try another slot.");
            return;
        }

        System.out.print("Enter purpose: ");
        String purpose = scanner.nextLine().trim();

        appointments[count++] = new Appointment(name, day, timeSlot, purpose);
        System.out.println("Appointment added successfully!");
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

    public void cancelAppointment(Scanner scanner) {
        if (count == 0) {
            System.out.println("\nNo appointments to cancel.");
            return;
        }

        viewAppointments();

        System.out.print("Enter the number of the appointment to cancel: ");
        int num;

        try {
            num = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (num < 1 || num > count) {
            System.out.println("Invalid selection.");
            return;
        }

        String cancelledName = appointments[num - 1].getName();

        for (int i = num - 1; i < count - 1; i++) {
            appointments[i] = appointments[i + 1];
        }
        appointments[--count] = null;

        System.out.println("Appointment for " + cancelledName + " has been cancelled.");

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
        System.out.println("Appointments sorted by day and time.");
        viewAppointments();
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
