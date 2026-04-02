//creo classe gioco con costruttore e metodo per settare lo stato dello sviluppo
public class Gioco {
    String titolo;
    String genere;
    double costoSviluppo;
    String stato; // in sviluppo, in test, pubblicato

    public Gioco(String titolo, String genere, double costoSviluppo, String stato) {
        this.titolo = titolo;
        this.genere = genere;
        this.costoSviluppo = costoSviluppo;
        this.stato = stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String toString() {
        return titolo + " (" + genere + ") - Costo: " + costoSviluppo + " - Stato: " + stato;
    }
}