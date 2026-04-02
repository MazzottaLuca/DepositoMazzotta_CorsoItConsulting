//creo classe prodotto con costruttore e metodi vendi e stampa

public class Prodotto {
    private String nome;
    private int quantita;
    private double prezzo;

    public Prodotto(String nome, int quantita, double prezzo) {
        this.nome = nome;
        this.quantita = quantita;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void vendi(int q) {
        if (quantita >= q) {
            quantita -= q;
            System.out.println("Vendita effettuata!");
        } else {
            System.out.println("Quantità non disponibile.");
        }
    }

    public void stampa() {
        System.out.println("Prodotto: " + nome);
        System.out.println("Prezzo: " + prezzo);
        System.out.println("Quantità: " + quantita);
    }
} 
    

