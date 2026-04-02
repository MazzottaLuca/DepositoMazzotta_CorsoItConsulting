import java.sql.*;

public class DatabaseConnection {
    private static final String URL_DB = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "scuola";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    // Ottiene la connessione a un database specifico
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_DB + DB_NAME, USER, PASSWORD);
    }

    // Setup database e tabella studenti
    public static void setupDatabase() {
        try (Connection con = DriverManager.getConnection(URL_DB, USER, PASSWORD);
                Statement st = con.createStatement()) {

            // crea il database 
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME); //se non metto IF NOT EXISTS al secondo run del main mi dà errore 

            // crea la tabella studenti
            try (Connection dbCon = getConnection();
                    Statement st2 = dbCon.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS studenti (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "nome VARCHAR(100) NOT NULL, " +
                        "voto INT NOT NULL)";
                st2.executeUpdate(sql);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}