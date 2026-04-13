const API_BASE = 'http://localhost:8080'; // Cambia se necessario

function displayResult(result) {
    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = '<pre>' + JSON.stringify(result, null, 2) + '</pre>';
}

async function saluta() {
    try {
        const response = await fetch(`${API_BASE}/saluta`);
        const result = await response.text();
        displayResult(result);
    } catch (error) {
        displayResult('Errore: ' + error.message);
    }
}

async function somma() {
    try {
        const response = await fetch(`${API_BASE}/somma`);
        const result = await response.json();
        displayResult(result);
    } catch (error) {
        displayResult('Errore: ' + error.message);
    }
}

async function ordina() {
    const messaggio = document.getElementById('ordineInput').value;
    try {
        const response = await fetch(`${API_BASE}/ordina`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(messaggio)
        });
        const result = await response.text();
        displayResult(result);
    } catch (error) {
        displayResult('Errore: ' + error.message);
    }
}

async function getProdotti() {
    try {
        const response = await fetch(`${API_BASE}/prodotti`);
        const result = await response.json();
        displayResult(result);
    } catch (error) {
        displayResult('Errore: ' + error.message);
    }
}

async function creaProdotto() {
    const nome = document.getElementById('prodottoNome').value;
    const prezzo = parseFloat(document.getElementById('prodottoPrezzo').value);
    const prodotto = { nome, prezzo };
    try {
        const response = await fetch(`${API_BASE}/prodotti`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(prodotto)
        });
        const result = await response.json();
        displayResult(result);
    } catch (error) {
        displayResult('Errore: ' + error.message);
    }
}

async function getProdottoById() {
    const id = document.getElementById('prodottoId').value;
    try {
        const response = await fetch(`${API_BASE}/prodotti/${id}`);
        const result = await response.json();
        displayResult(result);
    } catch (error) {
        displayResult('Errore: ' + error.message);
    }
}

async function aggiornaProdotto() {
    const id = document.getElementById('prodottoId').value;
    const nome = document.getElementById('prodottoNome').value;
    const prezzo = parseFloat(document.getElementById('prodottoPrezzo').value);
    const prodotto = { nome, prezzo };
    try {
        const response = await fetch(`${API_BASE}/prodotti/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(prodotto)
        });
        const result = await response.json();
        displayResult(result);
    } catch (error) {
        displayResult('Errore: ' + error.message);
    }
}

async function eliminaProdotto() {
    const id = document.getElementById('prodottoId').value;
    try {
        const response = await fetch(`${API_BASE}/prodotti/${id}`, {
            method: 'DELETE'
        });
        const result = await response.text();
        displayResult(result);
    } catch (error) {
        displayResult('Errore: ' + error.message);
    }
}