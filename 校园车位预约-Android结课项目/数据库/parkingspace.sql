/*
 Navicat Premium Data Transfer

 Source Server         : wanglang
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : parkingspace

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 31/05/2023 10:55:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for occupy
-- ----------------------------
DROP TABLE IF EXISTS `occupy`;
CREATE TABLE `occupy`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int NULL DEFAULT NULL,
  `uid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of occupy
-- ----------------------------
INSERT INTO `occupy` VALUES (2, 1, 1);

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `pid` int NOT NULL AUTO_INCREMENT,
  `place` char(35) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '位置',
  `status` int NOT NULL DEFAULT 0 COMMENT '车位状态：0:空闲 1：占用',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, 'A7-101', 1);
INSERT INTO `position` VALUES (2, 'A7-105', 0);
INSERT INTO `position` VALUES (3, 'A7-202', 0);
INSERT INTO `position` VALUES (4, 'A7-304', 0);
INSERT INTO `position` VALUES (5, 'A5-101', 0);
INSERT INTO `position` VALUES (6, 'A5-103', 0);
INSERT INTO `position` VALUES (7, 'B6-108', 0);
INSERT INTO `position` VALUES (8, 'B6-111', 0);
INSERT INTO `position` VALUES (9, 'A5-119', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account` char(35) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '用户账号',
  `name` char(35) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '用户姓名',
  `password` char(35) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'wanglang', '王狼', '123456');
INSERT INTO `user` VALUES (2, 'whp', '王海鹏', '123456');
INSERT INTO `user` VALUES (3, 'wzy', '王志宇', '123456');

SET FOREIGN_KEY_CHECKS = 1;
