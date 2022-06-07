DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price numeric(6, 2));
INSERT INTO products (title, price) VALUES
('Штаны', 120.50),
('Шапка', 30.30),
('Кофта', 210.00),
('Ботинки', 190.40),
('Шарф', 45.40);