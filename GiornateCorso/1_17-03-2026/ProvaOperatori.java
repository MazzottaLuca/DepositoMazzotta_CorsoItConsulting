public class ProvaOperatori {
    public static void main (String[] args){
        int numero = 10;
        System.out.println(numero);
        numero +=1;
        System.out.println(numero);
        numero -=5;
        System.out.println(numero);
        numero *=2;
        System.out.println(numero);
        numero /=4;
        System.out.println("numero finale " + numero);
        if (numero % 2 == 0) System.out.println("pari");
        else System.out.println("dispari");

        int numero1 = 10;
        System.out.println(numero1++); //prima stampa e poi incrementa quindi stampa 10 e poi incrementa 11
        System.out.println(++numero1); //prima incrementa e poi stampa quindi era diventato 11 e con l'incremento diventa 12

        /*operatori di confronto
        == uguale a (singolo uguale è assegnazione)
        != diverso
        > maggiore
        < minore
        >= maggiore o uguale
        <= minore o uguale */

        /*operatori logici
        && AND
        || OR
        ! NOT */
        System.out.println(numero < 10 && numero > 1);
        System.out.println(numero < 10 || numero%2 == 0);
        System.out.println(!(numero < 10));
        //stampa il risultato booleano, se la condizione è vera stampa true, altrimenti false
    }
    
}
