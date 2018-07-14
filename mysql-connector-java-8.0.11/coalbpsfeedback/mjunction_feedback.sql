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
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `Level_1` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `Sub_Category1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `Sub_Category2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `Sub_Category3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `Sub_Category4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `Sub_Categor5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `Sub_Category6` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `Other` varchar(5) COLLATE utf8mb4_bin NOT NULL DEFAULT 'Other',
  PRIMARY KEY (`Level_1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES ('Bug','App Crashing','Certain features not working','Order not placed','','','','Other'),('Design','Homepage more features required','Homepage lesser features','General filling details','Orders','Overall Design/Colors','','Other'),('Feature not working','Homepage','Send Booking','Booking Status','Cancel Booking','Refund Request','Sub-category','Other'),('OTP','Not recieving OTP','OTP is incorrect','','','','','Other'),('Usefulness','Not at all useful','Useful','Extremely Useful','','','','Other');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-29 12:14:08
