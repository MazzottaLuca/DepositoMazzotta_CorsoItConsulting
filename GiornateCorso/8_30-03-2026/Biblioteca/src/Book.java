public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    // Costruttore per libro nuovo (senza id perchè è autoincrement)
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    // Costruttore per libro caricato dal DB (con id)
    public Book(int id, String title, String author, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void mostraInfo() {
        System.out.println("ID: " + id + " Titolo: " + title);
        System.out.println("Autore:      " + author);
        System.out.println("Disponibile: " + (isAvailable ? " Sì" : " No"));

    }
}