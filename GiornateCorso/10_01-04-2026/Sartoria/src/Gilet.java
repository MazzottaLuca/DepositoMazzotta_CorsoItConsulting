public class Gilet extends CapoPrincipale {

    private boolean reverPresente;

    public Gilet(String codice, String nome, String tessuto,
            String colore, String taglia, double prezzo,
            boolean reverPresente) {
        super(codice, nome, tessuto, colore, taglia, prezzo);
        this.reverPresente = reverPresente;
    }

    public boolean isReverPresente() {
        return reverPresente;
    }

    public void setReverPresente(boolean reverPresente) {
        this.reverPresente = reverPresente;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Gilet: " + getNome() +
                " | Rever: " + reverPresente);
    }
}