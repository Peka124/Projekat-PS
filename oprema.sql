/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oprema` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `oprema`;



DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Administrator` VALUES 
(1,'Djordje','Tornjanski','djole','peka'),
(2,'Ivan','Vukosavljevic','ivan','peka');



DROP TABLE IF EXISTS `Kompanija`;

CREATE TABLE `Kompanija` (
  `KompanijaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `PIB` VARCHAR(20) NOT NULL,
  `NazivKompanije` VARCHAR(30) NOT NULL,
  `Adresa` VARCHAR(70) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`KompanijaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Kompanija` VALUES 
(1,'72837283', 'Tehnomanija', 'Bulevar Zorana Djindjica 78', 'tehnomania@gmail.com'),
(2,'92837253', 'Win Win', 'Topolovska 4', 'winwinbeograd@gmail.com'),
(3,'17283927', 'Computerland', 'Zeleznicka 34', 'computerland@gmail.com');


DROP TABLE IF EXISTS `TipOpreme`;

CREATE TABLE `TipOpreme` (
  `TipOpremeID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivTipaOpreme` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`TipOpremeID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `TipOpreme` VALUES 
(1,'Delovi'),
(2,'Korisnicki dodaci'),
(3,'Ostalo');



DROP TABLE IF EXISTS `Oprema`;

CREATE TABLE `Oprema` (
  `OpremaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivOpreme` VARCHAR(50) NOT NULL,
  `Opis` VARCHAR(200) NOT NULL,
  `GodinaGarancije` INT(7) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `TipOpremeID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`OpremaID`),
  CONSTRAINT `fk_tipOpreme_id` FOREIGN KEY (`TipOpremeID`) REFERENCES `TipOpreme` (`TipOpremeID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Oprema` VALUES 
(1,'Nvidia Ge Force 1660 Ti', 'vRAM', 5, 50, 1),
(2,'RedDragon 34 SUPREME', 'Mehanicka tastatura', 2, 120, 1),
(3,'Intel i5 6809', 'Procesor', 10, 260, 2),
(4,'Razer 18300 Optimum', 'Gejming stolica', 5, 350, 2),
(5,'Fujitsu elitebook 1550', 'Kancelarijski laptop', 10, 1780, 3),
(6,'Razer 1429 proDesk', 'Gejming sto', 100, 2000, 3);



DROP TABLE IF EXISTS `Narudzbina`;

CREATE TABLE `Narudzbina` (
  `NarudzbinaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `DatumIsporuke` DATE NOT NULL,
  `UkupnaCena` DECIMAL(10,2) NOT NULL,
  `KompanijaID` BIGINT(10) UNSIGNED NOT NULL,
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`NarudzbinaID`),
  CONSTRAINT `fk_komp_id` FOREIGN KEY (`KompanijaID`) REFERENCES `Kompanija` (`KompanijaID`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrator` (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT  INTO `Narudzbina` VALUES 
(1,'2024-02-05 20:05:03','2024-03-08',330000,1,1);


DROP TABLE IF EXISTS `StavkaNarudzbine`;

CREATE TABLE `StavkaNarudzbine` (
  `NarudzbinaID` BIGINT(10) UNSIGNED NOT NULL,
  `RbStavke` INT(7) NOT NULL,
  `Kolicina` INT(7) NOT NULL,
  `CenaStavke` DECIMAL(10,2) NOT NULL,
  `OpremaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`NarudzbinaID`,`RbStavke`),
  CONSTRAINT `fk_narudzbina_id` FOREIGN KEY (`NarudzbinaID`) REFERENCES `Narudzbina` (`NarudzbinaID`) ON DELETE CASCADE,
  CONSTRAINT `fk_oprema_id` FOREIGN KEY (`OpremaID`) REFERENCES `Oprema` (`OpremaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `StavkaNarudzbine` VALUES 
(1,1,100,30000,2),
(1,2,100,300000,5);




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
