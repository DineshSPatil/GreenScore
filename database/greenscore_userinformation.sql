CREATE DATABASE  IF NOT EXISTS `greenscore` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `greenscore`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: greenscore
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `userinformation`
--

DROP TABLE IF EXISTS `userinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinformation` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `drivingLicense` varchar(45) NOT NULL,
  `greenScore` varchar(45) DEFAULT NULL,
  `oldGreenScore` int(11) DEFAULT '100',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `drivingLicense_UNIQUE` (`drivingLicense`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinformation`
--

LOCK TABLES `userinformation` WRITE;
/*!40000 ALTER TABLE `userinformation` DISABLE KEYS */;
INSERT INTO `userinformation` VALUES (1,'abc','22','1234','96',96),(3,'bcd','22','12345','88',90),(4,'HG','22','12','98',100),(5,'sachin','555','11','95',96);
/*!40000 ALTER TABLE `userinformation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-10 15:10:07
