public abstract class CapoPrincipale {

    private String codice;
    private String nome;
    private String tessuto;
    private String colore;
    private String taglia;
    private double prezzo;

    public CapoPrincipale(String codice, String nome, String tessuto,
            String colore, String taglia, double prezzo) {
        setCodice(codice);
        setNome(nome);
        setTessuto(tessuto);
        setColore(colore);
        setTaglia(taglia);
        setPrezzo(prezzo);
    }

    // GETTER
    public String getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getTessuto() {
        return tessuto;
    }

    public String getColore() {
        return colore;
    }

    public String getTaglia() {
        return taglia;
    }

    public double getPrezzo() {
        return prezzo;
    }

    // SETTER CON CONTROLLI
    public void setCodice(String codice) {
        if (codice == null || codice.isEmpty())
            throw new IllegalArgumentException("Codice non valido");
        this.codice = codice;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome non valido");
        this.nome = nome;
    }

    public void setTessuto(String tessuto) {
        if (tessuto == null || tessuto.isEmpty())
            throw new IllegalArgumentException("Tessuto non valido");
        this.tessuto = tessuto;
    }

    public void setColore(String colore) {
        if (colore == null || colore.isEmpty())
            throw new IllegalArgumentException("Colore non valido");
        this.colore = colore;
    }

    public void setTaglia(String taglia) {
        if (taglia == null || taglia.isEmpty())
            throw new IllegalArgumentException("Taglia non valida");
        this.taglia = taglia;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0)
            throw new IllegalArgumentException("Prezzo deve essere > 0");
        this.prezzo = prezzo;
    }

    // POLIMORFISMO
    public abstract void mostraDettagli();
}