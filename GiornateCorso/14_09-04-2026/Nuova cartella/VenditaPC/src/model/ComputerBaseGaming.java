package model;
/**
 * CONCRETE COMPONENT
 * Rappresenta una delle configurazioni base senza componenti extra.
 */
public class ComputerBaseGaming implements Computer {
    @Override
    public String getDescrizione() {
        return "PC Configurazione Gaming (Case RGB, Alimentatore 750W)";
    }

    @Override
    public double getPrezzo() {
        return 950.00;
    }
}