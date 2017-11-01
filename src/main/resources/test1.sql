/*
Navicat MySQL Data Transfer

Source Server         : zx
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : test1

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-11-01 14:53:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `teacher` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '数据库', '陈建利');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `passWord` varchar(32) DEFAULT NULL COMMENT '密码',
  `user_sex` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('29', 'bb', 'b123456', 'WOMAN', null);
INSERT INTO `users` VALUES ('30', 'cc', 'b123456', 'WOMAN', null);
INSERT INTO `users` VALUES ('31', 'aa', 'a123456', 'MAN', null);
INSERT INTO `users` VALUES ('32', 'bb', 'b123456', 'WOMAN', null);
INSERT INTO `users` VALUES ('33', 'cc', 'b123456', 'WOMAN', null);
INSERT INTO `users` VALUES ('34', 'aa', 'a123456', 'MAN', null);
INSERT INTO `users` VALUES ('35', 'bb', 'b123456', 'WOMAN', null);
INSERT INTO `users` VALUES ('36', 'cc', 'b123456', 'WOMAN', null);
