package model;

import observer.ObserverOrdine;
import observer.StatoCentro;
import strategy.DatiOrdine;
import strategy.EvasioneControllata;
import strategy.EvasioneNormale;
import strategy.EvasionePrioritaria;
import strategy.StrategiaEvasione;

public class Ordine implements ObserverOrdine, DatiOrdine {
    private String id;
    private String cliente;
    private String prodotto;
    private double prezzo;
    private StrategiaEvasione strategia;

    public Ordine(String id, String cliente, String prodotto, double prezzo) {
        this.id = id;
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.prezzo = prezzo;
    }

    public void setStrategia(StrategiaEvasione strategia) {
        this.strategia = strategia;
    }

    public void eseguiEvasione() {
        if (strategia == null) {
            System.out.println("Nessuna strategia impostata per l'ordine " + id);
            return;
        }
        strategia.eseguiEvasione(this);
    }

    @Override
    public void aggiorna(StatoCentro stato) {
        System.out.println("  [Ordine " + id + "] Stato cambiato a " + stato
                + " - aggiorno la strategia.");
        switch (stato) {
            case NORMALE:
                this.strategia = new EvasioneNormale();
                break;
            case PRIORITA:
                this.strategia = new EvasionePrioritaria();
                break;
            case CONTROLLO:
                this.strategia = new EvasioneControllata();
                break;
        }
    }

    @Override
    public String toString() {
        return "Ordine{id='" + id + "', cliente='" + cliente
                + "', prodotto='" + prodotto + "', prezzo=" + prezzo + "}";
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getProdotto() {
        return prodotto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double p) {
        this.prezzo = p;
    }
}