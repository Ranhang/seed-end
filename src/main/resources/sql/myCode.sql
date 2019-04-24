/*
 Navicat Premium Data Transfer

 Source Server         : c
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : c5:3306
 Source Schema         : gend-db

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 19/04/2019 17:09:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级主键',
  `seq` tinyint(2) NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_updator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上次更新人',
  `last_update_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES (1, '总经办', '王家桥', '01', 'glyphicon-lock ', NULL, 0, 0, '', '2019-04-16 11:10:53', '', '0000-00-00 00:00:00');
INSERT INTO `organization` VALUES (3, '技术部', '', '02', 'glyphicon-wrench ', NULL, 1, 0, '', '2019-04-16 11:10:53', '', '0000-00-00 00:00:00');
INSERT INTO `organization` VALUES (5, '产品部', '', '03', 'glyphicon-send ', NULL, 2, 0, '', '2019-04-16 11:10:53', '', '0000-00-00 00:00:00');
INSERT INTO `organization` VALUES (6, '测试组', '', '04', 'glyphicon-headphones ', 3, 0, 0, '', '2019-04-16 11:10:53', '', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源路径',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级资源id',
  `seq` tinyint(2) NOT NULL DEFAULT 0 COMMENT '排序',
  `resource_type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '资源类别',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_updator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上次更新人',
  `last_update_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 232 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, '权限管理', '', '系统管理', 'glyphicon-folder-open ', NULL, 0, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (11, '资源管理', '/resource/manager', '资源管理', 'glyphicon-th ', 1, 1, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (12, '角色管理', '/role/manager', '角色管理', 'glyphicon-eye-open ', 1, 2, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (13, '用户管理', '/user/manager', '用户管理', 'glyphicon-user ', 1, 3, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (14, '部门管理', '/organization/manager', '部门管理', 'glyphicon-lock ', 1, 4, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (111, '列表', '/resource/treeGrid', '资源列表', 'glyphicon-list ', 11, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (112, '添加', '/resource/add', '资源添加', 'glyphicon-plus icon-green', 11, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (113, '编辑', '/resource/edit', '资源编辑', 'glyphicon-pencil icon-blue', 11, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (114, '删除', '/resource/delete', '资源删除', 'glyphicon-trash icon-red', 11, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (121, '列表', '/role/dataGrid', '角色列表', 'glyphicon-list ', 12, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (122, '添加', '/role/add', '角色添加', 'glyphicon-plus icon-green', 12, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (123, '编辑', '/role/edit', '角色编辑', 'glyphicon-pencil icon-blue', 12, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (124, '删除', '/role/delete', '角色删除', 'glyphicon-trash icon-red', 12, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (125, '授权', '/role/grant', '角色授权', 'glyphicon-ok icon-green', 12, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (131, '列表', '/user/dataGrid', '用户列表', 'glyphicon-list ', 13, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (132, '添加', '/user/add', '用户添加', 'glyphicon-plus icon-green', 13, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (133, '编辑', '/user/edit', '用户编辑', 'glyphicon-pencil icon-blue', 13, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (134, '删除', '/user/delete', '用户删除', 'glyphicon-trash icon-red', 13, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (141, '列表', '/organization/treeGrid', '用户列表', 'glyphicon-list ', 14, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (142, '添加', '/organization/add', '部门添加', 'glyphicon-plus icon-green', 14, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (143, '编辑', '/organization/edit', '部门编辑', 'glyphicon-pencil icon-blue', 14, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (144, '删除', '/organization/delete', '部门删除', 'glyphicon-trash icon-red', 14, 0, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (221, '日志监控', '', NULL, 'glyphicon-dashboard ', NULL, 3, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (222, '视频教程', '', NULL, 'glyphicon-film ', NULL, 2, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (223, '官方网站', 'https://www.dreamlu.net', NULL, 'glyphicon-globe ', 222, 0, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (224, 'jfinal视频', 'http://blog.dreamlu.net/blog/79', NULL, 'glyphicon-blackboard ', 222, 1, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (226, '修改密码', '/user/editPwdPage', NULL, 'glyphicon-eye-close ', NULL, 4, 1, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (227, '登录日志', '/sysLog/manager', NULL, 'glyphicon-exclamation-sign ', 221, 0, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (228, 'Druid监控', '/druid', NULL, 'glyphicon-sunglasses ', 221, 0, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (229, '系统图标', '/icons.html', NULL, 'glyphicon-picture ', 221, 0, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (230, '文章管理', '', NULL, 'glyphicon-duplicate ', NULL, 1, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');
INSERT INTO `resource` VALUES (231, '新建文章', '/article/create', NULL, 'glyphicon-open-file ', 230, 0, 0, 0, '', '2019-04-16 11:10:19', '', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `seq` tinyint(2) NOT NULL DEFAULT 0 COMMENT '排序号',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_updator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上次更新人',
  `last_update_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_resource_ids`(`role_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 481 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `opt_content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容',
  `client_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户端ip',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆名',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码加密盐',
  `sex` tinyint(2) NOT NULL DEFAULT 0 COMMENT '性别',
  `age` tinyint(2) DEFAULT 0 COMMENT '年龄',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `organization_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属机构',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_updator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上次更新人',
  `last_update_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDx_user_login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '05a671c66aefea124cc08b76ea6d30bb', 'test', 0, 25, '18707173376', 1, 0, '', '2019-04-19 09:22:56', '', '2019-04-16 11:11:29');
INSERT INTO `user` VALUES (13, 'snoopy', 'snoopy', '05a671c66aefea124cc08b76ea6d30bb', 'test', 0, 25, '18707173376', 3, 0, '', '2019-04-19 09:23:00', '', '2019-04-16 11:11:29');
INSERT INTO `user` VALUES (14, 'dreamlu', 'dreamlu', '05a671c66aefea124cc08b76ea6d30bb', 'test', 0, 25, '18707173376', 5, 0, '', '2019-04-19 09:23:04', '', '2019-04-16 11:11:29');
INSERT INTO `user` VALUES (15, 'test', 'test', '05a671c66aefea124cc08b76ea6d30bb', 'test', 0, 25, '18707173376', 6, 0, '', '2019-04-19 09:23:10', '', '2019-04-16 11:11:29');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_role_ids`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (53, 15, 8, '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES (60, 1, 1, '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES (61, 1, 2, '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES (62, 1, 7, '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES (63, 13, 2, '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES (64, 14, 7, '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES (65, 1, 8, '', '2019-04-16 11:13:49');

SET FOREIGN_KEY_CHECKS = 1;
