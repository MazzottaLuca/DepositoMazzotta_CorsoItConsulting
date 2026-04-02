public class ProvaMatematica {
    public static void main(String[] args) {
        int x = 10;
        int y = 7;
        int a = -6;
        int massimo = Math.max(x, y); // max trova il valore più alto
        int minimo = Math.min(x, y); //min trova il valore più basso
        int assoluto = Math.abs(a); //abs trova il modulo
        double casuale = Math.random(); //random trova il numero random tra 0 e 1
        System.out.println(massimo);
        System.out.println(minimo);
        System.out.println(assoluto);
        System.out.println(casuale);

    }
}
