/*
SQLyog v10.2 
MySQL - 8.0.19 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `websites` (
	`id` int (11),
	`name` char (60),
	`url` varchar (765),
	`alexa` int (11),
	`country` char (30)
); 
insert into `websites` (`id`, `name`, `url`, `alexa`, `country`) values('1','Google','https://www.google.cm/','1','USA');
insert into `websites` (`id`, `name`, `url`, `alexa`, `country`) values('2','淘宝','https://www.taobao.com/','9000','CN');
insert into `websites` (`id`, `name`, `url`, `alexa`, `country`) values('3','菜鸟教程','http://www.runoob.com/','5892','CN');
insert into `websites` (`id`, `name`, `url`, `alexa`, `country`) values('5','Facebook','https://www.facebook.com/','1900','USA');
insert into `websites` (`id`, `name`, `url`, `alexa`, `country`) values('6','百度','http://www.baidu.com','1000','CN');
insert into `websites` (`id`, `name`, `url`, `alexa`, `country`) values('7','凤凰网网站','https://www.ifeng.com/','9000','CN');
insert into `websites` (`id`, `name`, `url`, `alexa`, `country`) values('8','NICE哥哥','http://www.nicegege.com','1000','CN');
