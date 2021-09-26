


drop database if exists Employee_Database;
create database Employee_Database;
use Employee_Database;

CREATE TABLE EMPLOYEE(EMPLOYEE_ID INT(5)
 PRIMARY KEY,
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(20),
date_Of_Joining DATE,salary INT(10),
 age INT(3),
 designation varchar(20),
  phone varchar(10),
 password varchar(20)

);

insert into employee values(45,'Vishnu','lal','2000-4-02',20000,22,'manager','9207450908','Vishnu@123');
insert into employee values(46,'Midhun','Raj','2010-4-02',10000,22,'OE','9876543210','raj@123');
insert into employee values(47,'Umar','Azeez','2000-4-02',20000,22,'OE','7894561230','baby@123');
insert into employee values(48,'Revathy','S','2000-4-02',20000,22,'MD','7894561230','baby@123');
insert into employee values(49,'Aswathy','PK','2000-4-02',20000,22,'MD','7894561230','baby@123');

select * from employee;