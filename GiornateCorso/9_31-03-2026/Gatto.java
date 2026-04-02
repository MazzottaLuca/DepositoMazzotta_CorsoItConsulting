public class Gatto extends Animale {
    public Gatto(String nome, int eta) {
        super(nome, eta);
    }

    @Override
    public void verso(){
        System.out.println("Miao!");
    }
}