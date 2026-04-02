import java.sql.*;

public class DBSetup {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "delivery";
    private static final String USER = "root"; 
    private static final String PASS = "";     

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + DB_NAME, USER, PASS);
    }

    public static void setupDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);

            try (Connection dbConn = getConnection(); Statement dbStmt = dbConn.createStatement()) {
                String creaVeicoli = "CREATE TABLE IF NOT EXISTS Veicoli (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "tipo VARCHAR(20) NOT NULL," +
                        "targa VARCHAR(20) NOT NULL," +
                        "caricoMassimo FLOAT NOT NULL)";
                dbStmt.executeUpdate(creaVeicoli);

                String creaPacchi = "CREATE TABLE IF NOT EXISTS Pacchi (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "idVeicolo INT NOT NULL," +
                        "destinazione VARCHAR(100) NOT NULL," +
                        "peso FLOAT NOT NULL," +
                        "tracking VARCHAR(50) NOT NULL," +
                        "FOREIGN KEY (idVeicolo) REFERENCES Veicoli(id) ON DELETE CASCADE)";
                dbStmt.executeUpdate(creaPacchi);

                System.out.println("Database e tabelle creati/verificati.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}