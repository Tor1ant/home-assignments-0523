create table user_roles
(
    user_id BIGINT not null,
    role_name varchar not null,
    primary key (user_id, role_name),
    foreign key (user_id) references users (id)
        on update cascade on delete cascade
);