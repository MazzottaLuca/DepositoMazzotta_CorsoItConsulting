import java.util.ArrayList;

public abstract class VeicoloConsegna {
    protected String targa;
    protected float caricoMassimo;
    protected ArrayList<Pacco> pacchiConsegnati = new ArrayList<>();

    public VeicoloConsegna(String targa, float caricoMassimo) {
        this.targa = targa;
        this.caricoMassimo = caricoMassimo;
    }

    public abstract boolean consegnaPacco(String destinazione, float pesoPacco, String codiceTracking);

    public void stampaInfo() {
        System.out.println("Targa: " + targa + ", Carico massimo: " + caricoMassimo + " kg");
        if (pacchiConsegnati.isEmpty()) {
            System.out.println("Nessun pacco consegnato.");
        } else {
            System.out.println("Pacchi consegnati:");
            for (Pacco p : pacchiConsegnati) {
                System.out.println(" - Destinazione: " + p.destinazione + ", Peso: " + p.peso + " kg, Tracking: "
                        + p.codiceTracking);
            }
        }
    }
}