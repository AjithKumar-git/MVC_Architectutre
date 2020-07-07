create database bank;
use bank;

create table customer
(
customer_id int not null auto_increment,
name varchar(50),
account_number int not null,

primary key(customer_id),
foreign key(account_number) references account(account_number)
);

create table account(
account_number int not null auto_increment,
balance double,

primary key(account_number)
);

