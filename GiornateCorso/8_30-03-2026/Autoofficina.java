import java.util.ArrayList;
import java.util.Scanner;

// Classe Auto
class Auto {
    private String targa;
    private String modello;

    public Auto(String targa, String modello) {
        this.targa = targa;
        this.modello = modello;
    }

    public String getTarga() {
        return targa;
    }

    public String getModello() {
        return modello;
    }
}

// Classe Officina
class Officina {
    private ArrayList<Auto> autoList;

    public Officina() {
        autoList = new ArrayList<>();
    }

    // Metodo per aggiungere un'auto
    public void aggiungiAuto(Auto auto) {
        autoList.add(auto);
        System.out.println("Auto aggiunta: " + auto.getModello() + " (" + auto.getTarga() + ")");
    }

    // Metodo per rimuovere un'auto tramite targa
    public void rimuoviAuto(String targa) {
        boolean rimossa = false;
        for (int i = 0; i < autoList.size(); i++) {
            if (autoList.get(i).getTarga().equalsIgnoreCase(targa)) {
                System.out.println("Rimuovo auto: " + autoList.get(i).getModello() + " (" + autoList.get(i).getTarga() + ")");
                autoList.remove(i);
                rimossa = true;
                break;
            }
        }
        if (!rimossa) {
            System.out.println("Auto con targa " + targa + " non trovata.");
        }
    }

    // Metodo per stampare tutte le auto
    public void stampaAuto() {
        if (autoList.isEmpty()) {
            System.out.println("Nessuna auto in officina.");
        } else {
            System.out.println("Elenco auto in officina:");
            for (Auto a : autoList) {
                System.out.println("Targa: " + a.getTarga() + ", Modello: " + a.getModello());
            }
        }
    }
}

// Classe principale
public class Autoofficina {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Officina officina = new Officina();

        boolean esci = false;

        while (!esci) {
            System.out.println("\n--- Menu Officina ---");
            System.out.println("1. Aggiungi auto");
            System.out.println("2. Rimuovi auto");
            System.out.println("3. Mostra auto");
            System.out.println("4. Esci");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci targa: ");
                    String targa = scanner.nextLine();
                    System.out.print("Inserisci modello: ");
                    String modello = scanner.nextLine();
                    Auto nuovaAuto = new Auto(targa, modello);
                    officina.aggiungiAuto(nuovaAuto);
                    break;
                case 2:
                    System.out.print("Inserisci targa da rimuovere: ");
                    String targaRimuovere = scanner.nextLine();
                    officina.rimuoviAuto(targaRimuovere);
                    break;
                case 3:
                    officina.stampaAuto();
                    break;
                case 4:
                    esci = true;
                    System.out.println("Uscita dall'officina.");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }

        scanner.close();
    }
}