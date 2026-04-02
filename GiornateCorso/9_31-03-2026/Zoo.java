import java.util.ArrayList;
import java.util.Scanner;

public class Zoo {
    private final ArrayList<Cane> cani = new ArrayList<>();
    private final ArrayList<Gatto> gatti = new ArrayList<>();
    private final ArrayList<Cavallo> cavalli = new ArrayList<>();

    public void inserisciAnimali() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Scrivi il numero di cani da inserire");
        int numerocani = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numerocani; i++) {
            System.out.println("Nome cane: ");
            String nome = scanner.nextLine();

            System.out.println("Età cane: ");
            int eta = scanner.nextInt();
            scanner.nextLine();
            cani.add(new Cane(nome, eta));
        }
        System.out.println("Scrivi il numero di gatti da inserire");
        int numerogatti = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numerogatti; i++) {
            System.out.println("Nome gatto: ");
            String nome = scanner.nextLine();

            System.out.println("Età gatto: ");
            int eta = scanner.nextInt();
            scanner.nextLine();

            gatti.add(new Gatto(nome, eta));
        }
        System.out.println("Scrivi il numero di cavalli da inserire");
        int numerocavalli = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numerocavalli; i++) {
            System.out.println("Nome cavallo: ");
            String nome = scanner.nextLine();

            System.out.println("Età cavallo: ");
            int eta = scanner.nextInt();
            scanner.nextLine(); 

            cavalli.add(new Cavallo(nome, eta));
        }
    }

    public void stampaZoo() {
        System.out.println("-----------");
        System.out.println("Animale, Eta, Verso");
        System.out.println("Cani: ");
        for (Cane c : cani) {
            System.out.println(c.getNome() + " anni:" + c.getEta());
            c.verso();
        }
        System.out.println("Gatti: ");
        for (Gatto g : gatti) {
            System.out.println(g.getNome() + " anni:" + g.getEta());
            g.verso();
        }
        System.out.println("Cavalli: ");
        for (Cavallo ca : cavalli) {
            System.out.println(ca.getNome() + " anni:" + ca.getEta());
            ca.verso();
        }
        
    }
}
