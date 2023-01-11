drop table if exists vote;
drop table if exists meal;
drop table if exists restaurant;
drop table if exists user_role;
drop table if exists users;

create table users
(
    id       int generated by default as identity primary key,
    name     varchar not null,
    email    varchar not null,
    password varchar not null
);

create table user_role
(
    user_id int     not null,
    role    varchar not null,
    constraint user_roles_idx unique (user_id, role),
    foreign key (user_id) references users (id) on delete cascade
);

create table restaurant
(
    id   int generated by default as identity primary key,
    name varchar not null
);

create table meal
(
    id            int generated by default as identity primary key,
    name          varchar   not null,
    price         int       not null,
    restaurant_id int references restaurant (id) on delete cascade,
    set_at        timestamp not null
);

create table vote
(
    id            int generated by default as identity primary key,
    created_at    timestamp not null,
    user_id       int       references users (id) on delete set null,
    restaurant_id int       references restaurant (id) on delete set null,
    constraint user_vote unique (user_id, created_at)
);