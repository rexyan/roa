/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : zcw

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 20/04/2020 23:40:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account_type_cert
-- ----------------------------
DROP TABLE IF EXISTS `t_account_type_cert`;
CREATE TABLE `t_account_type_cert` (
  `id` int(11) NOT NULL,
  `accttype` char(1) DEFAULT NULL COMMENT '?˻????',
  `certid` int(11) DEFAULT NULL COMMENT '????????',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_19` (`certid`),
  CONSTRAINT `FK_Reference_19` FOREIGN KEY (`certid`) REFERENCES `t_cert` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?˻????ʹ?ϵ?';

-- ----------------------------
-- Records of t_account_type_cert
-- ----------------------------
BEGIN;
INSERT INTO `t_account_type_cert` VALUES (4, '0', 1);
INSERT INTO `t_account_type_cert` VALUES (5, '0', 2);
INSERT INTO `t_account_type_cert` VALUES (6, '0', 3);
INSERT INTO `t_account_type_cert` VALUES (7, '0', 5);
INSERT INTO `t_account_type_cert` VALUES (8, '1', 1);
INSERT INTO `t_account_type_cert` VALUES (9, '1', 6);
INSERT INTO `t_account_type_cert` VALUES (10, '2', 6);
INSERT INTO `t_account_type_cert` VALUES (11, '2', 7);
INSERT INTO `t_account_type_cert` VALUES (12, '3', 5);
INSERT INTO `t_account_type_cert` VALUES (13, '3', 4);
COMMIT;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginacct` varchar(255) NOT NULL,
  `userpswd` char(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `createtime` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='????Ա?';

-- ----------------------------
-- Records of t_admin
-- ----------------------------
BEGIN;
INSERT INTO `t_admin` VALUES (1, 'superadmin', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', '超级管理员', 'admin@atguigu.com', '2019-01-12 17:18:00');
INSERT INTO `t_admin` VALUES (11, '111111111', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', '111111111', 'my@yanrunsha.com', '2020-03-17 22:38:25');
INSERT INTO `t_admin` VALUES (12, '222222222', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', '22222222', 'my@yanrunsha.com', '2020-03-17 22:39:17');
INSERT INTO `t_admin` VALUES (13, '33333333', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', '3333333', '33333333', '2020-03-17 22:39:27');
INSERT INTO `t_admin` VALUES (14, '454545', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', '454545', '454545', '2020-03-17 22:41:45');
INSERT INTO `t_admin` VALUES (15, 'zhangsan', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', 'lisi', 'wangwu', '2020-03-18 22:33:25');
INSERT INTO `t_admin` VALUES (16, 'aa', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', 'aaa', 'aaa', '2020-03-18 22:37:14');
INSERT INTO `t_admin` VALUES (17, 'a', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', 'a', 'a', '2020-03-18 22:37:22');
INSERT INTO `t_admin` VALUES (18, 'ads', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', 'ads', 'ads', '2020-03-18 22:37:29');
INSERT INTO `t_admin` VALUES (19, 'asasdffsd', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', 'asdasdasd', 'sdfsdffdg', '2020-03-18 22:37:41');
INSERT INTO `t_admin` VALUES (20, 'ertetrret', '$2a$10$DEe3fHA3of.m1HRxE0CbJe/c8IyXPjxBEuYO0YKYN8QJUTrZG/P4y', 'tryrytrty', 'sdfsdfaaa', '2020-03-18 22:37:46');
COMMIT;

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`adminid`),
  KEY `FK_Reference_2` (`roleid`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`adminid`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='????Ա??ɫ?';

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
BEGIN;
INSERT INTO `t_admin_role` VALUES (22, 1, 11);
INSERT INTO `t_admin_role` VALUES (23, 1, 8);
INSERT INTO `t_admin_role` VALUES (24, 1, 3);
INSERT INTO `t_admin_role` VALUES (25, 1, 4);
INSERT INTO `t_admin_role` VALUES (27, 20, 6);
INSERT INTO `t_admin_role` VALUES (28, 20, 7);
INSERT INTO `t_admin_role` VALUES (29, 20, 8);
INSERT INTO `t_admin_role` VALUES (30, 20, 9);
INSERT INTO `t_admin_role` VALUES (31, 20, 11);
INSERT INTO `t_admin_role` VALUES (32, 19, 5);
INSERT INTO `t_admin_role` VALUES (33, 19, 6);
INSERT INTO `t_admin_role` VALUES (34, 19, 7);
INSERT INTO `t_admin_role` VALUES (35, 19, 8);
INSERT INTO `t_admin_role` VALUES (36, 19, 9);
INSERT INTO `t_admin_role` VALUES (37, 15, 8);
COMMIT;

-- ----------------------------
-- Table structure for t_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `t_advertisement`;
CREATE TABLE `t_advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `iconpath` varchar(255) DEFAULT NULL,
  `status` char(1) DEFAULT NULL COMMENT '0 - ?ݸ壬 1 - δ???ˣ? 2 - ???????ɣ? 3 - ????',
  `url` varchar(255) DEFAULT NULL,
  `adminid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????';

-- ----------------------------
-- Table structure for t_cert
-- ----------------------------
DROP TABLE IF EXISTS `t_cert`;
CREATE TABLE `t_cert` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '????????',
  `name` varchar(255) DEFAULT NULL COMMENT '???????',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='???ʱ';

-- ----------------------------
-- Records of t_cert
-- ----------------------------
BEGIN;
INSERT INTO `t_cert` VALUES (1, '营业执照副本');
INSERT INTO `t_cert` VALUES (2, '税务登记证');
INSERT INTO `t_cert` VALUES (3, '组织机构代码证');
INSERT INTO `t_cert` VALUES (4, '单位登记证件');
INSERT INTO `t_cert` VALUES (5, '法定代表人证件');
INSERT INTO `t_cert` VALUES (6, '经营者证件');
INSERT INTO `t_cert` VALUES (7, '手执身份证照片');
COMMIT;

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `subcode` varchar(255) DEFAULT NULL,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='?????ֵ';

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `t_dictionary` VALUES (1, '性别', 'sex', '男', '0');
INSERT INTO `t_dictionary` VALUES (2, '性别', 'sex', '女', '1');
INSERT INTO `t_dictionary` VALUES (3, '实名认证状态', 'authstatus', '未实名认证', '0');
INSERT INTO `t_dictionary` VALUES (4, '实名认证状态', 'authstatus', '实名认证审核中', '1');
INSERT INTO `t_dictionary` VALUES (5, '实名认证状态', 'authstatus', '已实名认证', '2');
INSERT INTO `t_dictionary` VALUES (6, '账户类型', 'accttype', '企业', '0');
INSERT INTO `t_dictionary` VALUES (7, '账户类型', 'accttype', '个体', '1');
INSERT INTO `t_dictionary` VALUES (8, '账户类型', 'accttype', '个人', '2');
INSERT INTO `t_dictionary` VALUES (9, '账户类型', 'accttype', '政府', '3');
COMMIT;

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginacct` varchar(255) NOT NULL COMMENT '??¼?˺',
  `userpswd` char(255) NOT NULL COMMENT '??¼???',
  `username` varchar(255) NOT NULL COMMENT '?û????',
  `email` varchar(255) NOT NULL COMMENT '???????',
  `authstatus` char(1) NOT NULL COMMENT 'ʵ????֤״̬ 0 - δʵ????֤?? 1 - ʵ????֤?????У? 2 - ??ʵ????֤',
  `usertype` char(1) NOT NULL COMMENT ' ?û?????: 0 - ???ˣ? 1 - ?',
  `realname` varchar(255) DEFAULT NULL COMMENT '??ʵ???',
  `cardnum` varchar(255) DEFAULT NULL COMMENT '????֤???',
  `accttype` char(1) DEFAULT NULL COMMENT '?˻?????: 0 - ??ҵ?? 1 - ???壬 2 - ???ˣ? 3 - ????',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='??Ա?';

-- ----------------------------
-- Records of t_member
-- ----------------------------
BEGIN;
INSERT INTO `t_member` VALUES (1, '17615153175', '$2a$10$SLX4ln.gLXpomzsmDDeFwuZy8vadnFT9KGatYt7RANva5tA6TBAWq', '17615153175', '123131313123@126.com', '0', '0', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_member_address
-- ----------------------------
DROP TABLE IF EXISTS `t_member_address`;
CREATE TABLE `t_member_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`memberid`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?ջ???ַ?';

-- ----------------------------
-- Table structure for t_member_cert
-- ----------------------------
DROP TABLE IF EXISTS `t_member_cert`;
CREATE TABLE `t_member_cert` (
  `id` int(11) NOT NULL,
  `memberid` int(11) DEFAULT NULL COMMENT '??ԱID',
  `certid` int(11) DEFAULT NULL COMMENT '????ID',
  `iconpath` varchar(255) DEFAULT NULL COMMENT 'ͼƬ·??',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_14` (`memberid`),
  KEY `FK_Reference_15` (`certid`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`certid`) REFERENCES `t_cert` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??Ա???ʱ';

-- ----------------------------
-- Table structure for t_member_project_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_member_project_follow`;
CREATE TABLE `t_member_project_follow` (
  `id` int(11) NOT NULL,
  `projectid` int(11) DEFAULT NULL,
  `memberid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_11` (`projectid`),
  KEY `FK_Reference_12` (`memberid`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??Ŀ??ע?';

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='?˵??';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_menu` VALUES (1, 0, '控制面板', 'glyphicon glyphicon-dashboard', 'main');
INSERT INTO `t_menu` VALUES (2, 0, '权限管理', 'glyphicon glyphicon glyphicon-tasks', NULL);
INSERT INTO `t_menu` VALUES (3, 2, '用户维护', 'glyphicon glyphicon-user', 'admin/index');
INSERT INTO `t_menu` VALUES (4, 2, '角色维护', 'glyphicon glyphicon-king', 'role/index');
INSERT INTO `t_menu` VALUES (5, 2, '权限维护', 'glyphicon glyphicon-lock', 'permission/index');
INSERT INTO `t_menu` VALUES (6, 2, '菜单维护', 'glyphicon glyphicon-th-list', 'menu/index');
INSERT INTO `t_menu` VALUES (7, 0, '业务审核', 'glyphicon glyphicon-ok', NULL);
INSERT INTO `t_menu` VALUES (8, 7, '实名认证审核', 'glyphicon glyphicon-check', 'auth_cert/index');
INSERT INTO `t_menu` VALUES (9, 7, '广告审核', 'glyphicon glyphicon-check', 'auth_adv/index');
INSERT INTO `t_menu` VALUES (10, 7, '项目审核', 'glyphicon glyphicon-check', 'auth_project/index');
INSERT INTO `t_menu` VALUES (11, 0, '业务管理', 'glyphicon glyphicon-th-large', NULL);
INSERT INTO `t_menu` VALUES (12, 11, '资质维护', 'glyphicon glyphicon-picture', 'cert/index');
INSERT INTO `t_menu` VALUES (13, 11, '分类管理', 'glyphicon glyphicon-equalizer', 'certtype/index');
INSERT INTO `t_menu` VALUES (14, 11, '流程管理', 'glyphicon glyphicon-random', 'process/index');
INSERT INTO `t_menu` VALUES (15, 11, '广告管理', 'glyphicon glyphicon-hdd', 'advert/index');
INSERT INTO `t_menu` VALUES (16, 11, '消息模板', 'glyphicon glyphicon-comment', 'message/index');
INSERT INTO `t_menu` VALUES (17, 11, '项目分类', 'glyphicon glyphicon-list', 'projectType/index');
INSERT INTO `t_menu` VALUES (18, 11, '项目标签', 'glyphicon glyphicon-tags', 'tag/index');
INSERT INTO `t_menu` VALUES (19, 0, '参数管理', 'glyphicon glyphicon-list-alt', 'param/index');
INSERT INTO `t_menu` VALUES (24, 0, '3333', '3333', '3333');
INSERT INTO `t_menu` VALUES (25, 24, '444', '444', '444');
INSERT INTO `t_menu` VALUES (26, 24, '555', '555', '555');
COMMIT;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `senddate` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??Ϣ?';

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) DEFAULT NULL COMMENT '??ԱID',
  `projectid` int(11) DEFAULT NULL COMMENT '??ĿID',
  `returnid` int(11) DEFAULT NULL COMMENT '?ر?ID',
  `ordernum` varchar(255) DEFAULT NULL COMMENT '???????',
  `createdate` char(19) DEFAULT NULL COMMENT '????ʱ?',
  `money` int(11) DEFAULT NULL COMMENT '֧?ֽ',
  `rtncount` int(11) DEFAULT NULL COMMENT '?ر?????',
  `status` char(1) DEFAULT NULL COMMENT '0 - ????? 1 - ???????ɣ? 9 - ???׹ر',
  `address` varchar(255) DEFAULT NULL COMMENT '?ջ???ַ',
  `invoice` char(1) DEFAULT NULL COMMENT '0 - ??????Ʊ?? 1 - ????Ʊ',
  `invoictitle` varchar(255) DEFAULT NULL COMMENT '??Ʊ̧ͷ',
  `remark` varchar(255) DEFAULT NULL COMMENT '??ע',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_16` (`returnid`),
  KEY `FK_Reference_17` (`projectid`),
  KEY `FK_Reference_18` (`memberid`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`returnid`) REFERENCES `t_return` (`id`),
  CONSTRAINT `FK_Reference_17` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????';

-- ----------------------------
-- Table structure for t_param
-- ----------------------------
DROP TABLE IF EXISTS `t_param`;
CREATE TABLE `t_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????';

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '????',
  `name` varchar(255) DEFAULT NULL COMMENT '???ɱ?ʶ',
  `title` varchar(255) DEFAULT NULL COMMENT '??ʶ˵??',
  `icon` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL COMMENT '??Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='???ɣ?Ȩ?ޣ??';

-- ----------------------------
-- Records of t_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` VALUES (1, NULL, '用户模块', 'glyphicon glyphicon-user', 0);
INSERT INTO `t_permission` VALUES (2, 'user:add', '新增', 'glyphicon glyphicon-plus', 1);
INSERT INTO `t_permission` VALUES (3, 'user:delete', '删除', 'glyphicon glyphicon-remove', 1);
INSERT INTO `t_permission` VALUES (4, 'user:update', '更新', 'glyphicon glyphicon-pencil', 1);
INSERT INTO `t_permission` VALUES (5, 'user:get', '查询', 'glyphicon glyphicon-zoom-in', 1);
INSERT INTO `t_permission` VALUES (6, 'user:assign:role', '授予角色', 'glyphicon glyphicon-user', 1);
INSERT INTO `t_permission` VALUES (7, NULL, '角色模块', 'glyphicon glyphicon-heart', 0);
INSERT INTO `t_permission` VALUES (8, 'role:add', '新增', 'glyphicon glyphicon-plus', 7);
INSERT INTO `t_permission` VALUES (9, 'role:delete', '删除', 'glyphicon glyphicon-remove', 7);
INSERT INTO `t_permission` VALUES (10, 'role:get', '查询', 'glyphicon glyphicon-zoom-in', 7);
INSERT INTO `t_permission` VALUES (11, 'role:update', '修改', 'glyphicon glyphicon-pencil', 7);
INSERT INTO `t_permission` VALUES (12, 'role:assign:permission', '授予权限', 'glyphicon glyphicon-user', 7);
INSERT INTO `t_permission` VALUES (13, NULL, '菜单模块', 'glyphicon glyphicon-th-list', 0);
INSERT INTO `t_permission` VALUES (14, 'menu:add', '新增', 'glyphicon glyphicon-plus', 13);
INSERT INTO `t_permission` VALUES (15, 'menu:delete', '删除', 'glyphicon glyphicon-remove', 13);
INSERT INTO `t_permission` VALUES (16, 'menu:update', '修改', 'glyphicon glyphicon-pencil', 13);
INSERT INTO `t_permission` VALUES (17, 'menu:get', '查询', 'glyphicon glyphicon-zoom-in', 13);
INSERT INTO `t_permission` VALUES (18, 'menu:assign:permission', '授予权限', 'glyphicon glyphicon-user', 13);
INSERT INTO `t_permission` VALUES (25, '111', '1111', '1111', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_permission_menu`;
CREATE TABLE `t_permission_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`menuid`),
  KEY `FK_Reference_9` (`permissionid`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menuid`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`permissionid`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???ɲ˵??';

-- ----------------------------
-- Table structure for t_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_permission_resource`;
CREATE TABLE `t_permission_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resourceid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_20` (`permissionid`),
  KEY `FK_Reference_21` (`resourceid`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`permissionid`) REFERENCES `t_permission` (`id`),
  CONSTRAINT `FK_Reference_21` FOREIGN KEY (`resourceid`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??????Դ?';

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '??Ŀ???',
  `remark` varchar(255) DEFAULT NULL COMMENT '??Ŀ?',
  `money` bigint(11) DEFAULT NULL COMMENT '???ʽ',
  `day` int(11) DEFAULT NULL COMMENT '????????',
  `status` char(10) DEFAULT NULL COMMENT '0 - ??????ʼ?? 1 - ?ڳ??У? 2 - ?ڳ??ɹ??? 3 - ?ڳ?ʧ?',
  `deploydate` char(19) DEFAULT NULL COMMENT '???????',
  `supportmoney` bigint(11) DEFAULT NULL COMMENT '֧?ֽ',
  `supporter` int(11) DEFAULT NULL COMMENT '֧????????',
  `completion` int(3) DEFAULT NULL COMMENT '???ɶ',
  `memberid` int(11) DEFAULT NULL COMMENT '??????ID',
  `createdate` char(19) DEFAULT NULL COMMENT '???????',
  `follower` int(11) DEFAULT NULL COMMENT '??ע??????',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='??Ŀ?';

-- ----------------------------
-- Records of t_project
-- ----------------------------
BEGIN;
INSERT INTO `t_project` VALUES (2, '项目名称', '项目简介', 111, 11, '草稿', '2020-04-18 12:14:05', 0, 0, 0, 1, '2020-04-18 12:14:05', 0);
INSERT INTO `t_project` VALUES (3, '项目名称', '项目简介', 111, 11, '草稿', '2020-04-18 12:15:30', 0, 0, 0, 1, '2020-04-18 12:15:30', 0);
INSERT INTO `t_project` VALUES (4, '项目名称', '项目简介', 111, 11, '草稿', '2020-04-18 12:16:46', 0, 0, 0, 1, '2020-04-18 12:16:46', 0);
INSERT INTO `t_project` VALUES (5, '项目名称', '项目简介', 111, 11, '草稿', '2020-04-18 12:17:50', 0, 0, 0, 1, '2020-04-18 12:17:50', 0);
INSERT INTO `t_project` VALUES (7, '项目名称', '项目简介', 111, 11, '草稿', '2020-04-18 12:22:28', 0, 0, 0, 1, '2020-04-18 12:22:28', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_project_images
-- ----------------------------
DROP TABLE IF EXISTS `t_project_images`;
CREATE TABLE `t_project_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `imgtype` tinyint(4) DEFAULT NULL COMMENT '0-头部图片 1-详情图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_images
-- ----------------------------
BEGIN;
INSERT INTO `t_project_images` VALUES (3, 2, '\"\"', 0);
INSERT INTO `t_project_images` VALUES (4, 2, '\"\"', 1);
INSERT INTO `t_project_images` VALUES (5, 3, '\"\"', 0);
INSERT INTO `t_project_images` VALUES (6, 3, '\"\"', 1);
INSERT INTO `t_project_images` VALUES (7, 4, '\"\"', 0);
INSERT INTO `t_project_images` VALUES (8, 4, '\"\"', 1);
INSERT INTO `t_project_images` VALUES (9, 5, '\"\"', 0);
INSERT INTO `t_project_images` VALUES (10, 5, '\"\"', 1);
INSERT INTO `t_project_images` VALUES (11, 7, '\"\"', 0);
INSERT INTO `t_project_images` VALUES (12, 7, '\"\"', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_project_initiator
-- ----------------------------
DROP TABLE IF EXISTS `t_project_initiator`;
CREATE TABLE `t_project_initiator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `selfintroduction` varchar(255) DEFAULT NULL,
  `detailselfintroduction` varchar(255) DEFAULT NULL,
  `telphone` varchar(13) DEFAULT NULL,
  `hotline` varchar(13) DEFAULT NULL,
  `projectid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_project_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_project_tag`;
CREATE TABLE `t_project_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `tagid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`projectid`),
  KEY `FK_Reference_8` (`tagid`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`tagid`) REFERENCES `t_tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??Ŀ??ǩ??ϵ?';

-- ----------------------------
-- Table structure for t_project_type
-- ----------------------------
DROP TABLE IF EXISTS `t_project_type`;
CREATE TABLE `t_project_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`projectid`),
  KEY `FK_Reference_6` (`typeid`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`typeid`) REFERENCES `t_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??Ŀ??????ϵ?';

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `createat` varchar(19) DEFAULT NULL,
  `updateat` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??Դ?';

-- ----------------------------
-- Table structure for t_return
-- ----------------------------
DROP TABLE IF EXISTS `t_return`;
CREATE TABLE `t_return` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '????',
  `projectid` int(11) DEFAULT NULL COMMENT '??ĿID',
  `type` char(11) DEFAULT NULL COMMENT '0 - ʵ???ر??? 1 ??????Ʒ?ر?',
  `supportmoney` int(11) DEFAULT NULL COMMENT '֧?ֽ',
  `content` varchar(255) DEFAULT NULL COMMENT '?ر????',
  `count` int(11) DEFAULT NULL COMMENT '0 Ϊ???޻ر?????',
  `signalpurchase` int(11) DEFAULT NULL COMMENT '?????޹?',
  `purchase` int(11) DEFAULT NULL COMMENT '?޹?????',
  `freight` int(11) DEFAULT NULL COMMENT '?˷',
  `invoice` char(11) DEFAULT NULL COMMENT '0 - ??????Ʊ?? 1 - ????Ʊ',
  `rtndate` int(11) DEFAULT NULL COMMENT '?ر?ʱ??,?ڳ??ɹ????????????лر?',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='?ر??';

-- ----------------------------
-- Records of t_return
-- ----------------------------
BEGIN;
INSERT INTO `t_return` VALUES (1, NULL, '0', 200, '赠送价值 500 元按摩椅一个', 1, 1, 20, 0, '0', 0);
INSERT INTO `t_return` VALUES (2, NULL, '0', 200, '赠送价值 500 元按摩椅一个', 1, 1, 20, 0, '0', 0);
INSERT INTO `t_return` VALUES (3, NULL, '0', 200, '赠送价值 500 元按摩椅一个', 1, 1, 20, 0, '0', 0);
INSERT INTO `t_return` VALUES (4, NULL, '0', 200, '赠送价值 500 元按摩椅一个', 1, 1, 20, 0, '0', 0);
INSERT INTO `t_return` VALUES (5, 7, '0', 200, '赠送价值 500 元按摩椅一个', 1, 1, 20, 0, '0', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='??ɫ?';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (2, 'SE - 软件工程师');
INSERT INTO `t_role` VALUES (3, 'PG - 程序员');
INSERT INTO `t_role` VALUES (4, 'TL - 组长');
INSERT INTO `t_role` VALUES (5, 'GL - 组长');
INSERT INTO `t_role` VALUES (6, 'QA - 品质保证');
INSERT INTO `t_role` VALUES (7, 'QC - 品质控制');
INSERT INTO `t_role` VALUES (8, 'SA - 软件架构师');
INSERT INTO `t_role` VALUES (9, 'CMO / CMS - 配置管理员');
INSERT INTO `t_role` VALUES (11, 'hmas777');
INSERT INTO `t_role` VALUES (12, 'TEST-测试');
COMMIT;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`roleid`),
  KEY `FK_Reference_4` (`permissionid`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`permissionid`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COMMENT='??ɫ???ɱ';

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` VALUES (37, 2, 1);
INSERT INTO `t_role_permission` VALUES (38, 2, 2);
INSERT INTO `t_role_permission` VALUES (39, 2, 3);
INSERT INTO `t_role_permission` VALUES (40, 2, 7);
INSERT INTO `t_role_permission` VALUES (41, 2, 8);
INSERT INTO `t_role_permission` VALUES (42, 2, 9);
INSERT INTO `t_role_permission` VALUES (43, 3, 7);
INSERT INTO `t_role_permission` VALUES (44, 3, 8);
INSERT INTO `t_role_permission` VALUES (45, 3, 9);
INSERT INTO `t_role_permission` VALUES (46, 3, 10);
INSERT INTO `t_role_permission` VALUES (47, 3, 11);
INSERT INTO `t_role_permission` VALUES (48, 3, 12);
INSERT INTO `t_role_permission` VALUES (49, 5, 13);
INSERT INTO `t_role_permission` VALUES (50, 5, 17);
INSERT INTO `t_role_permission` VALUES (51, 5, 18);
INSERT INTO `t_role_permission` VALUES (70, 6, 1);
INSERT INTO `t_role_permission` VALUES (71, 6, 2);
INSERT INTO `t_role_permission` VALUES (72, 6, 3);
INSERT INTO `t_role_permission` VALUES (73, 6, 4);
INSERT INTO `t_role_permission` VALUES (74, 6, 5);
INSERT INTO `t_role_permission` VALUES (75, 6, 6);
INSERT INTO `t_role_permission` VALUES (76, 6, 7);
INSERT INTO `t_role_permission` VALUES (77, 6, 8);
INSERT INTO `t_role_permission` VALUES (78, 6, 9);
INSERT INTO `t_role_permission` VALUES (79, 6, 10);
INSERT INTO `t_role_permission` VALUES (80, 6, 11);
INSERT INTO `t_role_permission` VALUES (81, 6, 12);
COMMIT;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='??Ŀ??ǩ';

-- ----------------------------
-- Records of t_tag
-- ----------------------------
BEGIN;
INSERT INTO `t_tag` VALUES (1, 0, '颜色');
INSERT INTO `t_tag` VALUES (2, 1, '红色');
COMMIT;

-- ----------------------------
-- Table structure for t_transaction
-- ----------------------------
DROP TABLE IF EXISTS `t_transaction`;
CREATE TABLE `t_transaction` (
  `id` int(11) NOT NULL,
  `ordersn` varchar(255) DEFAULT NULL,
  `paysn` varchar(255) DEFAULT NULL,
  `memberid` int(11) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `paystate` tinyint(4) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `completiontime` varchar(19) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `createat` varchar(19) DEFAULT NULL,
  `updateat` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='֧???';

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='??Ŀ???';

-- ----------------------------
-- Records of t_type
-- ----------------------------
BEGIN;
INSERT INTO `t_type` VALUES (1, '数码', '数码产品');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
