package appointmentScheduler.JComponents;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import appointmentScheduler.AppointmentManager;
import appointmentScheduler.FileHandler;

import java.awt.*;

public class StatusPanel extends JPanel {
    private JLabel statusLabel;

    public StatusPanel(AppointmentManager manager, FileHandler fileHandler) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Total Appointments: " + manager.getCount());
        add(statusLabel);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            int saveSchedule = JOptionPane.showConfirmDialog(null, "Do you want to save the schedule before exiting?",
                    "Save Schedule",
                    JOptionPane.YES_NO_OPTION);
            if (saveSchedule == JOptionPane.YES_OPTION) {
                fileHandler.saveSchedule(manager.getAppointments(), manager.getCount());
                JOptionPane.showMessageDialog(null, "Schedule saved to " + FileHandler.FILENAME);
                System.exit(0);
            } else {
                System.exit(0);
            }
        });
        add(exitButton);
    }

    public void updateCount(int count) {
        statusLabel.setText("Total Appointments: " + count);
    }
}
