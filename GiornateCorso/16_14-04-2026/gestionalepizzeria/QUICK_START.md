# 🚀 Quick Start Guide - Gestionale Pizzeria

## Passaggi di setup

### 1️⃣ Prerequisiti
- ✅ Java 17+ installato
- ✅ Maven 3.6+ installato  
- ✅ MySQL Server in esecuzione (default: localhost:3306)

### 2️⃣ Configurazione database (IMPORTANTE!)

**Se MySQL è installato localmente con password root vuota:**

```sql
-- Eseguire nel MySQL client o MySQL Workbench:
CREATE DATABASE IF NOT EXISTS gestionale_pizzeria;
```

**Se hai una password MySQL, modifica in `src/main/resources/application.properties`:**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestionale_pizzeria?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=TUA_PASSWORD_QUI
```

### 3️⃣ Avviare l'applicazione

**Da terminale:**
```bash
cd gestionalepizzeria
mvn spring-boot:run
```

**Oppure da IDE:**
- IntelliJ: Right-click su `GestionalepizzeriaApplication.java` → Run
- Eclipse: Run As → Spring Boot App

### 4️⃣ Accedere all'applicazione

1. Aprire browser: `http://localhost:8080`
2. Fare login (creazione automatica utente al primo accesso)

---

## 👥 Test Users

Il database viene inizializzato automaticamente con:

| Nome | Ruolo | Accesso |
|------|-------|---------|
| Mario Rossi | Utente | Nome: "Mario Rossi" |
| Luigi Verdi | Utente | Nome: "Luigi Verdi" |
| Admin Pizzeria | Admin | Nome: "Admin Pizzeria" ✓ Admin |

---

## 📋 Funzionalità

### Per Utenti Normali
- ✅ Visualizzare menu
- ✅ Aggiungere articoli al carrello
- ✅ Effettuare ordini
- ✅ Visualizzare storico ordini

### Per Amministratore
- ✅ Visualizzare tutti gli articoli (anche non disponibili)
- ✅ Aggiungere nuovi articoli al menu
- ✅ Modificare prezzo e disponibilità
- ✅ Eliminare articoli

---

## 🔌 API REST

**Tutte le API sono disponibili su:** `http://localhost:8080/api`

### Principali endpoint:
- `GET /api/users` - Lista utenti
- `POST /api/users` - Crea utente  
- `GET /api/menu` - Ottieni menu
- `POST /api/menu` - Aggiungi articolo (Admin)
- `GET /api/orders/user/{userId}` - Ordini utente
- `POST /api/orders/user/{userId}` - Crea ordine

📖 Vedi `API_DOCUMENTATION.md` per tutti gli endpoint e esempi

---

## 🗂️ Struttura file importanti

```
gestionalepizzeria/
├── src/main/java/.../gestionalepizzeria/
│   ├── entity/          ← Classi del database
│   ├── dto/             ← Modelli dati API
│   ├── service/         ← Logica di business
│   ├── repository/      ← Accesso al database
│   └── controller/      ← REST endpoints
├── src/main/resources/
│   ├── application.properties  ← Configurazione
│   ├── data.sql               ← Dati iniziali
│   └── static/
│       └── index.html         ← Frontend
├── pom.xml              ← Dipendenze Maven
└── README.md            ← Documentazione completa
```

---

## 🐛 Troubleshooting

### Errore: "Access denied for user 'root'@'localhost'"
→ Aggiorna `spring.datasource.password` in application.properties

### Errore: "Can't connect to MySQL server"
→ Assicurati che MySQL sia in esecuzione:
```bash
# Windows
net start MySQL
# Mac/Linux
sudo systemctl start mysql
```

### Errore: "Address already in use :8080"
→ Cambia porta in application.properties:
```properties
server.port=8081
```

### Frontend non carica nè si connette alle API
→ Controlla che sia http://localhost:8080 (non https)

---

## 📝 Primo Login

1. **Utente normale:**
   - Nome: Mario Rossi
   - Admin: No (unchecked)
   - Login

2. **Admin:**
   - Nome: Admin Pizzeria
   - Admin: Yes (checked)
   - Login

3. **Nuovo utente:**
   - Nome: [digita nome nuovo]
   - Admin: [scegli ruolo]
   - Login (verrà creato automaticamente)

---

## ✨ Caratteristiche implementate

✅ Multi-utente con identity-based access (nome/ID)
✅ Ordini multipli per utente
✅ Gestione Menu (CRUD) per admin
✅ Carrello dinamico frontend
✅ Storico ordini
✅ Interfaccia responsiva (mobile/desktop)
✅ Database MySQL relazionale
✅ API REST completa
✅ CORS enabled
✅ Dati iniziali precaricati

---

## 📚 Documentazione

- `README.md` - Documentazione generale
- `API_DOCUMENTATION.md` - Tutti gli endpoint API
- `QUICK_START.md` - Questa guida

---

## 🎯 Prossime migliorie possibili

- [ ] Implementare modifica singoli articoli menu
- [ ] Aggiungere validazione form lato server
- [ ] Pagination su ordini e menu
- [ ] Categorie menu
- [ ] Ricerca e filtri
- [ ] Upload foto articoli
- [ ] Email conferma ordine
- [ ] Dark mode frontend
- [ ] Statistiche vendite admin
- [ ] Esportazione ordini PDF

---

## 📞 Aiuto

Se hai problemi:

1. Controlla che **MySQL sia running**
2. Verifica le credenziali in `application.properties`
3. Leggi `API_DOCUMENTATION.md` per i dettagli API
4. Vedi la console di Spring Boot per error stack trace

---

**Buon divertimento con Gestionale Pizzeria! 🍕**
