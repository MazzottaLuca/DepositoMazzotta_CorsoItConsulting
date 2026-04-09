public class Main {
    public static void main(String[] args) {

        // Messaggio base
        Messaggio messaggio = new MessaggioBase("Ciao, questo messaggio viene convertito in maiuscolo!");

        // Decorazione in maiuscolo
        Messaggio messaggioMaiuscolo = new DecoratoreMaiuscolo(messaggio);

        // Output
        System.out.println("Messaggio originale: " + messaggio.getContenuto());
        System.out.println("Messaggio decorato: " + messaggioMaiuscolo.getContenuto());
    }
}