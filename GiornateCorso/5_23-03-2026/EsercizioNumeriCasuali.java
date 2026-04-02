import java.util.Scanner;
import java.io.Console;

public class EsercizioNumeriCasuali {
    public static void main(String[] args) {
        Console console = System.console(); // il numero sgreto non è visibile a schermo perchè è stampato in console

        if (console == null) {
            System.out.println("Console non disponibile!");
            return;
        }
        System.out.println("Inserisci numero segreto:");
        String input = new String(console.readPassword()); // input invisibile
        int numerosegreto = Integer.parseInt(input);

        System.out.println("Numero segreto impostato!");
        int tentativi = 5;
        boolean indovinato = false;
        while (tentativi > 0 && !indovinato) {
            System.out.println("indovina il numero segreto");
            Scanner scanner1 = new Scanner(System.in);
            String tentativos = scanner1.nextLine();
            if (tentativos.equalsIgnoreCase("mi arrendo")) {
                System.out.println("Ti sei arreso! Il numero era: " + numerosegreto);
                return;
            }
            int tentativo = Integer.parseInt(tentativos);

            if (tentativo == numerosegreto) {
                System.out.println("Complimenti! Hai indovinato!");
                indovinato = true;
            } else if (tentativo > numerosegreto) {
                System.out.println("Troppo alto!"); // se il numero inserito è più alto
                tentativi--; // abbassa di numero finchè non terminano i tentativi
                System.out.println("Tentativi rimasti: " + tentativi);
            } else {
                System.out.println("Troppo basso!"); // se il numero inserito è più basso
                tentativi--; // abbassa di numero finchè non terminano i tentativi
                System.out.println("Tentativi rimasti: " + tentativi);
            }

        }

        if (!indovinato) {
            System.out.println("Hai finito i tentativi! Il numero era: " + numerosegreto);
        }

    }
}
