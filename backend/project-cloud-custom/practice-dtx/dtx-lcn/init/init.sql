create schema `dtx-lcn`;

create table if not exists  lcn_order (
    id bigint(20) primary key  auto_increment,
    order_name nvarchar(64) default  null
);

create table if not exists  lcn_pay (
    id bigint(20) primary key  auto_increment,
    pay_name nvarchar(64) default  null
);

DROP TABLE IF EXISTS `t_tx_exception`;
CREATE TABLE `t_tx_exception`  (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
   `unit_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
   `mod_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
   `transaction_state` tinyint(4) NULL DEFAULT NULL,
   `registrar` tinyint(4) NULL DEFAULT NULL,
   `ex_state` tinyint(4) NULL DEFAULT NULL COMMENT '0 待处理 1已处理',
   `remark` varchar(10240) NULL DEFAULT NULL COMMENT '备注',
   `create_time` datetime(0) NULL DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 967 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
