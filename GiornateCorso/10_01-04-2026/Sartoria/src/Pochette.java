public class Pochette extends ComponenteFinitura {

    private String piegaDecorativa;

    public Pochette(String codice, String nome, String materiale,
            String colore, double prezzo, String piegaDecorativa) {
        super(codice, nome, materiale, colore, prezzo);
        setPiegaDecorativa(piegaDecorativa);
    }

    public String getPiegaDecorativa() {
        return piegaDecorativa;
    }

    public void setPiegaDecorativa(String piegaDecorativa) {
        if (piegaDecorativa == null || piegaDecorativa.isEmpty())
            throw new IllegalArgumentException("Piega non valida");
        this.piegaDecorativa = piegaDecorativa;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Pochette: " + getNome() +
                " | Piega: " + piegaDecorativa);
    }
}