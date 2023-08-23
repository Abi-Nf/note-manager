create table "group"(
    id serial primary key,
    name varchar(255)
);

create table "class"(
    id serial primary key,
    name varchar(255)
);

create table "student"(
    id serial primary key,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    ref varchar(30) not null,
    email text,
    phone varchar(45),
    birthdate date check(extract(year from age(current_date,birthdate)) > 10),
    creation_date timestamp,
    classId int references "class"(id),
    GroupId int references "group"(id)
);

create table "subject"(
    id serial primary key,
    name varchar(255) not null,
    description text
);

create table "note"(
   id serial primary key,
   value double precision default 0 check(value >= 0),
   subjectId int references subject(id),
   studentId int references student(id),
   evaluationDate timestamp,
   hasBonus double precision default 0
);