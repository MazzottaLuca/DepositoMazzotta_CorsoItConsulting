import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Logger {
    private static Logger istanza; //campo privato statico istanza
    
    // Costruttore privato
    private Logger() {
    }
    // Metodo statico pubblico 
    public static Logger getIstanza(){
        if (istanza == null) {
            istanza = new Logger();
        }
        return istanza; //restituisce sempre la stessa
    }
    // Metodo che stampa il messaggio con data e ora
    public void scriviMessaggio(String messaggio) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("[" + timestamp + "] " + messaggio);
    }
}
