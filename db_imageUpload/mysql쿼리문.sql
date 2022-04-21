DROP table if exists imageTBL;
CREATE table imageTBL(
	ino int primary key auto_increment,
	img blob
);
SELECT * from imageTBL;
