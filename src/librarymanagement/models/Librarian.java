package librarymanagement.models;
public class Librarian extends User {
    public Librarian(int id, String username, String name) {
        super(id, username, name, "LIBRARIAN");
    }
}