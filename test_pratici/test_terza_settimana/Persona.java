abstract class Persona { //creo classe astratta generica persona da implementare nelle altre classi
    private String nome;
    private int eta;
    //costruttore 
    public Persona(int eta, String nome) {
        this.eta = eta;
        this.nome = nome;
    }
    //getter e setter per accedere alle variabili private fuori dalla classe

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public abstract void descriviRuolo();//metodo che estendo in studenti e docenti
}