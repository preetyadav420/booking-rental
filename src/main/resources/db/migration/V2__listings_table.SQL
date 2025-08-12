create table listings
(
    id            uuid         not null
        constraint listings_pk
            primary key,
    vendor_id     uuid         not null
        constraint listings_users_id_fk
            references users,
    title         varchar(255) not null,
    description   varchar(255),
    price_per_day DECIMAL      not null,
    created_at    timestamp    not null
);

