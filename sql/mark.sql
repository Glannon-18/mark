/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : mark

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-07-17 17:59:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `iconCls` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` bigint DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parentId` (`parentId`) USING BTREE,
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/', null, null, '所有', null, null, null, null, null, '1');
INSERT INTO `menu` VALUES ('2', '/', '/personnal', 'Layout', '我的工作', 'my_work', 'dashboard', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('3', '/', '/leader', 'Layout', '组长工作', 'leader_work', 'dashboard', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('4', '/', '/manager', 'Layout', '经理工作', 'manager_work', 'dashboard', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('5', '/', '/system', 'Layout', '系统管理', 'system_management', 'dashboard', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('6', '/personal/upload_corpus/**', '/personnal/upload_corpus', 'Upload_corpus', '上传语料', 'upload_corpus', 'dashboard', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('7', '/personal/statistics/**', '/personnal/statistics', 'Statistics', '我的统计', 'statistics', 'dashboard', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('8', '/leader/publish/**', '/leader/publish', 'Publish', '发布项目', 'publish', 'dashboard', null, '1', '3', '1');
INSERT INTO `menu` VALUES ('9', '/leader/chexk/**', '/leader/check', 'Leader_check', '初步审核', 'leader_check', 'dashboard', null, '1', '3', '1');
INSERT INTO `menu` VALUES ('10', '/manager/chexk/**', '/manager/check', 'Manager_check', '最终审核', 'manager_check', 'dashboard', null, '1', '4', '1');
INSERT INTO `menu` VALUES ('11', '/system/user/**', '/system/user', 'System_user', '用户管理', 'system_user', 'dashboard', null, '1', '5', '1');
INSERT INTO `menu` VALUES ('12', '/system/permission/**', '/system/permission', 'System_permission', '角色权限管理', 'system_permission', 'dashboard', null, '1', '5', '1');
INSERT INTO `menu` VALUES ('14', '/system/team/**', '/system/team', 'System_team', '小组管理', 'system_team', 'dashboard', null, '1', '5', '1');
INSERT INTO `menu` VALUES ('15', '/system/type/**', '/system/type', 'System_type', '语料类型管理', 'system_type', 'dashboard', null, '1', '5', '1');
INSERT INTO `menu` VALUES ('16', '/system/label/**', '/system/label', 'System_label', '标注管理', 'system_label', 'dashboard', null, '1', '5', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discard` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nameZh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '0', '2020-06-13 10:46:47', 'Administrator', '管理员');
INSERT INTO `role` VALUES ('2', '0', '2020-06-13 10:47:26', 'leader', '组长');
INSERT INTO `role` VALUES ('3', '0', '2020-06-13 10:48:06', 'manager', '经理');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rid` bigint NOT NULL,
  `mid` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  KEY `mid` (`mid`) USING BTREE,
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '6');
INSERT INTO `role_menu` VALUES ('2', '1', '7');
INSERT INTO `role_menu` VALUES ('3', '1', '8');
INSERT INTO `role_menu` VALUES ('4', '1', '9');
INSERT INTO `role_menu` VALUES ('5', '1', '10');
INSERT INTO `role_menu` VALUES ('6', '1', '11');
INSERT INTO `role_menu` VALUES ('7', '1', '12');
INSERT INTO `role_menu` VALUES ('8', '1', '14');
INSERT INTO `role_menu` VALUES ('9', '1', '15');
INSERT INTO `role_menu` VALUES ('10', '2', '16');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discard` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `telephone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `photo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '0', '2020-06-13 10:46:03', 'admin', '$2a$10$PLQOwQJtPix2Tl0faXyQA.IrBFzYSYWQ/P.q3qgdqGDqEY/g0Ahdq', '习近平', null, '17787877878', '', '3b7ab01b-ca85-4582-bce7-0170ddfac4c5.jpg');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint NOT NULL,
  `rid` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '1', '3');
INSERT INTO `user_role` VALUES ('3', '1', '2');
