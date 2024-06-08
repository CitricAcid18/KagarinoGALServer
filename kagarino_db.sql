/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 80020
Source Host           : 127.0.0.1:3306
Source Database       : kagarino_db

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2024-06-05 20:19:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kagarino_article
-- ----------------------------
DROP TABLE IF EXISTS `kagarino_article`;
CREATE TABLE `kagarino_article` (
  `article_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '帖子ID主键',
  `article_title` tinytext NOT NULL COMMENT '帖子标题',
  `article_scan_count` mediumint NOT NULL DEFAULT '0' COMMENT '浏览数',
  `article_like_count` mediumint NOT NULL DEFAULT '0' COMMENT '点赞数',
  `article_tag` tinytext NOT NULL COMMENT '标签',
  `article_comment_count` mediumint NOT NULL DEFAULT '0' COMMENT '评论数',
  `article_author_id` bigint unsigned NOT NULL COMMENT '作者ID',
  `article_author_name` varchar(31) NOT NULL COMMENT '作者名',
  `article_content` text NOT NULL COMMENT '帖子内容',
  `article_gmt_release` datetime NOT NULL COMMENT '发帖时间',
  `article_portrait` varchar(63) NOT NULL DEFAULT '../data/article/portrait/default.png' COMMENT '帖子配图',
  `article_illustration` varchar(511) NOT NULL DEFAULT '' COMMENT '帖子配图',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帖表';

-- ----------------------------
-- Records of kagarino_article
-- ----------------------------

-- ----------------------------
-- Table structure for kagarino_comment
-- ----------------------------
DROP TABLE IF EXISTS `kagarino_comment`;
CREATE TABLE `kagarino_comment` (
  `comment_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '评论ID主键',
  `comment_article_id` bigint unsigned NOT NULL COMMENT '帖子ID',
  `comment_content` varchar(1535) NOT NULL DEFAULT '' COMMENT '评论内容',
  `comment_gmt_release` datetime NOT NULL COMMENT '评论时间',
  `comment_dialog_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '被评论ID',
  `comment_name` varchar(31) NOT NULL COMMENT '评论者用户名',
  `comment_dialog_name` varchar(31) NOT NULL DEFAULT 'none' COMMENT '被评论者用户名',
  `comment_state` tinyint NOT NULL DEFAULT '0' COMMENT '审核状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';

-- ----------------------------
-- Records of kagarino_comment
-- ----------------------------

-- ----------------------------
-- Table structure for kagarino_user
-- ----------------------------
DROP TABLE IF EXISTS `kagarino_user`;
CREATE TABLE `kagarino_user` (
  `user_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID主键',
  `user_name` varchar(63) NOT NULL COMMENT '用户名',
  `user_password` varchar(255) NOT NULL COMMENT '用户密码',
  `user_mail` varchar(63) NOT NULL COMMENT '用户邮箱',
  `user_gmt_logon` datetime NOT NULL COMMENT '注册时间',
  `user_login_count` mediumint NOT NULL DEFAULT '0' COMMENT '登录天数',
  `user_article_count` mediumint NOT NULL DEFAULT '0' COMMENT '发帖数量',
  `user_brief` varchar(511) DEFAULT '' COMMENT '简介',
  `user_portrait` varchar(127) NOT NULL DEFAULT '../data/portrait/default.png' COMMENT '头像',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uniq_user` (`user_name`,`user_mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of kagarino_user
-- ----------------------------
