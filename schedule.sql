
create database schedule;

use schedule;

create table if not exists writer (
    writerId bigint primary key auto_increment,
    name varchar(255),
    email varchar(255),
    createdAt datetime not null default current_timestamp,
    updatedAt datetime not null default current_timestamp
);

create table if not exists schedule (
    scheduleId bigint primary key auto_increment,
    writerName varchar(255),
    workToDo text NULL,
    password varchar(255) not null,
    createdAt datetime not null default current_timestamp,
    updatedAt datetime not null default current_timestamp,
    writerId bigint,
    foreign key (writerId) references writer(writerId)
);