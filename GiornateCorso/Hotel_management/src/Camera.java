import java.sql.Connection;
import java.sql.PreparedStatement;

public class Camera {
    private int numero;
    private float prezzo;

    public Camera(int numero, float prezzo) {
        this.numero = numero;
        this.prezzo = prezzo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void dettagli() {
        System.out.println("Camera numero: " + numero + ", Prezzo: " + prezzo);
    }

    public void dettagli(boolean conPrezzo) {
        if (conPrezzo)
            dettagli();
        else
            System.out.println("Camera numero: " + numero);
    }

    // Inserisce la camera nel DB
    public void inserisciDB() {
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO camera (numero, prezzo) VALUES (?, ?) ON DUPLICATE KEY UPDATE prezzo=VALUES(prezzo)")) {
            stmt.setInt(1, numero);
            stmt.setFloat(2, prezzo);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}