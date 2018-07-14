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
-- Table structure for table `navigation`
--

DROP TABLE IF EXISTS `navigation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `navigation` (
  `level_1` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `Sub_Category1` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `Sub_Category2` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `Sub_Category3` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `Sub_Category4` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `Sub_Categor5` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `Sub_Category6` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `answer1` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `answer2` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `answer3` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `answer4` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `answer5` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `answer6` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`level_1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `navigation`
--

LOCK TABLES `navigation` WRITE;
/*!40000 ALTER TABLE `navigation` DISABLE KEYS */;
INSERT INTO `navigation` VALUES ('Account','27C Availablity','Balance Amount','','','','','It is diaplayed on the homescreen. Contact customer care if have further grievances.','Balance amount is displayed on the homescreen as well as last reconcilation date.','answer','answer','answer','answer'),('Booking','Booking Status','Which number will I get SMS confirmation?','Is my booking cancelled?','','','','On the homepage, select Booking status.Scroll to the desired order. Under Booking status it will be displayed.Even cancelled/rejected orders will be displayed.','The registered mobile number which you used to login with will recieve the message.','The order status page will provide information regarding order cancellation.','answer','answer','answer'),('Filler-3','Sub-Category','Sub-Category','Sub-Category','Sub-Category','Sub-Category','Sub-Category','answer','answer','answer','answer','answer','answer'),('Filler-4','Sub-Category','Sub-Category','Sub-Category','Sub-Category','Sub-Category','Sub-Category','answer','answer','answer','answer','answer','answer');
/*!40000 ALTER TABLE `navigation` ENABLE KEYS */;
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
