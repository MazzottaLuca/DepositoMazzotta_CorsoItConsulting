#!/bin/bash
# Script di test API - Gestionale Pizzeria
# Test comandi: bash test-api.sh oppure ./test-api.sh

BASE_URL="http://localhost:8080/api"

# Colori per output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${BLUE}===============================================${NC}"
echo -e "${BLUE}🍕 GESTIONALE PIZZERIA - API TEST SCRIPT${NC}"
echo -e "${BLUE}===============================================${NC}\n"

# 1. Test GET all users
echo -e "${GREEN}1. GET /api/users - Ottenere tutti gli utenti${NC}"
curl -s -X GET "$BASE_URL/users" | jq '.'
echo -e "\n"

# 2. Test GET user by name
echo -e "${GREEN}2. GET /api/users/name/Mario%20Rossi${NC}"
curl -s -X GET "$BASE_URL/users/name/Mario%20Rossi" | jq '.'
echo -e "\n"

# 3. Test GET all menu items
echo -e "${GREEN}3. GET /api/menu - Ottner tutte le voci menu${NC}"
curl -s -X GET "$BASE_URL/menu" | jq '.' | head -50
echo -e "\n[... altri articoli ...]\n"

# 4. Test GET available menu items
echo -e "${GREEN}4. GET /api/menu/available - Ottenere articoli disponibili${NC}"
curl -s -X GET "$BASE_URL/menu/available" | jq '.[0:3]'
echo -e "\n[... altri articoli ...]\n"

# 5. Test POST create user
echo -e "${GREEN}5. POST /api/users - Creare nuovo utente${NC}"
USER_RESPONSE=$(curl -s -X POST "$BASE_URL/users" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "isAdmin": false
  }')
echo "$USER_RESPONSE" | jq '.'
USER_ID=$(echo "$USER_RESPONSE" | jq '.id')
echo "New User ID: $USER_ID"
echo -e "\n"

# 6. Test GET user by ID
echo -e "${GREEN}6. GET /api/users/{id} - Ottenere utente per ID${NC}"
curl -s -X GET "$BASE_URL/users/1" | jq '.'
echo -e "\n"

# 7. Test GET all orders
echo -e "${GREEN}7. GET /api/orders - Ottenere tutti gli ordini${NC}"
curl -s -X GET "$BASE_URL/orders" | jq '.'
echo -e "\n"

# 8. Test GET user orders
echo -e "${GREEN}8. GET /api/orders/user/1 - Ottenere ordini di un utente${NC}"
curl -s -X GET "$BASE_URL/orders/user/1" | jq '.'
echo -e "\n"

# 9. Test POST create menu item (requires admin)
echo -e "${YELLOW}9. POST /api/menu - Creare nuovo articolo (ADMIN ONLY)${NC}"
echo "   Nota: Richiede header User-Id di un utente admin"
curl -s -X POST "$BASE_URL/menu" \
  -H "Content-Type: application/json" \
  -H "User-Id: 3" \
  -d '{
    "name": "Test Pizza",
    "description": "Pizza di test da cancellare",
    "price": 10.00,
    "available": true
  }' | jq '.'
echo -e "\n"

# 10. Test POST create order
echo -e "${GREEN}10. POST /api/orders/user/{userId} - Creare un ordine${NC}"
echo "    Ordine: 2x Margherita + 1x Diavola + 2x Coca Cola"
ORDER_RESPONSE=$(curl -s -X POST "$BASE_URL/orders/user/1" \
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
  ]')
echo "$ORDER_RESPONSE" | jq '.'
ORDER_ID=$(echo "$ORDER_RESPONSE" | jq '.id')
echo "New Order ID: $ORDER_ID"
echo -e "\n"

# 11. Test GET order by ID
echo -e "${GREEN}11. GET /api/orders/{id} - Ottenere ordine per ID${NC}"
curl -s -X GET "$BASE_URL/orders/1" | jq '.'
echo -e "\n"

# 12. Test PUT update user (make admin)
echo -e "${YELLOW}12. PUT /api/users/{id} - Aggiornare utente${NC}"
echo "    Tentativo: Promuovere Test User a admin"
curl -s -X PUT "$BASE_URL/users/$USER_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "isAdmin": true
  }' | jq '.'
echo -e "\n"

# 13. Test DELETE menu item (requires admin)
echo -e "${RED}13. DELETE /api/menu/{id} - Eliminare articolo (ADMIN ONLY)${NC}"
echo "    Nota: Elimina l'articolo creato al passo 9"
LAST_ID=$(curl -s -X GET "$BASE_URL/menu" | jq '.[-1].id')
echo "Tentativo eliminazione articolo ID: $LAST_ID"
curl -s -X DELETE "$BASE_URL/menu/$LAST_ID" \
  -H "User-Id: 3" \
  -v 2>&1 | grep -E "< HTTP|< Date"
echo -e "\n"

echo -e "${BLUE}===============================================${NC}"
echo -e "${BLUE}✅ Test completati!${NC}"
echo -e "${BLUE}===============================================${NC}\n"

echo -e "${YELLOW}Note:${NC}"
echo "- ID Admin (Test): 3"
echo "- Menu Item IDs: 1 (Margherita), 2 (Diavola), 15 (Coca Cola)"
echo "- Credenziali Admin: 'Admin Pizzeria' con isAdmin=true"
echo -e "\n"
