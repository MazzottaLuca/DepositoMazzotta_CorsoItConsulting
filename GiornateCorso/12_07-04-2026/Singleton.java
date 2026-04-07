//definizione classe singleton
public class Singleton{
//istanza privata statica della classse Logger
    private static Singleton instance;
//costuttore privato per impedire l'istanza diretta
    private Singleton() {
    }
    //metodo pubblico statico per ottenere l'unica istanza della classe
    public static Singleton getInstance(){
        //se l'istanza non esiste, viene creata
        if (instance == null){
            instance = new Singleton();
        }
    //restituisce l'istanza esistente
    return instance;
    }
    //metodo per stampare un messaggio di log
    public void DoSomething(){
        System.out.println("Singleton: DoSomething()  called");
    }
}
