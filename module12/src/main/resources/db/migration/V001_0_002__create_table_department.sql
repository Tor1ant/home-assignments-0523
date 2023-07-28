create table PUBLIC.DEPARTMENT
(
    ID              BIGINT auto_increment,
    DEPARTMENT_NAME CHARACTER VARYING(200) not null,
    constraint DEPARTMENT_PK
        primary key (ID)
);