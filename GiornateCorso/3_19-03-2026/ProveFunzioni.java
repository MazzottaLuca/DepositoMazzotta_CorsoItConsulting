public class ProveFunzioni {
    static void saluta() { //creo una funzione che mi restituisce un output fuori dal main
        System.out.println("Ciao");
    }
    static int somma (int a, int b){ //faccio una classe con dei valori
        return a + b; // ritorna una funzione
    }
    static void mostra(int numero) { //overloading, metodo richiamato più volte con parametri diversi
        System.out.println("Numero: " + (10 + numero));// se non metto le parentesi stampa 1010, come stringa
    }
    static void mostra(String testo) {
        System.out.println("Testo " + testo);
    }
    public static void main(String[] args) {
        saluta(); //chiamo la funzione nel main e viene mostrata in output
        System.out.println(somma(10, 20)); //stampa in output la funzione con i valori assegnati
        mostra(10);
        mostra("Ciao");
        return;
    }
}
