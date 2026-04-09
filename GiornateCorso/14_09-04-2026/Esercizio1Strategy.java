import java.util.*;

public class Esercizio1Strategy {

    public interface Operazione {
        int esegui(int a, int b);
    }

    public static class Addizione implements Operazione {
        @Override
        public int esegui(int a, int b) {
            return a + b;
        }
    }

    public static class Moltiplicazione implements Operazione {
        @Override
        public int esegui(int a, int b) {
            return a * b;
        }
    }

    public static class Calcolatore {
        private Operazione operazione;

        public void setOperazione(Operazione operazione) {
            this.operazione = operazione;
        }

        public int calcola(int a, int b) {
            if (operazione == null) {
                throw new IllegalStateException("Nessuna operazione impostata");
            }
            return operazione.esegui(a, b);
        }
    }

    public static void main(String[] args) {
        Calcolatore calc = new Calcolatore();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci a: ");
        int a = scanner.nextInt();
        System.out.print("Inserisci b: ");
        int b = scanner.nextInt();

        calc.setOperazione(new Addizione());
        System.out.println("Addizione = " + calc.calcola(a, b)); 

        calc.setOperazione(new Moltiplicazione());
        System.out.println("Moltiplicazione = " + calc.calcola(a, b)); 

        scanner.close();
    }
}