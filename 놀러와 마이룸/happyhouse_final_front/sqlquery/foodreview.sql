create table foodreview (
	id int auto_increment primary key,
    writer varchar(20),
    title varchar(50),
    content text,
    star int,
    shop varchar(50)
);
SELECT * FROM foodreview;

