public class Pirata extends Persona {

    public Pirata(String nome, String cognome, int eta, String sesso) {
        super(nome, cognome, eta, sesso, "pirata");
    }

    @Override
    public void saluta() {
        System.out.println("Sono " + nome + " " + cognome +
                ", ho " + eta + " anni e sono un " + mestiere);
    }
}