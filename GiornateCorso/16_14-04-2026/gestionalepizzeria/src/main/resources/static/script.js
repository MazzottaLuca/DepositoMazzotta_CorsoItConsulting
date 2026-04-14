const API_URL = 'http://localhost:8080/api';
let currentUserId = null;
let currentUserName = null;
let currentUserIsAdmin = false;
let currentOrder = [];

// Login
async function login() {
    const userName = document.getElementById('userName').value.trim();
    const userId = document.getElementById('userId').value;
    const isAdmin = document.getElementById('isAdmin').checked;

    if (!userName && !userId) {
        showAlert('menuAlert', 'Inserisci nome o ID', 'error');
        return;
    }

    try {
        let user;
        if (userId) {
            // Get user by ID
            const response = await fetch(`${API_URL}/users/${userId}`);
            if (!response.ok) throw new Error('Utente non trovato');
            user = await response.json();
        } else {
            // Get or create user by name
            try {
                const response = await fetch(`${API_URL}/users/name/${userName}`);
                if (response.ok) {
                    user = await response.json();
                } else {
                    // Create new user
                    const createResponse = await fetch(`${API_URL}/users`, {
                        method: 'POST',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({ name: userName, isAdmin: isAdmin })
                    });
                    if (!createResponse.ok) throw new Error('Errore nella creazione utente');
                    user = await createResponse.json();
                }
            } catch (e) {
                // Create new user
                const createResponse = await fetch(`${API_URL}/users`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ name: userName, isAdmin: isAdmin })
                });
                if (!createResponse.ok) throw new Error('Errore nella creazione utente');
                user = await createResponse.json();
            }
        }

        currentUserId = user.id;
        currentUserName = user.name;
        currentUserIsAdmin = user.isAdmin;

        document.getElementById('loginSection').classList.add('hidden');
        document.getElementById('mainApp').classList.remove('hidden');
        document.getElementById('currentUser').textContent = `${currentUserName} ${currentUserIsAdmin ? '(Admin)' : ''}`;

        if (currentUserIsAdmin) {
            document.getElementById('adminSection').classList.remove('hidden');
            document.getElementById('userSection').classList.add('hidden');
            loadAdminMenu();
        } else {
            document.getElementById('userSection').classList.remove('hidden');
            document.getElementById('adminSection').classList.add('hidden');
            loadMenu();
            loadUserOrders();
        }
    } catch (error) {
        showAlert('menuAlert', 'Errore: ' + error.message, 'error');
    }
}

// Logout
function logout() {
    currentUserId = null;
    currentUserName = null;
    currentUserIsAdmin = false;
    currentOrder = [];
    document.getElementById('loginSection').classList.remove('hidden');
    document.getElementById('mainApp').classList.add('hidden');
    document.getElementById('userName').value = '';
    document.getElementById('userId').value = '';
    document.getElementById('isAdmin').checked = false;
}

// Load menu for users
async function loadMenu() {
    try {
        const response = await fetch(`${API_URL}/menu/available`);
        if (!response.ok) throw new Error('Errore nel caricamento menu');
        const items = await response.json();

        const menuGrid = document.getElementById('menuGrid');
        menuGrid.innerHTML = '';

        items.forEach((item, index) => {
            const div = document.createElement('div');
            div.className = `carousel-item ${index === 0 ? 'active' : ''}`;
            div.innerHTML = `
                <div class="d-flex justify-content-center">
                    <div class="menu-item" style="width: 250px;">
                        <h3>${item.name}</h3>
                        <p>${item.description || 'Nessuna descrizione'}</p>
                        <div class="price">€${item.price}</div>
                        <div class="quantity">
                            <input type="number" min="1" max="10" value="1" data-itemid="${item.id}" style="width: 60px; padding: 5px; border: 1px solid #ddd; border-radius: 4px; text-align: center;">
                        </div>
                        <button class="btn btn-secondary" style="width: 100%;" onclick="addToOrder(${item.id}, this)">Aggiungi</button>
                    </div>
                </div>
            `;
            menuGrid.appendChild(div);
        });
    } catch (error) {
        showAlert('menuAlert', 'Errore: ' + error.message, 'error');
    }
}

