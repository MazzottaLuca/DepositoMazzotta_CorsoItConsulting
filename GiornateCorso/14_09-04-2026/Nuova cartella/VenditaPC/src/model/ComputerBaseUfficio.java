package model;

public class ComputerBaseUfficio implements Computer {
    @Override
    public String getDescrizione() {
        return "PC Configurazione Ufficio (Monitor 24\", Mouse e Tastiera)";
    }

    @Override
    public double getPrezzo() {
        return 450.00;
    }
}