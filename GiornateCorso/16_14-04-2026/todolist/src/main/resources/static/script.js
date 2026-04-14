// API base URL
const API_BASE = '/todos';

// Load todos when page loads
document.addEventListener('DOMContentLoaded', function() {
    loadTodos();
});

// Load all todos from the API
async function loadTodos() {
    try {
        const response = await fetch(API_BASE);
        const todos = await response.json();
        displayTodos(todos);
    } catch (error) {
        console.error('Error loading todos:', error);
        alert('Error loading todos. Please check if the backend is running.');
    }
}

// Display todos in the UI
function displayTodos(todos) {
    const todoList = document.getElementById('todo-list');
    const filterValue = document.getElementById('status-filter').value;

    // Filter todos if needed
    const filteredTodos = filterValue === 'ALL' ? todos : todos.filter(todo => todo.stato === filterValue);

    // Sort by priority (high to low) and then by due date
    filteredTodos.sort((a, b) => {
        if (a.priorita !== b.priorita) {
            return b.priorita - a.priorita; // Higher priority first
        }
        if (a.dataScadenza && b.dataScadenza) {
            return new Date(a.dataScadenza) - new Date(b.dataScadenza);
        }
        return 0;
    });

    todoList.innerHTML = '';

    if (filteredTodos.length === 0) {
        todoList.innerHTML = '<p>No todos found.</p>';
        return;
    }

    filteredTodos.forEach(todo => {
        const todoElement = createTodoElement(todo);
        todoList.appendChild(todoElement);
    });
}

// Create a todo element for display
function createTodoElement(todo) {
    const div = document.createElement('div');
    div.className = `todo-item priority-${todo.priorita} status-${todo.stato.toLowerCase()}`;

    const dueDate = todo.dataScadenza ? new Date(todo.dataScadenza).toLocaleDateString() : 'No due date';
    const isOverdue = todo.dataScadenza && new Date(todo.dataScadenza) < new Date() && todo.stato !== 'DONE';

    div.innerHTML = `
        <div class="todo-content">
            <h3>${todo.descrizione}</h3>
            <div class="todo-details">
                <span class="status ${todo.stato.toLowerCase()}">${formatStatus(todo.stato)}</span>
                <span class="priority">Priority: ${getPriorityText(todo.priorita)}</span>
                <span class="due-date ${isOverdue ? 'overdue' : ''}">Due: ${dueDate}</span>
            </div>
        </div>
        <div class="todo-actions">
            <button onclick="editTodo(${todo.id})">Edit</button>
            <button onclick="deleteTodo(${todo.id})" class="delete-btn">Delete</button>
        </div>
    `;

    return div;
}

// Format status for display
function formatStatus(status) {
    return status.replace('_', ' ');
}

// Get priority text
function getPriorityText(priority) {
    switch(priority) {
        case 1: return 'Low';
        case 2: return 'Medium';
        case 3: return 'High';
        default: return 'Unknown';
    }
}

// Add a new todo
async function addTodo() {
    const description = document.getElementById('todo-description').value.trim();
    const priority = parseInt(document.getElementById('todo-priority').value);
    const dueDate = document.getElementById('todo-due-date').value;

    if (!description) {
        alert('Please enter a description');
        return;
    }

    const todo = {
        descrizione: description,
        stato: 'TODO',
        priorita: priority,
        dataScadenza: dueDate || null
    };

    try {
        const response = await fetch(API_BASE, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(todo)
        });

        if (response.ok) {
            document.getElementById('todo-description').value = '';
            document.getElementById('todo-due-date').value = '';
            loadTodos(); // Reload the list
        } else {
            alert('Error adding todo');
        }
    } catch (error) {
        console.error('Error adding todo:', error);
        alert('Error adding todo');
    }
}

// Edit a todo
function editTodo(id) {
    // Find the todo to edit
    fetch(`${API_BASE}/${id}`)
        .then(response => response.json())
        .then(todo => {
            document.getElementById('edit-description').value = todo.descrizione;
            document.getElementById('edit-status').value = todo.stato;
            document.getElementById('edit-priority').value = todo.priorita;
            document.getElementById('edit-due-date').value = todo.dataScadenza || '';

            // Store the ID for updating
            document.getElementById('edit-modal').dataset.todoId = id;

            // Show modal
            document.getElementById('edit-modal').style.display = 'block';
        })
        .catch(error => {
            console.error('Error loading todo for edit:', error);
            alert('Error loading todo for edit');
        });
}

// Update a todo
async function updateTodo() {
    const id = document.getElementById('edit-modal').dataset.todoId;
    const description = document.getElementById('edit-description').value.trim();
    const status = document.getElementById('edit-status').value;
    const priority = parseInt(document.getElementById('edit-priority').value);
    const dueDate = document.getElementById('edit-due-date').value;

    if (!description) {
        alert('Please enter a description');
        return;
    }

    const todo = {
        descrizione: description,
        stato: status,
        priorita: priority,
        dataScadenza: dueDate || null
    };

    try {
        const response = await fetch(`${API_BASE}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(todo)
        });

        if (response.ok) {
            closeEditModal();
            loadTodos(); // Reload the list
        } else {
            alert('Error updating todo');
        }
    } catch (error) {
        console.error('Error updating todo:', error);
        alert('Error updating todo');
    }
}

// Delete a todo
async function deleteTodo(id) {
    if (!confirm('Are you sure you want to delete this todo?')) {
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            loadTodos(); // Reload the list
        } else {
            alert('Error deleting todo');
        }
    } catch (error) {
        console.error('Error deleting todo:', error);
        alert('Error deleting todo');
    }
}

// Filter todos
function filterTodos() {
    loadTodos(); // Reload with filter applied
}

// Close edit modal
function closeEditModal() {
    document.getElementById('edit-modal').style.display = 'none';
}

// Close modal when clicking outside
window.onclick = function(event) {
    const modal = document.getElementById('edit-modal');
    if (event.target === modal) {
        closeEditModal();
    }
}