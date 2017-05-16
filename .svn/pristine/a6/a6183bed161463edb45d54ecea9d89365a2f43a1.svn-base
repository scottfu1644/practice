CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 10.43.142.199    Database: test
-- ------------------------------------------------------
-- Server version	5.6.10

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DepName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `isView` int(11) DEFAULT NULL COMMENT '是否可视 1 不可视',
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(45) DEFAULT NULL COMMENT '角色名',
  `RoleDesc` varchar(45) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_menu`
--

DROP TABLE IF EXISTS `roles_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuId` int(11) DEFAULT NULL,
  `rolesId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `PK_roles_id_idx` (`rolesId`),
  KEY `pk_menu_id_idx` (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_menu`
--

LOCK TABLES `roles_menu` WRITE;
/*!40000 ALTER TABLE `roles_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testModel`
--

DROP TABLE IF EXISTS `testModel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testModel` (
  `test1` varchar(255) DEFAULT NULL,
  `test2` varchar(255) DEFAULT NULL,
  `test3` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testModel`
--

LOCK TABLES `testModel` WRITE;
/*!40000 ALTER TABLE `testModel` DISABLE KEYS */;
INSERT INTO `testModel` VALUES ('a','b','c');
/*!40000 ALTER TABLE `testModel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` int(11) DEFAULT NULL,
  `nickname` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,223,'1224我'),(3,334,'我们'),(4,1,'q'),(5,2,'r'),(6,3,'e'),(7,4,'e'),(8,5,'q'),(9,6,'q'),(10,7,'qe'),(11,8,'e'),(12,9,'q'),(13,10,'q'),(14,12,'eqe'),(15,13,'q'),(16,1,'q'),(17,2,'r'),(18,3,'e'),(19,4,'e'),(20,5,'q'),(21,6,'q'),(22,7,'qe'),(23,8,'e'),(24,9,'q'),(25,10,'q'),(26,12,'eqe'),(27,13,'q'),(28,1,'q'),(29,2,'r'),(30,3,'e'),(31,4,'e'),(32,5,'q'),(33,6,'q'),(34,7,'qe'),(35,8,'e'),(36,9,'q'),(37,10,'q'),(38,12,'eqe'),(39,13,'q'),(40,1,'q'),(41,2,'r'),(42,3,'e'),(43,4,'e'),(44,5,'q'),(45,6,'q'),(46,7,'qe'),(47,8,'e'),(48,9,'q'),(49,10,'q'),(50,12,'eqe'),(51,13,'q'),(52,1,'q'),(53,2,'r'),(54,3,'e'),(55,4,'e'),(56,5,'q'),(57,6,'q'),(58,7,'qe'),(59,8,'e'),(60,9,'q'),(61,10,'q'),(62,12,'eqe'),(63,13,'q'),(64,1,'q'),(65,2,'r'),(66,3,'e'),(67,4,'e'),(68,5,'q'),(69,6,'q'),(70,7,'qe'),(71,8,'e'),(72,9,'q'),(73,10,'q'),(74,12,'eqe'),(75,13,'q'),(76,1,'q'),(77,2,'r'),(78,3,'e'),(79,4,'e'),(80,5,'q'),(81,6,'q'),(82,7,'qe'),(83,8,'e'),(84,9,'q'),(85,10,'q'),(86,12,'eqe'),(87,13,'q'),(88,1,'q'),(89,2,'r'),(90,3,'e'),(91,4,'e'),(92,5,'q'),(93,6,'q'),(94,7,'qe'),(95,8,'e'),(96,9,'q'),(97,10,'q'),(98,12,'eqe'),(99,13,'q');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `RoleId` int(11) DEFAULT NULL,
  `UserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `PK_USER_ID_idx` (`UserId`),
  KEY `PK_ROLE_ID_idx` (`RoleId`),
  CONSTRAINT `PK_ROLE_ID` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PK_USER_ID` FOREIGN KEY (`UserId`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DepId` int(11) DEFAULT NULL,
  `UserID` varchar(45) NOT NULL,
  `UserName` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `RealName` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Sex` varchar(45) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL COMMENT '用户状态:0,正常.',
  `IsDel` int(11) DEFAULT NULL COMMENT '是否删除,1删除.',
  `CrtDate` datetime DEFAULT NULL,
  `CrtUser` varchar(45) DEFAULT NULL,
  `UptDate` datetime DEFAULT NULL,
  `UptUser` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`UserID`),
  UNIQUE KEY `UserID_UNIQUE` (`UserID`),
  KEY `PK_DEP_ID_idx` (`DepId`),
  CONSTRAINT `PK_DEP_ID` FOREIGN KEY (`DepId`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-03 15:06:39
