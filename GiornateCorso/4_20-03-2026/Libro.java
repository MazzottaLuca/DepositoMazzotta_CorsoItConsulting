class Libro {
    String titolo; 
    String autore;
    double prezzo;
    int id;
    public Libro(String titolo, String autore, double prezzo, int id) { //genero il costruttore in automatico (tasto destro source actions generate constructors)
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
        this.id = id;
    }
     // Metodo descrizione
    public String descrizione() {
        return titolo + " di " + autore + ", prezzo: " + prezzo + "€, ID: " + id;
    }
    
}
