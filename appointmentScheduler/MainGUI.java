package appointmentScheduler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class MainGUI extends JFrame {
    private AppointmentManager manager = new AppointmentManager();
    private FileHandler fileHandler = new FileHandler();
    private ConflictChecker conflictChecker = new ConflictChecker();
    private JLabel statusLabel;

    // Table to display appointments
    private DefaultTableModel tableModel;
    private JTable table;
    // input fields
    private JTextField nameField;
    private JTextField purposeField;
    private JComboBox<String> dayBox;
    private JComboBox<String> timeBox;

    public MainGUI() {
        setTitle("Appointment Scheduling System");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centers the window

        add(buildFormPanel(), BorderLayout.WEST);
        add(buildTablePanel(), BorderLayout.CENTER);
        add(buildStatusPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 8));
        panel.setBorder(BorderFactory.createTitledBorder("New Appointment"));

        nameField = new JTextField();
        purposeField = new JTextField();
        dayBox = new JComboBox<>(AppointmentManager.DAYS);
        timeBox = new JComboBox<>(AppointmentManager.TIME_SLOTS);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Day:"));
        panel.add(dayBox);

        panel.add(new JLabel("Time Slot:"));
        panel.add(timeBox);

        panel.add(new JLabel("Purpose:"));
        panel.add(purposeField);

        JButton addButton = new JButton("Add Appointment");
        addButton.addActionListener(e -> {
            handleAddAppointment(
                    nameField.getText().trim(),
                    (String) dayBox.getSelectedItem(),
                    (String) timeBox.getSelectedItem(),
                    purposeField.getText().trim());
            clearForm();
        });
        panel.add(addButton);
        return panel;
    }

    private JPanel buildTablePanel() {
        String[] column = { "#", "Name", "Day", "Time Slot", "Purpose" };
        tableModel = new DefaultTableModel(column, 0);
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Schedule"));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton cancelButton = new JButton("Cancel Selected");
        cancelButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                handleCancelAppointment(selectedRow + 1);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select an appointment to cancel.");
            }
        });

        JButton sortButton = new JButton("Sort Appointments");
        sortButton.addActionListener(e -> {
            manager.sortAppointments();
            JOptionPane.showMessageDialog(this, "Appointments sorted by day and time.");
            refreshTable();
        });

        JButton saveButton = new JButton("Save Schedule");
        saveButton.addActionListener(e -> {
            fileHandler.saveSchedule(manager.getAppointments(), manager.getCount());
            JOptionPane.showMessageDialog(this, "Schedule saved to " + FileHandler.FILENAME);
        });

        bottomButtons.add(cancelButton);
        bottomButtons.add(sortButton);
        bottomButtons.add(saveButton);

        panel.add(bottomButtons, BorderLayout.SOUTH);
        return panel;
    }

    public JPanel buildStatusPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Total Appointments: " + manager.getCount());
        panel.add(statusLabel);
        return panel;
    }

    public void handleAddAppointment(String name, String day, String timeSlot, String purpose) {
        if (name.isEmpty() || day.isEmpty() || timeSlot.isEmpty() || purpose.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }
        if (conflictChecker.hasConflict(manager.getAppointments(), manager.getCount(), day, timeSlot)) {
            JOptionPane.showMessageDialog(this,
                    "Conflict! " + day + " " + timeSlot + " is already taken. Please try another slot.");

            return;
        }
        manager.addAppointment(name, day, timeSlot, purpose);
        refreshTable();
    }

    public void handleCancelAppointment(int index) {
        JOptionPane.showMessageDialog(this,
                "Appointment cancelled for : " + manager.getAppointments()[index - 1].getName() + " on "
                        + manager.getAppointments()[index - 1].getDay() + " at "
                        + manager.getAppointments()[index - 1].getTimeSlot());

        manager.cancelAppointment(index);
        statusLabel.setText("Total Appointments: " + manager.getCount());
    }

    public void clearForm() {
        nameField.setText("");
        purposeField.setText("");
        dayBox.setSelectedIndex(0);
        timeBox.setSelectedIndex(0);
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        Appointment[] appointments = manager.getAppointments();
        for (int i = 0; i < manager.getCount(); i++) {
            tableModel.addRow(new Object[] {
                    i + 1,
                    appointments[i].getName(),
                    appointments[i].getDay(),
                    appointments[i].getTimeSlot(),
                    appointments[i].getPurpose()
            });
        }
        statusLabel.setText("Total Appointments: " + manager.getCount());
    }

}
