drop schema if exists bd_hacienda_rosal;
create schema bd_hacienda_rosal;
use bd_hacienda_rosal;

create table if not exists user_type (
	id int (2) not null primary key,
    rol varchar(15) not null
);

create table if not exists session(
	id int (2) not null auto_increment primary key,
    user varchar(15) not null,
    password varchar(15) not null
);

create table if not exists user (
	document_number varchar(10) not null primary key,
    first_name varchar(15) not null,
    second_name varchar(15),
    last_name varchar(30) not null,
    cellphone varchar(15) not null,
    id_type int (2) not null,
    id_session int (2) not null,
    KEY id_type (id_type),
    constraint `id_type_ibfk_1` foreign key (id_type) references user_type (id),
    KEY id_session (id_session),
    constraint `id_session_ibfk_2` foreign key (id_session) references session (id)
);

create table if not exists home (
	tower_number_home varchar (15) not null primary key,
    document_number varchar(10) not null,
    KEY document_number (document_number),
    constraint `document_number_ibfk_1` foreign key (document_number) references user (document_number)
);

create table if not exists debt (
	id int (2) not null auto_increment primary key,
	amount double not null,
    months int (3) not null,
    tower_number_home varchar (15) not null,
    KEY tower_number_home (tower_number_home),
    constraint `id_debt_ibfk_1` foreign key (tower_number_home) references home (tower_number_home)
);

create table if not exists type_request (
	id int (2) not null primary key,
    affair varchar(15) not null
);

create table if not exists state_request (
	id int (2) not null primary key,
    state varchar(15) not null
);

create table if not exists request (
	id int not null auto_increment primary key,
    message varchar(500) not null,
	id_state int (2) not null,
    id_type int (2) not null,
    publish_date date not null,
	tower_number_home varchar (15) not null,
    response varchar(300),
	KEY id_state (id_state),
    constraint `id_state_ibfk_1` foreign key (id_state) references state_request (id),
    KEY id_type (id_type),
    constraint `id_type_ibfk_2` foreign key (id_type) references type_request (id),
    KEY tower_number_home (tower_number_home),
    constraint `tower_number_home_ibfk_3` foreign key (tower_number_home) references home (tower_number_home)

);

create table if not exists news (
	id int not null auto_increment primary key,
    information varchar(800) not null,
    publish_date date not null
);
create table if not exists comentary (
	id int not null auto_increment primary key,
    message varchar(800) not null,
    publish_date date not null,
    document varchar(10) not null,
    id_news int not null,
    KEY document (document),
    constraint `document_ibfk_1` foreign key (document) references user (document_number),
    KEY id_news (id_news),
    constraint `id_news_ibfk_2` foreign key (id_news) references news (id)
);

