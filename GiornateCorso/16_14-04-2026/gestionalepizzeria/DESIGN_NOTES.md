# 🏗️ Design & Implementation Notes - Gestionale Pizzeria

## Decisioni Architetturali

### 1. Three-Tier Architecture
```
Controller (REST API)
    ↓
Service (Business Logic)
    ↓
Repository (Data Access)
    ↓
Entity (Database)
```

**Motivo:** Separazione delle responsabilità, facilità di testing, manutenibilità.

---

### 2. DTOs (Data Transfer Objects)
**Pattern:** Classe di dominio ≠ Oggetto API

**Vantaggi:**
- Exposizione selettiva di campi
- Versioning API indipendente
- Protezione della logica interna
- Validazione controllata

**Esempio:**
```
User (Entity) → UserDTO (API)
```

---

### 3. Identity-Based Access (No Passwords)
**Scelta:** Login semplice con name/ID per prototipo

**Implementazione:**
```java
// Utente accede con nome
User findByName(String name)

// Se non esiste, viene creato automaticamente
userRepository.save(new User(name, isAdmin))
```

**Admin Identification:**
```java
@Column(nullable = false)
private Boolean isAdmin;
```

---

### 4. Protection Pattern (Admin Only)
**Metodo:** Header-based user validation

```java
@PostMapping
public ResponseEntity<MenuItemDTO> createMenuItem(
    @RequestHeader("User-Id") Long userId,
    @RequestBody MenuItemDTO menuItemDTO) {
    
    UserDTO user = userService.getUserById(userId);
    if (!user.getIsAdmin()) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    // ... proceed
}
```

**Pro:** Semplice, efficace per prototipo
**Contro:** Non securizzato per produzione

---

### 5. Order Design - Snapshot Price
**Pattern:** Memorizza il prezzo al momento dell'ordine

```java
@Entity
class OrderItem {
    @ManyToOne MenuItem menuItem;
    @Decimal BigDecimal priceAtOrder;  // Snapshot storico
    Integer quantity;
}
```

**Vantaggi:**
- Storico accurato ordini
- Il cambio di prezzo non influenza ordini vecchi
- Reporting accurato

---

### 6. Relations Strategy

#### User → Orders (1-to-Many)
```java
@OneToMany(mappedBy = "user")
List<Order> orders;  // Un utente ha molti ordini
```

#### Order → OrderItems (1-to-Many)
```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
List<OrderItem> items;  // Un ordine ha molti item
```

#### OrderItem → MenuItem (Many-to-One)
```java
@ManyToOne
MenuItem menuItem;  // Molti ordini possono il lo stesso articolo
```

---

### 7. Cascade Strategy
```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
List<OrderItem> items;
```

**Effetto:**
- Quando l'Order viene salvato, gli OrderItem vengono salvati
- Quando un OrderItem viene rimosso dalla lista, viene eliminato dal DB
- Eliminazione ordine elimina automaticamente gli items

---

### 8. REST Endpoint Design

### Naming Convention
```
GET    /api/resource           → Get all
GET    /api/resource/{id}      → Get by id
GET    /api/resource/search/{param}  → Custom get
POST   /api/resource           → Create
PUT    /api/resource/{id}      → Update
DELETE /api/resource/{id}      → Delete
```

### HTTP Status Codes
```
200 → OK (GET, PUT)
201 → Created (POST)
204 → No Content (DELETE)
400 → Bad Request
401 → Unauthorized
403 → Forbidden (admin check failed)
404 → Not Found
```

---

### 9. Frontend Architecture

#### Single Page Application (No Framework)
**Decisionis:** Plain HTML/CSS/JavaScript per semplicità

**Struttura:**
```javascript
// State Management
let currentUserId = null;
let currentOrder = [];

// API calls with async/await
async function login() { /* ... */ }
async function loadMenu() { /* ... */ }
async function placeOrder() { /* ... */ }

// DOM manipulation
function updateOrderSummary() { /* ... */ }
function showAlert() { /* ... */ }
```

