/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : seed_db

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-01-10 09:29:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级主键',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_updator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上次更新人',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='组织机构';

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('1', '总经办', '王家桥', '01', 'glyphicon-lock ', null, '0', '0', '', '2019-04-16 11:10:53', '', '0000-00-00 00:00:00');
INSERT INTO `organization` VALUES ('3', '技术部', '', '02', 'glyphicon-wrench ', null, '1', '0', '', '2019-04-16 11:10:53', '', '0000-00-00 00:00:00');
INSERT INTO `organization` VALUES ('5', '产品部', '', '03', 'glyphicon-send ', null, '2', '0', '', '2019-04-16 11:10:53', '', '0000-00-00 00:00:00');
INSERT INTO `organization` VALUES ('6', '测试组', '', '04', 'glyphicon-headphones ', '3', '0', '0', '', '2019-04-16 11:10:53', '', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `Permission` varchar(50) NOT NULL DEFAULT '' COMMENT '权限',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `description` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(32) DEFAULT NULL COMMENT '资源图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级资源id',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `resource_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类别',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `creator` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_updator` varchar(64) NOT NULL COMMENT '上次更新人',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='资源';

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '权限管理', 'system:user:get', '', '系统管理', 'glyphicon-folder-open ', null, '0', '0', '0', '', '2019-04-16 11:10:19', '', '2019-04-16 11:10:19');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序号',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_updator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上次更新人',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'cike', '0', '刺客', '0', 'admin', '2020-01-07 13:03:58', 'admin', '2020-01-07 13:03:53');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_resource_ids` (`role_id`,`resource_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=481 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色资源';

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1', '1', 'admin', '2020-01-07 12:57:45');
INSERT INTO `role_resource` VALUES ('2', '1', '122', 'admin', '2020-01-07 12:58:46');
INSERT INTO `role_resource` VALUES ('3', '1', '111', 'admin', '2020-01-07 14:48:23');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `opt_content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容',
  `client_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户端ip',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆名',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码加密盐',
  `sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别',
  `age` tinyint(2) DEFAULT '0' COMMENT '年龄',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `organization_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属机构',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_updator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上次更新人',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `IDx_user_login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '79842c242dc3cb6b96a6de6c9c6ac841', 'test', '0', '25', '18707173376', '1', '1', '', '2020-01-06 18:45:43', '', '2019-04-16 11:11:29');
INSERT INTO `user` VALUES ('13', 'snoopy', 'snoopy', '05a671c66aefea124cc08b76ea6d30bb', 'test', '0', '25', '18707173376', '3', '0', '', '2019-04-19 09:23:00', '', '2019-04-16 11:11:29');
INSERT INTO `user` VALUES ('14', 'dreamlu', 'dreamlu', '05a671c66aefea124cc08b76ea6d30bb', 'test', '0', '25', '18707173376', '5', '0', '', '2019-04-19 09:23:04', '', '2019-04-16 11:11:29');
INSERT INTO `user` VALUES ('15', 'test', 'test', '05a671c66aefea124cc08b76ea6d30bb', 'test', '0', '25', '18707173376', '6', '0', '', '2019-04-19 09:23:10', '', '2019-04-16 11:11:29');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_role_ids` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('53', '15', '8', '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES ('60', '1', '1', '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES ('61', '1', '2', '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES ('62', '1', '7', '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES ('63', '13', '2', '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES ('64', '14', '7', '', '2019-04-16 11:13:49');
INSERT INTO `user_role` VALUES ('65', '1', '8', '', '2019-04-16 11:13:49');
