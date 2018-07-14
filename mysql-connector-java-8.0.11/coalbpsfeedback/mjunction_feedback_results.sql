-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mjunction
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.33-MariaDB

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
-- Table structure for table `feedback_results`
--

DROP TABLE IF EXISTS `feedback_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback_results` (
  `Level_1` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `Sub_Category1` int(11) NOT NULL DEFAULT '0',
  `Sub_Category2` int(11) NOT NULL DEFAULT '0',
  `Sub_Category3` int(11) NOT NULL DEFAULT '0',
  `Sub_Category4` int(11) NOT NULL DEFAULT '0',
  `Sub_Category5` int(11) NOT NULL DEFAULT '0',
  `Sub_Category6` int(11) NOT NULL DEFAULT '0',
  `Other` varchar(10000) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`Level_1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_results`
--

LOCK TABLES `feedback_results` WRITE;
/*!40000 ALTER TABLE `feedback_results` DISABLE KEYS */;
INSERT INTO `feedback_results` VALUES ('Filler-1',0,0,0,0,0,0,''),('Filler-2',0,0,0,0,0,0,''),('Filler-3',0,0,0,0,0,0,''),('Filler-4',0,0,0,0,0,0,'');
/*!40000 ALTER TABLE `feedback_results` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-29 12:14:07
