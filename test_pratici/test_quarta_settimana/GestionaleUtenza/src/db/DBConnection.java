package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
//istanza unica, pattern singleton
    private static DBConnection instance;
    private Connection connection;
//parametri di connessione
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB_NAME = "gestione_vendite";
    private final String USER = "root";
    private final String PASSWORD = "";

    private DBConnection() {
        try {
            //Connessione senza DB (per crearlo)
            Connection tempConn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = tempConn.createStatement();

            //Creazione DB
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);

            //Connessione al DB creato
            connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);

            //Setup tabelle
            setupDatabase();

            tempConn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    //DB SETUP (tabelle create automaticamente)
    private void setupDatabase() {
        try {
            Statement stmt = connection.createStatement();

            // USERS
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50)," +
                "password VARCHAR(50)," +
                "role VARCHAR(20)" +
                ")"
            );

            // PRODUCTS
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS products (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100)," +
                "price DOUBLE" +
                ")"
            );

            // ORDERS
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS orders (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "user_id INT," +
                "product_id INT," +
                "quantity INT," +
                "FOREIGN KEY (user_id) REFERENCES users(id)," +
                "FOREIGN KEY (product_id) REFERENCES products(id)" +
                ")"
            );

            System.out.println("Database e tabelle pronti!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}