#### Component-like Organization
```html
<!-- Login Section -->
<div id="loginSection">

<!-- Main App -->
<div id="mainApp">
  <!-- User Section -->
  <div id="userSection">
  
  <!-- Admin Section -->
  <div id="adminSection">
```

---

### 10. CORS Implementation
```java
@RestController
@RequestMapping("/api/...")
@CrossOrigin(origins = "*")  // Allow all origins
public class XController {
```

**Motivo:** Permettere frontend localhost:8080 di comunicare con backend

---

## Database Design Decisions

### 1. Table Structure
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR UNIQUE,
    isAdmin BOOLEAN
);

CREATE TABLE menu_items (
    id BIGINT PRIMARY KEY,
    name VARCHAR,
    price DECIMAL(10, 2),
    available BOOLEAN
);

CREATE TABLE orders (
    id BIGINT PRIMARY KEY,
    user_id BIGINT,
    totalPrice DECIMAL(10, 2),
    orderDate DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    id BIGINT PRIMARY KEY,
    order_id BIGINT,
    menu_item_id BIGINT,
    quantity INT,
    priceAtOrder DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);
```

### 2. Indexes
```
users.name         → UNIQUE (lookup veloce)
orders.user_id     → FK (join veloce)
order_items.order_id → FK (query item ordine)
```

### 3. Data Types
- **IDs**: BIGINT AUTO_INCREMENT (scalabilità)
- **Prices**: DECIMAL(10,2) (precisione monetaria)
- **Dates**: DATETIME (timezone support)
- **Booleans**: BOOLEAN (MySQL→bit)

---

## Service Layer Patterns

### 1. Conversion Pattern
```java
// Entity → DTO
private MenuItemDTO convertToDTO(MenuItem menuItem) {
    return new MenuItemDTO(
        menuItem.getId(),
        menuItem.getName(),
        menuItem.getDescription(),
        menuItem.getPrice(),
        menuItem.getAvailable()
    );
}
```

### 2. Exception Handling
```java
public UserDTO getUserById(Long id) {
    return userRepository.findById(id)
        .map(this::convertToDTO)
        .orElseThrow(() -> new RuntimeException("User not found"));
}
```

### 3. Calculation Logic
```java
// In OrderService
BigDecimal totalPrice = BigDecimal.ZERO;
for (OrderItemDTO itemDTO : items) {
    BigDecimal itemTotal = menuItem.getPrice()
        .multiply(new BigDecimal(itemDTO.getQuantity()));
    totalPrice = totalPrice.add(itemTotal);
}
```

---

## Frontend Patterns

### 1. Async/Await Pattern
```javascript
async function loadMenu() {
    try {
        const response = await fetch(`${API_URL}/menu/available`);
        if (!response.ok) throw new Error('...');
        const items = await response.json();
        // Process items
    } catch (error) {
        showAlert('error', error.message);
    }
}
```

### 2. DOM Update Pattern
```javascript
function updateOrderSummary() {
    const summary = document.getElementById('orderSummary');
    
    if (currentOrder.length === 0) {
        summary.classList.add('hidden');
        return;
    }
    
    summary.classList.remove('hidden');
    // Update content
}
```

### 3. Event Delegation
```javascript
// Click handler on button
button.onclick = () => addToOrder(itemId, button);
```

---

## Configuration Strategy

### application.properties
```properties
# Database auto-configuration
spring.datasource.url=jdbc:mysql://...
spring.datasource.username=root
spring.datasource.password=

# Hibernate auto-schema
spring.jpa.hibernate.ddl-auto=update

