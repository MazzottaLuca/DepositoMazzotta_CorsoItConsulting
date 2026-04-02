import java.util.Scanner;

public class Esercizio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nome = "";
        int numero = 0;

        do { //ho fatto il ciclo do while per rimanere dentro ai blocchi switch senza che ogni volta che clicco il numero il programma si blocca
            System.out.println("\nSelezionare:");
            System.out.println("1 - Visualizza profilo");
            System.out.println("2 - Modifica nome");
            System.out.println("3 - Logout");

            numero = scanner.nextInt();
            scanner.nextLine(); 

            switch (numero) {
                case 1:
                    if (nome.equals("")) {
                        System.out.println("Profilo vuoto, nessun nome inserito");
                    } else {
                        System.out.println("Profilo utente visualizzato, nome: " + nome);
                    }
                    break;

                case 2:
                    System.out.println("Inserire nome:");
                    nome = scanner.nextLine();
                    System.out.println("Nome aggiornato!");
                    break;

                case 3:
                    System.out.println("Logout effettuato");
                    break;

                default:
                    System.out.println("Scelta non valida");
            }

        } while (numero != 3);

        scanner.close();
    }
}