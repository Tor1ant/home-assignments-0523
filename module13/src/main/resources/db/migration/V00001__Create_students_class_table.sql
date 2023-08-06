create table students_class
(
    id           BIGINT auto_increment,
    class_lvl   BIGINT    not null,
    class_letter character not null,
    constraint students_class_pk
        primary key (id)
);