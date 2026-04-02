public class Papillon extends ComponenteFinitura {

    private String tipoChiusura;

    public Papillon(String codice, String nome, String materiale,
            String colore, double prezzo, String tipoChiusura) {
        super(codice, nome, materiale, colore, prezzo);
        setTipoChiusura(tipoChiusura);
    }

    public String getTipoChiusura() {
        return tipoChiusura;
    }

    public void setTipoChiusura(String tipoChiusura) {
        if (tipoChiusura == null || tipoChiusura.isEmpty())
            throw new IllegalArgumentException("Chiusura non valida");
        this.tipoChiusura = tipoChiusura;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Papillon: " + getNome() +
                " | Chiusura: " + tipoChiusura);
    }
}