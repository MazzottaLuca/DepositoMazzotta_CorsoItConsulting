# 🔌 API Documentation - Gestionale Pizzeria

## Base URL
```
http://localhost:8080/api
```

## Endpoints Utenti

### 1. Ottenere tutti gli utenti
```bash
curl -X GET http://localhost:8080/api/users
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Mario Rossi",
    "isAdmin": false
  },
  {
    "id": 2,
    "name": "Admin Pizzeria",
    "isAdmin": true
  }
]
```

---

### 2. Ottenere utente per ID
```bash
curl -X GET http://localhost:8080/api/users/1
```

**Response:**
```json
{
  "id": 1,
  "name": "Mario Rossi",
  "isAdmin": false
}
```

---

### 3. Ottenere utente per nome
```bash
curl -X GET http://localhost:8080/api/users/name/Mario%20Rossi
```

**Response:**
```json
{
  "id": 1,
  "name": "Mario Rossi",
  "isAdmin": false
}
```

---

### 4. Creare nuovo utente
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Giovanni Bianchi",
    "isAdmin": false
  }'
```

**Response:** (HTTP 201 Created)
```json
{
  "id": 3,
  "name": "Giovanni Bianchi",
  "isAdmin": false
}
```

---

### 5. Aggiornare utente
```bash
curl -X PUT http://localhost:8080/api/users/3 \
  -H "Content-Type: application/json" \
  -d '{
    "isAdmin": true
  }'
```

**Response:**
```json
{
  "id": 3,
  "name": "Giovanni Bianchi",
  "isAdmin": true
}
```

---

## Endpoints Menu

### 1. Ottenere tutti gli articoli menu
```bash
curl -X GET http://localhost:8080/api/menu
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Margherita",
    "description": "Pizza classica con pomodoro, mozzarella e basilico",
    "price": 8.50,
    "available": true
  },
  {
    "id": 2,
    "name": "Diavola",
    "description": "Pizza piccante con peperoncino e salsiccia",
    "price": 10.00,
    "available": true
  }
]
```

---

### 2. Ottenere articoli disponibili
```bash
curl -X GET http://localhost:8080/api/menu/available
```

**Response:** (Same as above, solo articoli con available=true)

---

### 3. Creare articolo menu (ADMIN ONLY)
```bash
curl -X POST http://localhost:8080/api/menu \
  -H "Content-Type: application/json" \
  -H "User-Id: 2" \
  -d '{
    "name": "Pizza Speciale",
    "description": "Pizza con ingredienti premium",
    "price": 15.50,
    "available": true
  }'
```

**Headers richiesti:**
- `User-Id`: ID dell'utente admin che effettua la richiesta

**Response:** (HTTP 201 Created)
```json
{
  "id": 25,
  "name": "Pizza Speciale",
  "description": "Pizza con ingredienti premium",
  "price": 15.50,
  "available": true
}
```

**Errori:**
- HTTP 401: Utente non trovato
- HTTP 403: Utente non è admin

---

### 4. Aggiornare articolo menu (ADMIN ONLY)
```bash
curl -X PUT http://localhost:8080/api/menu/1 \
  -H "Content-Type: application/json" \
  -H "User-Id: 2" \
  -d '{
    "price": 9.50,
    "available": true
  }'
```

**Response:**
```json
{
  "id": 1,
  "name": "Margherita",
  "description": "Pizza classica con pomodoro, mozzarella e basilico",
  "price": 9.50,
  "available": true
}
```

---

### 5. Eliminare articolo menu (ADMIN ONLY)
```bash
curl -X DELETE http://localhost:8080/api/menu/25 \
  -H "User-Id: 2"
