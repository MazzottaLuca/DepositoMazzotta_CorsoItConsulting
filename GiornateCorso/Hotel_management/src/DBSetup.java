import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSetup {
    public static void creaTabelle() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/?serverTimezone=UTC", "root", "")) {

            Statement stmt = conn.createStatement();

            // Creazione database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS hotel_management");
            stmt.executeUpdate("USE hotel_management");

            // Tabelle
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS camera (" +
                    "numero INT PRIMARY KEY," +
                    "prezzo FLOAT NOT NULL" +
                    ")");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS suite (" +
                    "numero INT PRIMARY KEY," +
                    "serviziExtra VARCHAR(255)," +
                    "FOREIGN KEY (numero) REFERENCES camera(numero)" +
                    ")");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS hotel (" +
                    "nome VARCHAR(100) PRIMARY KEY" +
                    ")");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS hotel_camera (" +
                    "hotel_nome VARCHAR(100)," +
                    "camera_numero INT," +
                    "PRIMARY KEY(hotel_nome, camera_numero)," +
                    "FOREIGN KEY(hotel_nome) REFERENCES hotel(nome)," +
                    "FOREIGN KEY(camera_numero) REFERENCES camera(numero)" +
                    ")");

            System.out.println("Database e tabelle create correttamente!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}