package appointmentScheduler.JComponents;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import appointmentScheduler.AppointmentManager;
import appointmentScheduler.FileHandler;
import appointmentScheduler.TimeUtil;

import java.awt.*;

public class StatusPanel extends JPanel {
    private JLabel statusLabel;
    private JLabel clockLabel;

    public StatusPanel(AppointmentManager manager, FileHandler fileHandler) {
        setLayout(new BorderLayout(50, 10));
        statusLabel = new JLabel("Total Appointments: " + manager.getCount());
        add(statusLabel, BorderLayout.CENTER);

        clockLabel = new JLabel();
        startClock();
        add(clockLabel, BorderLayout.WEST);

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
        add(exitButton, BorderLayout.EAST);
    }

    public void updateCount(int count) {
        statusLabel.setText("Total Appointments: " + count);
    }

    private void startClock() {

        clockLabel.setText(TimeUtil.getLiveClockTime());

        Timer timer = new Timer(1000, e -> {
            clockLabel.setText(TimeUtil.getLiveClockTime());
        });
        timer.start();
    }
}
