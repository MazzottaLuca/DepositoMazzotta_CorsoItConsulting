public class Camion extends Veicolo implements IGestioneDB { //estende la classe astratta veicolo
    private double pesoTonellate;

    public Camion(String targa, double velocita, int numeroAssi, double pesoTonellate) {
        super(targa, velocita, numeroAssi);
        setPesoTonellate(pesoTonellate);
    }

    public double getPesoTonellate() { return pesoTonellate; }
    public void setPesoTonellate(double peso) {
        if (peso <= 0) throw new IllegalArgumentException("Peso non valido");
        this.pesoTonellate = peso;
    }

    @Override
    public double calcolaPedaggio() {
        return 5.0 + (getNumeroAssi() * 1.5) + (pesoTonellate * 0.3);
    }

    @Override
    public String getAttributoExtra() { return "Peso: " + pesoTonellate + " t"; }

    @Override
    public String getTipo() { return "Camion"; }
}