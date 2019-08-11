-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(45) NOT NULL,
  `a_description` varchar(255) NOT NULL,
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `coupon_id` int(11) DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (8,'长期活动','长期活动','2020-08-10 16:00:00',12,'2019-08-09 16:00:00'),(9,'七夕节活动','七夕节活动','2019-08-10 16:00:00',13,'2019-08-03 16:00:00');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity_movie` (
  `activity_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_movie`
--

LOCK TABLES `activity_movie` WRITE;
/*!40000 ALTER TABLE `activity_movie` DISABLE KEYS */;
INSERT INTO `activity_movie` VALUES (8,31),(8,30),(8,1),(8,28),(9,1);
/*!40000 ALTER TABLE `activity_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `target_amount` float DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (12,'满50减5','满50减5',50,5,'2019-08-09 16:00:00','2019-12-30 16:00:00'),(13,'满60减10','满60减10',60,10,'2019-08-03 16:00:00','2019-08-10 16:00:00');
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coupon_user` (
  `coupon_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user` DISABLE KEYS */;
INSERT INTO `coupon_user` VALUES (12,22),(12,23),(12,21),(12,21);
/*!40000 ALTER TABLE `coupon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `column` int(11) DEFAULT NULL,
  `row` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'1号厅',10,5),(2,'2号厅',12,8);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poster_url` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `screen_writer` varchar(255) DEFAULT NULL,
  `starring` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=749787945,828316934&fm=11&gp=0.jpg','饺子','饺子','吕艳婷，囧森瑟夫，瀚墨，陈浩','动画电影','中国大陆','普通话',110,'2019-07-24 16:00:00','哪吒之魔童降世','天地灵气孕育出一颗能量巨大的混元珠，元始天尊将混元珠提炼成灵珠和魔丸，灵珠投胎为人，助周伐纣时可堪大用；而魔丸则会诞出魔王，为祸人间。元始天尊启动了天劫咒语，3年后天雷将会降临，摧毁魔丸。太乙受命将灵珠托生于陈塘关李靖家的儿子哪吒身上。然而阴差阳错，灵珠和魔丸竟然被掉包。本应是灵珠英雄的哪吒却成了混世大魔王。调皮捣蛋顽劣不堪的哪吒却徒有一颗做英雄的心。然而面对众人对哪吒的误解和即将来临的天雷的降临，哪吒是否命中注定会立地成魔，他将何去何从',0),(28,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565499261615&di=e077e816bd60e480313b1846f7d4b226&imgtype=0&src=http%3A%2F%2Fdingyue.ws.126.net%2FbxjXXu3YGt3tgq5pOTkDASQ3l5yo9AlAIUoEncgH%3DaGvR1565264212371compressflag.png','邱礼涛','李敏','刘德华，古天乐，苗侨伟，林嘉欣，张国强，陈家乐，卫诗雅，恭硕良','剧情、动作、悬疑、犯罪','中国香港','粤语',99,'2019-07-04 16:00:00','扫毒2：天地对决','毒品市场维持四分天下的格局已久，幼时父亲因毒品去世又亲眼目睹自己的儿子也因毒品坠楼身亡的余顺天为了让香港不再有毒贩，以毒攻毒，策划的一连串黑吃黑事件企图“毒霸”香港毒品市场，警员林正风（苗侨伟饰）便带着他的扫毒行动组全力追缉毒犯。后林警官的女儿因朋友被毒品害死而向慈善家兼金融巨子余顺天（刘德华饰）求助，他悬赏一亿追杀香港最大毒贩，此举在社会上引起轩然大波。原来，余顺天和地藏有着不可告人的同门关系，一场天地对决一触即发。在二十年的同门情谊面前，兄弟二人又将如何面对这场“毒局”？与此同时，林正风妻子、同事在执行缉毒任务时被吸毒者和毒贩残忍杀害，背负着丧妻之痛的他也将与毒贩们展开殊死对决。',0),(29,'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1911342301,4037535416&fm=11&gp=0.jpg','宫崎骏','宫崎骏','柊瑠美，入野自由，中村彰男，夏木真理','剧情、动画、奇幻','日本','日语',125,'2019-06-20 16:00:00','千与千寻','10岁的少女千寻与父母一起从都市搬家到了乡下。没想到在搬家的途中，一家人发生了意外。他们进入了汤屋老板魔女控制的奇特世界——在那里不劳动的人将会被变成动物。千寻的爸爸妈妈因贪吃变成了猪，千寻为了救爸爸妈妈经历了很多磨难，在期间她遇见了白龙，一个既聪明又冷酷的少年，在经历了很多事情之后，千寻最后救出了爸爸妈妈，拯救了白龙。',1),(30,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565499901765&di=9446929e4e39c77ba1165e86bd299afe&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20190729%2F12015046e85f41c98838728b1accbdb5.jpeg','文伟鸿','','张家辉，古天乐，吴镇宇，姜珮瑶','动作、犯罪','中国香港','粤语',98,'2019-08-06 16:00:00','使徒行者2：谍影行动','电影讲的是童年兄弟意外失散，30年后因一桩罪案相见，两人已身处不同阵营。随着对罪案调查的深入，一个多年来隐于幕后的恐怖组织渐渐浮出水面。',0),(31,'http://p3.ifengimg.com/2019_30/a33e8fc2-f3b0-42cc-ba51-213f694d6669_AEE3E19A6C2544120113E8291538DD35BF82FDB9_w1000_h1428.jpg','大卫·雷奇','克里斯·摩根，盖瑞·斯科特·汤普森，德鲁·皮尔斯',' 道恩·强森，杰森·斯坦森，伊德里斯·艾尔巴，凡妮莎·柯比，艾莎·冈萨雷斯','动作、犯罪','美国','英语',134,'2019-08-22 16:00:00','速度与激情：特别行动','一个是美国外交安全局的忠诚特工、身材魁梧的执法者霍布斯（强森饰），一个是前英国军事特工精英、无法无天的恶棍肖（斯坦森饰）。在2015年的《速度与激情7》中首次对峙之后，两人不论言语还是肢体都冲突不断，一直试图打倒对方。 \n　　然而，通过高科技进行了基因增强的无政府主义者布里克斯顿（伊德瑞斯·艾尔巴饰）控制了一种可能永远改变人类命运的不为人知的生化武器，并且还打败了一位大胆优秀又特立独行的军情六处特工（曾出演《王冠》的凡妮莎·科比饰）——她恰好是肖的妹妹，为了扳倒这个世界上唯一可能比他们更厉害的敌人，这两个不共戴天的宿敌不得不联手。 ',0);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie_like` (
  `movie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`movie_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `movie_like` WRITE;
/*!40000 ALTER TABLE `movie_like` DISABLE KEYS */;
INSERT INTO `movie_like` VALUES (1,21,'2019-08-11 01:58:44'),(1,22,'2019-08-11 03:18:37'),(28,21,'2019-08-11 02:57:59'),(28,23,'2019-08-11 03:44:36'),(29,21,'2019-08-11 02:57:34'),(30,21,'2019-08-11 02:57:26'),(30,22,'2019-08-11 03:15:31'),(31,21,'2019-08-11 02:57:20');
/*!40000 ALTER TABLE `movie_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `fare` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (71,1,1,'2019-08-11 07:00:00','2019-08-11 09:00:00',37),(72,1,1,'2019-08-12 07:00:00','2019-08-12 09:00:00',37),(73,2,1,'2019-08-12 07:00:00','2019-08-12 09:00:00',37),(74,2,1,'2019-08-11 07:00:00','2019-08-11 09:00:00',37),(75,1,30,'2019-08-11 05:00:00','2019-08-11 06:59:00',40),(76,2,30,'2019-08-11 05:00:00','2019-08-11 06:59:00',40),(77,1,30,'2019-08-12 05:00:00','2019-08-12 06:59:00',40),(78,2,30,'2019-08-12 05:00:00','2019-08-12 06:59:00',40),(79,2,28,'2019-08-12 14:00:00','2019-08-12 15:59:00',30),(80,1,28,'2019-08-12 14:00:00','2019-08-12 15:59:00',30),(81,1,28,'2019-08-11 14:00:00','2019-08-11 15:59:00',30),(82,2,28,'2019-08-11 14:00:00','2019-08-11 15:59:00',30),(83,1,31,'2019-08-23 13:00:00','2019-08-23 15:20:00',40),(84,2,31,'2019-08-23 13:00:00','2019-08-23 15:20:00',40),(85,1,31,'2019-08-24 13:00:00','2019-08-24 15:20:00',40),(86,1,31,'2019-08-25 13:00:00','2019-08-25 15:20:00',40),(87,1,31,'2019-08-26 13:00:00','2019-08-26 15:20:00',40),(88,1,30,'2019-08-13 01:00:00','2019-08-13 03:00:00',40),(89,2,30,'2019-08-13 01:00:00','2019-08-13 03:00:00',40),(90,1,30,'2019-08-14 01:00:00','2019-08-14 03:00:00',40),(91,1,30,'2019-08-15 01:00:00','2019-08-15 03:00:00',40),(92,1,30,'2019-08-16 01:00:00','2019-08-16 03:00:00',40),(93,1,30,'2019-08-17 01:00:00','2019-08-17 03:00:00',40),(94,1,30,'2019-08-18 01:00:00','2019-08-18 03:00:00',40),(95,1,30,'2019-08-19 01:00:00','2019-08-19 03:00:00',40),(96,1,30,'2019-08-20 01:00:00','2019-08-20 03:00:00',40),(97,1,1,'2019-08-12 01:00:00','2019-08-12 03:00:00',40),(98,2,1,'2019-08-12 01:00:00','2019-08-12 03:00:00',40);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ticket` (
  `user_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `column_index` int(11) DEFAULT NULL,
  `row_index` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (21,73,5,4,1,89,'2019-08-11 01:59:10'),(21,73,6,4,1,90,'2019-08-11 01:59:10'),(21,97,4,2,2,91,'2019-08-11 02:58:25'),(21,97,5,2,2,92,'2019-08-11 02:58:25'),(21,88,4,3,1,93,'2019-08-11 03:07:51'),(21,88,5,3,1,94,'2019-08-11 03:07:51'),(22,76,4,4,1,95,'2019-08-11 03:15:39'),(22,76,5,4,1,96,'2019-08-11 03:15:39'),(22,76,6,4,1,97,'2019-08-11 03:15:39'),(22,76,7,4,1,98,'2019-08-11 03:15:39'),(22,76,4,5,1,99,'2019-08-11 03:15:39'),(22,76,5,5,1,100,'2019-08-11 03:15:39'),(22,76,6,5,1,101,'2019-08-11 03:15:39'),(22,76,7,5,1,102,'2019-08-11 03:15:39'),(22,71,3,3,1,103,'2019-08-11 03:18:42'),(22,71,4,3,1,104,'2019-08-11 03:18:42'),(22,71,5,3,1,105,'2019-08-11 03:18:42'),(22,71,6,3,1,106,'2019-08-11 03:18:42'),(23,82,4,4,1,107,'2019-08-11 03:44:49'),(23,82,5,4,1,108,'2019-08-11 03:44:49'),(23,82,6,4,1,109,'2019-08-11 03:44:49'),(23,82,7,4,1,110,'2019-08-11 03:44:49'),(23,82,4,5,1,111,'2019-08-11 03:44:49'),(23,82,5,5,1,112,'2019-08-11 03:44:49'),(23,82,6,5,1,113,'2019-08-11 03:44:49'),(23,82,7,5,1,114,'2019-08-11 03:44:49'),(23,82,4,6,1,115,'2019-08-11 03:44:49'),(23,82,5,6,1,116,'2019-08-11 03:44:49'),(23,82,6,6,1,117,'2019-08-11 03:44:49'),(23,82,7,6,1,118,'2019-08-11 03:44:49'),(21,71,0,0,1,119,'2019-08-11 04:27:50'),(21,71,1,0,1,120,'2019-08-11 04:27:50'),(21,71,2,0,1,121,'2019-08-11 04:27:50'),(21,71,3,0,1,122,'2019-08-11 04:27:50'),(21,71,4,0,1,123,'2019-08-11 04:27:50'),(21,71,5,0,1,124,'2019-08-11 04:27:50'),(21,71,6,0,1,125,'2019-08-11 04:27:50'),(21,71,7,0,1,126,'2019-08-11 04:27:50'),(21,71,8,0,1,127,'2019-08-11 04:27:50'),(21,71,9,0,1,128,'2019-08-11 04:27:50'),(21,71,0,1,1,129,'2019-08-11 04:27:50'),(21,71,1,1,1,130,'2019-08-11 04:27:50'),(21,71,2,1,1,131,'2019-08-11 04:27:50'),(21,71,3,1,1,132,'2019-08-11 04:27:50'),(21,71,4,1,1,133,'2019-08-11 04:27:50'),(21,71,5,1,1,134,'2019-08-11 04:27:50'),(21,71,6,1,1,135,'2019-08-11 04:27:50'),(21,71,7,1,1,136,'2019-08-11 04:27:50'),(21,71,8,1,1,137,'2019-08-11 04:27:50'),(21,71,9,1,1,138,'2019-08-11 04:27:50'),(21,71,0,2,1,139,'2019-08-11 04:27:50'),(21,71,1,2,1,140,'2019-08-11 04:27:50'),(21,71,2,2,1,141,'2019-08-11 04:27:50'),(21,71,3,2,1,142,'2019-08-11 04:27:50'),(21,71,4,2,1,143,'2019-08-11 04:27:50'),(21,71,5,2,1,144,'2019-08-11 04:27:50'),(21,71,6,2,1,145,'2019-08-11 04:27:50'),(21,71,7,2,1,146,'2019-08-11 04:27:50'),(21,71,8,2,1,147,'2019-08-11 04:27:50'),(21,71,9,2,1,148,'2019-08-11 04:27:50'),(21,71,0,3,1,149,'2019-08-11 04:27:50'),(21,71,1,3,1,150,'2019-08-11 04:27:50'),(21,71,2,3,1,151,'2019-08-11 04:27:50'),(21,71,7,3,1,152,'2019-08-11 04:27:50'),(21,71,8,3,1,153,'2019-08-11 04:27:50'),(21,71,9,3,1,154,'2019-08-11 04:27:50'),(21,74,6,4,1,155,'2019-08-11 05:21:19'),(21,97,3,2,2,158,'2019-08-11 12:44:49'),(21,97,4,2,2,159,'2019-08-11 12:44:49'),(21,97,5,2,2,160,'2019-08-11 12:44:49');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (20,'root','njumki'),(21,'test','njumki'),(22,'test1','123456'),(23,'test2','123456');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view` DISABLE KEYS */;
INSERT INTO `view` VALUES (2,5);
/*!40000 ALTER TABLE `view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vip_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vip_card_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card` DISABLE KEYS */;
INSERT INTO `vip_card` VALUES (9,21,868,'2019-08-11 02:58:41'),(12,22,0,'2019-08-11 03:38:07');
/*!40000 ALTER TABLE `vip_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cinema'
--

--
-- Dumping routines for database 'cinema'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-11 21:33:10
