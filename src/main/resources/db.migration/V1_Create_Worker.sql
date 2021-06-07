create table worker
(
    id  bigint not null
        constraint worker_pkey
            primary key,
    birthday timestamp,
    job varchar(255),
    name varchar(255),
    number varchar(255),
    patronic varchar(255),
    started_work timestamp,
    surname varchar(255)
);

alter table worker owner to postgres;

