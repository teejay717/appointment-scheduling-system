package appointmentScheduler;

// Group Coordinator: Terenz Jahred Dantes
// Members: 
// Brent Angelo Lambino
// Jude Robin Sumalinab

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if (WelcomePopup.showWelcomeDialog()) {
                new MainGUI();
            } else {
                System.exit(0);
            }
        });
    }
}