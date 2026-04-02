import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBConn.inizializza();

        List<Veicolo> veicoli = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean esci = false;

        System.out.println("=== SISTEMA GESTIONE AUTOSTRADALE ===");

        while (!esci) {
            System.out.println("\n1. Aggiungi Auto");
            System.out.println("2. Aggiungi Camion");
            System.out.println("3. Aggiungi Moto");
            System.out.println("4. Mostra tutti i veicoli");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");

            int scelta = sc.nextInt();
            sc.nextLine();

            switch (scelta) {
                case 1 -> {
                    System.out.print("Targa: ");
                    String targa = sc.nextLine();
                    System.out.print("Velocità: ");
                    double vel = sc.nextDouble();
                    System.out.print("Numero assi: ");
                    int assi = sc.nextInt();
                    System.out.print("Cilindrata (cc): ");
                    double cil = sc.nextDouble();
                    sc.nextLine();
                    Auto a = new Auto(targa, vel, assi, cil);
                    veicoli.add(a);
                    a.salvaDB(); // niente cast
                }
                case 2 -> {
                    System.out.print("Targa: ");
                    String targa = sc.nextLine();
                    System.out.print("Velocità: ");
                    double vel = sc.nextDouble();
                    System.out.print("Numero assi: ");
                    int assi = sc.nextInt();
                    System.out.print("Peso (tonnellate): ");
                    double peso = sc.nextDouble();
                    sc.nextLine();
                    Camion c = new Camion(targa, vel, assi, peso);
                    veicoli.add(c);
                    c.salvaDB(); // niente cast
                }
                case 3 -> {
                    System.out.print("Targa: ");
                    String targa = sc.nextLine();
                    System.out.print("Velocità: ");
                    double vel = sc.nextDouble();
                    System.out.print("Numero assi: ");
                    int assi = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo moto: ");
                    String tipo = sc.nextLine();
                    Moto m = new Moto(targa, vel, assi, tipo);
                    veicoli.add(m);
                    m.salvaDB(); // niente cast
                }
                case 4 -> DBSetup.stampaTuttiDalDB();
                case 5 ->
                    esci = true;
                default -> System.out.println("Scelta non valida.");
            }
        }
        sc.close();
        System.out.println("Arrivederci!");
    }
}