public class Animale {
    protected String nome;
    protected int eta;
    public Animale(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }
    public void verso(){
        System.out.println("verso di un animale");
    }
    public String getNome() {
        return nome;
    }
    public int getEta() {
        return eta;
    }
    
}
