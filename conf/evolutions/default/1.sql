# --- First database schema

# --- !Ups
create table todo
(
    id   bigint       not null auto_increment,
    name varchar(255) not null,
    constraint pk_todo primary key (id)
);

insert into todo(name)
values ('本棚整理');

insert into todo(name)
values ('本の返却')


# --- !Downs
drop table if exists todo;

drop sequence if exists todo_seq;