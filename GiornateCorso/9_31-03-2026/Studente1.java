public class Studente1 {
    private String nome;
    private int eta;

    public Studente1(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }

    @Override
    public String toString() {
        return "Studente: " + nome + ", Età: " + eta;
    }

    // Metodo main per testare
    public static void main(String[] args) {
        Studente1 s1 = new Studente1("Luca", 20);
        System.out.println(s1); // Chiama automaticamente toString()
    }
}
