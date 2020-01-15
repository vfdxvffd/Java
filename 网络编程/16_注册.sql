create table user(
	username varchar(15) primary key,
	password varchar(15),
	country varchar(15),
	sex varchar(10),
	technology_one varchar(10),
	technology_two varchar(10),
	technology_thr varchar(10),
	technology_fou varchar(10)
);

insert into user values('ergouzi','123456','China','male','Java','Html',null,null);