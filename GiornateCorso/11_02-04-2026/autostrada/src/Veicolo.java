public abstract class Veicolo { //classe astratta che estende auto camion e moto, non interfaccia perchè non ha solo metodi
    //non la istanzio perchè serve solo da base per le sottoclassi
    private String targa;
    private double velocita;
    private int numeroAssi;

    public Veicolo(String targa, double velocita, int numeroAssi) {
        setTarga(targa);
        setVelocita(velocita);
        setNumeroAssi(numeroAssi);
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        if (targa == null || targa.isBlank())
            throw new IllegalArgumentException("Targa vuota o non valida");
        this.targa = targa;
    }

    public double getVelocita() {
        return velocita;
    }

    public void setVelocita(double velocita) {
        if (velocita < 0)
            throw new IllegalArgumentException("Velocità non può essere negativa");
        this.velocita = velocita;
    }

    public int getNumeroAssi() {
        return numeroAssi;
    }

    public void setNumeroAssi(int numeroAssi) {
        if (numeroAssi <= 0)
            throw new IllegalArgumentException("Numero assi non valido");
        this.numeroAssi = numeroAssi;
    }

    public abstract double calcolaPedaggio();

    public abstract String getAttributoExtra();

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("[%s] Targa: %s | Velocità: %.1f km/h | Assi: %d | %s | Pedaggio: €%.2f", //restituisce i dati formattati (l'ho cercato)
                getTipo(), targa, velocita, numeroAssi, getAttributoExtra(), calcolaPedaggio());
    }

}
