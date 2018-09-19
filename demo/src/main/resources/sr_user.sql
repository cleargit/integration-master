/*
 Navicat MySQL Data Transfer

 Source Server         : food
 Source Server Type    : MySQL
 Source Server Version : 50617
 Source Host           : 172.16.2.241:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50617
 File Encoding         : 65001

 Date: 19/09/2018 09:04:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sr_user
-- ----------------------------
DROP TABLE IF EXISTS `sr_user`;
CREATE TABLE `sr_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `alias` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  `sex` tinyint(2) NULL DEFAULT NULL COMMENT '性别',
  `expertise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专长',
  `birthDate` int(11) NULL DEFAULT NULL COMMENT '出生时间',
  `category` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '范畴',
  `rank` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职级',
  `remart` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sr_user
-- ----------------------------
INSERT INTO `sr_user` VALUES (1, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (2, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (3, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (4, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (5, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (6, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (7, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (8, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (9, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (11, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);
INSERT INTO `sr_user` VALUES (111, '小桃', 'shamer', 1, 'ko', NULL, '123', '超级管理员', NULL);

SET FOREIGN_KEY_CHECKS = 1;
