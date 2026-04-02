
// DatabaseManager.java
import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private Connection connection;

    public DatabaseManager() {
        createDatabaseIfNotExists();
        connect();
        createTable();
    }

    private void createDatabaseIfNotExists() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", DB_USER, DB_PASS);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS biblioteca");
            System.out.println("Database 'biblioteca' pronto.");
        } catch (SQLException e) {
            System.err.println("Errore creazione database: " + e.getMessage());
        }
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connessione a MySQL avvenuta con successo.");
        } catch (SQLException e) {
            System.err.println("Errore connessione: " + e.getMessage());
        }
    }

    private void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS books (
                    id        INT          AUTO_INCREMENT PRIMARY KEY,
                    title     VARCHAR(255) NOT NULL,
                    author    VARCHAR(255) NOT NULL,
                    available TINYINT(1)   NOT NULL DEFAULT 1
                )
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);

        } catch (SQLException e) {
            System.err.println("Errore creazione tabella: " + e.getMessage());
        }
    }

    // ─── INSERT ───────────────────────────────────────────────────────────────
    public void insertBook(Book book) {
        String sql = "INSERT INTO books (title, author, available) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, 1);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Errore inserimento: " + e.getMessage());
        }
    }

    // ─── SELECT ALL ───────────────────────────────────────────────────────────
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("available") == 1));
            }
        } catch (SQLException e) {
            System.err.println("❌ Errore lettura: " + e.getMessage());
        }
        return books;
    }

    // ─── SELECT per TITOLO ────────────────────────────────────────────────────
    public List<Book> searchByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + title + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("available") == 1));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca: " + e.getMessage());
        }
        return books;
    }

    // ─── SELECT per AUTORE ────────────────────────────────────────────────────
    public List<Book> searchByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + author + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("available") == 1));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca: " + e.getMessage());
        }
        return books;
    }

    // ─── SELECT per DISPONIBILITÀ ─────────────────────────────────────────────
    public List<Book> searchByAvailability(boolean available) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE available = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, available ? 1 : 0);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("available") == 1));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca: " + e.getMessage());
        }
        return books;
    }

    // ─── UPDATE titolo/autore ─────────────────────────────────────────────────
    public boolean updateBook(String oldTitle, String newTitle, String newAuthor) {
        // Costruisce la query dinamicamente in base ai campi da aggiornare
        StringBuilder sql = new StringBuilder("UPDATE books SET ");
        List<String> params = new ArrayList<>();

        if (!newTitle.isEmpty()) {
            sql.append("title = ?, ");
            params.add(newTitle);
        }
        if (!newAuthor.isEmpty()) {
            sql.append("author = ?, ");
            params.add(newAuthor);
        }

        if (params.isEmpty()) {
            System.out.println("Nessuna modifica da applicare.");
            return false;
        }

        // Rimuove l'ultima virgola
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE title = ?");
        params.add(oldTitle);

        System.out.println(sql.toString().replace("?", "%s").formatted(params.toArray()));

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setString(i + 1, params.get(i));
            }
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Errore modifica: " + e.getMessage());
            return false;
        }
    }

    // ─── UPDATE disponibilità ─────────────────────────────────────────────────
    public boolean updateAvailability(String title, boolean available) {
        String sql = "UPDATE books SET available = ? WHERE title = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, available ? 1 : 0);
            pstmt.setString(2, title);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Errore aggiornamento: " + e.getMessage());
            return false;
        }
    }

    // ─── DELETE ───────────────────────────────────────────────────────────────
    public boolean deleteBook(String title) {
        String sql = "DELETE FROM books WHERE title = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Errore eliminazione: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null)
                connection.close();
            System.out.println("Connessione chiusa.");
        } catch (SQLException e) {
            System.err.println("Errore chiusura: " + e.getMessage());
        }
    }
}