public class Cane extends Animale {
    public Cane(String nome, int eta) {
        super(nome, eta);
    }

    @Override
    public void verso(){
        System.out.println("Bau bau");
    }
}
