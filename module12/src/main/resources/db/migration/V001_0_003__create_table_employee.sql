create table PUBLIC.EMPLOYEE
(
    ID            BIGINT auto_increment,
    FIRST_NAME    CHARACTER VARYING(100) not null,
    LAST_NAME     CHARACTER VARYING(200) not null,
    BIRTH_DAY     TIMESTAMP              not null,
    ROLE          CHARACTER VARYING(100) not null,
    PROJECT_ID    BIGINT                not null,
    DEPARTMENT_ID BIGINT                not null,
    IS_CHIEF      BOOLEAN default FALSE  not null,
    constraint EMPLOYEE_PK
        primary key (ID)
        );