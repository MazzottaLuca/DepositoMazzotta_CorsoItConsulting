import java.util.ArrayList;
import java.util.List;

public class NotificaBorsa {
    // creo una interfaccia observer investitore con il metodo notifica()
    interface Investitore {
        void notifica(String azione, double valore);
    }

    // creo una classe AgenziaBorsa che gestisce la lista degli investitori e le
    // notifiche delle variazioni azionarie
    static class AgenziaBorsa {
        private List<Investitore> investitori = new ArrayList<>(); // lista degli observer registrati

        // Registra un nuovo investitore come observer
        public void aggiungiInvestitore(Investitore i) {
            investitori.add(i);
        }

        // Rimuove un investitore dalla lista degli observer
        public void rimuoviInvestitore(Investitore i) {
            investitori.remove(i);
        }

        // Chiama notifica() su tutti gli investitori registrati
        private void notificaInvestitori(String azione, double valore) {
            for (Investitore i : investitori)
                i.notifica(azione, valore);
        }

        // Aggiorna il valore di un'azione e notifica automaticamente tutti gli observer
        public void aggiornaValoreAzione(String nome, double valore) {
            System.out.println("\n[Agenzia] Aggiornamento: " + nome + " : " + valore );
            notificaInvestitori(nome, valore);
        }
    }
    // CREAZIONE OBSERVER CHE STAMPANO MESSAGGI DIVERSI E VALUTANO SITUAZIONI
    // DIVERSE
    // (L'INVESTITORE PRIVATO VALUTA UNA SOGLIA DI ATTENZIONE SOTTO LA QUALE SALE LA
    // PREOCCUPAZIONE)
    // (L'INVESTITORE BANCARIO VALUTA LE AZIONI E LA CONVENIENZA DELLA VENDITA DI
    // ESSE IN BASE ALLA FASCIA DI RISCHIO)

    // Observer 1: investitore privato
    static class InvestitorePrivato implements Investitore {
        private String nome;
        private double sogliaAttenzione;

        public InvestitorePrivato(String nome, double sogliaAttenzione) {
            this.nome = nome;
            this.sogliaAttenzione = sogliaAttenzione;
        }

        @Override
        public void notifica(String azione, double valore) {
            if (valore < sogliaAttenzione) {
                System.out.println("  [Privato - " + nome + "]  " + azione +
                        " sotto soglia! Valore: " + valore + " (soglia: " + sogliaAttenzione + ")");
            } else {
                System.out.println("  [Privato - " + nome + "] " + azione +
                        " stabile a " + valore + ". Nessuna azione.");
            }
        }
    }

    // Observer 2: investitore bancario
    static class InvestitoreBancario implements Investitore {
        private String banca;

        public InvestitoreBancario(String banca) {
            this.banca = banca;
        }

        @Override
        public void notifica(String azione, double valore) {
            // Determina il livello di rischio in base al valore
            String rischio;
            if (valore >= 200)
                rischio = "BASSO: posizione mantenuta";
            else if (valore >= 100)
                rischio = "MEDIO: monitoraggio attivo";
            else
                rischio = "ALTO: valutare vendita";

            System.out.println("  [Banca - " + banca + "] " + azione +
                    " a " + valore + " | Rischio: " + rischio);
        }
    }

    public static void main(String[] args) {
        AgenziaBorsa agenzia = new AgenziaBorsa();

        // Registriamo gli investitori come observer dell'agenzia
        agenzia.aggiungiInvestitore(new InvestitorePrivato("Mario Rossi", 150.0));
        agenzia.aggiungiInvestitore(new InvestitorePrivato("Luca Bianchi", 90.0));
        agenzia.aggiungiInvestitore(new InvestitoreBancario("Banca Nazionale"));
        agenzia.aggiungiInvestitore(new InvestitoreBancario("Mediocredito SpA"));

        // Ogni aggiornamento notifica automaticamente tutti gli observer
        agenzia.aggiornaValoreAzione("apple", 175.50);
        agenzia.aggiornaValoreAzione("tesla", 85.30);
        agenzia.aggiornaValoreAzione("google", 210.00);
    }
}
