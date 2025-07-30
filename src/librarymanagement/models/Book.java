package librarymanagement.models;

public class Book {
    private int id;
    private String title, author, publisher, isbn;
    private int year, quantity;

    public Book(int id, String title, String author, String publisher, int year, String isbn, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public int getYear() { return year; }
    public String getIsbn() { return isbn; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int q) { this.quantity = q; }
}
