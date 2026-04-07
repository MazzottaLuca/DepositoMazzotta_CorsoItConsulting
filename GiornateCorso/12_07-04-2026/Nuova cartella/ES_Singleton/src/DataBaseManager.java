import java.sql.*;
import java.util.Scanner;

public class DataBaseManager {

    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "singleton_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static final String JDBC_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
            + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static DataBaseManager instance;
    private Connection connection;
    private int connectionCount = 0;
    private String currentUser = null;

    private DataBaseManager() {
        initDataBase();
    }

    public static DataBaseManager getInstance() {
        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    private void initDataBase() {
        try {
            String baseUrl = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT
                    + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

            try (Connection tmp = DriverManager.getConnection(baseUrl, DB_USER, DB_PASS);
                    Statement s = tmp.createStatement()) {

                s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            }

            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

            String createUsers = """
                    CREATE TABLE IF NOT EXISTS utenti (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(100) NOT NULL UNIQUE,
                        creato TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                    """;

            String createData = """
                    CREATE TABLE IF NOT EXISTS dati (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        utente VARCHAR(100),
                        chiave VARCHAR(255),
                        valore TEXT,
                        aggiornato TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                        ON UPDATE CURRENT_TIMESTAMP,
                        UNIQUE KEY unica (utente, chiave)
                    )
                    """;

            try (Statement s = connection.createStatement()) {
                s.executeUpdate(createUsers);
                s.executeUpdate(createData);
            }

            System.out.println("Database pronto!");

        } catch (SQLException e) {
            System.err.println("Errore DB: " + e.getMessage());
        }
    }

    public void connect() {
        connectionCount++;
        System.out.println("Connessione stabilita. Connessioni attive: " + connectionCount);
    }

    public int getConnectionCount() {
        return connectionCount;
    }

    public void setUser(String username) {
        try {
            String sql = "INSERT IGNORE INTO utenti (username) VALUES (?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.executeUpdate();
            }

            currentUser = username;
            System.out.println("Utente impostato: " + currentUser);

        } catch (SQLException e) {
            System.err.println("Errore setUser: " + e.getMessage());
        }
    }

    private boolean isUserLoggedIn() {
        return currentUser != null && !currentUser.isEmpty();
    }

    public void saveData(String key, String value) {
        if (!isUserLoggedIn()) {
            System.out.println("Nessun utente loggato!");
            return;
        }

        try {
            String sql = """
                    INSERT INTO dati (utente, chiave, valore)
                    VALUES (?, ?, ?)
                    ON DUPLICATE KEY UPDATE valore = VALUES(valore)
                    """;

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, currentUser);
                ps.setString(2, key);
                ps.setString(3, value);
                ps.executeUpdate();
            }

            System.out.println("Dato salvato!");

        } catch (SQLException e) {
            System.err.println("Errore saveData: " + e.getMessage());
        }
    }

    public String getData(String key) {
        if (!isUserLoggedIn()) {
            System.out.println("Nessun utente loggato!");
            return null;
        }

        try {
            String sql = "SELECT valore FROM dati WHERE utente = ? AND chiave = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, currentUser);
                ps.setString(2, key);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getString("valore");
                }
            }

        } catch (SQLException e) {
            System.err.println("Errore getData: " + e.getMessage());
        }

        return null;
    }

    public void printAllData() {
        if (!isUserLoggedIn()) {
            System.out.println("Nessun utente loggato.");
            return;
        }

        try {
            String sql = "SELECT chiave, valore FROM dati WHERE utente = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, currentUser);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString("chiave") + " = " + rs.getString("valore"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connessione chiusa.");
            }
        } catch (SQLException e) {
            System.err.println("Errore chiusura: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        DataBaseManager db1 = DataBaseManager.getInstance();
        DataBaseManager db2 = DataBaseManager.getInstance();

        System.out.println("Stessa istanza? " + (db1 == db2));

        db1.connect();
        db2.connect();

        System.out.println("Connessioni totali: " + db1.getConnectionCount());

        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n1. Set utente");
            System.out.println("2. Salva dato (nome + password)");
            System.out.println("3. Leggi dato (inserisci password)");
            System.out.println("4. Mostra dati");
            System.out.println("0. Esci");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> {
                    System.out.print("Nome utente: ");
                    db1.setUser(scanner.nextLine());
                }

                case "2" -> {
                    System.out.print("Nome (valore): ");
                    String value = scanner.nextLine();

                    System.out.print("Password (chiave): ");
                    String key = scanner.nextLine();

                    db1.saveData(key, value); // 🔥 corretto
                }

                case "3" -> {
                    System.out.print("Inserisci password: ");
                    String key = scanner.nextLine();

                    String result = db1.getData(key);

                    if (result != null) {
                        System.out.println("Nome associato: " + result);
                    } else {
                        System.out.println("Password non trovata.");
                    }
                }

                case "4" -> db1.printAllData();

                case "0" -> {
                    run = false;
                    db1.close();
                    System.out.println("Uscita.");
                }

                default -> System.out.println("Scelta non valida.");
            }
        }

        scanner.close();
    }
}