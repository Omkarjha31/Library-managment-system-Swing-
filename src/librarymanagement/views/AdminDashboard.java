package librarymanagement.views;

import java.awt.*;
import javax.swing.*;
import librarymanagement.models.Admin;

public class AdminDashboard extends JFrame {
    private Admin admin;

    public AdminDashboard(Admin admin) {
        this.admin = admin;
        setTitle("Admin Dashboard - " + admin.getName());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Users", new UserManagementView());
        tabs.addTab("Reports", new ReportsView());
        tabs.addTab("Notifications", new NotificationsView());

        // Create logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginScreen().setVisible(true);
        });

        // Panel for logout button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(logoutButton);

        // Set layout and add components
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabs, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
    }
}