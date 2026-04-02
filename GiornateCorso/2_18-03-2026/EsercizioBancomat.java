import java.util.Scanner;

public class EsercizioBancomat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double saldo = 1000.0;
        int scelta;
        do {
            System.out.println(" 1 - visualizza saldo \n 2 - preleva \n 3 - deposita \n 4 - esci");

            scelta = scanner.nextInt();
            switch (scelta) {

                case 1:
                    System.out.println("Saldo attuale: " + saldo);
                    break;
                case 2:
                    System.out.println("quanto vuoi prelevare?");
                    double denarodaprelevare;
                    do {
                        denarodaprelevare = scanner.nextDouble();
                        if (denarodaprelevare > saldo) {
                            System.out.println("Saldo insufficiente, riprova:");
                        }
                    } while (denarodaprelevare >= saldo);
                    saldo -= denarodaprelevare;
                    break;
                case 3:
                    System.out.println("quanto vuoi depositare?");
                    double denarodadepositare = scanner.nextDouble();
                    saldo += denarodadepositare;
                    break;
                case 4:
                    System.out.println("sei uscito, il saldo è " + saldo);
                    break;
                default:
                    System.out.println("scelta non valida");
            }

        } while (scelta != 4);
        scanner.close();
    }
}
