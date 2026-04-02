public class Cavallo extends Animale {
    public Cavallo(String nome, int eta) {
        super(nome, eta);
    }

    @Override
    public void verso(){
        System.out.println("Iiiiiih");
    }
}
