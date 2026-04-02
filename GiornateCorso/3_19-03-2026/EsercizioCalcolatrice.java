import java.util.Scanner;

public class EsercizioCalcolatrice {
    public double numero1(Scanner inserisci) {
        System.out.println("inserisci primo numero");
        return inserisci.nextDouble();
    }

    public double numero2(Scanner inserisci) {
        System.out.println("inserisci secondo numero");
        return inserisci.nextDouble();
    }

    double somma(double a, double b) {
        return a + b;
    }

    double differenza(double a, double b) {
        return a - b;
    }

    double prodotto(double a, double b) {
        return a * b;
    }

    double quoziente(double a, double b) {
        return a / b;
    }

    double potenza(double a, double b) {
        return Math.pow(a, b);
    }

    int sommaMultipla(Scanner inserisci) {
        System.out.print("Quanti numeri vuoi sommare? ");
        int quanti = inserisci.nextInt();

        int somma = 0;
        for (int i = 1; i <= quanti; i++) {
            System.out.print("Inserisci numero " + i + ": ");
            somma += inserisci.nextInt();
        }

        return somma;
    }

    public static void main(String[] args) {
        Scanner inserisci = new Scanner(System.in);
        EsercizioCalcolatrice e = new EsercizioCalcolatrice();
        boolean restanelprogramma = true;
        while (restanelprogramma) {
            System.out.println("scegli che operazione vuoi fare:");
            System.out.println("1 - addizione");
            System.out.println("2 - sottrazione");
            System.out.println("3 - moltiplicazione");
            System.out.println("4 - divisione");
            System.out.println("5 - elevamento a potenza");
            System.out.println("6 - somma multipla");
            System.out.println("7 - esci");
            int scelta = inserisci.nextInt();
            switch (scelta) {
                case 1: {
                    double n1 = e.numero1(inserisci);
                    double n2 = e.numero2(inserisci);
                    System.out.println(e.somma(n1, n2));
                    break;
                }
                case 2: {
                    double n1 = e.numero1(inserisci);
                    double n2 = e.numero2(inserisci);
                    System.out.println(e.differenza(n1, n2));
                    break;
                }
                case 3: {
                    double n1 = e.numero1(inserisci);
                    double n2 = e.numero2(inserisci);
                    System.out.println(e.prodotto(n1, n2));
                    break;
                }
                case 4: {
                    double n1 = e.numero1(inserisci);
                    double n2 = e.numero2(inserisci);
                    if (n2 == 0) {
                        System.out.println("Non si può dividere per zero"); // ho fatto così invece di usare le
                                                                            // eccezioni
                    } else
                        System.out.println(e.quoziente(n1, n2));
                    break;
                }
                case 5: {
                    double n1 = e.numero1(inserisci);
                    double n2 = e.numero2(inserisci);
                    System.out.println(e.potenza(n1, n2));
                    break;
                }
                case 6:
                    System.out.println(e.sommaMultipla(inserisci));
                    break;
                case 7:
                    System.out.println("Sei uscito");
                    restanelprogramma = false;
                    break;
                default:
                    System.out.println("Comando non valido.");
            }
        }
    }
}
