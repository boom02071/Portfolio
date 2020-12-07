create table heartlist (
	heartid int auto_increment primary key,
	userid varchar(50),
    aptname varchar(50),
    guarantee varchar(50),
    rent varchar(50),
    date varchar(50),
    size double,
    floor int
);
select * from heartlist where userid='ssafy';