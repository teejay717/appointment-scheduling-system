package appointmentScheduler.JComponents;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import appointmentScheduler.AppointmentManager;
import appointmentScheduler.ConflictChecker;

import java.awt.*;

public class FormPanel extends JPanel {

    private AppointmentManager manager;
    private ConflictChecker conflictChecker;
    private TablePanel tablePanel;

    private JTextField nameField;
    private JTextField purposeField;
    private JComboBox<String> dayBox;
    private JComboBox<String> timeBox;

    public FormPanel(AppointmentManager manager, TablePanel tablePanel) {
        this.manager = manager;
        this.conflictChecker = new ConflictChecker();
        this.tablePanel = tablePanel;

        setLayout(new GridLayout(6, 2, 5, 8));
        setBorder(BorderFactory.createTitledBorder("New Appointment"));

        nameField = new JTextField();
        purposeField = new JTextField();
        dayBox = new JComboBox<>(AppointmentManager.DAYS);
        timeBox = new JComboBox<>(AppointmentManager.TIME_SLOTS);

        add(new JLabel("Name:"));
        add(nameField);

        add(new JLabel("Day:"));
        add(dayBox);

        add(new JLabel("Time Slot:"));
        add(timeBox);

        add(new JLabel("Purpose:"));
        add(purposeField);

        JButton addButton = new JButton("Add Appointment");
        addButton.addActionListener(e -> {
            handleAddAppointment(
                    nameField.getText().trim(),
                    (String) dayBox.getSelectedItem(),
                    (String) timeBox.getSelectedItem(),
                    purposeField.getText().trim());
            clearForm();
        });
        add(addButton);
    }

    public void handleAddAppointment(String name, String day, String timeSlot, String purpose) {
        if (name.isEmpty() || day.isEmpty() || timeSlot.isEmpty() || purpose.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }
        if (conflictChecker.hasConflict(manager.getAppointments(), manager.getCount(), day, timeSlot)) {
            JOptionPane.showMessageDialog(null,
                    "Conflict! " + day + " " + timeSlot + " is already taken. Please try another slot.");

            return;
        }
        manager.addAppointment(name, day, timeSlot, purpose);
        tablePanel.refreshTable();
    }

    public void clearForm() {
        nameField.setText("");
        purposeField.setText("");
        dayBox.setSelectedIndex(0);
        timeBox.setSelectedIndex(0);
    }

}
