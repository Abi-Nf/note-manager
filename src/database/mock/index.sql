drop table student cascade ;

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

insert into subject
    (name, description)
values
    ('PROG2','Learn POO with Java language');

insert into class
    (name)
values ('L1');

insert into "group" (name)
values ('H3');

--
-- insert into student
--     (firstname, lastname, ref, email, phone, birthdate, creation_date, classid, groupid)
--
-- values ('Abi','RALAIVAO','STD22078',
--         'hei.abigail.3@gmail.com','+261 34 38 355 54',
--         '2005-08-15'::date,now(),1,1);
--
-- insert into note
--     (value, subjectid, studentid, evaluationdate)
-- values
--     (15.0,1,1, '2023-06-15T15:00'::timestamp)