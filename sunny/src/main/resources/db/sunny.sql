CREATE DATABASE `sunny` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: sunny
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.04.1

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
-- Table structure for table `t_device`
--

DROP TABLE IF EXISTS `t_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_group` varchar(50) DEFAULT NULL,
  `device_state` varchar(255) NOT NULL,
  `device_type` varchar(50) DEFAULT NULL,
  `identification_code` varchar(150) NOT NULL,
  `name` varchar(100) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gtwm6sw1u1xw4oiqowdxleiqd` (`user_id`),
  CONSTRAINT `FK_gtwm6sw1u1xw4oiqowdxleiqd` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_device`
--

LOCK TABLES `t_device` WRITE;
/*!40000 ALTER TABLE `t_device` DISABLE KEYS */;
INSERT INTO `t_device` VALUES (1,'light','CONNECTED','home light','111222asdf|1212qdasdfasd','light001',2),(2,'light','CONNECTED','home light','11222asdfx|122qdasdfasda','lightxx002',2),(3,'Air Conditioner','DISCONNECTED','Conditioner','11x222asdfx|122qxdasdfasda','air001',2);
/*!40000 ALTER TABLE `t_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_device_feature`
--

DROP TABLE IF EXISTS `t_device_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_device_feature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `feature_name` varchar(150) NOT NULL,
  `feature_num` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4lkbuc35htwnaki9vue59j6ws` (`feature_name`),
  UNIQUE KEY `UK_rj6bgn7pk78cwh5gnwneuixo9` (`feature_num`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_device_feature`
--

LOCK TABLES `t_device_feature` WRITE;
/*!40000 ALTER TABLE `t_device_feature` DISABLE KEYS */;
INSERT INTO `t_device_feature` VALUES (1,'set temperature to ','setTemperatureTo','3aa1831f0325477e988973e50dd74a2d');
/*!40000 ALTER TABLE `t_device_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_device_feature_instance`
--

DROP TABLE IF EXISTS `t_device_feature_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_device_feature_instance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `feature_instance_num` varchar(255) NOT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `device_feature_id` bigint(20) DEFAULT NULL,
  `strategy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qi3ctkq57r7hr9dww6yyur526` (`feature_instance_num`),
  KEY `FK_cdjuwrxl1ojgs65v2amgbis35` (`device_id`),
  KEY `FK_psbmn3bdehx3mx0t8cvby5fun` (`device_feature_id`),
  KEY `FK_p9qp8wgnltb7rbntw44h2k5p6` (`strategy_id`),
  CONSTRAINT `FK_cdjuwrxl1ojgs65v2amgbis35` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`id`),
  CONSTRAINT `FK_p9qp8wgnltb7rbntw44h2k5p6` FOREIGN KEY (`strategy_id`) REFERENCES `t_strategy` (`id`),
  CONSTRAINT `FK_psbmn3bdehx3mx0t8cvby5fun` FOREIGN KEY (`device_feature_id`) REFERENCES `t_device_feature` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_device_feature_instance`
--

LOCK TABLES `t_device_feature_instance` WRITE;
/*!40000 ALTER TABLE `t_device_feature_instance` DISABLE KEYS */;
INSERT INTO `t_device_feature_instance` VALUES (3,'e4182324b0d64fe8a3c591318c2a05d9',1,1,3),(4,'cd82b8f7367e477bb6ab50cae4508def',1,1,3);
/*!40000 ALTER TABLE `t_device_feature_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_device_feature_instance_assemble`
--

DROP TABLE IF EXISTS `t_device_feature_instance_assemble`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_device_feature_instance_assemble` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assemble_feature_instance_id` bigint(20) DEFAULT NULL,
  `father_feature_instance_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hi8f8j2d7l612bri1dbb2a097` (`assemble_feature_instance_id`),
  KEY `FK_k9f1q495c3ck36auiy5nu37jx` (`father_feature_instance_id`),
  CONSTRAINT `FK_hi8f8j2d7l612bri1dbb2a097` FOREIGN KEY (`assemble_feature_instance_id`) REFERENCES `t_device_feature_instance` (`id`),
  CONSTRAINT `FK_k9f1q495c3ck36auiy5nu37jx` FOREIGN KEY (`father_feature_instance_id`) REFERENCES `t_device_feature_instance` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_device_feature_instance_assemble`
--

LOCK TABLES `t_device_feature_instance_assemble` WRITE;
/*!40000 ALTER TABLE `t_device_feature_instance_assemble` DISABLE KEYS */;
INSERT INTO `t_device_feature_instance_assemble` VALUES (1,4,3);
/*!40000 ALTER TABLE `t_device_feature_instance_assemble` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_device_feature_run_log`
--

DROP TABLE IF EXISTS `t_device_feature_run_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_device_feature_run_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `feature_run_log_num` varchar(255) NOT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `device_feature_id` bigint(20) DEFAULT NULL,
  `owner_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dbw5rpj4n919hpx2xuniqcjb` (`feature_run_log_num`),
  KEY `FK_pyhtj36s77n77gl0apvvd2gh2` (`device_id`),
  KEY `FK_ebdcpi4o8jhvlcjtc6nxwkva3` (`device_feature_id`),
  KEY `FK_tfqqcp54iby5hft9qa2njrt0v` (`owner_user_id`),
  CONSTRAINT `FK_ebdcpi4o8jhvlcjtc6nxwkva3` FOREIGN KEY (`device_feature_id`) REFERENCES `t_device_feature` (`id`),
  CONSTRAINT `FK_pyhtj36s77n77gl0apvvd2gh2` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`id`),
  CONSTRAINT `FK_tfqqcp54iby5hft9qa2njrt0v` FOREIGN KEY (`owner_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_device_feature_run_log`
--

LOCK TABLES `t_device_feature_run_log` WRITE;
/*!40000 ALTER TABLE `t_device_feature_run_log` DISABLE KEYS */;
INSERT INTO `t_device_feature_run_log` VALUES (1,'428d7909e4fe47a3a5a0a9d8ba05fad0',1,1,2);
/*!40000 ALTER TABLE `t_device_feature_run_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_feature_function`
--

DROP TABLE IF EXISTS `t_feature_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_feature_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `function_group` varchar(255) NOT NULL,
  `function_name` varchar(255) NOT NULL,
  `function_type` varchar(255) NOT NULL,
  `sequence_num` int(11) NOT NULL,
  `device_feature_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9ovsge5tnb7amqre10xo5brtg` (`device_feature_id`),
  CONSTRAINT `FK_9ovsge5tnb7amqre10xo5brtg` FOREIGN KEY (`device_feature_id`) REFERENCES `t_device_feature` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_feature_function`
--

LOCK TABLES `t_feature_function` WRITE;
/*!40000 ALTER TABLE `t_feature_function` DISABLE KEYS */;
INSERT INTO `t_feature_function` VALUES (1,'deviceGroup','setTemperature','SYNC',1,1),(2,'deviceGroup','turnOn','SYNC',0,1),(3,'deviceGroup','turnOff','SYNC',2,1);
/*!40000 ALTER TABLE `t_feature_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_feature_instance_function_value`
--

DROP TABLE IF EXISTS `t_feature_instance_function_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_feature_instance_function_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arg_name` varchar(50) DEFAULT NULL,
  `function_group` varchar(255) NOT NULL,
  `function_name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `device_feature_instance_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dlufh4ltjj3ngrnvf7m4rudih` (`device_feature_instance_id`),
  CONSTRAINT `FK_dlufh4ltjj3ngrnvf7m4rudih` FOREIGN KEY (`device_feature_instance_id`) REFERENCES `t_device_feature_instance` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_feature_instance_function_value`
--

LOCK TABLES `t_feature_instance_function_value` WRITE;
/*!40000 ALTER TABLE `t_feature_instance_function_value` DISABLE KEYS */;
INSERT INTO `t_feature_instance_function_value` VALUES (5,'temperature','deviceGroup','setTemperature','25.5',3),(6,'isopen','deviceGroup','setTemperature','true',3),(7,'temperature','deviceGroup','setTemperature','30',4),(8,'isopen','deviceGroup','setTemperature','false',4);
/*!40000 ALTER TABLE `t_feature_instance_function_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_feature_trigger`
--

DROP TABLE IF EXISTS `t_feature_trigger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_feature_trigger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trigger_type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `device_feature_instance_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_849e25jo85yjq1r0c3ejn660m` (`device_feature_instance_id`),
  CONSTRAINT `FK_849e25jo85yjq1r0c3ejn660m` FOREIGN KEY (`device_feature_instance_id`) REFERENCES `t_device_feature_instance` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_feature_trigger`
--

LOCK TABLES `t_feature_trigger` WRITE;
/*!40000 ALTER TABLE `t_feature_trigger` DISABLE KEYS */;
INSERT INTO `t_feature_trigger` VALUES (3,'TIMER','{time : 2015-06-19 12:10:30}',3),(4,'TIMER','{time : 2015-06-19 12:10:30}',4);
/*!40000 ALTER TABLE `t_feature_trigger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_function_argument`
--

DROP TABLE IF EXISTS `t_function_argument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_function_argument` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_type` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `object_state` varchar(255) NOT NULL,
  `feature_function_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dyc3y4ue7l6n1km121w7n0cle` (`feature_function_id`),
  CONSTRAINT `FK_dyc3y4ue7l6n1km121w7n0cle` FOREIGN KEY (`feature_function_id`) REFERENCES `t_feature_function` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_function_argument`
--

LOCK TABLES `t_function_argument` WRITE;
/*!40000 ALTER TABLE `t_function_argument` DISABLE KEYS */;
INSERT INTO `t_function_argument` VALUES (1,'FLOAT','temperature','ACTIVE',1),(2,'BOOLEAN','isopen','ACTIVE',1);
/*!40000 ALTER TABLE `t_function_argument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_log_function_value`
--

DROP TABLE IF EXISTS `t_log_function_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_log_function_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arg_name` varchar(50) DEFAULT NULL,
  `function_group` varchar(255) NOT NULL,
  `function_name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `feature_run_log_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_klb68fr96jvxsd0mqhu2e0x9l` (`feature_run_log_id`),
  CONSTRAINT `FK_klb68fr96jvxsd0mqhu2e0x9l` FOREIGN KEY (`feature_run_log_id`) REFERENCES `t_device_feature_run_log` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log_function_value`
--

LOCK TABLES `t_log_function_value` WRITE;
/*!40000 ALTER TABLE `t_log_function_value` DISABLE KEYS */;
INSERT INTO `t_log_function_value` VALUES (1,'temperature','deviceGroup','setTemperature','25.5',1),(2,'isopen','deviceGroup','setTemperature','true',1);
/*!40000 ALTER TABLE `t_log_function_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_strategy`
--

DROP TABLE IF EXISTS `t_strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_strategy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  `strategy_name` varchar(150) NOT NULL,
  `strategy_num` varchar(255) NOT NULL,
  `owner_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ribvwr391b35fben100saoo4n` (`strategy_num`),
  KEY `FK_n3t191dv4rjxsn8y5su9nybsy` (`owner_user_id`),
  CONSTRAINT `FK_n3t191dv4rjxsn8y5su9nybsy` FOREIGN KEY (`owner_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_strategy`
--

LOCK TABLES `t_strategy` WRITE;
/*!40000 ALTER TABLE `t_strategy` DISABLE KEYS */;
INSERT INTO `t_strategy` VALUES (3,' strategy instance','SCHEDULING','strategy001','b754a544b41d4a7b813d2002c07bc138',2);
/*!40000 ALTER TABLE `t_strategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(100) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `expires_in` bigint(20) DEFAULT NULL,
  `hash_user_id` varchar(100) NOT NULL,
  `refresh_token` varchar(100) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `screen_name` varchar(100) NOT NULL,
  `token_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i6qjjoe560mee5ajdg7v1o6mi` (`email`),
  UNIQUE KEY `UK_q618xf4spfeygatdhg4m77vrd` (`hash_user_id`),
  UNIQUE KEY `UK_s2f7hcx9rdcpan2hvmgrg56pp` (`screen_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (2,'69c14dd9-e896-4dd3-9351-6049b51f1342','ching-zhou@anicloud.com',43199,'4c781d51d638cf133df74e6176f839e2','f6f3f52d-06f4-49fc-ba23-c1e3c96e8809','read write','Yeh','bearer');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-25 14:08:30
