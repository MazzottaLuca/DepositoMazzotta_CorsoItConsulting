package strategy;

public interface DatiOrdine {
    String getId();

    double getPrezzo();

    String getCliente();

    void setPrezzo(double p);
}