// Load admin menu
async function loadAdminMenu() {
    try {
        const response = await fetch(`${API_URL}/menu`);
        if (!response.ok) throw new Error('Errore nel caricamento menu');
        const items = await response.json();

        const adminMenuGrid = document.getElementById('adminMenuGrid');
        adminMenuGrid.innerHTML = '';

        items.forEach((item, index) => {
            const div = document.createElement('div');
            div.className = `carousel-item ${index === 0 ? 'active' : ''}`;
            div.innerHTML = `
                <div class="d-flex justify-content-center">
                    <div class="menu-item" style="width: 250px;">
                        <h3>${item.name}</h3>
                        <p>${item.description || 'Nessuna descrizione'}</p>
                        <div class="price">€${item.price}</div>
                        <div style="font-size: 0.9em; color: #666; margin-bottom: 10px;">
                            ${item.available ? '✓ Disponibile' : '✗ Non disponibile'}
                        </div>
                        <div class="d-flex justify-content-between">
                            <button class="btn btn-secondary" style="width: 48%;" onclick="editMenuItem(${item.id})">Modifica</button>
                            <button class="btn btn-danger" style="width: 48%;" onclick="deleteMenuItem(${item.id})">Elimina</button>
                        </div>
                    </div>
                </div>
            `;
            adminMenuGrid.appendChild(div);
        });
    } catch (error) {
        showAlert('adminAlert', 'Errore: ' + error.message, 'error');
    }
}

// Add menu item (admin)
async function addMenuItem() {
    const name = document.getElementById('adminItemName').value.trim();
    const description = document.getElementById('adminItemDesc').value.trim();
    const price = document.getElementById('adminItemPrice').value;
    const available = document.getElementById('adminItemAvailable').checked;

    if (!name || !price) {
        showAlert('adminAlert', 'Compila i campi obbligatori', 'error');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/menu`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'User-Id': currentUserId
            },
            body: JSON.stringify({
                name: name,
                description: description,
                price: parseFloat(price),
                available: available
            })
        });

        if (!response.ok) throw new Error('Errore nella creazione articolo');

        showAlert('adminAlert', 'Articolo aggiunto con successo', 'success');
        document.getElementById('adminItemName').value = '';
        document.getElementById('adminItemDesc').value = '';
        document.getElementById('adminItemPrice').value = '';
        loadAdminMenu();
    } catch (error) {
        showAlert('adminAlert', 'Errore: ' + error.message, 'error');
    }
}

// Delete menu item (admin)
async function deleteMenuItem(itemId) {
    if (!confirm('Sei sicuro di voler eliminare questo articolo?')) return;

    try {
        const response = await fetch(`${API_URL}/menu/${itemId}`, {
            method: 'DELETE',
            headers: {
                'User-Id': currentUserId
            }
        });

        if (!response.ok) throw new Error('Errore nell\'eliminazione');

        showAlert('adminAlert', 'Articolo eliminato con successo', 'success');
        loadAdminMenu();
    } catch (error) {
        showAlert('adminAlert', 'Errore: ' + error.message, 'error');
    }
}

// Edit menu item (stub - can be enhanced)
function editMenuItem(itemId) {
    alert('Funzione di modifica articolo: da implementare');
}

// Add to order
async function addToOrder(itemId, button) {
    const quantityInput = button.parentElement.querySelector('input');
    const quantity = parseInt(quantityInput.value);

    if (quantity < 1) {
        showAlert('menuAlert', 'Quantità non valida', 'error');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/menu/${itemId}`);
        if (!response.ok) throw new Error('Errore nel caricamento articolo');
        const item = await response.json();

        // Check if item already in order
        const existingItem = currentOrder.find(o => o.menuItemId === itemId);
        if (existingItem) {
            existingItem.quantity += quantity;
        } else {
            currentOrder.push({
                menuItemId: itemId,
                menuItemName: item.name,
                quantity: quantity,
                priceAtOrder: item.price
            });
        }

        updateOrderSummary();
        showAlert('menuAlert', `${item.name} aggiunto all'ordine`, 'success');
    } catch (error) {
        showAlert('menuAlert', 'Errore: ' + error.message, 'error');
    }
}

