package librarymanagement.views;

import java.awt.*;
import javax.swing.*;
import librarymanagement.models.Librarian;

public class LibrarianDashboard extends JFrame {
    public LibrarianDashboard(Librarian lib) {
        setTitle("Librarian Dashboard - " + lib.getName());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Books", new BookManagementView());
        tabs.addTab("Borrow/Return", new BorrowReturnView());
        tabs.addTab("Fines", new FinesView());
        tabs.addTab("Notifications", new NotificationsView());

        // Add logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginScreen().setVisible(true); // Changed from LoginView to LoginScreen
        });

        // Create a panel for the logout button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(logoutButton);

        // Add components to frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabs, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
    }
}