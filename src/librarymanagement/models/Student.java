package librarymanagement.models;
public class Student extends User {
    public Student(int id, String username, String name) {
        super(id, username, name, "STUDENT");
    }
}