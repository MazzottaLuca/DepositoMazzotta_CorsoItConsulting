public class Auto extends Veicolo implements IGestioneDB{ // estende la classe astratta veicolo
    private double cilindrata;

    public Auto(String targa, double velocita, int numeroAssi, double cilindrata) {
        super(targa, velocita, numeroAssi);
        setCilindrata(cilindrata);
    }

    public double getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(double cilindrata) {
        if (cilindrata <= 0)
            throw new IllegalArgumentException("Cilindrata non valida");
        this.cilindrata = cilindrata;
    }

    @Override
    public double calcolaPedaggio() {
        return 2.0 + (cilindrata * 0.001);
    }

    @Override
    public String getAttributoExtra() {
        return "Cilindrata: " + cilindrata + " cc";
    }

    @Override
    public String getTipo() {
        return "Auto";
    }
}