/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.12 : Database - backend
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`backend` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `backend`;

/*Table structure for table `backend_permission` */

DROP TABLE IF EXISTS `backend_permission`;

CREATE TABLE `backend_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称  不区分大小写',
  `path` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路径',
  `type` tinyint(1) NOT NULL COMMENT '类型  0目录  2菜单  3按钮',
  `is_open` tinyint(1) NOT NULL COMMENT '是否展开  0闭合  1展开',
  `icon` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '样式',
  `sort` tinyint(20) NOT NULL COMMENT '排序  数值越大越靠后',
  `is_enable` tinyint(1) NOT NULL COMMENT '是否启用  0禁用  1启用',
  `is_non_delete` tinyint(1) NOT NULL COMMENT '是否未删除  0删除  1未删除',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限数据表';

/*Data for the table `backend_permission` */

/*Table structure for table `backend_role` */

DROP TABLE IF EXISTS `backend_role`;

CREATE TABLE `backend_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名  不区分大小写',
  `is_enable` tinyint(1) NOT NULL COMMENT '是否启用  0禁用  1启用',
  `is_non_delete` tinyint(1) NOT NULL COMMENT '是否未删除  0删除  1未删除',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色数据表';

/*Data for the table `backend_role` */

insert  into `backend_role`(`id`,`name`,`is_enable`,`is_non_delete`,`create_user`,`create_time`,`update_user`,`update_time`) values 
(1,'admin',1,1,0,'2018-11-23 15:54:16','0','2018-11-23 15:54:19');

/*Table structure for table `backend_role_permission` */

DROP TABLE IF EXISTS `backend_role_permission`;

CREATE TABLE `backend_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限关联数据表';

/*Data for the table `backend_role_permission` */

/*Table structure for table `backend_user` */

DROP TABLE IF EXISTS `backend_user`;

CREATE TABLE `backend_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名  不区分大小写',
  `password` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码  区分大小写',
  `salt` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码盐值  区分大小写',
  `is_enable` tinyint(1) NOT NULL COMMENT '是否启用  0禁用  1启用',
  `is_non_delete` tinyint(1) NOT NULL COMMENT '是否未删除  0删除  1未删除',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户数据表';

/*Data for the table `backend_user` */

insert  into `backend_user`(`id`,`name`,`password`,`salt`,`is_enable`,`is_non_delete`,`create_user`,`create_time`,`update_user`,`update_time`) values 
(1,'admin','admin','admin',1,1,0,'2018-11-23 14:04:08','0','2018-11-23 14:04:12');

/*Table structure for table `backend_user_role` */

DROP TABLE IF EXISTS `backend_user_role`;

CREATE TABLE `backend_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `is_enable` tinyint(1) NOT NULL COMMENT '是否启用  0禁用  1启用',
  `is_non_delete` tinyint(1) NOT NULL COMMENT '是否未删除  0删除  1未删除',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色关联数据表';

/*Data for the table `backend_user_role` */

insert  into `backend_user_role`(`id`,`user_id`,`role_id`,`is_enable`,`is_non_delete`,`create_user`,`create_time`,`update_user`,`update_time`) values 
(1,1,1,1,1,0,'2018-11-23 15:54:48','0','2018-11-23 15:54:52');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
