const endpoints = {
  utenti: '/utenti',
  todo: '/todo',
  commenti: '/commenti'
};

const state = {
  utenti: [],
  todo: [],
  commenti: []
};

const elements = {
  message: document.getElementById('message'),
  userForm: document.getElementById('user-form'),
  todoForm: document.getElementById('todo-form'),
  commentForm: document.getElementById('comment-form'),
  userList: document.getElementById('user-list'),
  todoList: document.getElementById('todo-list'),
  commentList: document.getElementById('comment-list'),
  todoUserSelect: document.getElementById('todo-user'),
  commentTodoSelect: document.getElementById('comment-todo'),
  filterUser: document.getElementById('filter-user'),
  filterTodo: document.getElementById('filter-todo'),
  detailsUser: document.getElementById('user-details'),
  detailsTodo: document.getElementById('todo-details')
};

function showMessage(text, type = 'info') {
  elements.message.textContent = text;
  elements.message.className = `message ${type} visible`;
  setTimeout(() => {
    elements.message.classList.remove('visible');
  }, 3500);
}

async function fetchJson(url, options = {}) {
  const response = await fetch(url, options);
  const text = await response.text();
  if (!response.ok) {
    throw new Error(text || `HTTP ${response.status}`);
  }
  if (!text) {
    return null;
  }
  try {
    return JSON.parse(text);
  } catch {
    return text;
  }
}

async function loadUsers() {
  state.utenti = await fetchJson(endpoints.utenti);
  renderUsers();
  renderUserFilters();
}

async function loadTodos() {
  state.todo = await fetchJson(endpoints.todo);
  renderTodos();
  renderTodoFilters();
}

async function loadComments() {
  state.commenti = await fetchJson(endpoints.commenti);
  renderComments();
}

function renderUsers() {
  elements.userList.innerHTML = '';
  state.utenti.forEach((utente) => {
    const li = document.createElement('li');
    li.innerHTML = `
      <div class="item-header">
        <span class="item-label">${escapeHtml(utente.nome)} <small>#${utente.id}</small></span>
        <div class="small-buttons">
          <button type="button" class="secondary" onclick="fetchTodosByUser(${utente.id})">Todo</button>
          <button type="button" class="danger" onclick="deleteUser(${utente.id})">Elimina</button>
        </div>
      </div>
    `;
    elements.userList.appendChild(li);
  });
}

function renderTodos() {
  elements.todoList.innerHTML = '';
  state.todo.forEach((todo) => {
    const li = document.createElement('li');
    const status = todo.completato ? 'Completato' : 'Aperto';
    const utenteNome = todo.utente ? todo.utente.nome : 'Nessun utente';
    li.innerHTML = `
      <div class="item-header">
        <span class="item-label">${escapeHtml(todo.descrizione)} <small>#${todo.id}</small></span>
        <span class="item-meta">Utente: ${escapeHtml(utenteNome)} • Stato: ${status}</span>
      </div>
      <div class="small-buttons">
        <button type="button" class="secondary" onclick="toggleTodoComplete(${todo.id}, ${todo.completato})">${todo.completato ? 'Segna aperto' : 'Segna completato'}</button>
        <button type="button" class="secondary" onclick="fetchCommentsByTodo(${todo.id})">Commenti</button>
        <button type="button" class="danger" onclick="deleteTodo(${todo.id})">Elimina</button>
      </div>
    `;
    elements.todoList.appendChild(li);
  });
}

function renderComments() {
  elements.commentList.innerHTML = '';
  state.commenti.forEach((commento) => {
    const li = document.createElement('li');
    const todoId = commento.todo ? commento.todo.id : '—';
    li.innerHTML = `
      <div class="item-header">
        <span class="item-label">${escapeHtml(commento.testo)}</span>
        <span class="item-meta">Todo #${todoId}</span>
      </div>
      <div class="small-buttons">
        <button type="button" class="danger" onclick="deleteComment(${commento.id})">Elimina</button>
      </div>
    `;
    elements.commentList.appendChild(li);
  });
}

