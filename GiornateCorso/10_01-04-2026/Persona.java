public class Persona {
    protected String nome;
    protected String cognome;
    protected int eta;
    protected String sesso;
    protected String mestiere;

    public Persona(String nome, String cognome, int eta, String sesso, String mestiere) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.sesso = sesso;
        this.mestiere = mestiere;
    }

    public void saluta() {
        System.out.println("Ciao, sono " + nome + " " + cognome +
                ", ho " + eta + " anni, sono " + sesso +
                " e sono " + mestiere);
    }
}