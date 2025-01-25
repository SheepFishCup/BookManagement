package Bean;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publishDate;
    private int stock;
    private String type;
    private String description;

    public Book() {
    }

    public Book(String title, String author, String isbn, String publisher, String publishDate, int stock, String type, String description) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.stock = stock;
        this.type = type;
        this.description = description;
    }
    public Book(int id, String title, String author, String isbn, String publisher, String publishDate, int stock, String type, String description) {
        this.id=id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.stock = stock;
        this.type = type;
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
