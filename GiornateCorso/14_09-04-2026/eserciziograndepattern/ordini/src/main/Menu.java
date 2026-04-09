package main;

import decorator.DecoratoreLog;
import decorator.DecoratoreNotifica;
import decorator.DecoratoreSconto;
import model.Ordine;
import observer.CentroProorita;
import observer.StatoCentro;
import strategy.EvasioneNormale;
import strategy.StrategiaEvasione;

public class Menu {
    public static void main(String[] args) {

        //nuovi ordini cui poi verranno aopplicate le evasioni 
        Ordine o1 = new Ordine("ORD-001", "Mario Rossi", "Laptop", 1200.00);
        Ordine o2 = new Ordine("ORD-002", "Giulia Bianchi", "Monitor", 350.00);
        Ordine o3 = new Ordine("ORD-003", "Luca Verdi", "Tastiera", 80.00);

        
        CentroProorita centro = new CentroProorita(StatoCentro.NORMALE);
        centro.registraObserver(o1);
        centro.registraObserver(o2);
        centro.registraObserver(o3);

        
        o1.setStrategia(new EvasioneNormale());
        o2.setStrategia(new EvasioneNormale());
        o3.setStrategia(new EvasioneNormale());

        
        System.out.println("=== Evasione con Decorator (Log + Notifica) ===");
        StrategiaEvasione strategiaDecorata = new DecoratoreNotifica(
                new DecoratoreLog(
                        new EvasioneNormale()));
        o1.setStrategia(strategiaDecorata);
        o1.eseguiEvasione();

        
        System.out.println("\n=== Evasione con Decorator (Sconto 10% + Log) ===");
        StrategiaEvasione strategiaScontata = new DecoratoreLog(
                new DecoratoreSconto(
                        new EvasioneNormale(), 10));
        o2.setStrategia(strategiaScontata);
        o2.eseguiEvasione();

        
        centro.setStato(StatoCentro.PRIORITA);

        System.out.println("\n=== Evasione dopo cambio stato a PRIORITA ===");
        o1.eseguiEvasione();
        o2.eseguiEvasione();
        o3.eseguiEvasione();

        
        centro.setStato(StatoCentro.CONTROLLO);

        System.out.println("\n=== Evasione dopo cambio stato a CONTROLLO ===");
        o3.eseguiEvasione();

        
        System.out.println("\n=== Rimuovo o2 dagli observer e cambio stato ===");
        centro.rimuoviObserver(o2);
        centro.setStato(StatoCentro.NORMALE);
        System.out.println("o2 non ha ricevuto l'aggiornamento, evade ancora con CONTROLLO:");
        o2.eseguiEvasione();
    }
}