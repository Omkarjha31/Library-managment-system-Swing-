package librarymanagement.models;
import java.util.Date;
public class BorrowRecord {
    private int id, bookId, studentId;
    private Date borrowDate, dueDate, returnDate;
    public BorrowRecord(int id, int bookId, int studentId, Date borrowDate, Date dueDate, Date returnDate) {
        this.id = id; this.bookId = bookId; this.studentId = studentId;
        this.borrowDate = borrowDate; this.dueDate = dueDate; this.returnDate = returnDate;
    }
    public int getId() { return id; }
    public int getBookId() { return bookId; }
    public int getStudentId() { return studentId; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getDueDate() { return dueDate; }
    public Date getReturnDate() { return returnDate; }
}
