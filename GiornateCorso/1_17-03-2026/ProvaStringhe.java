import java.util.Arrays;
public class ProvaStringhe {
    public static void main(String[] args) {
        String saluto = "Hello";
        System.out.println("Lunghezza stringa: " + saluto.length());
        System.out.println(saluto.charAt(0));//stampa il carattere alla posizione 0
        System.out.println(saluto.toUpperCase());//stampa tutto maiuscolo
        System.out.println(saluto.indexOf("e"));//stampa il posizionamento del carattere nella stringa, il contrario di charat
        String destinatario = "World";
        System.out.println(saluto + " " + destinatario + "!");
        System.out.println(saluto.concat(destinatario));//concat mette stringhe concatenate senza gli spazi, vanno messi manualmente
        /*int numero1 = 10;
        int numero2 = 20;
        System.out.println(numero1.concat(numero2)); non si può fare coi numeri*/
        String testo = "We are the so called \"Vikings\" from the north "; //si mette backslash per virgolettati
        System.out.println(testo);
        // \n nuova riga, \r sali di riga, \t tab, \b backspace, \f cambia pagina (avanzamento modulo)
        String str = "Hello World Ciao Mondo"; 
        String [] words = str.split("\\s"); //crei un array che prende le parole presenti in quella stringa semplicemente dividendo le stringhe in base agli spazi 
        System.out.println(Arrays.toString(words));
    }
    
}
