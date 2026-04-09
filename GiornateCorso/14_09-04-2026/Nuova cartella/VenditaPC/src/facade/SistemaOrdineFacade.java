package facade;

import decorators.*;
import model.*;
import strategies.*;
/**
 * PATTERN FACADE
 * Semplifica l'accesso al sistema complesso (Decorator + Strategy).
 * L'utente chiama un solo metodo invece di gestire manualmente i pacchetti.
 */
public class SistemaOrdineFacade {
    private Computer computer;
    private StrategiaPagamento strategia;

    public void selezionaBase(String tipo) {
        if (tipo.equalsIgnoreCase("gaming")) {
            this.computer = new ComputerBaseGaming();
        } else {
            this.computer = new ComputerBaseUfficio();
        }
        System.out.println("Configurazione scelta: " + computer.getDescrizione());
    }

    public void aggiungiRam() {
        this.computer = new RamExtra(this.computer);
        System.out.println("Aggiunta RAM...");
    }

    public void aggiungiSsd() {
        this.computer = new SsdExtra(this.computer);
        System.out.println("Aggiunto SSD...");
    }

    
    public void aggiungiGpu() {
        this.computer = new SchedaVideoExtra(this.computer);
        System.out.println("Aggiunta Scheda Video...");
    }

    public void scegliMetodoPagamento(StrategiaPagamento strategiaScelta) {
        this.strategia = strategiaScelta;
    }

    public void completaOrdine() {
        System.out.println("\n--- RIEPILOGO FINALE ---");
        // Grazie al pattern Decorator, qui getDescrizione() restituirà la lista completa
        System.out.println("Configurazione finale: " + computer.getDescrizione());
        System.out.println("Prezzo Totale: " + computer.getPrezzo() + "€");
        
        if (strategia != null) {
            strategia.eseguiPagamento(computer.getPrezzo());
            System.out.println("Ordine completato con successo!");
        } else {
            System.out.println("ERRORE: Metodo di pagamento non impostato.");
        }
    }
}