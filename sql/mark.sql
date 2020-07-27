/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : mark

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 27/07/2020 17:49:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `discard` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `iconCls` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `keepAlive` tinyint(1) NULL DEFAULT NULL,
  `requireAuth` tinyint(1) NULL DEFAULT NULL,
  `parentId` bigint(0) NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parentId`(`parentId`) USING BTREE,
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '/', NULL, NULL, '所有', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `menu` VALUES (2, '/', '/personnal', 'Layout', '我的工作', 'my_work', 'dashboard', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (3, '/', '/leader', 'Layout', '组长工作', 'leader_work', 'dashboard', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (4, '/', '/manager', 'Layout', '经理工作', 'manager_work', 'dashboard', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (5, '/', '/system', 'Layout', '系统管理', 'system_management', 'system', NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (6, '/personal/upload_corpus/**', '/personnal/upload_corpus', 'Upload_corpus', '上传语料', 'upload_corpus', 'dashboard', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (7, '/personal/statistics/**', '/personnal/statistics', 'Statistics', '我的统计', 'statistics', 'dashboard', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (8, '/leader/publish/**', '/leader/publish', 'Publish', '发布项目', 'publish', 'dashboard', NULL, 1, 3, 1);
INSERT INTO `menu` VALUES (9, '/leader/chexk/**', '/leader/check', 'Leader_check', '初步审核', 'leader_check', 'dashboard', NULL, 1, 3, 1);
INSERT INTO `menu` VALUES (10, '/manager/chexk/**', '/manager/check', 'Manager_check', '最终审核', 'manager_check', 'dashboard', NULL, 1, 4, 1);
INSERT INTO `menu` VALUES (11, '/system/user/**', '/system/user', 'System_user', '用户管理', 'system_user', 'user', NULL, 1, 5, 1);
INSERT INTO `menu` VALUES (12, '/system/permission/**', '/system/permission', 'System_permission', '角色权限管理', 'system_permission', 'permission', NULL, 1, 5, 1);
INSERT INTO `menu` VALUES (14, '/system/team/**', '/system/team', 'System_team', '小组管理', 'system_team', 'group', NULL, 1, 5, 1);
INSERT INTO `menu` VALUES (15, '/system/type/**', '/system/type', 'System_type', '语料类型管理', 'system_type', 'type', NULL, 1, 5, 1);
INSERT INTO `menu` VALUES (16, '/system/label/**', '/system/label', 'System_label', '标注管理', 'system_label', 'sign', NULL, 1, 5, 1);

-- ----------------------------
-- Table structure for pair_type
-- ----------------------------
DROP TABLE IF EXISTS `pair_type`;
CREATE TABLE `pair_type`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `discard` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pair_type
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `discard` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nameZh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '0', '2020-06-13 10:46:47', 'administrator', '管理员');
INSERT INTO `role` VALUES (2, '0', '2020-06-13 10:47:26', 'leader', '组长');
INSERT INTO `role` VALUES (3, '0', '2020-06-13 10:48:06', 'manager', '经理');
INSERT INTO `role` VALUES (4, '0', '2020-07-23 17:48:22', 'ddd', '大声道');
INSERT INTO `role` VALUES (5, '0', '2020-07-30 17:51:47', 'fdsa', '大时代');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `rid` bigint(0) NOT NULL,
  `mid` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1, 6);
INSERT INTO `role_menu` VALUES (2, 1, 7);
INSERT INTO `role_menu` VALUES (3, 1, 8);
INSERT INTO `role_menu` VALUES (4, 1, 9);
INSERT INTO `role_menu` VALUES (5, 1, 10);
INSERT INTO `role_menu` VALUES (6, 1, 11);
INSERT INTO `role_menu` VALUES (7, 1, 12);
INSERT INTO `role_menu` VALUES (8, 1, 14);
INSERT INTO `role_menu` VALUES (9, 1, 15);
INSERT INTO `role_menu` VALUES (10, 2, 16);
INSERT INTO `role_menu` VALUES (11, 4, 6);
INSERT INTO `role_menu` VALUES (12, 4, 7);
INSERT INTO `role_menu` VALUES (13, 5, 15);
INSERT INTO `role_menu` VALUES (14, 5, 16);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `discard` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_login_time` datetime(0) NULL DEFAULT NULL,
  `telephone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enabled` bit(1) NULL DEFAULT NULL,
  `photo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '0', '2020-06-13 10:46:03', 'admin', '$2a$10$PLQOwQJtPix2Tl0faXyQA.IrBFzYSYWQ/P.q3qgdqGDqEY/g0Ahdq', '习近平9', NULL, '17787877878', b'1', '2ac74148-74ff-4462-aa8e-b5745362c582.jpg');
INSERT INTO `user` VALUES (3, '0', '2020-07-22 11:01:08', 'uuuu', '$2a$10$3AEnhGFYABDlMbbxrr0JcOSkhZc62bWz36qoEFmy6VQeBA6QF0Ew.', 'uuuii', NULL, '14478752585', b'1', NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `uid` bigint(0) NOT NULL,
  `rid` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (7, 3, 3);
INSERT INTO `user_role` VALUES (11, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
