package librarymanagement.models;

public class User {
    protected int id;
    protected String username;
    protected String name;
    protected String role;

    public User(int id, String username, String name, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getName() { return name; }
    public String getRole() { return role; }
}
