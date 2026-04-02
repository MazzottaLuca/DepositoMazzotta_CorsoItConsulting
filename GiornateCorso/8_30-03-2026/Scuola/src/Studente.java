import java.sql.*;
import java.util.ArrayList;

public class Studente {
    private int id;
    private String nome;
    private int voto;

    // Costruttore per nuovo studente (ID è autoincrementale, quindi non lo passo
    // nel costruttore)
    public Studente(String nome, int voto) {
        this.nome = nome;
        this.voto = voto;
    }

    // Costruttore per studente già nel DB
    public Studente(int id, String nome, int voto) { // qua sì per la ricerca (anche se la ricerca la faccio per nome)
        this.id = id;
        this.nome = nome;
        this.voto = voto;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void stampaStudente() {
        System.out.println("ID: " + id + " Nome: " + nome + " Voto: " + voto);
    }

    // Salva lo studente nel DB
    public void salvaDB() {
        String query = "INSERT INTO studenti (nome, voto) VALUES (?, ?)"; // no id
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            //RETURN_GENERATED_KEYS l'ho messo perchè la query mi deve generare la chiave primaria id che è autoincrement
            ps.setString(1, this.nome);
            ps.setInt(2, this.voto);
            ps.executeUpdate();

            // l'id lo prendo dal database con quel metodo getGeneratedKeys
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Legge tutti gli studenti dal DB
    public static ArrayList<Studente> leggiTutti() {
        ArrayList<Studente> lista = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM studenti")) {

            while (rs.next()) {
                lista.add(new Studente(rs.getInt("id"), rs.getString("nome"), rs.getInt("voto")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Cerca studente per nome
    public static Studente cercaStudente(String nome) {
        String sql = "SELECT * FROM studenti WHERE nome = ?";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Studente(rs.getInt("id"), rs.getString("nome"), rs.getInt("voto"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Modifica voto per nome
    public static void modificaVoto(String nome, int nuovoVoto) {
        String sql = "UPDATE studenti SET voto = ? WHERE nome = ?";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nuovoVoto);
            ps.setString(2, nome);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}