public class Pilota {
    private int id;
    private String nome;
    private String numeroBrevetto;
    private int oreVolo;

    public Pilota(String nome, String numeroBrevetto, int oreVolo) {
        this.nome = nome;                
        this.numeroBrevetto = numeroBrevetto;
        this.oreVolo = oreVolo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroBrevetto() {
        return numeroBrevetto;
    }

    public int getOreVolo() {
        return oreVolo;
    }

    // Setter con controllo, fatto solo perchè non lo mettiamo noi come input ma prendiamo i dati dal db
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Il nome non può essere vuoto o nullo");
        }
    }

    public void setNumeroBrevetto(String numeroBrevetto) {
        if (numeroBrevetto != null && !numeroBrevetto.trim().isEmpty()) {
            this.numeroBrevetto = numeroBrevetto;
        } else {
            System.out.println("Il numero di brevetto non può essere vuoto o nullo");
        }
    }

    public void setOreVolo(int oreVolo) {
        if (oreVolo > 0) {
            this.oreVolo = oreVolo;
        } else {
            System.out.println("Le ore di volo devono essere maggiori di 0");
        }
    }
}