function renderUserFilters() {
  [elements.todoUserSelect, elements.filterUser].forEach((select) => {
    select.innerHTML = '<option value="">Seleziona utente</option>';
    state.utenti.forEach((utente) => {
      const option = document.createElement('option');
      option.value = utente.id;
      option.textContent = `${utente.nome} (#${utente.id})`;
      select.appendChild(option);
    });
  });
}

function renderTodoFilters() {
  [elements.commentTodoSelect, elements.filterTodo].forEach((select) => {
    select.innerHTML = '<option value="">Seleziona todo</option>';
    state.todo.forEach((todo) => {
      const option = document.createElement('option');
      option.value = todo.id;
      option.textContent = `${todo.descrizione} (#${todo.id})`;
      select.appendChild(option);
    });
  });
}

async function createUser(event) {
  event.preventDefault();
  const name = document.getElementById('user-name').value.trim();
  if (!name) return;
  const payload = { nome: name };
  await fetchJson(endpoints.utenti, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  document.getElementById('user-name').value = '';
  showMessage('Utente creato con successo');
  await loadUsers();
}

async function createTodo(event) {
  event.preventDefault();
  const descrizione = document.getElementById('todo-text').value.trim();
  const utenteId = elements.todoUserSelect.value;
  if (!descrizione || !utenteId) {
    showMessage('Inserisci una descrizione e seleziona un utente', 'error');
    return;
  }
  const payload = {
    descrizione,
    utenteId: Number(utenteId)
  };
  await fetchJson(endpoints.todo, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  document.getElementById('todo-text').value = '';
  showMessage('Todo creato con successo');
  await loadTodos();
}

async function createComment(event) {
  event.preventDefault();
  const testo = document.getElementById('comment-text').value.trim();
  const todoId = elements.commentTodoSelect.value;
  if (!testo || !todoId) {
    showMessage('Inserisci il testo e seleziona un Todo', 'error');
    return;
  }
  const payload = {
    testo,
    todoId: Number(todoId)
  };
  await fetchJson(endpoints.commenti, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  document.getElementById('comment-text').value = '';
  showMessage('Commento creato con successo');
  await loadComments();
}

async function deleteUser(id) {
  if (!confirm('Eliminare questo utente?')) return;
  try {
    await fetchJson(`${endpoints.utenti}/${id}`, { method: 'DELETE' });
    showMessage('Utente eliminato');
    // Rimuovi dall'array locale (confronta come numeri)
    const numId = Number(id);
    state.utenti = state.utenti.filter(user => user.id !== numId);
    // Rimuovi anche i todo associati
    state.todo = state.todo.filter(todo => todo.utente.id !== numId);
    // Aggiorna UI
    renderUsers();
    renderTodos();
    renderUserFilters();
    renderTodoFilters();
  } catch (error) {
    showMessage('Errore nell\'eliminazione dell\'utente: ' + error.message, 'error');
  }
}

async function deleteTodo(id) {
  if (!confirm('Eliminare questo todo?')) return;
  try {
    await fetchJson(`${endpoints.todo}/${id}`, { method: 'DELETE' });
    showMessage('Todo eliminato');
    // Rimuovi dall'array locale (confronta come numeri)
    const numId = Number(id);
    state.todo = state.todo.filter(todo => todo.id !== numId);
    // Rimuovi anche i commenti associati
    state.commenti = state.commenti.filter(comment => comment.todo.id !== numId);
    // Aggiorna UI
    renderTodos();
    renderComments();
    renderTodoFilters();
  } catch (error) {
    showMessage('Errore nell\'eliminazione del todo: ' + error.message, 'error');
  }
}

async function deleteComment(id) {
  if (!confirm('Eliminare questo commento?')) return;
  try {
    await fetchJson(`${endpoints.commenti}/${id}`, { method: 'DELETE' });
    showMessage('Commento eliminato');
    // Rimuovi dall'array locale (confronta come numeri)
    const numId = Number(id);
    state.commenti = state.commenti.filter(comment => comment.id !== numId);
    // Aggiorna UI
    renderComments();
  } catch (error) {
    showMessage('Errore nell\'eliminazione del commento: ' + error.message, 'error');
  }
}

async function toggleTodoComplete(id, completato) {
  await fetchJson(`${endpoints.todo}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ completato: !completato, descrizione: getTodoDescriptionById(id) })
  });
  showMessage('Stato todo aggiornato');
  // Aggiorna lo stato locale
  const todo = state.todo.find(t => t.id === id);
  if (todo) {
    todo.completato = !completato;
  }
  // Aggiorna UI
  renderTodos();
}

function getTodoDescriptionById(id) {
  const todo = state.todo.find((item) => item.id === id);
  return todo ? todo.descrizione : '';
}

async function fetchTodosByUser(id) {
  const todos = await fetchJson(`${endpoints.utenti}/${id}/todo`);
  renderFilteredTodos(todos, `Todo dell'utente #${id}`);
}

async function fetchCommentsByTodo(id) {
  const comments = await fetchJson(`${endpoints.todo}/${id}/commenti`);
  renderFilteredComments(comments, `Commenti del Todo #${id}`);
}

function renderFilteredTodos(todos, title) {
  elements.detailsTodo.innerHTML = `<strong>${title}</strong><br>${todos.length} elementi trovati.`;
  elements.todoList.innerHTML = '';
  todos.forEach((todo) => {
    const li = document.createElement('li');
    const status = todo.completato ? 'Completato' : 'Aperto';
    const utenteNome = todo.utente ? todo.utente.nome : 'Nessun utente';
    li.innerHTML = `
      <div class="item-header">
        <span class="item-label">${escapeHtml(todo.descrizione)} <small>#${todo.id}</small></span>
        <span class="item-meta">Utente: ${escapeHtml(utenteNome)} • Stato: ${status}</span>
      </div>
      <div class="small-buttons">
        <button type="button" class="secondary" onclick="toggleTodoComplete(${todo.id}, ${todo.completato})">${todo.completato ? 'Segna aperto' : 'Segna completato'}</button>
        <button type="button" class="secondary" onclick="fetchCommentsByTodo(${todo.id})">Commenti</button>
        <button type="button" class="danger" onclick="deleteTodo(${todo.id})">Elimina</button>
      </div>
    `;
    elements.todoList.appendChild(li);
  });
}

function renderFilteredComments(comments, title) {
  elements.detailsTodo.innerHTML = `<strong>${title}</strong><br>${comments.length} elementi trovati.`;
  elements.commentList.innerHTML = '';
  comments.forEach((commento) => {
    const li = document.createElement('li');
    const todoId = commento.todo ? commento.todo.id : '—';
    li.innerHTML = `
      <div class="item-header">
        <span class="item-label">${escapeHtml(commento.testo)}</span>
        <span class="item-meta">Todo #${todoId}</span>
      </div>
      <div class="small-buttons">
        <button type="button" class="danger" onclick="deleteComment(${commento.id})">Elimina</button>
      </div>
    `;
    elements.commentList.appendChild(li);
  });
}

function escapeHtml(text) {
  return String(text)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&#039;');
}

async function initialize() {
  elements.userForm.addEventListener('submit', createUser);
  elements.todoForm.addEventListener('submit', createTodo);
  elements.commentForm.addEventListener('submit', createComment);
  document.getElementById('load-user-todos').addEventListener('click', async () => {
    const id = Number(elements.filterUser.value);
    if (!id) {
      showMessage('Seleziona prima un utente', 'error');
      return;
    }
    await fetchTodosByUser(id);
  });
  document.getElementById('load-todo-comments').addEventListener('click', async () => {
    const id = Number(elements.filterTodo.value);
    if (!id) {
      showMessage('Seleziona prima un todo', 'error');
      return;
    }
    await fetchCommentsByTodo(id);
  });
  await Promise.all([loadUsers(), loadTodos(), loadComments()]);
}

initialize().catch((error) => {
  console.error(error);
  showMessage('Errore di inizializzazione: ' + error.message, 'error');
});
