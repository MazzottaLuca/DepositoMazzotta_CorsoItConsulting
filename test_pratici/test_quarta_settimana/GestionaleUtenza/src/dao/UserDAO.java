package dao;

import db.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 * DAO per la gestione degli utenti.
 * Contiene tutte le operazioni SQL relative agli utenti:
 * - registrazione
 * - login
 * - recupero lista utenti
 */
public class UserDAO {

    /**
     * REGISTRAZIONE UTENTE
     */
    public void registerUser(String username, String password, String role) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();

            String query = "INSERT INTO users(username, password, role) VALUES (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            stmt.executeUpdate();

            System.out.println("Utente registrato con successo!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * LOGIN UTENTE
     * ritorna User se credenziali corrette, altrimenti null
     */
    public User login(String username, String password) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * RECUPERO LISTA UTENTI
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try {
            Connection conn = DBConnection.getInstance().getConnection();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}