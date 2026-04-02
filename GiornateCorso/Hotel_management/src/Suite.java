import java.sql.Connection;
import java.sql.PreparedStatement;

public class Suite extends Camera {
    private String serviziExtra;

    public Suite(int numero, float prezzo, String serviziExtra) {
        super(numero, prezzo);
        this.serviziExtra = serviziExtra;
    }

    public String getServiziExtra() {
        return serviziExtra;
    }

    public void setServiziExtra(String serviziExtra) {
        this.serviziExtra = serviziExtra;
    }

    @Override
    public void dettagli() {
        super.dettagli();
        System.out.println("Servizi extra: " + serviziExtra);
    }

    @Override
    public void dettagli(boolean conPrezzo) {
        if (conPrezzo)
            dettagli();
        else {
            System.out.println("Suite numero: " + getNumero());
            System.out.println("Servizi extra: " + serviziExtra);
        }
    }

    @Override
    public void inserisciDB() {
        super.inserisciDB(); // Inserisce la camera
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO suite (numero, serviziExtra) VALUES (?, ?) ON DUPLICATE KEY UPDATE serviziExtra=VALUES(serviziExtra)")) {
            stmt.setInt(1, getNumero());
            stmt.setString(2, serviziExtra);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}