CREATE DATABASE  IF NOT EXISTS `findal` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `findal`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: findal
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
-- Temporary table structure for view `browse_view`
--

DROP TABLE IF EXISTS `browse_view`;
/*!50001 DROP VIEW IF EXISTS `browse_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `browse_view` (
  `CRN` tinyint NOT NULL,
  `NAME` tinyint NOT NULL,
  `TEACHER_EMAIL` tinyint NOT NULL,
  `ENROLLED` tinyint NOT NULL,
  `L_NAME` tinyint NOT NULL,
  `F_NAME` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `NAME` varchar(45) DEFAULT 'TBA',
  `TEACHER_EMAIL` varchar(45) DEFAULT 'TBA',
  `CRN` int(5) NOT NULL,
  PRIMARY KEY (`CRN`),
  KEY `fk_courses_users1_idx` (`TEACHER_EMAIL`),
  CONSTRAINT `fk_courses_users1` FOREIGN KEY (`TEACHER_EMAIL`) REFERENCES `users` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('POOP','craig@uga.edu',100002),('POOOP','TBA',100007),('INTRO TO POOP','TBA',900000);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrollment` (
  `CRN` int(5) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  KEY `fk_students_courses1_idx` (`CRN`),
  KEY `fk_students_users1_idx` (`EMAIL`),
  CONSTRAINT `fk_students_courses1` FOREIGN KEY (`CRN`) REFERENCES `courses` (`CRN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_students_users1` FOREIGN KEY (`EMAIL`) REFERENCES `users` (`EMAIL`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES (900000,'student@uga.edu');
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `student_view`
--

DROP TABLE IF EXISTS `student_view`;
/*!50001 DROP VIEW IF EXISTS `student_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `student_view` (
  `NAME` tinyint NOT NULL,
  `TEACHER_EMAIL` tinyint NOT NULL,
  `CRN` tinyint NOT NULL,
  `EMAIL` tinyint NOT NULL,
  `L_NAME` tinyint NOT NULL,
  `F_NAME` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `EMAIL` varchar(45) NOT NULL,
  `PASSWORD` int(11) DEFAULT NULL,
  `PERMISSIONS` int(1) DEFAULT NULL,
  `L_NAME` varchar(45) DEFAULT NULL,
  `F_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin@mvollk.com',48,2,'Matt','Voll'),('craig@uga.edu',48,1,'Peircy','Craig'),('poop@poop.com',120205,0,'man','poopy'),('poop@poop.poop',48,0,'Poop','Poop'),('student@uga.edu',48,0,'Vollkommer','Matthew'),('TBA',NULL,4,'TBA','TBA'),('teachers@uga.edu',48,1,'2','teacher');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `browse_view`
--

/*!50001 DROP TABLE IF EXISTS `browse_view`*/;
/*!50001 DROP VIEW IF EXISTS `browse_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `browse_view` AS select distinct `courses`.`CRN` AS `CRN`,`courses`.`NAME` AS `NAME`,`courses`.`TEACHER_EMAIL` AS `TEACHER_EMAIL`,count(`enrollment`.`EMAIL`) AS `ENROLLED`,`users`.`L_NAME` AS `L_NAME`,`users`.`F_NAME` AS `F_NAME` from ((`courses` left join `enrollment` on((`courses`.`CRN` = `enrollment`.`CRN`))) left join `users` on((`users`.`EMAIL` = `courses`.`TEACHER_EMAIL`))) group by `courses`.`CRN`,`courses`.`NAME`,`courses`.`TEACHER_EMAIL`,`users`.`L_NAME`,`users`.`F_NAME` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `student_view`
--

/*!50001 DROP TABLE IF EXISTS `student_view`*/;
/*!50001 DROP VIEW IF EXISTS `student_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `student_view` AS select distinct `courses`.`NAME` AS `NAME`,`courses`.`TEACHER_EMAIL` AS `TEACHER_EMAIL`,`courses`.`CRN` AS `CRN`,`enrollment`.`EMAIL` AS `EMAIL`,`users`.`L_NAME` AS `L_NAME`,`users`.`F_NAME` AS `F_NAME` from ((`courses` left join `enrollment` on((`courses`.`CRN` = `enrollment`.`CRN`))) left join `users` on((`users`.`EMAIL` = `courses`.`TEACHER_EMAIL`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-17 21:32:42
