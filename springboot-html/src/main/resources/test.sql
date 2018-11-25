/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2018-11-25 23:26:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cas_user
-- ----------------------------
DROP TABLE IF EXISTS `cas_user`;
CREATE TABLE `cas_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `pass_word` varchar(21000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cas_user
-- ----------------------------
INSERT INTO `cas_user` VALUES ('1', 'admin', '123456');
INSERT INTO `cas_user` VALUES ('2', 'user1', '123456');
INSERT INTO `cas_user` VALUES ('3', 'user2', '123456');