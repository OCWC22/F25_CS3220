drop table if exists lab10_user_phone_contacts;
drop table if exists lab10_users;
drop table if exists lab10_phone_numbers;
drop table if exists lab10_contact_types;

create table lab10_users (
	id			integer auto_increment primary key,
    first_name	varchar(255),
    last_name	varchar(255),
    email		varchar(255) not null unique,
    birthday	date,
    last_update	timestamp
);

insert into lab10_users (first_name, last_name, email, birthday, last_update) values ('John', 'Deo', 'john@gmail.com', '2004-03-05', now());
insert into lab10_users (first_name, last_name, email, birthday, last_update) values ('Jane', 'Deo', 'jane@gmail.com', '2002-10-25', now());
insert into lab10_users (first_name, last_name, email, birthday, last_update) values ('Tom', 'Smith', 'tom@gmail.com', '2004-08-01', now());

create table lab10_phone_numbers (
	id				integer auto_increment primary key,
    area_code		integer,
    phone_number 	integer
);

insert into lab10_phone_numbers (area_code, phone_number) values (323, 3433000);
insert into lab10_phone_numbers (area_code, phone_number) values (626, 6465001);
insert into lab10_phone_numbers (area_code, phone_number) values (213, 4947000);
insert into lab10_phone_numbers (area_code, phone_number) values (714, 8526666);
insert into lab10_phone_numbers (area_code, phone_number) values (909, 4567890);
insert into lab10_phone_numbers (area_code, phone_number) values (323, 7891234);
insert into lab10_phone_numbers (area_code, phone_number) values (714, 6664444);

create table lab10_contact_types (
	id			integer auto_increment primary key,
    type		varchar(255) not null unique
);

insert into lab10_contact_types (type) values ('Home');
insert into lab10_contact_types (type) values ('Mobile');
insert into lab10_contact_types (type) values ('Office');
insert into lab10_contact_types (type) values ('School');

create table lab10_user_phone_contacts (
	id				integer auto_increment primary key,
    user_id			integer not null references lab10_users(id),
    phone_number_id	integer not null references lab10_phone_numbers(id),
    contact_type_id	integer not null references lab10_contact_types(id)
);

insert into lab10_user_phone_contacts (user_id, phone_number_id, contact_type_id) values (1, 1, 4);
insert into lab10_user_phone_contacts (user_id, phone_number_id, contact_type_id) values (2, 1, 4);
insert into lab10_user_phone_contacts (user_id, phone_number_id, contact_type_id) values (1, 2, 2);
insert into lab10_user_phone_contacts (user_id, phone_number_id, contact_type_id) values (2, 3, 2);
insert into lab10_user_phone_contacts (user_id, phone_number_id, contact_type_id) values (1, 4, 1);
insert into lab10_user_phone_contacts (user_id, phone_number_id, contact_type_id) values (2, 4, 1);
insert into lab10_user_phone_contacts (user_id, phone_number_id, contact_type_id) values (3, 5, 2);
insert into lab10_user_phone_contacts (user_id, phone_number_id, contact_type_id) values (3, 6, 3);
insert into lab10_user_phone_contacts (user_id, phone_number_id, contact_type_id) values (2, 7, 3);