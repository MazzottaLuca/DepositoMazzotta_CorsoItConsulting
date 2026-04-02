public class OrdineProdotto {
    private Object prodotto; // Hamburger o Dessert
    private int quantita;

    public OrdineProdotto(Object prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Object getProdotto() { return prodotto; }
    public int getQuantita() { return quantita; }

    public void prepara() {
        if (prodotto instanceof Hamburger) ((Hamburger) prodotto).prepara(quantita);
        else if (prodotto instanceof Dessert) ((Dessert) prodotto).prepara(quantita);
    }

    public String getNome() {
        if (prodotto instanceof Hamburger) return ((Hamburger) prodotto).getNome();
        if (prodotto instanceof Dessert) return ((Dessert) prodotto).getNome();
        return "Sconosciuto";
    }
}