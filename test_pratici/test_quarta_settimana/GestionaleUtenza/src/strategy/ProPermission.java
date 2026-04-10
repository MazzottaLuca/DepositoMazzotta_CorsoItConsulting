package strategy;

public class ProPermission implements PermissionStrategy {
    //uso il pattern strategy per gestire i permessi
    public void execute() {
        System.out.println("Accesso modifiche limitate (pro)"); //in questo caso l'utente pro ha accesso con possibilità limitate di modifica
    }
}