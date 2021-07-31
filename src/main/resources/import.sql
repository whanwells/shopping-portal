-- Accessories
INSERT INTO category (name) VALUES ('accessories');
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (1, 'DualSense™ Wireless Controller - White/Black', '2020-10-30', 69.99, 13);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (1, 'DualSense™ Wireless Controller - Cosmic Red', '2021-06-11', 74.99, 40);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (1, 'DualSense™ Wireless Controller - Midnight Black', '2021-06-11', 69.99, 38);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (1, 'DualSense™ Charging Station', '2020-10-30', 29.99, 17);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (1, 'PULSE 3D™ Wireless Headset', '2020-10-30', 99.99, 39);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (1, 'HD Camera', '2020-10-30', 59.99, 10);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (1, 'Media Remote', '2020-10-30', 29.99, 20);

-- Consoles
INSERT INTO category (name) VALUES ('consoles');
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (2, 'PlayStation®5', '2020-11-12', 499.99, 1);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (2, 'PlayStation®5 Digital Edition', '2020-11-12', 399.99, 0);

-- Games
INSERT INTO category (name) VALUES ('games');
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'DEATH STRANDING™ Director''s Cut', '2021-09-24', 49.99, 0);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'Demon''s Souls', '2020-11-12', 69.99, 30);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'Destruction AllStars', '2021-02-02', 19.99, 27);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'Ghost of Tsushima™ Director''s Cut', '2021-08-20', 69.99, 0);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'Marvel''s Spider-Man: Miles Morales Launch Edition', '2020-11-12', 49.99, 50);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'Marvel''s Spider-Man: Miles Morales Ultimate Edition', '2020-11-12', 69.99, 29);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'MLB The Show 21', '2021-04-20', 69.99, 40);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'Ratchet & Clank: Rift Apart', '2021-06-11', 69.99, 24);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'Ratchet & Clank: Rift Apart Launch Edition', '2021-06-11', 69.99, 0);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'Returnal', '2021-04-30', 69.99, 37);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'Sackboy: A Big Adventure', '2020-11-20', 59.99, 41);
INSERT INTO product (category_id, name, release_date, msrp, quantity) VALUES (3, 'The Nioh Collection', '2021-02-05', 69.99, 37);

-- Roles
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('CUSTOMER');

-- Administrators
INSERT INTO user (email, password) VALUES ('admin@example.com', '$2y$10$L0ztoND98EQ1uOl50S0h/uD7ss.A5TgfbjMpDO1MuOGMo5cDQ8Y5q');
INSERT INTO user_roles (users_id, roles_id) VALUES (1, 1);
INSERT INTO user_roles (users_id, roles_id) VALUES (1, 2);

-- Customers
INSERT INTO user (email, password) VALUES ('customer@example.com', '$2y$10$L0ztoND98EQ1uOl50S0h/uD7ss.A5TgfbjMpDO1MuOGMo5cDQ8Y5q');
INSERT INTO user_roles (users_id, roles_id) VALUES (2, 2);
