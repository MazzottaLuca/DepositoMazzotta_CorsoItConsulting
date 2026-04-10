package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDAO {

    /**
     * crea un ordina, l'utente pro può comprare un prodotto
     */
    public void addOrder(int userId, int productId, int quantity) {

        try {
            Connection conn = DBConnection.getInstance().getConnection();

            String query = "INSERT INTO orders(user_id, product_id, quantity) VALUES (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);

            stmt.executeUpdate();

            System.out.println("Ordine creato con successo!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}