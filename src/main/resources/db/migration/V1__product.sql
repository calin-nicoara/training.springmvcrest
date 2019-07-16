create table product
(
    id bigint not null
        constraint product_pkey
            primary key,
    brand varchar(255),
    name varchar(255),
    price numeric(19,2) not null
);