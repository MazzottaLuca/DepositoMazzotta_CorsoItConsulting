-- Dati di esempio per gestionale_pizzeria
-- Creazione tabella utenti
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    is_admin BOOLEAN DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Creazione tabella menu_items
CREATE TABLE IF NOT EXISTS menu_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(6,2) NOT NULL,
    available BOOLEAN DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Inserire utenti di esempio
INSERT INTO users (name, is_admin) VALUES 
('Mario Rossi', 0),
('Luigi Verdi', 0),
('Admin Pizzeria', 1);

-- Inserire menu items di esempio
INSERT INTO menu_items (name, description, price, available) VALUES 
('Margherita', 'Pizza classica con pomodoro, mozzarella e basilico', 8.50, 1),
('Diavola', 'Pizza piccante con peperoncino e salsiccia', 10.00, 1),
('Quattro Formaggi', 'Pizza con mozzarella, gorgonzola, asiago e provola', 11.50, 1),
('Carbonara', 'Pizza con guanciale, uovo, pecorino e pepe', 9.50, 1),
('Romana', 'Pizza con alici, pomodoro e olive', 8.00, 1),
('Verdure', 'Pizza con verdure grigliate e mozzarella', 9.00, 1),
('BBQ Chicken', 'Pizza con pollo affumicato e salsa BBQ', 10.50, 1),
('Prosciutto e Melone', 'Pizza con prosciutto e melone fresco', 11.00, 1),
('Burrata', 'Pizza bianca con burrata e rucola', 12.00, 1),
('Kebab', 'Pizza con carne kebab e salsa', 10.00, 1),
('Fritta', 'Pizza fritta tradizionale riccia e croccante', 7.50, 1),
('Bianca', 'Pizza bianca con ricotta e mozzarella', 8.00, 1);

-- Inserire bevande
INSERT INTO menu_items (name, description, price, available) VALUES 
('Coca Cola', 'Coca Cola 33cl', 2.50, 1),
('Sprite', 'Sprite 33cl', 2.50, 1),
('Acqua Naturale', 'Acqua naturale 50cl', 1.50, 1),
('Acqua Frizzante', 'Acqua frizzante 50cl', 1.50, 1),
('Birra Peroni', 'Birra Peroni 66cl', 3.50, 1),
('Vino Rosso', 'Vino rosso della casa (bicchiere)', 4.00, 1),
('Vino Bianco', 'Vino bianco della casa (bicchiere)', 4.00, 1);

-- Inserire dolci/dessert
INSERT INTO menu_items (name, description, price, available) VALUES 
('Tiramisù', 'Dolce tradizionale italiano con mascarpone', 5.50, 1),
('Panna Cotta', 'Dessert cremoso con frutti di bosco', 5.00, 1),
('Gelato', 'Una pallina di gelato a scelta', 2.50, 1),
('Cheesecake', 'Cheesecake classica americana', 6.00, 1);
