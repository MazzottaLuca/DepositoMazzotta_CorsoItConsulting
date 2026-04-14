# 🍕 Gestionale Pizzeria

Sistema di gestione pizzeria con Spring Boot, MySQL e interfaccia web moderna.

## Caratteristiche

✅ **Menu** - Gestione completa del menu pizzeria
✅ **Utenti** - Gestione utenti con identity-based access (nome o ID)
✅ **Ordini** - Utenti possono effettuare ordini multipli
✅ **Admin** - Amministratore può modificare menu (aggiungere, modificare, eliminare articoli)
✅ **Database** - MySQL con configurazione automatica
✅ **Frontend** - Interfaccia web responsiva HTML/CSS/JavaScript

## Prerequisiti

- **Java 17+**
- **MySQL Server** (in esecuzione su localhost:3306)
- **Maven 3.6+**

## Configurazione iniziale

### 1. Creazione database

```sql
CREATE DATABASE IF NOT EXISTS gestionale_pizzeria;
```

### 2. Configurazione application.properties

File: `src/main/resources/application.properties`

Modificare le credenziali MySQL se necessario:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestionale_pizzeria?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
```

## Avvio dell'applicazione

### Da riga di comando:

```bash
mvn spring-boot:run
```

### Da IDE (IntelliJ):
1. Cliccare su `Run` → `Run 'GestionalepizzeriaApplication'`

## Accesso all'applicazione

1. Aprire il browser su: `http://localhost:8080`
2. **Login utente normale:**
   - Inserire nome utente e lasciare unchecked "Accedi come amministratore"
   - Oppure inserire ID utente se esiste già

3. **Login amministratore:**
   - Inserire nome e spuntare "Accedi come amministratore"
   - First login creherà automaticamente utente admin

## Struttura del progetto

```
src/main/java/com/example/gestionalepizzeria/
├── entity/           # Entity JPA (User, MenuItem, Order, OrderItem)
├── dto/              # Data Transfer Objects
├── repository/       # JPA Repositories
├── service/          # Logica di business
└── controller/       # REST Controllers
```

## API Endpoints

### Utenti
- `GET /api/users` - Lista tutti gli utenti
- `GET /api/users/{id}` - Get utente by ID
- `GET /api/users/name/{name}` - Get utente by nome
- `POST /api/users` - Crea nuovo utente
- `PUT /api/users/{id}` - Aggiorna utente

### Menu
- `GET /api/menu` - Lista tutti gli articoli
- `GET /api/menu/available` - Lista articoli disponibili
- `GET /api/menu/{id}` - Get articolo by ID
- `POST /api/menu` - Crea articolo (Admin only) ⚠️
- `PUT /api/menu/{id}` - Aggiorna articolo (Admin only) ⚠️
- `DELETE /api/menu/{id}` - Elimina articolo (Admin only) ⚠️

### Ordini
- `GET /api/orders` - Lista tutti gli ordini
- `GET /api/orders/{id}` - Get ordine by ID
- `GET /api/orders/user/{userId}` - Lista ordini utente
- `POST /api/orders/user/{userId}` - Crea nuovo ordine

## Utilizzo del frontend

### Per utenti normali:
1. Accedere col proprio nome o ID
2. Visualizzare il menu disponibile
3. Selezionare articoli e quantità
4. Aggiungere al carrello
5. Effettuare ordine
6. Visualizzare cronologia ordini

### Per amministratore:
1. Accedere spuntando "Accedi come amministratore"
2. Sezione "Gestione Menu":
   - Aggiungere nuovi articoli
   - Modificare articoli (prezzo, disponibilità)
   - Eliminare articoli
3. Visualizzare menu in tempo reale

## Authentication

⚠️ **Nota:** Non è implementato login securizzato. L'autenticazione avviene tramite:
- **Nome utente** - Se non esiste, viene creato automaticamente
- **ID utente** - Se esiste, accede all'utente corrispondente
- **Flag Admin** - Viene salvato nel profilo utente (bool isAdmin)

## Database Schema

### Tabella: users
```sql
- id (PK)
- name (UNIQUE)
- isAdmin (BOOLEAN)
```

### Tabella: menu_items
```sql
- id (PK)
- name
- description
- price (DECIMAL)
- available (BOOLEAN)
```

### Tabella: orders
```sql
- id (PK)
- user_id (FK)
- totalPrice (DECIMAL)
- orderDate (DATETIME)
```

### Tabella: order_items
```sql
- id (PK)
- order_id (FK)
- menu_item_id (FK)
- quantity (INT)
- priceAtOrder (DECIMAL)
```

## Troubleshooting

### Errore: "Connection refused"
- Verificare che MySQL sia in esecuzione su localhost:3306
- Controllare credenziali in application.properties

### Errore: "Table doesn't exist"
- Hibernate creerà automaticamente le tabelle (ddl-auto=update)
- Se non funziona, eseguire manualmente CREATE DATABASE

### CORS Error
- Le API hanno CORS abilitato (`@CrossOrigin(origins = "*")`)
- Se ancora problemi, controllare origin nel browser

### Frontend non carica
- Aprire `http://localhost:8080/index.html`
- Controllare che i file siano in `src/main/resources/static/`

## Tecnologie utilizzate

- **Spring Boot 4.0.5**
- **Spring Data JPA**
- **MySQL 8.0+**
- **Lombok** - Riduce boilerplate code
- **HTML5 / CSS3 / JavaScript** - Frontend

## Prossimi passi

- [ ] Implementare modifica articoli per admin
- [ ] Aggiungere validazione lato client
- [ ] Implementare pagination per ordini
- [ ] Aggiungere categorie menu
- [ ] Filtri e ricerca menu
- [ ] Email conferma ordine
- [ ] Storico ordini con statistiche

## Licenza

Open Source
