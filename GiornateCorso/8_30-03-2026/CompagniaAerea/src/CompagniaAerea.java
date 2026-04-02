public class CompagniaAerea {
    private int id;
    private String nome;

    public CompagniaAerea(String nome) {
        this.nome = nome;
    }

    public CompagniaAerea(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}