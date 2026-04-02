public abstract class Dessert {
    protected String nome;
    protected int disponibilita;

    public Dessert(String nome, int disponibilita) {
        this.nome = nome;
        this.disponibilita = disponibilita;
    }

    public String getNome() { return nome; }
    public int getDisponibilita() { return disponibilita; }
    public void setDisponibilita(int disponibilita) { this.disponibilita = disponibilita; }

    public abstract void prepara(int quantita);
}

class Gelato extends Dessert {
    public Gelato(int disponibilita) { super("Gelato", disponibilita); }
    @Override
    public void prepara(int quantita) {
        System.out.println("Preparazione " + quantita + " x Gelato: coppetta, gelato, topping");
    }
}

class Torta extends Dessert {
    public Torta(int disponibilita) { super("Torta", disponibilita); }
    @Override
    public void prepara(int quantita) {
        System.out.println("Preparazione " + quantita + " x Torta: pan di spagna, crema, decorazioni");
    }
}