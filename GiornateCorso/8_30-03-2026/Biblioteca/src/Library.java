import java.util.*;

public class Library {
    private ArrayList<Book> books;
    private DatabaseManager db;

    public Library() {
        this.db = new DatabaseManager();
        this.books = new ArrayList<>(db.getAllBooks());
    }

    public void addBook(Book book) {
        db.insertBook(book);
        books = new ArrayList<>(db.getAllBooks()); // ricarica dal DB
        System.out.println("Libro aggiunto: \"" + book.getTitle() + "\"");
    }

    public void displayBooks() {
        books = new ArrayList<>(db.getAllBooks());
        if (books.isEmpty()) {
            System.out.println("Nessun libro in biblioteca.");
            return;
        }

        for (Book b : books)
            b.mostraInfo();
    }

    public void searchByTitle(String title) {
        List<Book> results = db.searchByTitle(title);
        printResults(results);
    }

    public void searchByAuthor(String author) {
        List<Book> results = db.searchByAuthor(author);
        printResults(results);
    }

    public void searchByAvailability(boolean available) {
        List<Book> results = db.searchByAvailability(available);
        printResults(results);
    }

    public void updateBook(String oldTitle, String newTitle, String newAuthor) {
        if (db.updateBook(oldTitle, newTitle, newAuthor)) {
            books = new ArrayList<>(db.getAllBooks());
            System.out.println("Libro modificato con successo.");
        } else {
            System.out.println("Libro non trovato o nessuna modifica applicata.");
        }
    }

    public void deleteBook(String title) {
        if (db.deleteBook(title)) {
            books = new ArrayList<>(db.getAllBooks());
            System.out.println("Libro eliminato: \"" + title + "\"");
        } else {
            System.out.println("Libro \"" + title + "\" non trovato.");
        }
    }

    public void borrowBook(String title) {
        if (db.updateAvailability(title, false)) {
            books = new ArrayList<>(db.getAllBooks());
            System.out.println("Hai preso in prestito: \"" + title + "\"");
        } else {
            System.out.println("Libro non disponibile o non trovato.");
        }
    }

    public void returnBook(String title) {
        if (db.updateAvailability(title, true)) {
            books = new ArrayList<>(db.getAllBooks());
            System.out.println("Libro restituito: \"" + title + "\"");
        } else {
            System.out.println("Libro non trovato.");
        }
    }

    private void printResults(List<Book> results) {
        if (results.isEmpty()) {
            System.out.println("Nessun risultato trovato.");
        } else {

            for (Book b : results)
                b.mostraInfo();
        }
    }

    public void close() {
        db.close();
    }
}