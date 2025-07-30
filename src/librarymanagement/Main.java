package librarymanagement;
import librarymanagement.utils.DatabaseConnection;
import librarymanagement.views.LoginScreen;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.init();
        javax.swing.SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true));
    }
}
