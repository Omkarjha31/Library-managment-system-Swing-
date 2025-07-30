package librarymanagement.views;

import librarymanagement.controllers.AuthController;
import librarymanagement.models.*;
import librarymanagement.utils.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    public LoginScreen() {
        setTitle("Library Login"); setSize(300,200); setDefaultCloseOperation(EXIT_ON_CLOSE); setLocationRelativeTo(null);
        JPanel p=new JPanel(new GridLayout(3,2,5,5));
        p.add(new JLabel("Username:")); txtUser=new JTextField(); p.add(txtUser);
        p.add(new JLabel("Password:")); txtPass=new JPasswordField(); p.add(txtPass);
        JButton btn=new JButton("Login"); p.add(btn);
        getContentPane().add(p, BorderLayout.CENTER);
        btn.addActionListener(e->{
            String user=txtUser.getText(), pass=new String(txtPass.getPassword());
            User u=AuthController.authenticate(user,pass);
            if(u!=null){
                dispose();
                switch(u.getRole()){
                    case "ADMIN": new AdminDashboard((Admin)u).setVisible(true); break;
                    case "LIBRARIAN": new LibrarianDashboard((Librarian)u).setVisible(true); break;
                    case "STUDENT": new StudentDashboard((Student)u).setVisible(true); break;
                }
            } else JOptionPane.showMessageDialog(this,"Invalid creds");
        });
    }
}