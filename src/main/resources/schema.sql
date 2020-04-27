drop schema if exists bd_hacienda_rosal;
create schema bd_hacienda_rosal;
use bd_hacienda_rosal;

create table if not exists user_type (
	id int not null primary key,
    rol varchar(15) NOT NULL
);


create table if not exists user (
	id INT NOT NULL AUTO_INCREMENT primary key,
	document_number varchar(10) not null,
    name varchar(100) not null,
    cellphone varchar(15) not null,
    id_type int not null,
    KEY id_type (id_type),
    constraint `id_type_ibfk_1` foreign key (id_type) references user_type (id)
);


create table if not exists debt (
	id INT NOT NULL AUTO_INCREMENT primary key,
	tower_number_home varchar (15) not null,
	amount double not null,
    months int not null
);	

create table if not exists home (
	id INT NOT NULL AUTO_INCREMENT primary key,
	tower_number_home varchar (15),
    id_user int not null,
    id_debt int not null,
    KEY id_user (id_user),
    constraint `id_user_ibfk_1` foreign key (id_user) references user (id),
    KEY id_debt (id_debt),
    constraint `id_debt_ibfk_2` foreign key (id_debt) references debt (id)
);

create table if not exists credential (
	id INT NOT NULL AUTO_INCREMENT primary key,
	user varchar(100) not null unique,
	password varchar(100) not null,
    id_home int not null,
    KEY id_home (id_home),
    constraint `id_home_ibfk_1` foreign key (id_home) references home (id)
);

create table if not exists type_request (
	id int  not null primary key,
    affair varchar(15) not null
);

create table if not exists state_request (
	id int  not null primary key,
    state varchar(15) not null
);

create table if not exists request (
	id INT NOT NULL AUTO_INCREMENT primary key,
    message varchar(500) not null,
	id_state int  not null,
    id_type int  not null,
    publish_date date not null,
	id_home int not null,
    response varchar(300),
	KEY id_state (id_state),
    constraint `id_state_ibfk_1` foreign key (id_state) references state_request (id),
    KEY id_type (id_type),
    constraint `id_type_ibfk_2` foreign key (id_type) references type_request (id),
    KEY id_home (id_home),
    constraint `id_home_ibfk_3` foreign key (id_home) references home (id)
);

create table if not exists news (
	id INT NOT NULL AUTO_INCREMENT key,
    information varchar(800) not null,
    publish_date  varchar(40) not null
);
create table if not exists commentary (
	id INT NOT NULL AUTO_INCREMENT key,
    message varchar(800) not null,
    publish_date varchar(40) not null,
    id_user_c int not null,
    id_news int not null,
    KEY id_user_c (id_user_c),
    constraint `id_user_cibfk_1` foreign key (id_user_c) references user (id),
    KEY id_news (id_news),
    constraint `id_news_ibfk_2` foreign key (id_news) references news (id)
);

