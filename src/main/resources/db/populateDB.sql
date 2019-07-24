DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO restaurant (name, address)
VALUES ('Москва', 'г. Москва, ул. Ленина 28'),
       ('Дружба', 'г. Санкт-Петербург, ул. Ким Чен Ына 18');

INSERT INTO meals (description, price, restaurant_id, date_lunch)
VALUES ('Макароны', 500.5, 100003, '2015-05-30 10:00:00'),
       ('Шашлык', 1000, 100003, '2015-05-30 10:00:00'),
       ('Суп', 500, 100003, '2015-05-30 10:00:00'),
       ('Гамбургер', 500, 100002, '2015-05-30 10:00:00'),
       ('Кока Кола', 1000, 100002, '2015-05-30 10:00:00'),
       ('Хот дог', 510, 100002, '2015-05-30 10:00:00');

INSERT INTO votes (user_id, restaurant_id, date_voting)
VALUES (100000, 100003, '2019-07-06 13:09:00'),
       (100001, 100002,'2019-07-06 13:09:00'),
       (100001, 100002,'2019-07-08 13:10:00');