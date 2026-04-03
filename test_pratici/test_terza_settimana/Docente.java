
import java.util.ArrayList;
import java.util.List;

class Docente extends Persona implements Registrabile {
    private String materia;
    private List <Studente> studentiSpecifici = new ArrayList<>();

    public Docente(String nome, int eta, String materia) {
        super(eta, nome); // prende i dati da persona
        this.materia = materia; //aggiungo l'attributo mancante
    }
    //getter e setter per materia

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @Override
    public void descriviRuolo() {
        System.out.println("Sono un docente di " + materia);
    }

    @Override
    public void registrazione() {
        System.out.println("Registrazione tramite segreteria didattica");
    }

    public void aggiungiStudente(Studente studente) { studentiSpecifici.add(studente); }

    // Solo questo docente può vedere la sua lista studenti
    public List<Studente> getStudentiSpecifici() { return studentiSpecifici; }

    public void assegnaVoto(Studente studente, int voto) {
        if (studentiSpecifici.contains(studente)) {
            studente.aggiungiVoto(voto);
            System.out.println("Voto " + voto + " assegnato a " + studente.getNome());
        } else {
            System.out.println("Errore: " + studente.getNome() + " non è nella lista di " + getNome());
        }
    }

}
