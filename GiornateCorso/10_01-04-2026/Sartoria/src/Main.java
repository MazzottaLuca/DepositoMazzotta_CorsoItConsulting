import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Setup database
        DBConn.setup();

        // =========================
        // CAPI PRINCIPALI
        // =========================
        ArrayList<CapoPrincipale> capi = new ArrayList<>();

        capi.add(new Giacca("G1", "Giacca elegante", "Lana", "Nero", "M", 120.0, 2));
        capi.add(new Pantalone("P1", "Pantalone classico", "Cotone", "Blu", "L", 80.0, "Slim"));
        capi.add(new Gilet("GL1", "Gilet raffinato", "Lino", "Grigio", "M", 60.0, true));

        System.out.println("=== CAPI PRINCIPALI ===");

        for (CapoPrincipale c : capi) {
            c.mostraDettagli(); //  POLIMORFISMO
        }

        // =========================
        // COMPONENTI FINITURA
        // =========================
        ArrayList<ComponenteFinitura> componenti = new ArrayList<>();

        componenti.add(new Cravatta("C1", "Cravatta seta", "Seta", "Rosso", 25.0, 7.5));
        componenti.add(new Papillon("PAP1", "Papillon nero", "Raso", "Nero", 20.0, "Regolabile"));
        componenti.add(new Pochette("PO1", "Pochette bianca", "Cotone", "Bianco", 15.0, "Piatta"));

        System.out.println("\n=== COMPONENTI FINITURA ===");

        for (ComponenteFinitura c : componenti) {
            c.mostraDettagli(); // POLIMORFISMO
        }
    }
}