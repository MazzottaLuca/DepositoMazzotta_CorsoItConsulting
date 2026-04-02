import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // creo i giochi
        Gioco g1 = new Gioco("Assassin's Creed", "Stealth", 50000, "in sviluppo");
        Gioco g2 = new Gioco("God of War", "Action", 30000, "in test");
        Gioco g3 = new Gioco("Persona 5", "RPG", 80000, "pubblicato");

        // creo i team
        Team team1 = new Team("Team");
        Team team2 = new Team("Tram");
        Team team3 = new Team("Trump");

        int scelta;

        do { // do while per il ciclo finchè la scelta è 0
            System.out.println("\nMENU:");
            System.out.println("1. Assegna gioco a team");
            System.out.println("2. Visualizza team");
            System.out.println("3. Gioco con costo più alto");
            System.out.println("4. Modifica stato gioco");
            System.out.println("5. Aggiungi sviluppatori a team");
            System.out.println("0. Esci");

            scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    System.out.println("Scegli team (1- Team 2- Tram 3- Trump):");
                    int t = scanner.nextInt();
                    System.out.println("Scegli gioco (1- Assassin's Creed 2- God of War 3- Persona 5):");
                    int g = scanner.nextInt();

                    Gioco giocoScelto = (g == 1) ? g1 : (g == 2) ? g2 : g3;

                    if (t == 1)
                        team1.assegnaGioco(giocoScelto);
                    else if (t == 2)
                        team2.assegnaGioco(giocoScelto);
                    else if (t == 3)
                        team3.assegnaGioco(giocoScelto);
                    break;

                case 2:
                    // visualizza tutti i team compreso il numero di sviluppatori
                    System.out.println(team1);
                    System.out.println(team2);
                    System.out.println(team3);
                    break;

                case 3:
                    Gioco max = g1;
                    if (g2.costoSviluppo > max.costoSviluppo)
                        max = g2;
                    if (g3.costoSviluppo > max.costoSviluppo)
                        max = g3;
                    System.out.println("Gioco più costoso: " + max);
                    break;

                case 4:
                    System.out.println("Scegli gioco (1-3):");
                    int gm = scanner.nextInt();
                    scanner.nextLine();
                    if (gm != 1 || gm != 2 || gm != 3) {
                        System.out.println("scelta errata");
                        break;
                    }
                    System.out.println("Nuovo stato:");
                    String stato = scanner.nextLine();

                    if (gm == 1)
                        g1.setStato(stato);
                    else if (gm == 2)
                        g2.setStato(stato);
                    else if (gm == 3)
                        g3.setStato(stato);
                    break;
                case 5:
                    System.out.println("Scegli team (1-3):");
                    int teamScelto = scanner.nextInt();
                    scanner.nextLine(); // pulisce il buffer

                    System.out.println("Quanti sviluppatori vuoi aggiungere?");
                    int n = scanner.nextInt();
                    scanner.nextLine(); // pulisce il buffer

                    for (int i = 0; i < n; i++) {
                        System.out.println("Inserisci il nome dello sviluppatore " + (i + 1) + ":");
                        String nomeSviluppatore = scanner.nextLine();

                        if (teamScelto == 1)
                            team1.aggiungiSviluppatore(nomeSviluppatore);
                        else if (teamScelto == 2)
                            team2.aggiungiSviluppatore(nomeSviluppatore);
                        else if (teamScelto == 3)
                            team3.aggiungiSviluppatore(nomeSviluppatore);
                        else
                            System.out.println("Team non valido!");
                    }
                    break;
            }
        } while (scelta != 0);

        scanner.close();
    }
}