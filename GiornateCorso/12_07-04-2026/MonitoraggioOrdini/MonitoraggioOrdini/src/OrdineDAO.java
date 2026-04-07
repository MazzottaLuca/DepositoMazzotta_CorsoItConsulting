import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdineDAO {
    // nel data access object faccio le query di inserimento e aggiornamento ordini
    // nel database
    private Connection conn;

    public OrdineDAO() throws SQLException {
        this.conn = DBConn.getInstance().getConnection();
    }

    public void inserisciOrdine(Ordine ordine) throws SQLException {
        String sql = "INSERT INTO ordini (cliente, prodotto, quantita, stato) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ordine.getCliente());
            stmt.setString(2, ordine.getProdotto());
            stmt.setInt(3, ordine.getQuantita());
            stmt.setString(4, ordine.getStato());

            stmt.executeUpdate();

            // recupero ID auto-generato
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    ordine.setId(rs.getInt(1));
                }
            }
        }
    }

    public void aggiornaStato(int id, String nuovoStato) throws SQLException {
        String sql = "UPDATE ordini SET stato = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuovoStato);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public Ordine getOrdine(int id) throws SQLException {
        String sql = "SELECT * FROM ordini WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Ordine(rs.getInt("id"), rs.getString("cliente"), rs.getString("prodotto"),
                        rs.getInt("quantita"), rs.getString("stato"));
            }
        }
        return null;
    }
}
