package model;

/**
 * Classe modello che rappresenta un utente del sistema.
 * Contiene i dati dell'utente e i metodi getter.
 */
public class User {

    // Identificatore univoco dell'utente
    private int id;

    // Username dell'utente
    private String username;

    // Password dell'utente (presente anche nel DB)
    private String password;

    // Ruolo dell'utente (ADMIN, PRO, NORMAL)
    private String role;

    /**
     * Costruttore completo per inizializzare un utente
     */
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    // Getter password (serve per login)
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}