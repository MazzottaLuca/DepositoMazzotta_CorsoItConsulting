public class Team {
    String nome;
    int numeroSviluppatori;
    Gioco gioco;
    Sviluppatore[] sviluppatori; // array
    int count = 0; // numero effettivo di sviluppatori nell'array

    public Team(String nome) { 
        this.nome = nome;
        this.numeroSviluppatori = 0; // parte da zero
        sviluppatori = new Sviluppatore[100]; // dimensione massima
    }

    public void assegnaGioco(Gioco gioco) {
        this.gioco = gioco;
    }

    // aggiunge sviluppatore con nome
    public void aggiungiSviluppatore(String nome) {
        if (count < sviluppatori.length) {
            sviluppatori[count] = new Sviluppatore(nome);
            count++;
            numeroSviluppatori++;
        } else {
            System.out.println("Array pieno!");
        }
    }

    public String toString() {
        String nomeGioco = (gioco != null) ? gioco.titolo : "Nessun gioco";

        String lista = "";
        for (int i = 0; i < count; i++) {
            lista += sviluppatori[i] + " ";
        }

        return "Team: " + nome +
                ", Numero Sviluppatori: " + numeroSviluppatori +
                ", Lista: " + lista +
                ", Gioco: " + nomeGioco;
    }
}