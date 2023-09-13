
CREATE TABLE users
(
    id                BIGINT auto_increment,
    first_name        VARCHAR NOT NULL,
    last_name         VARCHAR NOT NULL,
    birth_date        DATE    NOT NULL,
    username          VARCHAR(255) UNIQUE NOT NULL,
    password          VARCHAR(255) NOT NULL,
    students_class_id BIGINT,
    CONSTRAINT users_pk
        PRIMARY KEY (id),
    CONSTRAINT users_username_uk
        UNIQUE (username),
    CONSTRAINT users___fk
        FOREIGN KEY (students_class_id) REFERENCES STUDENTS_CLASS
            ON UPDATE CASCADE
);