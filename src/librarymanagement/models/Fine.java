package librarymanagement.models;
public class Fine {
    private int id, borrowId;
    private double amount;
    private boolean paid;
    public Fine(int id, int borrowId, double amount, boolean paid) {
        this.id = id; this.borrowId = borrowId; this.amount = amount; this.paid = paid;
    }
    public int getId() { return id; }
    public int getBorrowId() { return borrowId; }
    public double getAmount() { return amount; }
    public boolean isPaid() { return paid; }
}
