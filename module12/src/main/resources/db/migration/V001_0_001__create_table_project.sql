create table project
(
    id   BIGINT auto_increment,
    name varchar(300) not null,
    constraint project_pk
        primary key (id)
);