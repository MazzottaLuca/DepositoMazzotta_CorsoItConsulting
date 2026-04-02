public class Main {
    public static void main(String[] args) {

        // Creazione dei due oggetti
        Libro libro1 = new Libro("libro uno", "autore uno", 12.5, 1);
        Libro libro2 = new Libro("libro due", "autore due", 10.0, 2);

        // Stampa la descrizione 
        System.out.println(libro1.descrizione());
        System.out.println(libro2.descrizione()); 

        //creazione oggetto persona
        Persona persona1 = new Persona("Luca", 26, "Lecce");
        Persona persona2 = new Persona("Marco", 30, "Milano");

        //Stampa i dettagli
        System.out.println(persona1.dettagli());
        System.out.println(persona2.dettagli());

    }
}
