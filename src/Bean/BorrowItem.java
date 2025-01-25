package Bean;

public class BorrowItem {
    private Integer id;
    private String name;
    private String title;
    private String isbn;
    private String borrowDate;
    private String borrowState;

    public BorrowItem() {
    }

    public BorrowItem(Integer id, String name, String title, String isbn, String borrowDate, String borrowState) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.isbn = isbn;
        this.borrowDate = borrowDate;
        this.borrowState = borrowState;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(String borrowState) {
        this.borrowState = borrowState;
    }
}
