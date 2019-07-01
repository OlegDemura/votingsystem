DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User', 'user@yandex.ru', 'password'),
('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100001);

INSERT INTO restaurant (name, address) VALUES
('Москва', 'г. Москва, ул. Ленина 28'),
('Дружба', 'г. Санкт-Петербург, ул. Ким Чен Ына 18');

INSERT INTO meals (description, price, restaurant_id)
VALUES ('Завтрак', 500, 100003),
       ('Обед', 1000, 100003),
       ('Ужин', 500, 100003),
       ('Завтрак', 500, 100002),
       ('Обед', 1000, 100002),
       ('Ужин', 510, 100002);

INSERT INTO votes (user_id, restaurant_id) VALUES
(100000, 100003),
(100001, 100002);