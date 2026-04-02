import java.sql.*;
import java.util.Scanner;

public class Esercizio2 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = "root";
        String psw = "";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci una stringa da cercare nel titolo: ");
        String stringa = scanner.nextLine();

        String query = " SELECT title, description, release_year FROM film WHERE title LIKE ? ";
        //String query = " SELECT title, description, release_year FROM film WHERE title LIKE % ? % " facendo così e togliendo % a riga 21 non funziona
        try (Connection conn = DriverManager.getConnection(url, username, psw);
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            // aggiungo %
            pstmt.setString(1, "%" + stringa + "%"); // % serve per dire: "contiene questa stringa"

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title"); //prendo le colonne in base al nome, non alla posizione
                String description = rs.getString("description");
                int year = rs.getInt("release_year");

                System.out.println("Titolo: " + title);
                System.out.println("Descrizione: " + description);
                System.out.println("Anno: " + year);
                System.out.println("-----------------------------");
                scanner.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}