# Data initialization
spring.sql.init.mode=always
spring.sql.init.data-locations=classpath:data.sql
```

### Vantaggi
- ✅ Zero-config database (auto-create)
- ✅ Auto schema migration
- ✅ Sample data auto-loaded
- ✅ Perfect for development

### Cautela
- ⚠️ Non usare ddl-auto=create in produzione
- ⚠️ Backup database before schema changes

---

## Security Considerations

### Current Implementation (NOT Production Ready)
```
❌ No password authentication
❌ Header-based admin check (non-cryptographic)
❌ All origins CORS enabled
❌ No JWT/OAuth
❌ No rate limiting
```

### To Make Production Ready
```
✅ Implement Spring Security
✅ Add JWT tokenization
✅ Restrict CORS origins
✅ Add input validation
✅ Implement rate limiting
✅ Add audit logging
✅ Use HTTPS
✅ Hash sensitive data
```

---

## Performance Considerations

### Current Setup
- Single connection pool
- Full entity loading (no lazy loading configured)
- No caching

### Optimizations (when needed)
- Add pagination: `@Query(value = "...", countQuery = "...")`
- Lazy loading: `@OneToMany(fetch = FetchType.LAZY)`
- Caching: `@Cacheable("menuItems")`
- Index frequently queried columns

---

## Testing Strategy

### Unit Testing (Not implemented)
```java
@Test
void testUserCreation() { }
@Test
void testMenuItemFiltering() { }
@Test
void testOrderCalculation() { }
```

### Integration Testing (Not implemented)
```java
@SpringBootTest
@ActiveProfiles("test")
class OrderControllerTest { }
```

### Manual Testing (Included)
- `test-api.sh` - Linux/Mac
- `test-api.bat` - Windows

---

## Deployment Considerations

### For Local Development
```bash
mvn spring-boot:run
# Application starts on http://localhost:8080
```

### For Production
```bash
mvn clean package
java -jar target/gestionalepizzeria-0.0.1-SNAPSHOT.jar
```

### Docker Ready (could add)
```dockerfile
FROM openjdk:17-slim
COPY target/gestionalepizzeria-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## Code Quality

### Applied Principles
- ✅ DRY (Don't Repeat Yourself)
- ✅ SOLID (Single Responsibility)
- ✅ KISS (Keep It Simple)
- ✅ Clean Code

### Tools Used
- **Lombok** - Reduce boilerplate (@Data, @NoArgsConstructor)
- **Spring Boot** - Convention over configuration
- **JPA** - ORM abstraction

---

## Future Enhancements

### Short Term
- [ ] Implementare PUT update per menu items
- [ ] Aggiungere validazione input completa
- [ ] Implement unit tests
- [ ] Add pagination API

### Medium Term
- [ ] Implementare autenticazione reale
- [ ] Agregate statistiche vendite
- [ ] Supporto multi-lingua
- [ ] Payment gateway integration

### Long Term
- [ ] Mobile app (React Native)
- [ ] Microservices architecture
- [ ] Machine learning (recommendations)
- [ ] Real-time updates (WebSocket)

---

## Learning Outcomes

Questo progetto dimostra:

### Backend
✅ Spring Boot framework
✅ JPA/Hibernate ORM
✅ RESTful API design
✅ Database relationships
✅ Service layer architecture
✅ Exception handling
✅ Configuration management

### Frontend
✅ Single Page Application
✅ Async programming (Fetch API)
✅ DOM manipulation
✅ Event handling
✅ Responsive design
✅ Form validation

### Full Stack
✅ API contract design
✅ Request/response handling
✅ State management
✅ Error handling
✅ User experience

---

## Conclusion

**Gestionale Pizzeria** è un prototipo educativo che dimostra i principi chiave dello sviluppo full-stack moderno con Spring Boot e JavaScript vanilla, seguendo patterns consolidati e best practices.

Non è meant per produzione (no password, no auth avanzato), ma è perfetto per:
- Insegnamento concetti backend/frontend
- Prototipazione rapida
- MVPs e POCs
- Baseline per progetti più complessi

---

**Happy Coding! 🍕**
