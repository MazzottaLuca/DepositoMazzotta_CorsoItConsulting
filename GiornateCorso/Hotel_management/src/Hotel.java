import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Hotel {
    private String nome;
    private ArrayList<Camera> listaCamere;

    public Hotel(String nome) {
        this.nome = nome;
        this.listaCamere = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void aggiungiCamera(Camera c) { listaCamere.add(c); }
    public ArrayList<Camera> getListaCamere() { return listaCamere; }

    public static int contaSuite(ArrayList<Camera> lista) {
        int count = 0;
        for (Camera c : lista) {
            if (c instanceof Suite) count++;
        }
        return count;
    }

    public void inserisciDB() {
        try (Connection conn = DBConnection.getConnection()) {
            // Inserisce l'hotel
            PreparedStatement stmtHotel = conn.prepareStatement(
                    "INSERT INTO hotel (nome) VALUES (?) ON DUPLICATE KEY UPDATE nome=nome");
            stmtHotel.setString(1, nome);
            stmtHotel.executeUpdate();

            // Inserisce camere e collegamenti
            for (Camera c : listaCamere) {
                c.inserisciDB();
                PreparedStatement stmtLink = conn.prepareStatement(
                        "INSERT INTO hotel_camera (hotel_nome, camera_numero) VALUES (?, ?) ON DUPLICATE KEY UPDATE hotel_nome=hotel_nome");
                stmtLink.setString(1, nome);
                stmtLink.setInt(2, c.getNumero());
                stmtLink.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}