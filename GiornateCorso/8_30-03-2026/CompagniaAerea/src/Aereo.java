public class Aereo {
    private int id;
    private String modello;
    private int numeroPosti;
    private String codice;

    public Aereo(String modello, int numeroPosti, String codice) {
        this.modello = modello;
        this.numeroPosti = numeroPosti;
        this.codice = codice;
    }

    public int getId() {
        return id;
    }

    public String getModello() {
        return modello;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public String getCodice() {
        return codice;
    }
}