create table bookings
(
    id          uuid        not null
        constraint bookings_pk
            primary key,
    listing_id  uuid        not null
        constraint bookings_listings_id_fk
            references listings,
    user_id     uuid        not null
        constraint bookings_users_id_fk
            references users,
    start_date  date,
    end_date    date,
    total_price decimal,
    status      varchar(20) not null
);

