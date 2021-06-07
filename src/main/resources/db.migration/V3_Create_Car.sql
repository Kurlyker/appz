create table car
(
    id bigint not null
        constraint car_pkey
            primary key,
    count_of_availability integer,
    description varchar(255),
    code varchar(255),
    brand varchar(255),
    name varchar(255),
    price numeric(19,2),
    supplier_id varchar(255)
        constraint fk2kxvbr72tmtscjvyp9yqb12by
            references supplier
);

alter table car owner to postgres;

