import java.util.Scanner;
public class Esercizio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserire importo");
        String importototale = scanner.nextLine();
        System.out.println("inserire codice sconto");
        String codicesconto = scanner.nextLine();
        Double importonumero = Double.parseDouble(importototale);
        if (codicesconto.equals("SCONTO10")){
            importonumero = importonumero - importonumero* 10 /100;
        }
        else if (codicesconto.equals( "SCONTO20")){
            importonumero = importonumero - (importonumero* 20 /100);
            System.out.println("codice valido");
        }
        else if (codicesconto.equals( "VIP")){
            if(importonumero > 100){
                importonumero = importonumero - (importonumero* 30/100);
                System.out.println("codice valido");
            }
        }
        else System.out.println("codice non valido");
        scanner.close();
    }
}
