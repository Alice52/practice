/*
Navicat MySQL Data Transfer

Source Server         : ubuntu-pc
Source Server Version : 50731
Source Host           : 192.168.1.5:3306
Source Database       : practice

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-12-18 00:19:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for licence
-- ----------------------------
DROP TABLE IF EXISTS `licence`;
CREATE TABLE `licence` (
  `licence_id` bigint(20) NOT NULL,
  `organization_id` bigint(20) NOT NULL,
  `licence_type` varchar(20) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `licence_max` int(11) NOT NULL,
  `licence_allocated` int(11) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`licence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of licence
-- ----------------------------
INSERT INTO `licence` VALUES ('1', '1', 'user', 'CustomerPro', '100', '5', null);
INSERT INTO `licence` VALUES ('2', '1', 'user', 'suitability-plus', '200', '189', null);
INSERT INTO `licence` VALUES ('3', '2', 'user', 'HR-PowerSuite', '100', '4', null);
INSERT INTO `licence` VALUES ('4', '2', 'core-prod', 'WildCat Application Gateway', '16', '16', null);
