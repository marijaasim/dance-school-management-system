/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - skola_plesa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`skola_plesa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `skola_plesa`;

/*Table structure for table `instruktor` */

DROP TABLE IF EXISTS `instruktor`;

CREATE TABLE `instruktor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sifra` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `instruktor` */

insert  into `instruktor`(`id`,`ime`,`prezime`,`email`,`sifra`) values 
(1,'Luka','Simic','luka@gmail.com','luka'),
(2,'Ema','Pesic','ema@gmail.com','ema'),
(3,'Lazar','Milanovic','lazar@gmail.com','lazar'),
(4,'Kosta','Jankovic','kosta@gmail.com','kosta'),
(5,'Lana','Jovanovic','lana@gmail.com','lana'),
(6,'Sergej','Petrovic','sergej@gmail.com','sergej'),
(7,'Marija','Simovic','marija@gmail.com','marija'),
(8,'Lana','Stankovic','lana@gmail.com','lana'),
(9,'Dijana','Lazic','dijana@gmail.com','dijana'),
(10,'Milica','Petrovic','milica@gmail.com','milica'),
(11,'Petar','Pavlovic','petar@gmail.com','petar'),
(12,'Milan','Ciric','milan@gmail.com','milan'),
(13,'Lazar','Djajic','lazar@gmail.com','laki'),
(14,'Filip','Nesic','filip@gmail.com','fica');

/*Table structure for table `instruktor_sertifikat` */

DROP TABLE IF EXISTS `instruktor_sertifikat`;

CREATE TABLE `instruktor_sertifikat` (
  `instruktor` bigint(20) NOT NULL,
  `sertifikat` bigint(20) NOT NULL,
  `datumSticanja` date DEFAULT NULL,
  PRIMARY KEY (`instruktor`,`sertifikat`),
  KEY `sertifikat` (`sertifikat`),
  CONSTRAINT `instruktor_sertifikat_ibfk_1` FOREIGN KEY (`instruktor`) REFERENCES `instruktor` (`id`),
  CONSTRAINT `instruktor_sertifikat_ibfk_2` FOREIGN KEY (`sertifikat`) REFERENCES `sertifikat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `instruktor_sertifikat` */

insert  into `instruktor_sertifikat`(`instruktor`,`sertifikat`,`datumSticanja`) values 
(1,1,'2025-03-06'),
(2,7,'2024-08-02'),
(3,6,'2024-09-02'),
(4,4,'2021-08-14'),
(6,4,'2020-08-15'),
(7,7,'2023-11-21'),
(11,9,'2019-06-12'),
(12,16,'2023-02-13'),
(13,14,'2022-03-24');

/*Table structure for table `kurs_plesa` */

DROP TABLE IF EXISTS `kurs_plesa`;

CREATE TABLE `kurs_plesa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) DEFAULT NULL,
  `trajanje` int(11) DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `vrsta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kurs_plesa` */

insert  into `kurs_plesa`(`id`,`naziv`,`trajanje`,`cena`,`vrsta`) values 
(1,'Upoznaj valcer',4,140,'valcer'),
(2,'Tango za parove',2,100,'tango'),
(3,'Osnove salse',3,110,'salsa'),
(4,'Balet za pocetnike',3,130,'balet'),
(5,'Street dance',2,90,'hiphop'),
(6,'Cha-cha advance',5,180,'chacha'),
(7,'Just rumba',2,80,'rumba'),
(8,'Upoznaj tango',3,170,'tango');

/*Table structure for table `nivo_vestine` */

DROP TABLE IF EXISTS `nivo_vestine`;

CREATE TABLE `nivo_vestine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nivo` varchar(50) DEFAULT NULL,
  `vrsta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `nivo_vestine` */

insert  into `nivo_vestine`(`id`,`nivo`,`vrsta`) values 
(1,'pocetni','salsa'),
(2,'srednji','salsa'),
(3,'napredni','salsa'),
(4,'pocetni','tango'),
(6,'srednji','tango'),
(7,'napredni','tango'),
(8,'pocetni','valcer'),
(9,'srednji','valcer'),
(10,'napredni','valcer'),
(11,'pocetni','hiphop'),
(12,'srednji','hiphop'),
(13,'napredni','hiphop'),
(14,'pocetni','rumba'),
(15,'srednji','rumba'),
(16,'napredni','rumba'),
(17,'pocetni','chacha'),
(18,'srednji','chacha'),
(19,'napredni','chacha'),
(20,'pocetni','balet'),
(21,'srednji','balet'),
(22,'napredni','balet');

/*Table structure for table `polaznik` */

DROP TABLE IF EXISTS `polaznik`;

CREATE TABLE `polaznik` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nivo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nivo` (`nivo`),
  CONSTRAINT `polaznik_ibfk_1` FOREIGN KEY (`nivo`) REFERENCES `nivo_vestine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `polaznik` */

insert  into `polaznik`(`id`,`ime`,`prezime`,`email`,`nivo`) values 
(1,'Pavle','Milanovic','paja@gmail.com',4),
(2,'Ana','Perovic','anci@gmail.com',8),
(4,'Katarina','Bobic','kaca@gmail.com',21),
(12,'Sergej','Campara','sergej@gmail.com',6),
(13,'Marija','Simovic','marija@gmail.com',15),
(17,'Stefana','Savkovic','stef@gmail.com',22),
(21,'Milos','Stefanovic','milos@gmail.com',6),
(32,'Nikola','Savic','nikola@gmail.com',8),
(35,'Maja','Markovic','maja@gmail.com',11),
(36,'Petar','Stanisic','petar@gmail.com',14),
(37,'Ivana','Mitrovic','ivana@gmail.com',13),
(38,'Stefan','Ilic','stefan@gmail.com',2),
(39,'Milica','Ristic','milica@gmail.com',10),
(40,'Aleksandar','Kovacevic','aleksandar@gmail.com',21),
(42,'Vladimir','Popovic','vladimir@gmail.com',7),
(43,'Helena','Petrovic','helena.petrovic@gmail.com',10),
(44,'Natalija','Milovanovic','natalija@gmail.com',6);

