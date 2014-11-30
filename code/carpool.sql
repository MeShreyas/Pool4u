-- MySQL dump 10.13  Distrib 5.1.47, for Win64 (unknown)
--
-- Host: localhost    Database: carpool
-- ------------------------------------------------------
-- Server version	5.1.47-community

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`activity_id`),
  UNIQUE KEY `index2` (`activity_desc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alert_delivery`
--

DROP TABLE IF EXISTS `alert_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alert_delivery` (
  `alert_Id` int(11) NOT NULL AUTO_INCREMENT,
  `event_Id` int(11) NOT NULL,
  `notification_Type` int(11) NOT NULL,
  `subject` varchar(512) DEFAULT NULL,
  `Message` blob,
  `status` char(1) CHARACTER SET latin1 NOT NULL,
  `rec_cre_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`alert_Id`),
  KEY `fk_alert_delivery_1` (`event_Id`),
  KEY `fk_alert_delivery_2` (`notification_Type`),
  KEY `fk_alert_delivery_3` (`user_id`),
  CONSTRAINT `fk_alert_delivery_1` FOREIGN KEY (`event_Id`) REFERENCES `notification_events` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alert_delivery_2` FOREIGN KEY (`notification_Type`) REFERENCES `notification_type` (`notification_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alert_delivery_3` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_delivery`
--

LOCK TABLES `alert_delivery` WRITE;
/*!40000 ALTER TABLE `alert_delivery` DISABLE KEYS */;
INSERT INTO `alert_delivery` VALUES (17,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 140841. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-06 15:06:55',100),(18,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 140317. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-08 15:14:21',101),(19,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 140317. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-08 15:20:41',101),(20,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 140317. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-08 15:22:30',101),(21,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 193651. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-08 18:10:51',102);
/*!40000 ALTER TABLE `alert_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alert_history`
--

DROP TABLE IF EXISTS `alert_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alert_history` (
  `alert_Id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `notification_type` int(11) NOT NULL,
  `subject` varchar(512) DEFAULT NULL,
  `message` blob,
  `status` char(1) CHARACTER SET latin1 NOT NULL,
  `rec_cre_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `alert_delivery_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`alert_Id`),
  KEY `fk_alert_history_1` (`user_id`),
  KEY `fk_alert_history_2` (`event_id`),
  KEY `fk_alert_history_3` (`notification_type`),
  CONSTRAINT `fk_alert_history_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alert_history_2` FOREIGN KEY (`event_id`) REFERENCES `notification_events` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alert_history_3` FOREIGN KEY (`notification_type`) REFERENCES `notification_type` (`notification_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_history`
--

LOCK TABLES `alert_history` WRITE;
/*!40000 ALTER TABLE `alert_history` DISABLE KEYS */;
INSERT INTO `alert_history` VALUES (17,100,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 140841. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-06 15:06:55','2011-09-06 15:06:55'),(18,101,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 140317. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-08 15:14:21','2011-09-08 15:14:21'),(19,101,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 140317. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-08 15:20:41','2011-09-08 15:20:41'),(20,101,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 140317. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-08 15:22:30','2011-09-08 15:22:30'),(21,102,1,2,'Pool4U : Confirmation code','Dear User, confirmation code to activate your account is 193651. PLease log on to pool4u.in to activate the account. Happy carpooling!','P','2011-09-08 18:10:51','2011-09-08 18:10:51');
/*!40000 ALTER TABLE `alert_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `area_name` varchar(45) NOT NULL,
  `area_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`area_id`),
  KEY `index2` (`area_name`),
  KEY `fk_area_1` (`city_id`),
  CONSTRAINT `fk_area_1` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES ('Swargate',1,1),('Pimple Gurav',2,1),('Pimple Saudagar',3,1),('Hinjewadi',4,1),('Golkunda',5,2),('Nabeerpura',6,2);
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(45) NOT NULL,
  `state_id` int(11) NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `index2` (`city_name`,`city_id`),
  KEY `fk_city_1` (`state_id`),
  CONSTRAINT `fk_city_1` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Pune',3),(2,'Hyderabad',1);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) DEFAULT NULL,
  `sector_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`company_id`),
  KEY `index2` (`company_name`),
  KEY `fk_company_1` (`area_id`),
  KEY `fk_company_3` (`sector_id`),
  CONSTRAINT `fk_company_1` FOREIGN KEY (`area_id`) REFERENCES `area` (`area_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_3` FOREIGN KEY (`sector_id`) REFERENCES `industry_sector` (`sector_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (4,'Infosys Technologies Limited',NULL,4,NULL,NULL),(5,'Cognizent ',NULL,4,NULL,NULL),(6,'Persistent',NULL,4,NULL,NULL);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `confing_param`
--

DROP TABLE IF EXISTS `confing_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `confing_param` (
  `param_name` varchar(128) NOT NULL,
  `param_value` varchar(256) NOT NULL,
  PRIMARY KEY (`param_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confing_param`
--

LOCK TABLES `confing_param` WRITE;
/*!40000 ALTER TABLE `confing_param` DISABLE KEYS */;
INSERT INTO `confing_param` VALUES ('USER_ACCOUNT_EXPIRY_LIMIT','30'),('USER_CREDITS','20'),('USER_CREDIT_EXPIRY_LIMIT','30');
/*!40000 ALTER TABLE `confing_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `device_id` int(11) NOT NULL AUTO_INCREMENT,
  `device_desc` varchar(45) NOT NULL,
  PRIMARY KEY (`device_id`),
  UNIQUE KEY `index2` (`device_desc`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (21,'Mozilla');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_mapping`
--

DROP TABLE IF EXISTS `friend_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_mapping` (
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `travel_count` int(11) DEFAULT NULL,
  `travel_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `fk_friend_mapping_1` (`user_id`),
  KEY `fk_friend_mapping_2` (`friend_id`),
  CONSTRAINT `fk_friend_mapping_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_friend_mapping_2` FOREIGN KEY (`friend_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_mapping`
--

LOCK TABLES `friend_mapping` WRITE;
/*!40000 ALTER TABLE `friend_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `industry_sector`
--

DROP TABLE IF EXISTS `industry_sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `industry_sector` (
  `sector_id` int(11) NOT NULL AUTO_INCREMENT,
  `sector_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sector_id`),
  UNIQUE KEY `index2` (`sector_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `industry_sector`
--

LOCK TABLES `industry_sector` WRITE;
/*!40000 ALTER TABLE `industry_sector` DISABLE KEYS */;
/*!40000 ALTER TABLE `industry_sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_events`
--

DROP TABLE IF EXISTS `notification_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_desc` varchar(45) NOT NULL,
  `is_active` varchar(2) NOT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `index2` (`event_desc`,`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_events`
--

LOCK TABLES `notification_events` WRITE;
/*!40000 ALTER TABLE `notification_events` DISABLE KEYS */;
INSERT INTO `notification_events` VALUES (1,'CONF_CODE','Y');
/*!40000 ALTER TABLE `notification_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_type`
--

DROP TABLE IF EXISTS `notification_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_type` (
  `notification_type` int(11) NOT NULL AUTO_INCREMENT,
  `notification_desc` varchar(45) NOT NULL,
  `is_active` varchar(2) NOT NULL,
  PRIMARY KEY (`notification_type`),
  UNIQUE KEY `index2` (`notification_type`,`notification_desc`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_type`
--

LOCK TABLES `notification_type` WRITE;
/*!40000 ALTER TABLE `notification_type` DISABLE KEYS */;
INSERT INTO `notification_type` VALUES (1,'SMS','Y'),(2,'EMAIL','Y');
/*!40000 ALTER TABLE `notification_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pool`
--

DROP TABLE IF EXISTS `pool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pool` (
  `pool_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `seats` int(11) NOT NULL,
  `auto_approve` varchar(45) DEFAULT NULL,
  `contribution` varchar(45) DEFAULT NULL,
  `rec_cre_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(512) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `start_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `return_time` datetime DEFAULT '0000-00-00 00:00:00',
  `pool_title` varchar(250) NOT NULL,
  `smoking` varchar(1) DEFAULT NULL,
  `women_only` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`pool_id`),
  KEY `fk_pool_2` (`route_id`),
  KEY `fk_pool_3` (`user_id`),
  CONSTRAINT `fk_pool_2` FOREIGN KEY (`route_id`) REFERENCES `routes` (`route_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pool_3` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pool`
--

LOCK TABLES `pool` WRITE;
/*!40000 ALTER TABLE `pool` DISABLE KEYS */;
INSERT INTO `pool` VALUES (78,59,3,'N','60','2011-10-01 12:58:51',NULL,8,'2011-02-10 05:00:00',NULL,'',NULL,NULL),(79,60,3,'N','60','2011-10-01 12:58:51',NULL,8,'2011-02-10 05:00:00',NULL,'',NULL,NULL),(80,61,3,'N','60','2011-10-01 12:56:37',NULL,8,'2011-02-10 05:00:00',NULL,'',NULL,NULL),(81,62,3,'N','60','2011-10-01 12:59:25',NULL,8,'2011-02-10 17:00:00',NULL,'',NULL,NULL),(82,63,3,'N','60','2011-10-01 13:26:11',NULL,8,'2011-02-10 17:00:00',NULL,'',NULL,NULL),(83,64,3,'N','60','2011-10-01 13:27:22',NULL,8,'2011-02-10 17:00:00',NULL,'',NULL,NULL),(84,64,3,'N','60','2011-10-01 13:33:36',NULL,8,'2011-02-10 17:00:00',NULL,'',NULL,NULL),(85,65,3,'N','60','2011-10-01 13:36:22',NULL,8,'2011-02-10 17:00:00',NULL,'',NULL,NULL),(86,65,3,'N','60','2011-10-01 13:38:20',NULL,8,'2011-02-10 17:00:00',NULL,'',NULL,NULL),(87,65,3,'N','60','2011-10-01 13:38:26',NULL,8,'2011-02-10 17:00:00',NULL,'',NULL,NULL),(88,66,3,'N','60','2011-10-01 13:38:49',NULL,8,'2011-02-10 17:00:00',NULL,'',NULL,NULL),(89,66,3,'N','60','2011-10-01 13:39:15',NULL,8,'2011-02-10 17:00:00',NULL,'',NULL,NULL),(90,67,3,'N','50000','2011-10-01 13:47:05',NULL,8,'2011-08-10 08:00:00','2011-09-10 08:00:00','',NULL,NULL),(91,69,3,'N','100','2011-10-02 14:18:58',NULL,8,'2011-03-10 17:00:00',NULL,'Satara to Pune',NULL,NULL),(92,70,3,'N','80','2011-10-02 14:37:29',NULL,8,'2011-10-03 08:30:00','2011-10-03 18:30:00','Satara to Pune',NULL,NULL),(93,71,2,'N','80','2011-10-02 14:38:03',NULL,8,'2011-10-03 08:30:00','2011-10-03 18:30:00','Satara to Pune',NULL,NULL),(94,72,2,'N','70','2011-10-02 14:38:42',NULL,8,'2011-10-03 08:30:00','2011-10-03 18:30:00','Satara to Pune',NULL,NULL),(95,72,2,'N','70','2011-10-02 14:56:20',NULL,8,'2011-10-03 08:30:00','2011-10-03 18:30:00','Satara to Pune',NULL,NULL),(96,73,3,'N','20','2011-10-02 15:01:25',NULL,102,'2011-10-03 00:00:00','2011-10-04 10:00:00','Pimple Nilakh Park, Late Chandrakant Raoji Jagtap Road, Pimple Nilakh, Pimpri Chinchwad|Chinchwad',NULL,NULL),(97,74,3,'N','50','2011-10-02 15:09:05',NULL,102,'2011-10-04 09:00:00',NULL,'Govind Garden, Sai Nagar Park, Pimple Saudagar, Pune|Infosys Phase 2, Hinjewadi',NULL,NULL),(98,76,2,'N','70','2011-10-02 15:14:01',NULL,8,'2011-10-04 08:30:00','2011-10-04 18:30:00','|',NULL,NULL),(99,76,2,'N','70','2011-10-02 15:21:18',NULL,8,'2011-10-04 08:30:00','2011-10-04 18:30:00','|',NULL,NULL),(100,76,3,'N','100','2011-10-02 15:22:01',NULL,8,'2011-10-04 12:00:00',NULL,'|',NULL,NULL),(101,76,3,'N','100','2011-10-02 15:23:15',NULL,8,'2011-10-04 12:00:00',NULL,'|',NULL,NULL),(102,70,3,'N','100','2011-10-02 15:24:58',NULL,8,'2011-10-04 09:00:00',NULL,'|',NULL,NULL),(103,78,3,'N','200','2011-10-02 15:26:41',NULL,8,'2011-10-07 18:30:00',NULL,'|',NULL,NULL),(104,65,3,'N','200','2011-10-02 15:28:01',NULL,8,'2011-10-07 21:00:00',NULL,'Pune|Mumbai',NULL,NULL),(105,69,3,'N','200','2011-10-08 09:04:43',NULL,8,'2011-10-10 08:00:00',NULL,'Shivaji Nagar, Pune|Visawa Park, Satara',NULL,NULL),(106,79,3,'N','20','2011-10-08 14:56:03',NULL,8,'2011-10-10 09:00:00',NULL,'Visawa Park, Satara|Powai Naka, Satara','Y','Y'),(107,78,3,'N','200','2011-10-08 15:15:52',NULL,8,'2011-10-10 09:00:00',NULL,'Visawa Park, Satara|Pune','N','N'),(108,78,3,'N','200','2011-10-08 15:20:10',NULL,8,'2011-10-10 07:00:00',NULL,'Visawa Park, Satara|Pimple Gurav bus Depot, Pune','Y','Y'),(109,69,3,'N','120','2011-10-08 16:24:34',NULL,8,'2011-10-10 08:00:00',NULL,'Satara|Pune','Y','Y'),(110,69,3,'N','125','2011-10-08 16:47:40',NULL,8,'2011-10-10 09:00:00',NULL,'Pune|Satara','Y','N'),(111,65,3,'N','125','2011-10-08 16:48:23',NULL,8,'2011-10-10 09:00:00',NULL,'Pune|Mumbai','Y','N'),(112,69,3,'N','120','2011-10-08 17:30:14',NULL,8,'2011-10-10 08:00:00',NULL,'Pune|Satara','N','N'),(113,69,3,'N','120','2011-10-08 17:37:07',NULL,8,'2011-10-10 08:00:00',NULL,'Pune|Satara','N','N'),(114,80,3,'N','500','2011-10-08 17:41:22',NULL,8,'2011-10-10 08:00:00',NULL,'Pune|Bangalore','N','N'),(126,82,3,'Y','50','2011-10-11 21:01:41',NULL,100,'2011-10-15 18:00:00',NULL,'Satara|Lonand','N','N');
/*!40000 ALTER TABLE `pool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pool_query`
--

DROP TABLE IF EXISTS `pool_query`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pool_query` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `pool_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `query` varchar(512) DEFAULT NULL,
  `response` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `fk_pool_queries_1` (`pool_id`),
  KEY `fk_pool_queries_2` (`user_id`),
  CONSTRAINT `fk_pool_queries_1` FOREIGN KEY (`pool_id`) REFERENCES `pool` (`pool_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pool_queries_2` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pool_query`
--

LOCK TABLES `pool_query` WRITE;
/*!40000 ALTER TABLE `pool_query` DISABLE KEYS */;
/*!40000 ALTER TABLE `pool_query` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pool_user_map`
--

DROP TABLE IF EXISTS `pool_user_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pool_user_map` (
  `pool_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `map_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approval_flag` varchar(1) NOT NULL,
  PRIMARY KEY (`pool_id`,`user_id`),
  KEY `fk_pool_user_map_1` (`pool_id`),
  KEY `fk_pool_user_map_2` (`user_id`),
  CONSTRAINT `fk_pool_user_map_1` FOREIGN KEY (`pool_id`) REFERENCES `pool` (`pool_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pool_user_map_2` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pool_user_map`
--

LOCK TABLES `pool_user_map` WRITE;
/*!40000 ALTER TABLE `pool_user_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `pool_user_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pool_waiting_users`
--

DROP TABLE IF EXISTS `pool_waiting_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pool_waiting_users` (
  `waiting_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `route_id` int(11) NOT NULL,
  `pool_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `company_id` int(11) NOT NULL,
  `notification_count` int(11) NOT NULL,
  PRIMARY KEY (`waiting_id`),
  KEY `fk_pool_waiting_user_1` (`user_id`),
  KEY `fk_pool_waiting_user_2` (`route_id`),
  KEY `fk_pool_waiting_user_3` (`company_id`),
  CONSTRAINT `fk_pool_waiting_user_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pool_waiting_user_2` FOREIGN KEY (`route_id`) REFERENCES `routes` (`route_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pool_waiting_user_3` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pool_waiting_users`
--

LOCK TABLES `pool_waiting_users` WRITE;
/*!40000 ALTER TABLE `pool_waiting_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `pool_waiting_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `routes` (
  `route_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (59,1),(60,1),(61,1),(62,1),(63,1),(64,2),(65,5),(66,2),(67,1),(69,12),(70,2),(71,1),(72,2),(73,1),(74,1),(76,4),(78,3),(79,1),(80,1),(81,4),(82,2);
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `state_id` int(11) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`state_id`),
  UNIQUE KEY `index2` (`state_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'AP'),(3,'MH'),(2,'MP');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestions`
--

DROP TABLE IF EXISTS `suggestions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suggestions` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `suggestion` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestions`
--

LOCK TABLES `suggestions` WRITE;
/*!40000 ALTER TABLE `suggestions` DISABLE KEYS */;
/*!40000 ALTER TABLE `suggestions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_activity`
--

DROP TABLE IF EXISTS `user_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_activity` (
  `user_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `activity_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `fk_user_activity_1` (`user_id`),
  KEY `fk_user_activity_2` (`activity_id`),
  CONSTRAINT `fk_user_activity_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_activity_2` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_activity`
--

LOCK TABLES `user_activity` WRITE;
/*!40000 ALTER TABLE `user_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_allowed_companies`
--

DROP TABLE IF EXISTS `user_allowed_companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_allowed_companies` (
  `user_id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL,
  `map_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`company_id`),
  UNIQUE KEY `index1` (`user_id`,`company_id`),
  KEY `fk_user_allowed_companies_1` (`user_id`),
  KEY `fk_user_allowed_companies_2` (`company_id`),
  CONSTRAINT `fk_user_allowed_companies_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_allowed_companies_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_allowed_companies`
--

LOCK TABLES `user_allowed_companies` WRITE;
/*!40000 ALTER TABLE `user_allowed_companies` DISABLE KEYS */;
INSERT INTO `user_allowed_companies` VALUES (8,4,'2011-10-11 18:58:31'),(8,5,'2011-10-11 18:58:31'),(8,6,'2011-10-11 18:58:31'),(100,4,'2011-10-11 19:00:03'),(100,5,'2011-10-11 19:00:03'),(100,6,'2011-10-11 19:00:03'),(101,4,'2011-10-11 19:01:09'),(101,5,'2011-10-11 19:01:09'),(101,6,'2011-10-11 19:01:09'),(102,4,'2011-10-11 18:59:30'),(102,5,'2011-10-11 18:59:30'),(102,6,'2011-10-11 18:59:30');
/*!40000 ALTER TABLE `user_allowed_companies` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `carpool`.`format_companies_insert`
AFTER INSERT ON `carpool`.`user_allowed_companies`
FOR EACH ROW
call update_usercompanies(NEW.user_id) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `carpool`.`format_companies_delete`
AFTER DELETE ON `carpool`.`user_allowed_companies`
FOR EACH ROW
call update_usercompanies(OLD.user_id) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_allowed_companies_formatted`
--

DROP TABLE IF EXISTS `user_allowed_companies_formatted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_allowed_companies_formatted` (
  `user_id` int(11) NOT NULL,
  `company_id` varchar(1000) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user_allowed_companies_formatted_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_allowed_companies_formatted`
--

LOCK TABLES `user_allowed_companies_formatted` WRITE;
/*!40000 ALTER TABLE `user_allowed_companies_formatted` DISABLE KEYS */;
INSERT INTO `user_allowed_companies_formatted` VALUES (8,'4,5,6'),(100,'4,5,6'),(101,'4,5,6'),(102,'4,5,6');
/*!40000 ALTER TABLE `user_allowed_companies_formatted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_credentials`
--

DROP TABLE IF EXISTS `user_credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_credentials` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `is_authorized` char(1) NOT NULL DEFAULT 'N',
  `login_attempt_count` int(11) NOT NULL,
  `is_active` char(1) NOT NULL DEFAULT 'Y',
  `user_creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_credits` double DEFAULT NULL,
  `credit_expiry_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `rec_cre_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `account_expiry_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_userName` (`user_name`),
  KEY `fk_user_credentials_1` (`user_id`),
  CONSTRAINT `fk_user_credentials_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_credentials`
--

LOCK TABLES `user_credentials` WRITE;
/*!40000 ALTER TABLE `user_credentials` DISABLE KEYS */;
INSERT INTO `user_credentials` VALUES (8,'deepak','deepak','Y',0,'Y','2011-09-11 15:13:32','2011-07-28 17:44:06',20,'2012-08-05 18:30:00','2011-08-05 18:30:00','2011-10-11 15:13:50'),(100,'anu','anu','Y',0,'Y','2011-09-11 15:13:32','2011-09-06 15:07:14',20,'2011-10-06 15:06:55','2011-09-06 15:06:55','2011-10-11 15:13:50'),(101,'amol','amol','Y',0,'Y','2011-09-11 15:13:32','2011-09-08 15:22:44',20,'2011-10-08 15:14:21','2011-09-08 15:14:21','2011-10-11 15:13:50'),(102,'shreyas','shreyas','Y',0,'Y','2011-09-11 15:13:32','2011-09-08 18:11:06',20,'2011-10-08 18:10:51','2011-09-08 18:10:51','2011-10-11 15:13:50');
/*!40000 ALTER TABLE `user_credentials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_data` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type_id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `display_name` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `vendor_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `index2` (`email`,`phone`,`first_name`),
  KEY `fk_user_data_1` (`company_id`),
  KEY `fk_user_data_2` (`vendor_id`),
  KEY `fk_user_data_3` (`user_type_id`),
  CONSTRAINT `fk_user_data_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_data_2` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`vendor_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_data_3` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
INSERT INTO `user_data` VALUES (8,1,'deepak',NULL,'deepak','M','9423080708','deepakndhage',4,NULL),(100,1,'anu',NULL,'anu','F',NULL,NULL,4,NULL),(101,1,'amol',NULL,'amol','M',NULL,NULL,4,NULL),(102,1,'shreyas',NULL,'shreyas','M',NULL,NULL,4,NULL);
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_login` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_ts` timestamp NULL DEFAULT NULL,
  `login_status` varchar(45) DEFAULT NULL,
  `status_comments` varchar(45) DEFAULT NULL,
  `device_id` int(11) NOT NULL,
  `rec_cre_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_id`),
  KEY `fk_user_login_1` (`device_id`),
  KEY `fk_user_login_2` (`user_id`),
  CONSTRAINT `fk_user_login_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_login_2` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login`
--

LOCK TABLES `user_login` WRITE;
/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
INSERT INTO `user_login` VALUES (8,'2011-10-11 21:02:30','SUCCESS','SUCCESS',21,'2011-10-11 21:02:30'),(100,'2011-10-11 21:01:33','SUCCESS','SUCCESS',21,'2011-10-11 21:01:33'),(101,'2011-10-11 19:00:58','SUCCESS','SUCCESS',21,'2011-10-11 19:00:58'),(102,'2011-10-11 18:59:20','SUCCESS','SUCCESS',21,'2011-10-11 18:59:20');
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notification`
--

DROP TABLE IF EXISTS `user_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `notification_type_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `fk_user_notification_1` (`notification_type_id`),
  KEY `fk_user_notification_2` (`event_id`),
  CONSTRAINT `fk_user_notification_1` FOREIGN KEY (`notification_type_id`) REFERENCES `notification_type` (`notification_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_notification_2` FOREIGN KEY (`event_id`) REFERENCES `notification_events` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notification`
--

LOCK TABLES `user_notification` WRITE;
/*!40000 ALTER TABLE `user_notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_preferences`
--

DROP TABLE IF EXISTS `user_preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_preferences` (
  `user_id` int(11) NOT NULL,
  `notification_id` int(11) NOT NULL,
  KEY `fk_user_preferences_1` (`user_id`),
  KEY `fk_user_preferences_2` (`notification_id`),
  CONSTRAINT `fk_user_preferences_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_preferences_2` FOREIGN KEY (`notification_id`) REFERENCES `user_notification` (`notification_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_preferences`
--

LOCK TABLES `user_preferences` WRITE;
/*!40000 ALTER TABLE `user_preferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_stats`
--

DROP TABLE IF EXISTS `user_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_stats` (
  `user_id` int(11) NOT NULL,
  `reliable_count` int(11) NOT NULL DEFAULT '10',
  PRIMARY KEY (`user_id`),
  KEY `fk_user_stats_1` (`user_id`),
  CONSTRAINT `fk_user_stats_1` FOREIGN KEY (`user_id`) REFERENCES `user_data` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_stats`
--

LOCK TABLES `user_stats` WRITE;
/*!40000 ALTER TABLE `user_stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `user_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type_desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_type_id`),
  UNIQUE KEY `index2` (`user_type_desc`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (3,'Corporate'),(1,'User'),(2,'Vendor');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendor` (
  `vendor_id` int(11) NOT NULL AUTO_INCREMENT,
  `vendor_name` varchar(45) NOT NULL,
  `state_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`vendor_id`),
  KEY `index2` (`vendor_name`),
  KEY `fk_vendor_1` (`area_id`),
  KEY `fk_vendor_2` (`city_id`),
  KEY `fk_vendor_3` (`state_id`),
  CONSTRAINT `fk_vendor_1` FOREIGN KEY (`area_id`) REFERENCES `area` (`area_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendor_2` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendor_3` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-10-13 21:42:31
