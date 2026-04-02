class Persona {
    String nome;
    int eta;
    String citta;
    public Persona(String nome, int eta, String citta) { //genero il costruttore in automatico 
        this.nome = nome;
        this.eta = eta;
        this.citta = citta;
    }
    //metodo dettagli
    public String dettagli(){
        return "nome: " + nome + " età: " + eta + " città: " + citta;
    }
}