```

**Response:** (HTTP 204 No Content)

---

## Endpoints Ordini

### 1. Ottenere tutti gli ordini
```bash
curl -X GET http://localhost:8080/api/orders
```

**Response:**
```json
[
  {
    "id": 1,
    "userId": 1,
    "items": [
      {
        "id": 1,
        "menuItemId": 1,
        "menuItemName": "Margherita",
        "quantity": 2,
        "priceAtOrder": 8.50
      },
      {
        "id": 2,
        "menuItemId": 2,
        "menuItemName": "Diavola",
        "quantity": 1,
        "priceAtOrder": 10.00
      }
    ],
    "totalPrice": 27.00,
    "orderDate": "2026-04-14T16:30:00"
  }
]
```

---

### 2. Ottenere ordine per ID
```bash
curl -X GET http://localhost:8080/api/orders/1
```

**Response:** (Same as above for single order)

---

### 3. Ottenere ordini di un utente
```bash
curl -X GET http://localhost:8080/api/orders/user/1
```

**Response:** (Array di ordini dell'utente con ID 1)

---

### 4. Creare nuovo ordine
```bash
curl -X POST http://localhost:8080/api/orders/user/1 \
  -H "Content-Type: application/json" \
  -d '[
    {
      "menuItemId": 1,
      "quantity": 2
    },
    {
      "menuItemId": 2,
      "quantity": 1
    },
    {
      "menuItemId": 15,
      "quantity": 2
    }
  ]'
```

**Request Body:**
- Array di `OrderItemDTO` con `menuItemId` e `quantity`

**Response:** (HTTP 201 Created)
```json
{
  "id": 2,
  "userId": 1,
  "items": [
    {
      "id": 3,
      "menuItemId": 1,
      "menuItemName": "Margherita",
      "quantity": 2,
      "priceAtOrder": 8.50
    },
    {
      "id": 4,
      "menuItemId": 2,
      "menuItemName": "Diavola",
      "quantity": 1,
      "priceAtOrder": 10.00
    },
    {
      "id": 5,
      "menuItemId": 15,
      "menuItemName": "Coca Cola",
      "quantity": 2,
      "priceAtOrder": 2.50
    }
  ],
  "totalPrice": 34.00,
  "orderDate": "2026-04-14T16:35:00"
}
```

**Errori:**
- HTTP 400: menuItemId non trovato o dati non validi

---

## HTTP Status Codes

| Code | Significato |
|------|-------------|
| 200 | OK - Richiesta riuscita |
| 201 | Created - Risorsa creata |
| 204 | No Content - Richiesta riuscita, nessun contenuto |
| 400 | Bad Request - Dati non validi |
| 401 | Unauthorized - Utente non autenticato/trovato |
| 403 | Forbidden - Utente non ha permessi (non admin) |
| 404 | Not Found - Risorsa non trovata |
| 500 | Internal Server Error - Errore server |

---

## Headers comuni

```
Content-Type: application/json
User-Id: <userId>  (richiesto per operazioni admin)
```

---

## Esempio di flusso completo (JavaScript/Fetch API)

```javascript
// 1. Creare/ottenere utente
const userResponse = await fetch('http://localhost:8080/api/users', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ name: 'Marco', isAdmin: false })
});
const user = await userResponse.json();

// 2. Ottenere menu disponibile
const menuResponse = await fetch('http://localhost:8080/api/menu/available');
const menu = await menuResponse.json();

// 3. Creare ordine
const orderResponse = await fetch(`http://localhost:8080/api/orders/user/${user.id}`, {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify([
    { menuItemId: 1, quantity: 2 },
    { menuItemId: 2, quantity: 1 }
  ])
});
const order = await orderResponse.json();

// 4. Ottenere ordini dell'utente
const userOrdersResponse = await fetch(`http://localhost:8080/api/orders/user/${user.id}`);
const userOrders = await userOrdersResponse.json();

console.log('Ordine creato:', order);
console.log('Tutti i tuoi ordini:', userOrders);
```

---

## Note importanti

- ✅ Tutte le API supportano **CORS** per frontend web
- ⚠️ **Admin protection**: Solo utenti con `isAdmin=true` possono modificare il menu
- 📝 Il `priceAtOrder` viene salvato al momento dell'ordine (snapshot del prezzo)
- 🔄 L'ordine mantiene storico anche se il prezzo del menu cambia successivamente
