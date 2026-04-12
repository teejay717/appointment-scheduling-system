package appointmentScheduler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();
        AppointmentManager manager = new AppointmentManager();
        int choice = 0;

        do {
            System.out.println("\n===== APPOINTMENT SCHEDULING SYSTEM =====");
            System.out.println("1. Add Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Sort Appointments");
            System.out.println("5. Save Schedule");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            switch (choice) {
                case 1:
                    manager.addAppointment(scanner);
                    break;
                case 2:
                    manager.viewAppointments();
                    break;
                case 3:
                    manager.cancelAppointment(scanner);
                    break;
                case 4:
                    manager.sortAppointments();
                    break;
                case 5:
                    fileHandler.saveSchedule(
                            manager.getAppointments(),
                            manager.getCount());
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 6);
    }
}
