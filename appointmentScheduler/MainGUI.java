package appointmentScheduler;

import javax.swing.*;

import appointmentScheduler.JComponents.StatusPanel;
import appointmentScheduler.JComponents.TablePanel;
import appointmentScheduler.JComponents.FormPanel;

import java.awt.*;

public class MainGUI extends JFrame {
    private AppointmentManager manager = new AppointmentManager();
    private FileHandler fileHandler = new FileHandler();

    public MainGUI() {

        StatusPanel statusPanel = new StatusPanel(manager, fileHandler);
        TablePanel tablePanel = new TablePanel(manager, fileHandler, statusPanel);
        FormPanel formPanel = new FormPanel(manager, tablePanel);

        setTitle("Appointment Scheduling System");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
