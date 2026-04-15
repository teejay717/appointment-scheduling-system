package appointmentScheduler;

// Group Coordinator: Terenz Jahred Dantes
// Members: 
// Brent Angelo Lambino
// Jude Robin Sumalinab

import javax.swing.*;
import java.awt.*;

public class WelcomePopup {
    public static boolean showWelcomeDialog() {
        JDialog welcome = new JDialog((Frame) null, "Welcome", true);
        welcome.setSize(500, 300);
        welcome.setLocationRelativeTo(null);
        welcome.setLayout(new BorderLayout());

        JLabel msg = new JLabel("Appointment Scheduling System", SwingConstants.CENTER);
        msg.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel sub = new JLabel("by Dantes, Lambino, Sumalinab", SwingConstants.CENTER);
        sub.setFont(new Font("SansSerif", Font.BOLD, 14));

        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton proceedBtn = new JButton("Proceed");
        JButton exitBtn = new JButton("Exit");

        final boolean[] proceeded = { false };
        proceedBtn.addActionListener(e -> {
            proceeded[0] = true;
            welcome.dispose();
        });
        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Goodbye!");
            welcome.dispose();
        });

        btnPanel.add(proceedBtn);
        btnPanel.add(exitBtn);

        welcome.add(sub, BorderLayout.NORTH);
        welcome.add(msg, BorderLayout.CENTER);
        welcome.add(btnPanel, BorderLayout.SOUTH);

        welcome.setVisible(true);
        return proceeded[0];
    }
}