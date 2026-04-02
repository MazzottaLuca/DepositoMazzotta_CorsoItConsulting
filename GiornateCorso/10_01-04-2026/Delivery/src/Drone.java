public class Drone extends VeicoloConsegna implements Tracciabile {

    public Drone(String targa, float caricoMassimo) {
        super(targa, caricoMassimo);
    }

    @Override
    public boolean consegnaPacco(String destinazione, float pesoPacco, String codiceTracking) {
        if (pesoPacco > caricoMassimo) {
            System.out.println("Errore: pacco troppo pesante per il drone!");
            return false; // blocca la consegna
        }
        Pacco pacco = new Pacco(destinazione, pesoPacco, codiceTracking);
        pacchiConsegnati.add(pacco);
        System.out.println("Il drone con targa " + targa + " sta consegnando a " + destinazione);
        return true;
    }

    @Override
    public void tracciaConsegna(String codiceTracking) {
        System.out.println("Tracciamento Drone: codice " + codiceTracking);
    }
}