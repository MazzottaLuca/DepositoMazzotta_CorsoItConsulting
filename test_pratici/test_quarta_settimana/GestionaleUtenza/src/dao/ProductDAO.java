package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO {
    // metodo per aggiungere un nuovo prodotto al database
    public void addProduct(String name, double price) {
        try {
            // ottiene la connessione al db tramite il singleton
            Connection conn = DBConnection.getInstance().getConnection();
            // query di inserimento del nuovo prodotto
            String query = "INSERT INTO products(name, price) VALUES (?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setDouble(2, price);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metodo per recuperare i prodotti
    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();

        try {
            Connection conn = DBConnection.getInstance().getConnection();

            String query = "SELECT * FROM products";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}