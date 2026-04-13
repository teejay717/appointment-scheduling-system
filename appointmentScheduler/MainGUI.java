package appointmentScheduler;

import javax.swing.*;
import java.awt.*;

import appointmentScheduler.JComponents.StatusPanel;
import appointmentScheduler.JComponents.TablePanel;
import appointmentScheduler.JComponents.FormPanel;

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

        add(headerPanel(), BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel headerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JLabel titleLabel = new JLabel("Appointment Scheduling System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel subTitle = new JLabel("by Dantes, Lambino, Sumalinab", SwingConstants.CENTER);
        subTitle.setFont(new Font("SansSerif", Font.BOLD, 14));

        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(subTitle, BorderLayout.SOUTH);
        return panel;
    }
}
