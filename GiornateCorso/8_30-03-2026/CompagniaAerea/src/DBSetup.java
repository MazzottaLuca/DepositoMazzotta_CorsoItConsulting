import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSetup {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "databaseCompagniaAerea";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement()) {

            // Creazione database
            String createDB = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(createDB);
            System.out.println("Database creato!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Connessione al database appena creato
        try (Connection conn = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
                Statement stmt = conn.createStatement()) {

            // Creazione tabella compagnia_aerea
            String compagnia = "CREATE TABLE IF NOT EXISTS compagnia_aerea ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nome VARCHAR(100)"
                    + ")";
            stmt.executeUpdate(compagnia);

            // Creazione tabella aereo
            String aereo = "CREATE TABLE IF NOT EXISTS aereo ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "modello VARCHAR(100), "
                    + "numero_posti INT, "
                    + "codice VARCHAR(50), "
                    + "compagnia_id INT, "
                    + "FOREIGN KEY (compagnia_id) REFERENCES compagnia_aerea(id)"
                    + ")";
            stmt.executeUpdate(aereo);

            // Creazione tabella pilota
            String pilota = "CREATE TABLE IF NOT EXISTS pilota ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nome VARCHAR(100), "
                    + "numero_brevetto VARCHAR(50), "
                    + "ore_volo INT, "
                    + "compagnia_id INT, "
                    + "FOREIGN KEY (compagnia_id) REFERENCES compagnia_aerea(id)"
                    + ")";
            stmt.executeUpdate(pilota);

            System.out.println("Tabelle create!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}