package fabbrica.produzione;
 
public class Macchina {
    private String nome;
    private boolean accesa;
 
    public Macchina(String nome) {
        this.nome = nome;
        this.accesa = false;
    }
 
    public void accendi() {
        accesa = true;
        System.out.println("Macchina \"" + nome + "\" accesa.");
    }
 
    public void spegni() {
        accesa = false;
        System.out.println("Macchina \"" + nome + "\" spenta.");
    }
 
    public void creaProdotto(String tipoProdotto) {
        if (accesa) {
            System.out.println("Macchina \"" + nome + "\" ha creato: " + tipoProdotto);
        } else {
            System.out.println("Impossibile creare prodotto: la macchina \"" + nome + "\" è spenta.");
        }
    }
 
    public void stampaStato() {
        System.out.println("Macchina: " + nome + " | Stato: " + (accesa ? "ACCESA" : "SPENTA"));
    }
 
    public boolean isAccesa() {
        return accesa;
    }
 
    public String getNome() {
        return nome;
    }
}