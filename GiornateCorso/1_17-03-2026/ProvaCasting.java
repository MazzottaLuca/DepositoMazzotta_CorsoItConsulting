public class ProvaCasting { 
    public static void main(String[] args) {
        int numerointero = 9;//casting implicito
        double numerodecimale = numerointero;
        System.out.println(numerointero);
        System.out.println("Numero fatto con casting implicito:" + numerodecimale);

        double numerodecimale1 = 9.78d;//casting esplicito
        int numerointero1 = (int) numerodecimale1;
        System.out.println(numerodecimale1);
        System.out.println("Numero fatto con casting esplicito:" + numerointero1);

        /*double numeroinstringa = 9.90d;
        String stringadanumero = (String) numeroinstringa; non si può fare senza il metodo*/
    }
}