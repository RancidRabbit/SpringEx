DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price numeric(6, 2));
INSERT INTO products (title, price) VALUES
('Штаны', 120.50),
('Шапка', 30.30),
('Кофта', 210.00),
('Ботинки', 190.40),
('Шарф', 45.40);


DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Кот'),
('Собака'),
('Человек');


DROP TABLE IF EXISTS purchase;
CREATE TABLE purchase (customer_id integer references customers (id), product_id integer references products (id));
INSERT INTO purchase (customer_id, product_id) VALUES
(1,2),
(1,5),
(2,4),
(3,1),
(3,2),
(3,3),
(3,4);