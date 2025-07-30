package librarymanagement.models;
public class Admin extends User {
    public Admin(int id, String username, String name) {
        super(id, username, name, "ADMIN");
    }
}