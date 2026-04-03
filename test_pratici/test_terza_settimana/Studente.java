import java.util.ArrayList;
import java.util.List;

class Studente extends Persona implements Registrabile {
    private String classeFrequentata;
    private List<Integer> votiRicevuti = new ArrayList<>();

    public Studente(String nome, int eta, String classeFrequentata) {
        super(eta, nome); // prende i dati da persona
        this.classeFrequentata = classeFrequentata; //aggiungo l'attributo mancante
    }

    public String getClasseFrequentata() {
        return classeFrequentata;
    }

    public void setClasseFrequentata(String classeFrequentata) {
        this.classeFrequentata = classeFrequentata;
    }

    @Override
    public void descriviRuolo() {
        System.out.println("Sono uno studente della classe " + classeFrequentata);
    }

    @Override
    public void registrazione() {
        System.out.println("Registrazione tramite modulo online");
    }

    public void aggiungiVoto(int voto) {
        votiRicevuti.add(voto);
    }

    public void stampaVoti() {
        System.out.println("Voti di " + getNome() + ": " + votiRicevuti);
    }

}
