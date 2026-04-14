@echo off
REM Script di test API - Gestionale Pizzeria (Windows)
REM Eseguire: test-api.bat

setlocal enabledelayedexpansion

set BASE_URL=http://localhost:8080/api

echo.
echo ===============================================
echo 🍕 GESTIONALE PIZZERIA - API TEST SCRIPT
echo ===============================================
echo.

REM 1. Test GET all users
echo 1. GET /api/users - Ottenere tutti gli utenti
curl -s -X GET "%BASE_URL%/users"
echo.
echo.

REM 2. Test GET user by name
echo 2. GET /api/users/name/Mario Rossi
curl -s -X GET "%BASE_URL%/users/name/Mario%%20Rossi"
echo.
echo.

REM 3. Test GET all menu items
echo 3. GET /api/menu - Ottenere tutte le voci menu (prime 3)
curl -s -X GET "%BASE_URL%/menu"
echo.
echo.

REM 4. Test GET available menu items
echo 4. GET /api/menu/available - Ottenere articoli disponibili
curl -s -X GET "%BASE_URL%/menu/available"
echo.
echo.

REM 5. Test POST create user
echo 5. POST /api/users - Creare nuovo utente
curl -s -X POST "%BASE_URL%/users" ^
  -H "Content-Type: application/json" ^
  -d "{\"name\": \"Test User\", \"isAdmin\": false}"
echo.
echo.

REM 6. Test GET user by ID
echo 6. GET /api/users/1 - Ottenere utente per ID 1
curl -s -X GET "%BASE_URL%/users/1"
echo.
echo.

REM 7. Test GET all orders
echo 7. GET /api/orders - Ottenere tutti gli ordini
curl -s -X GET "%BASE_URL%/orders"
echo.
echo.

REM 8. Test GET user orders
echo 8. GET /api/orders/user/1 - Ottenere ordini dell'utente 1
curl -s -X GET "%BASE_URL%/orders/user/1"
echo.
echo.

REM 9. Test POST create menu item (requires admin)
echo 9. POST /api/menu - Creare nuovo articolo (ADMIN: 3)
curl -s -X POST "%BASE_URL%/menu" ^
  -H "Content-Type: application/json" ^
  -H "User-Id: 3" ^
  -d "{\"name\": \"Test Pizza\", \"description\": \"Pizza test\", \"price\": 10.00, \"available\": true}"
echo.
echo.

REM 10. Test POST create order
echo 10. POST /api/orders/user/1 - Creare un ordine
echo     Ordine: 2x Margherita + 1x Diavola + 2x Coca Cola
curl -s -X POST "%BASE_URL%/orders/user/1" ^
  -H "Content-Type: application/json" ^
  -d "[{\"menuItemId\": 1, \"quantity\": 2}, {\"menuItemId\": 2, \"quantity\": 1}, {\"menuItemId\": 15, \"quantity\": 2}]"
echo.
echo.

REM 11. Test PUT update user
echo 11. PUT /api/users/1 - Aggiornare utente (cambio isAdmin)
curl -s -X PUT "%BASE_URL%/users/1" ^
  -H "Content-Type: application/json" ^
  -d "{\"isAdmin\": true}"
echo.
echo.

echo ===============================================
echo ✅ Test completati!
echo ===============================================
echo.
echo Note:
echo - ID Admin: 3 (Admin Pizzeria)
echo - Menu Item: 1 (Margherita), 2 (Diavola), 15 (Coca Cola)
echo - Tutti gli endpoint supportano CORS
echo.
pause