/*Table structure for table `prijava_na_kurs` */

DROP TABLE IF EXISTS `prijava_na_kurs`;

CREATE TABLE `prijava_na_kurs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `ukupanIznos` double DEFAULT NULL,
  `instruktor` bigint(20) DEFAULT NULL,
  `polaznik` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instruktor` (`instruktor`),
  KEY `polaznik` (`polaznik`),
  CONSTRAINT `prijava_na_kurs_ibfk_1` FOREIGN KEY (`instruktor`) REFERENCES `instruktor` (`id`),
  CONSTRAINT `prijava_na_kurs_ibfk_2` FOREIGN KEY (`polaznik`) REFERENCES `polaznik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `prijava_na_kurs` */

insert  into `prijava_na_kurs`(`id`,`datum`,`status`,`ukupanIznos`,`instruktor`,`polaznik`) values 
(1,'2025-09-11','PLACENA',200,3,12),
(2,'2025-09-27','PLACENA',300,5,1),
(3,'2025-09-18','NA_CEKANJU',600,6,4),
(4,'2025-09-09','PLACENA',890,1,13),
(5,'2025-09-06','PLACENA',600,4,2),
(12,'2025-09-06','POTVRDJENA',1850,2,4),
(13,'2027-10-06','POTVRDJENA',1850,1,13),
(14,'2028-09-06','ZAVRSENA',0,6,4),
(15,'2025-09-06','ZAVRSENA',570,5,4),
(16,'2025-09-06','KREIRANA',940,2,13),
(17,'2025-09-06','KREIRANA',1230,2,13),
(18,'2025-09-07','KREIRANA',1470,2,17),
(19,'2025-09-07','PLACENA',1080,4,12),
(20,'2025-09-07','NA_CEKANJU',740,4,17),
(21,'2025-09-07','KREIRANA',1410,2,13),
(22,'2025-09-08','KREIRANA',1090,7,12),
(23,'2025-09-11','KREIRANA',890,2,1),
(24,'2025-09-13','KREIRANA',340,4,4),
(25,'2025-09-24','PLACENA',330,3,12),
(26,'2025-10-06','KREIRANA',330,4,4),
(27,'2025-10-15','KREIRANA',1410,4,17),
(28,'2025-10-15','KREIRANA',510,4,17),
(29,'2025-10-15','KREIRANA',720,3,4),
(30,'2025-10-15','PLACENA',330,3,4),
(31,'2025-10-15','POTVRDJENA',560,3,2),
(32,'2025-10-15','PLACENA',1070,4,21);

/*Table structure for table `sertifikat` */

DROP TABLE IF EXISTS `sertifikat`;

CREATE TABLE `sertifikat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ples` varchar(50) DEFAULT NULL,
  `organizacija` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sertifikat` */

insert  into `sertifikat`(`id`,`ples`,`organizacija`) values 
(1,'salsa','DancePro Academy'),
(2,'tango','Global Dance Institute'),
(3,'valcer','StepUp Dance School'),
(4,'hiphop','Elite Dance Federation'),
(5,'rumba','Rhythm & Motion Academy'),
(6,'balet','Global Dance Institute'),
(7,'chacha','Elite Dance Federation'),
(8,'salsa','aaaaa'),
(9,'hiphop','Nova'),
(10,'balet','Neka organizacija'),
(11,'tango','AA Dance'),
(13,'hiphop','Hiphop dance academy'),
(14,'tango','TT dance'),
(15,'valcer','VV dance'),
(16,'hiphop','SoapDance'),
(17,'balet','Labudova prica'),
(18,'rumba','Organizacija 1'),
(20,'hiphop','Dance Academy'),
(21,'chacha','');

/*Table structure for table `stavka_prijave` */

DROP TABLE IF EXISTS `stavka_prijave`;

CREATE TABLE `stavka_prijave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prijava` bigint(20) NOT NULL,
  `iznos` double DEFAULT NULL,
  `kurs` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`prijava`),
  KEY `prijava` (`prijava`),
  KEY `kurs` (`kurs`),
  CONSTRAINT `stavka_prijave_ibfk_1` FOREIGN KEY (`prijava`) REFERENCES `prijava_na_kurs` (`id`),
  CONSTRAINT `stavka_prijave_ibfk_2` FOREIGN KEY (`kurs`) REFERENCES `kurs_plesa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavka_prijave` */

insert  into `stavka_prijave`(`id`,`prijava`,`iznos`,`kurs`) values 
(7,15,390,4),
(8,15,180,5),
(16,17,330,3),
(17,17,900,6),
(21,4,390,4),
(24,19,180,5),
(25,19,900,6),
(26,20,560,1),
(27,20,180,5),
(33,16,560,1),
(34,16,180,5),
(35,16,200,2),
(36,18,390,4),
(37,18,180,5),
(38,18,900,6),
(39,22,560,1),
(40,22,330,3),
(41,22,200,2),
(42,21,180,5),
(43,21,900,6),
(44,21,330,3),
(45,23,200,2),
(46,23,180,5),
(47,23,510,8),
(48,14,330,3),
(49,24,180,5),
(50,24,160,7),
(53,25,330,3),
(54,26,330,3),
(55,27,900,6),
(56,27,510,8),
(57,28,510,8),
(58,29,390,4),
(59,29,330,3),
(60,30,330,3),
(63,31,560,1),
(67,32,510,8),
(68,32,560,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
