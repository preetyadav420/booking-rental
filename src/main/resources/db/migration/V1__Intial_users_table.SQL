create table users
(
    id       uuid         not null
        constraint users_pk_2
            primary key,
    username varchar(50)  not null,
    email    varchar(100) not null,
    password varchar(255) not null,
    role     varchar(20) default 'USER',
    constraint users_pk
        unique (username, email)
);

