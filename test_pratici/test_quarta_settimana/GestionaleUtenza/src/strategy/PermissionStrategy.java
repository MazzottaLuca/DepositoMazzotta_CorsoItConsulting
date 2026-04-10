package strategy;

public interface PermissionStrategy {
    //questo metodo viene richiamato nelle altre strategy ed esegue in base ai permessi
    void execute();
}