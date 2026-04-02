package scuola;
import java.util.Scanner;

public class Menu {

    ClasseDAO classeDAO = new ClasseDAO();
    StudenteDAO studenteDAO = new StudenteDAO();
    VotoDAO votoDAO = new VotoDAO();

    public void start() {
        Scanner sc = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Inserisci Classe");
            System.out.println("2. Inserisci Studente");
            System.out.println("3. Inserisci Voto");
            System.out.println("4. Modifica Voto");
            System.out.println("0. Esci");

            scelta = sc.nextInt();
            sc.nextLine();

            switch (scelta) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Anno: ");
                    String anno = sc.nextLine();
                    System.out.print("Sezione: ");
                    String sezione = sc.nextLine();
                    System.out.print("Capienza: ");
                    int cap = sc.nextInt();

                    classeDAO.inserisciClasse(nome, anno, sezione, cap);
                    break;

                case 2:
                    System.out.print("Nome: ");
                    String n = sc.nextLine();
                    System.out.print("Cognome: ");
                    String c = sc.nextLine();
                    System.out.print("Data (YYYY-MM-DD): ");
                    String d = sc.nextLine();
                    System.out.print("ID Classe: ");
                    int idc = sc.nextInt();
                    sc.nextLine();
                    System.out.print("CF: ");
                    String cf = sc.nextLine();

                    studenteDAO.inserisciStudente(n, c, d, idc, cf);
                    break;

                case 3:
                    System.out.print("Materia: ");
                    String m = sc.nextLine();
                    System.out.print("Data: ");
                    String dat = sc.nextLine();
                    System.out.print("Voto: ");
                    float v = sc.nextFloat();
                    System.out.print("ID Studente: ");
                    int ids = sc.nextInt();

                    votoDAO.inserisciVoto(m, dat, v, ids);
                    break;

                case 4:
                    System.out.print("ID voto: ");
                    int id = sc.nextInt();
                    System.out.print("Nuovo voto: ");
                    float nv = sc.nextFloat();

                    votoDAO.modificaVoto(id, nv);
                    break;
            }

        } while (scelta != 0);
    }
}