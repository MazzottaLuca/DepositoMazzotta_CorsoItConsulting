public class Ordine {
    private int id;
    private String cliente;
    private String prodotto;
    private int quantita;
    private String stato;

    // Costruttore senza id (per nuovi ordini)
    public Ordine(String cliente, String prodotto, int quantita, String stato) {
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.stato = stato;
    }

    // Costruttore con id (per dati dal database)
    public Ordine(int id, String cliente, String prodotto, int quantita, String stato) {
        this.id = id;
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.stato = stato;
    }

    // getter e setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getProdotto() { return prodotto; }
    public void setProdotto(String prodotto) { this.prodotto = prodotto; }

    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }
}