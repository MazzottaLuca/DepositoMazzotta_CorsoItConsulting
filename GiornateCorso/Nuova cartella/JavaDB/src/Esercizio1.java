import java.sql.*;

public class Esercizio1 {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String psw = "";

        String query = "SELECT f.film_id, f.title, COUNT(r.rental_id) AS totale_noleggi FROM rental r JOIN inventory i ON r.inventory_id = i.inventory_id JOIN film f ON i.film_id = f.film_id GROUP BY f.film_id, f.title ORDER BY totale_noleggi DESC LIMIT ?";

        int nRow = 10; //la richiesta è che vengano presi i primi 10

        try (Connection conn = DriverManager.getConnection(url, username, psw);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, nRow);

            ResultSet result = pstmt.executeQuery();
            ResultSetMetaData meta = result.getMetaData();
            int numColumns = meta.getColumnCount();

            while (result.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    String column = meta.getColumnName(i);
                    Object val = result.getObject(i);
                    System.out.print(column + ":" + val);

                    if (i < numColumns) {
                        System.out.print(" | ");
                    }
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}