// Update order summary
function updateOrderSummary() {
    const summary = document.getElementById('orderSummary');
    const orderItems = document.getElementById('orderItems');
    const totalPrice = document.getElementById('totalPrice');

    if (currentOrder.length === 0) {
        summary.classList.add('hidden');
        return;
    }

    summary.classList.remove('hidden');
    orderItems.innerHTML = '';
    let total = 0;

    currentOrder.forEach((item, index) => {
        const itemTotal = item.priceAtOrder * item.quantity;
        total += itemTotal;

        const div = document.createElement('div');
        div.className = 'order-item';
        div.innerHTML = `
            <div class="order-item-header">
                <span class="order-item-name">${item.menuItemName}</span>
                <span class="order-item-price">€${itemTotal.toFixed(2)}</span>
            </div>
            <div class="order-item-details">
                Quantità: ${item.quantity} x €${item.priceAtOrder.toFixed(2)}
                <button class="btn btn-danger" style="float: right; padding: 2px 8px; font-size: 0.8em;" onclick="removeFromOrder(${index})">Rimuovi</button>
            </div>
        `;
        orderItems.appendChild(div);
    });

    totalPrice.textContent = `Totale: €${total.toFixed(2)}`;
}

// Remove from order
function removeFromOrder(index) {
    currentOrder.splice(index, 1);
    updateOrderSummary();
}

// Place order
async function placeOrder() {
    if (currentOrder.length === 0) {
        showAlert('menuAlert', 'L\'ordine è vuoto', 'error');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/orders/user/${currentUserId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(currentOrder)
        });

        if (!response.ok) throw new Error('Errore nell\'invio ordine');

        showAlert('menuAlert', 'Ordine inviato con successo!', 'success');
        currentOrder = [];
        updateOrderSummary();
        loadUserOrders();
    } catch (error) {
        showAlert('menuAlert', 'Errore: ' + error.message, 'error');
    }
}

// Load user orders
async function loadUserOrders() {
    try {
        const response = await fetch(`${API_URL}/orders/user/${currentUserId}`);
        if (!response.ok) throw new Error('Errore nel caricamento ordini');
        const orders = await response.json();

        const userOrders = document.getElementById('userOrders');
        userOrders.innerHTML = '';

        if (orders.length === 0) {
            userOrders.innerHTML = '<p style="color: #999; text-align: center;">Nessun ordine disponibile</p>';
            return;
        }

        orders.forEach(order => {
            const div = document.createElement('div');
            div.className = 'order-summary';
            let itemsHtml = '';

            order.items.forEach(item => {
                itemsHtml += `
                    <div class="order-item">
                        <div class="order-item-header">
                            <span class="order-item-name">${item.menuItemName}</span>
                            <span class="order-item-price">€${(item.priceAtOrder * item.quantity).toFixed(2)}</span>
                        </div>
                        <div class="order-item-details">
                            Quantità: ${item.quantity}
                        </div>
                    </div>
                `;
            });

            div.innerHTML = `
                <h3 style="color: #333; margin-bottom: 10px;">Ordine #${order.id}</h3>
                <p style="font-size: 0.9em; color: #666; margin-bottom: 10px;">
                    📅 ${new Date(order.orderDate).toLocaleString('it-IT')}
                </p>
                ${itemsHtml}
                <div class="total-price" style="font-size: 1.2em;">Totale: €${order.totalPrice.toFixed(2)}</div>
            `;
            userOrders.appendChild(div);
        });
    } catch (error) {
        showAlert('ordersAlert', 'Errore: ' + error.message, 'error');
    }
}

// Show alert
function showAlert(elementId, message, type) {
    const alert = document.getElementById(elementId);
    alert.textContent = message;
    alert.className = `alert ${type} show`;
    setTimeout(() => {
        alert.classList.remove('show');
    }, 3000);
}

// Scroll carousel grid
function scrollGrid(gridId, direction) {
    const grid = document.getElementById(gridId);
    if(grid) {
        // Scorri della larghezza di un elemento (250px) + gap (15px)
        grid.scrollBy({ left: direction * 265, behavior: 'smooth' });
    }
}