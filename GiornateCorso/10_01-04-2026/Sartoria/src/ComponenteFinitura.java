public abstract class ComponenteFinitura {

    private String codice;
    private String nome;
    private String materiale;
    private String colore;
    private double prezzo;

    public ComponenteFinitura(String codice, String nome,
            String materiale, String colore,
            double prezzo) {
        setCodice(codice);
        setNome(nome);
        setMateriale(materiale);
        setColore(colore);
        setPrezzo(prezzo);
    }

    // GETTER
    public String getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getMateriale() {
        return materiale;
    }

    public String getColore() {
        return colore;
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

    public void setMateriale(String materiale) {
        if (materiale == null || materiale.isEmpty())
            throw new IllegalArgumentException("Materiale non valido");
        this.materiale = materiale;
    }

    public void setColore(String colore) {
        if (colore == null || colore.isEmpty())
            throw new IllegalArgumentException("Colore non valido");
        this.colore = colore;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0)
            throw new IllegalArgumentException("Prezzo non valido");
        this.prezzo = prezzo;
    }

    public abstract void mostraDettagli();
}