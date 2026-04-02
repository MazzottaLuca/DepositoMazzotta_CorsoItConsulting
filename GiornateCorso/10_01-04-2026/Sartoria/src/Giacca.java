public class Giacca extends CapoPrincipale {

    private int numeroBottoni;

    public Giacca(String codice, String nome, String tessuto,
            String colore, String taglia, double prezzo,
            int numeroBottoni) {
        super(codice, nome, tessuto, colore, taglia, prezzo);
        setNumeroBottoni(numeroBottoni);
    }

    public int getNumeroBottoni() {
        return numeroBottoni;
    }

    public void setNumeroBottoni(int numeroBottoni) {
        if (numeroBottoni <= 0)
            throw new IllegalArgumentException("Bottoni non validi");
        this.numeroBottoni = numeroBottoni;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Giacca: " + getNome() +
                " | Bottoni: " + numeroBottoni);
    }
}