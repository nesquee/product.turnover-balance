create table users
(
    id            serial
        constraint users_pk
            primary key,
    username      varchar(64)                 not null unique,
    password      varchar(2048)               not null,
    role          varchar(32)                 not null,
    first_name    varchar(32)                 not null,
    last_name     varchar(255)                not null,
    enabled       boolean       default false not null,
    create_date   timestamp,
    update_date   timestamp
);

create unique index users_username_uindex
    on users (username);

create table files
(
    id   integer,
    filename varchar(255),
    duplicates varchar(255)
);
create sequence fileduplicate_seq
    as integer
    maxvalue 999999999;