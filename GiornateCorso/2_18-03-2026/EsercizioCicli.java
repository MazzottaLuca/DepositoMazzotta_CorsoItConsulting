import java.util.Scanner;
public class EsercizioCicli {
    public static void main(String[] args) {
        
        int tentativi= 0; //inizializzo il numero di tentativi a 0
        String passwordcorretta = "java123";
        System.out.println("inserire password");
        Scanner scanner = new Scanner(System.in);
        while (tentativi <3)
        {
            String password = scanner.nextLine();
            if (!password.equals(passwordcorretta)){
                System.out.println("password errata, riprova"); //finchè i tentativi sono meno di 3 resta nel primo ciclo
                tentativi++;
                if (tentativi == 3){System.out.println("hai terminato i tentativi, accesso bloccato"); //esce direttamente quando sbagi tre volte
                    break;
                }
            } else { //password corretta, richiesta di accesso
                System.out.println("Vuoi accedere al sistema? s/n");
                String conferma = scanner.nextLine();;
                
                if (conferma.equals("s")){
                    System.out.println("Accesso al sistema effettuato"); //con s accede al sistema, poi ho messo break perchè dopo non si fa nulla
                    break;
                } else if (conferma.equals("n")){
                    System.out.println("Accesso al sistema annullato"); //con n annulla accesso e fa break perchè esce dal sistema
                    break;
                } else {
                    System.out.println("Comando non valido, riaccedi con la password"); //se non fai il comando s o n richiede l'accesso, se metto break si blocca
                    
                }
                
            }
            
        } scanner.close();
    }
}
