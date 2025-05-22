
create database schedule;

use schedule;

create table if not exists schedule (
    scheduleId bigint primary key auto_increment,
    writerName varchar(255),
    workToDo text NULL,
    password varchar(255) not null,
    createdAt datetime not null default current_timestamp,
    # on update current_timestamp 붙이면 오류가 남. 왜...?
    # intellij 자체 버그...
    updatedAt datetime not null default current_timestamp
);