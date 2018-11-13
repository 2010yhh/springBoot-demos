/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2018-11-13 22:57:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `perm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `perm_name` varchar(20) NOT NULL COMMENT '权限名',
  PRIMARY KEY (`perm_id`),
  UNIQUE KEY `perm_name` (`perm_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'add');
INSERT INTO `sys_permission` VALUES ('2', 'delete');
INSERT INTO `sys_permission` VALUES ('4', 'query');
INSERT INTO `sys_permission` VALUES ('3', 'update');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin');
INSERT INTO `sys_role` VALUES ('2', 'role1');
INSERT INTO `sys_role` VALUES ('3', 'role2');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色-权限关联表的id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `perm_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `perm_id` (`perm_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  CONSTRAINT `sys_role_permission_perm_id` FOREIGN KEY (`perm_id`) REFERENCES `sys_permission` (`perm_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_permission_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色-权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '3');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '4');
INSERT INTO `sys_role_permission` VALUES ('5', '2', '4');
INSERT INTO `sys_role_permission` VALUES ('6', '3', '4');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(40) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实名',
  `pass_word` varchar(255) NOT NULL COMMENT '用户密码',
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`) USING BTREE,
  KEY `real_name` (`real_name`)
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('238', 'root', 'user104_RealName', 'root', '1111111', null, '111', '2018-10-09 18:50:03', '2018-09-28 19:28:06', null);
INSERT INTO `sys_user` VALUES ('239', 'user1', 'user1_RealName', 'user1', null, null, null, '2018-10-23 11:35:55', '2018-10-23 11:35:55', null);
INSERT INTO `sys_user` VALUES ('240', 'user2', 'user2_RealName', 'user2', null, null, null, '2018-10-23 14:29:51', '2018-10-23 14:29:51', null);
INSERT INTO `sys_user` VALUES ('241', 'yhh', 'yhh', 'yhh', null, null, null, '2018-11-13 22:37:08', '2018-11-13 22:37:08', null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户-角色关联表id',
  `user_id` int(40) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `sys_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户-角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('6', '238', '1');
INSERT INTO `sys_user_role` VALUES ('7', '238', '2');
INSERT INTO `sys_user_role` VALUES ('8', '238', '3');
INSERT INTO `sys_user_role` VALUES ('9', '239', '2');
INSERT INTO `sys_user_role` VALUES ('10', '240', '2');



