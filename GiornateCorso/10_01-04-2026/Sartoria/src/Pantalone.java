public class Pantalone extends CapoPrincipale {

    private String tipoTaglio;

    public Pantalone(String codice, String nome, String tessuto,
            String colore, String taglia, double prezzo,
            String tipoTaglio) {
        super(codice, nome, tessuto, colore, taglia, prezzo);
        setTipoTaglio(tipoTaglio);
    }

    public String getTipoTaglio() {
        return tipoTaglio;
    }

    public void setTipoTaglio(String tipoTaglio) {
        if (tipoTaglio == null || tipoTaglio.isEmpty())
            throw new IllegalArgumentException("Taglio non valido");
        this.tipoTaglio = tipoTaglio;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Pantalone: " + getNome() +
                " | Taglio: " + tipoTaglio);
    }
}