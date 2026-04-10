package strategy;

public class NormalPermission implements PermissionStrategy {
    //uso il pattern strategy per gestire i permessi
    public void execute() {
        System.out.println("Solo lettura (normal)"); //in questo caso l'utente normale ha accesso limitato(sola lettura)
    }
}