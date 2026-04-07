public class Main {
    public static void main(String[] args) {
        //ottenere istanza logger
        Logger logger1 = Logger.getIstanza();
        logger1.scriviMessaggio("Prima istanza");
        //ottenere nuova istanza logger
        Logger logger2 = Logger.getIstanza();
        logger2.scriviMessaggio("Seconda istanza");
        //far fare altro, giusto per il confronto tra istanze
        Logger logger3 = Logger.getIstanza();
        logger3.scriviMessaggio("Terza istanza");
        //faccio il confronto per dimostrare che è la stessa istanza, per la dimostrazione ho usato l'ora di stampa e il confronto con booleano
        System.out.println("logger1 == logger2 : " + (logger1 == logger2));
        System.out.println("logger2 == logger3 : " + (logger2 == logger3));
        System.out.println("logger1 == logger3 : " + (logger1 == logger3));
    }
}
