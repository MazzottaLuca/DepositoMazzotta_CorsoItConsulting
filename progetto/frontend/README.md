# Frontend per Progetto API

Questo è un frontend HTML/JavaScript con server Node.js per interagire con l'API Spring Boot del progetto.

## Come usare

1. Assicurati che l'applicazione Spring Boot sia in esecuzione su `http://localhost:8080`.

2. Nella cartella `frontend`, installa le dipendenze:
   ```
   npm install
   ```

3. Avvia il server frontend:
   ```
   npm start
   ```

4. Apri il browser su `http://localhost:3000` per vedere l'interfaccia.

## Funzionalità

- **Saluto**: Chiama GET /saluta
- **Somma**: Chiama GET /somma
- **Ordina**: Chiama POST /ordina con un messaggio
- **Prodotti**: CRUD completo per prodotti (GET, POST, PUT, DELETE)

Nota: Assicurati che CORS sia abilitato nell'applicazione Spring Boot per permettere richieste dal frontend.</content>
<parameter name="filePath">c:\Users\lucam\Documents\repository_github\DepositoMazzotta_CorsoItConsulting\progetto\frontend\README.md