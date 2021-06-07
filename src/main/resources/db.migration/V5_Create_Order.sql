create table order
(
    id  bigint not null
        constraint order_pkey
            primary key,
    created_date timestamp,
    description varchar(255),
    client_id varchar(255)
        constraint fkhs7eej4m2orrmr5cfbcrqs8yw
            references client,
    car_id bigint
        constraint fkq83pan5xy2a6rn0qsl9bckqai
            references car
);

alter table order owner to postgres;
