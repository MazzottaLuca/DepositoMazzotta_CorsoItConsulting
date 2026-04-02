public class Negozio {
    private String nomeNegozio;
    private Prodotto prodotto;

    public Negozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
        this.prodotto = null;
    }

    public String getNomeNegozio() {
        return nomeNegozio;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public void stampa() {
        System.out.println("Negozio: " + nomeNegozio);
        if (prodotto != null) {
            prodotto.stampa();
        } else {
            System.out.println("Nessun prodotto inserito.");
        }
        System.out.println("----------------------");
    }
}