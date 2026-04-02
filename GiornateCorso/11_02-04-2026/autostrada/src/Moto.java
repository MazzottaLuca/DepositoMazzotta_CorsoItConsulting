public class Moto extends Veicolo implements IGestioneDB {
    private String tipoCarico;

    public Moto(String targa, double velocita, int numeroAssi, String tipoCarico) {
        super(targa, velocita, numeroAssi);
        setTipoCarico(tipoCarico);
    }

    public String getTipoCarico() { return tipoCarico; }
    public void setTipoCarico(String tipoCarico) {
        if (tipoCarico == null || tipoCarico.isBlank()) throw new IllegalArgumentException("Tipo non valido");
        this.tipoCarico = tipoCarico;
    }

    @Override
    public double calcolaPedaggio() {
        return 1.0 + (getNumeroAssi() * 0.5);
    }

    @Override
    public String getAttributoExtra() { return "Tipo: " + tipoCarico; }

    @Override
    public String getTipo() { return "Moto"; }
}