DROP DATABASE IF EXISTS `spring_demo`;
CREATE DATABASE `spring_demo`;
USE `spring_demo`;
drop table if exists `book`;
create table if not exists  `book` (
    isbn varchar (50) primary  key  ,
    book_name varchar(255) not null,
    price bigint(30) not null
)

insert into book(isbn,book_name,price) values ('1001' ,'java',99);
insert into book(isbn,book_name,price) values ('1002' ,'mysql',130);
insert into book(isbn,book_name,price) values ('1003' ,'android',160);

drop table if exists `stock`;
create table if not exists  `stock` (
    isbn varchar (21) primary  key  ,
    stock int (30) not null
)

INSERT INTO `stock`(isbn ,stock) VALUES( '1001', 9 );
INSERT INTO `stock`(isbn ,stock) VALUES( '1002', 10 );
INSERT INTO `stock`(isbn ,stock) VALUES( '1003', 10 );

drop table if exists `account`;
create table if not exists  `account` (
    username varchar (55) primary key,
    balance int (11)
)

insert into account(username,balance) values ("Zhangsan",200);
insert into account(username,balance) values ("lisi",300);