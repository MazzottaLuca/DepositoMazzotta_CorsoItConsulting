public class Cravatta extends ComponenteFinitura {

    private double larghezza;

    public Cravatta(String codice, String nome, String materiale,
            String colore, double prezzo, double larghezza) {
        super(codice, nome, materiale, colore, prezzo);
        setLarghezza(larghezza);
    }

    public double getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(double larghezza) {
        if (larghezza <= 0)
            throw new IllegalArgumentException("Larghezza non valida");
        this.larghezza = larghezza;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Cravatta: " + getNome() +
                " | Larghezza: " + larghezza);
    }
}