INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT(NAME)
VALUES ('RESTAURANT1'),
       ('RESTAURANT2'),
       ('RESTAURANT3');

INSERT INTO MEAL(NAME, PRICE, RESTAURANT_ID, SET_AT)
VALUES ('MEAL1', 1000, 1, CURRENT_TIMESTAMP()),
       ('MEAL2', 999, 1, CURRENT_TIMESTAMP() - 1),
       ('MEAL3', 1299, 2, CURRENT_TIMESTAMP());

INSERT INTO VOTE(USER_ID, CREATED_AT, RESTAURANT_ID)
VALUES (1, CURRENT_TIMESTAMP(), 1),
       (2, CURRENT_TIMESTAMP()-1, 2),
       (3, CURRENT_TIMESTAMP(), 3);