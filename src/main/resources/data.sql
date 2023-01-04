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
VALUES ('MEAL1', 1000, 1, current_timestamp()),
       ('MEAL2', 999, 1, current_timestamp()-1),
       ('MEAL3', 1299, 2, current_timestamp());