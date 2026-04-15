package appointmentScheduler.JComponents;

// Group Coordinator: Terenz Jahred Dantes
// Members: 
// Brent Angelo Lambino
// Jude Robin Sumalinab

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import appointmentScheduler.Appointment;
import appointmentScheduler.AppointmentManager;
import appointmentScheduler.FileHandler;

import java.awt.*;

public class TablePanel extends JPanel {

    private AppointmentManager manager;
    private FileHandler fileHandler;
    private StatusPanel statusPanel;

    private DefaultTableModel tableModel;
    private JTable table;

    public TablePanel(AppointmentManager manager, FileHandler fileHandler, StatusPanel statusPanel) {
        this.manager = manager;
        this.fileHandler = fileHandler;
        this.statusPanel = statusPanel;

        String[] column = { "#", "Name", "Day", "Time Slot", "Purpose" };
        tableModel = new DefaultTableModel(column, 0);
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Schedule"));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton cancelButton = new JButton("Cancel Selected");
        cancelButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                handleCancelAppointment(selectedRow + 1);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "Please select an appointment to cancel.");
            }
        });

        JButton sortButton = new JButton("Sort Appointments");
        sortButton.addActionListener(e -> {
            manager.sortAppointments();
            JOptionPane.showMessageDialog(null, "Appointments sorted by day and time.");
            refreshTable();
        });

        JButton saveButton = new JButton("Save Schedule");
        saveButton.addActionListener(e -> {
            this.fileHandler.saveSchedule(manager.getAppointments(), manager.getCount());
            JOptionPane.showMessageDialog(null, "Schedule saved to " + FileHandler.FILENAME);
        });

        bottomButtons.add(cancelButton);
        bottomButtons.add(sortButton);
        bottomButtons.add(saveButton);

        add(bottomButtons, BorderLayout.SOUTH);
        refreshTable();
    }

    public void handleCancelAppointment(int index) {
        JOptionPane.showMessageDialog(null,
                "Appointment cancelled for " + manager.getAppointments()[index - 1].getName() + ", on "
                        + manager.getAppointments()[index - 1].getDay() + " at "
                        + manager.getAppointments()[index - 1].getTimeSlot());

        manager.cancelAppointment(index);
        statusPanel.updateCount(manager.getCount());
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
        statusPanel.updateCount(manager.getCount());
    }
}
