import java.util.ArrayList;
import java.util.List;

public class NotificaMeteo {
//questa è l'interfaccia observer, ogni display deve implementare il metodo aggiorna cui verrà fatto override ogni volta
    interface Display {
        void aggiorna(float temperatura);
    }

    static class StazioneMeteo {
        private List<Display> displays = new ArrayList<>(); //lista observer
        private float temperatura;
        //nuovo observer
        public void aggiungiDisplay(Display d) {
            displays.add(d);
        }
        //rimuovi observer dalla lista
        public void rimuoviDisplay(Display d) {
            displays.remove(d);
        }
        //chiama aggiorna sui display registrati
        private void notificaDisplay() {
            for (Display d : displays)
                d.aggiorna(temperatura);
        }
        //aggiorna la temperatura e manda la notifica sugli observer
        public void setTemperatura(float t) {
            this.temperatura = t;
            notificaDisplay();
        }
    }
//observer 1: stampa in console
    static class DisplayConsole implements Display {
        @Override
        public void aggiorna(float temperatura) {
            System.out.println("[Console] Temperatura: " + temperatura + "°C");
        }
    }
//observer 2: simula dispositivo mobile
    static class DisplayMobile implements Display {
        private String nome;

        public DisplayMobile(String nome) {
            this.nome = nome;
        }

        @Override
        public void aggiorna(float temperatura) {
            System.out.println("[Mobile - " + nome + "] Nuova temp: " + temperatura + "°C");
        }
    }

    public static void main(String[] args) {
        StazioneMeteo stazione = new StazioneMeteo();
//tre display come observer
        stazione.aggiungiDisplay(new DisplayConsole());
        stazione.aggiungiDisplay(new DisplayMobile("dispositivo numero 1"));
        stazione.aggiungiDisplay(new DisplayMobile("dispositivo numero 2"));
//quando la temperatura cambia tutti gli observer vengono aggiornati automaticamente
        System.out.println("=== Aggiornamento 1 ===");
        stazione.setTemperatura(22.5f);

        System.out.println("\n=== Aggiornamento 2 ===");
        stazione.setTemperatura(18.0f);
    }
}