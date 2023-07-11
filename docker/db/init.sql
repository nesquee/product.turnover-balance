create table auth
(
    id   integer,
    role varchar(255)
);
create table files
(
    id   integer,
    filename varchar(255),
    duplicates varchar(255)
);
create sequence fileduplicate_seq
    as integer
    maxvalue 999999999;