public abstract class Hamburger {
    protected String nome;
    protected int disponibilita;

    public Hamburger(String nome, int disponibilita) {
        this.nome = nome;
        this.disponibilita = disponibilita;
    }

    public String getNome() { return nome; }
    public int getDisponibilita() { return disponibilita; }
    public void setDisponibilita(int disponibilita) { this.disponibilita = disponibilita; }

    public abstract void prepara(int quantita);
}

class Cheeseburger extends Hamburger {
    public Cheeseburger(int disponibilita) { super("Cheeseburger", disponibilita); }
    @Override
    public void prepara(int quantita) {
        System.out.println("Preparazione " + quantita + " x Cheeseburger: pane, carne, formaggio, ketchup");
    }
}

class VegBurger extends Hamburger {
    public VegBurger(int disponibilita) { super("VegBurger", disponibilita); }
    @Override
    public void prepara(int quantita) {
        System.out.println("Preparazione " + quantita + " x VegBurger: pane integrale, burger vegetale, insalata, pomodoro");
    }
}

class DoubleBacon extends Hamburger {
    public DoubleBacon(int disponibilita) { super("DoubleBacon", disponibilita); }
    @Override
    public void prepara(int quantita) {
        System.out.println("Preparazione " + quantita + " x DoubleBacon: pane, doppia carne, bacon, cheddar, maionese");
    }
}