public class Prodotto {
    protected String nome;
    protected int disponibilita;

    public Prodotto(String nome, int disponibilita) {
        this.nome = nome;
        this.disponibilita = disponibilita;
    }

    public String getNome() {
        return nome;
    }

    public int getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(int disponibilita) {
        this.disponibilita = disponibilita;
    }

    public void prepara(int quantita) {
        System.out.println("Preparazione " + quantita + " x " + nome);
    }
}