import java.util.*;

public class GestioneScuola {

    private List<Studente> studenti = new ArrayList<>();
    private List<Docente> docenti = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // metodo per creare uno studente specifico aggiungendolo alla lista studenti
    public void creaStudenteSpecifico() {
        System.out.println("Inserisci nome studente: ");
        String nome = scanner.nextLine();
        System.out.println("Inserisci età studente: ");
        int eta = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci la classe frequentata dallo studente: ");
        String classe = scanner.nextLine();
        Studente s = new Studente(nome, eta, classe); // passo i valori nome eta e classe e creo una nuova variabile di
                                                      // tipo Studente
        s.registrazione();
        s.descriviRuolo();
        studenti.add(s); // aggiungo studenti alla lista
        System.out.println("studente aggiunto");
    }

    // metodo per creare un docente specifico aggiungendolo alla lista docenti
    public void creaDocenteSpecifico() {
        System.out.println("Inserisci nome docente: ");
        String nome = scanner.nextLine();
        System.out.println("Inserisci età docente: ");
        int eta = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci Materia: ");
        String materia = scanner.nextLine();
        Docente d = new Docente(nome, eta, materia); // passo i valori nome eta e materia e creo una nuova variabile di
                                                     // tipo Docente
        d.registrazione();
        d.descriviRuolo();
        docenti.add(d); // aggiungo docenti alla lista
        System.out.println("docente aggiunto");
    }

    // metodo per assegnare un voto allo studente
    public void assegnaVotoAStudente() {
        if (docenti.isEmpty() || studenti.isEmpty()) { // condizione per verificare che ci sia almeno uno studente e
                                                       // almeno un docente per poter assegnare un voto
            System.out.println("Servono almeno un docente e uno studente.");
            return;
        }
        System.out.println("Scegli docente:");
        // ciclo for per trovare i docenti dal nome
        for (int i = 0; i < docenti.size(); i++)
            System.out.println(i + " - " + docenti.get(i).getNome());
        int idDoc = Integer.parseInt(scanner.nextLine());
        Docente docente = docenti.get(idDoc);
        //assegna studente a docente
        System.out.println("Scegli studente da assegnare al docente:");
        for (int i = 0; i < studenti.size(); i++)
            System.out.println(i + " - " + studenti.get(i).getNome());
        int idStu = Integer.parseInt(scanner.nextLine());
        Studente studente = studenti.get(idStu);

        //condizione per verificare se lo studente non sia già stato assegnato al docente
        if (!docente.getStudentiSpecifici().contains(studente)) {
            docente.aggiungiStudente(studente); //assegna studente a docente
        }
        // ora permette l'assegnazione del voto
        System.out.print("Voto da assegnare: ");
        int voto = Integer.parseInt(scanner.nextLine());
        docente.assegnaVoto(studente, voto);

    }
    public void stampaVotiStudente(){
        //condizione per verificare che la lista di studenti non sia vuota
        if (studenti.isEmpty()){
            System.out.println("non ci sono studenti cui assegnare il voto");
            return;
        }
        System.out.println("scegli studente");
        for (int i = 0; i < studenti.size(); i++) //ciclo for per prendere dalla lista lo studente
            System.out.println(i + " - " + studenti.get(i).getNome()); //e ottenere il nome
        int idx = Integer.parseInt(scanner.nextLine()); //dal nome scritto nello scanner trovare l'id
        studenti.get(idx).stampaVoti(); //stampa voti studente
    }

    public static void main(String[] args) {
    GestioneScuola gs = new GestioneScuola();

    String scelta;

    do {
        System.out.println("\n=== GESTIONE SCUOLA ===");
        System.out.println("1. Crea studente");
        System.out.println("2. Crea docente");
        System.out.println("3. Assegna voto a studente");
        System.out.println("4. Stampa voti di uno studente");
        System.out.println("0. Esci");
        System.out.print("Scelta: ");

        scelta = gs.scanner.nextLine(); // usa quello della classe

        switch (scelta) {
            case "1" -> gs.creaStudenteSpecifico();
            case "2" -> gs.creaDocenteSpecifico();
            case "3" -> gs.assegnaVotoAStudente();
            case "4" -> gs.stampaVotiStudente();
            case "0" -> System.out.println("Arrivederci!");
            default  -> System.out.println("Scelta non valida.");
        }

    } while (!scelta.equals("0"));
}
}
