package strategy;

public class AdminPermission implements PermissionStrategy {
    //uso il pattern strategy per gestire i permessi
    public void execute() {
        System.out.println("Accesso completo (admin)"); //in questo caso l'amministratore ha l'accesso completo
    }
}