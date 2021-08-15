-- common uid
DROP TABLE IF EXISTS `uid_worker_node`;

CREATE TABLE `uid_worker_node` (
    `ID` bigint(20) NOT NULL AUTO_INCREMENT,
    `HOST_NAME` varchar(64) COLLATE utf8mb4_bin NOT NULL,
    `PORT` varchar(64) COLLATE utf8mb4_bin NOT NULL,
    `TYPE` int(11) NOT NULL,
    `LAUNCH_DATE` date NOT NULL,
    `MODIFIED` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `CREATED` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`ID`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

-- upms member
DROP TABLE IF EXISTS `upms_member`;

CREATE TABLE `upms_member` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username` varchar(255) DEFAULT NULL,
    `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
    `phone` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像',
    `is_actived` tinyint(1) DEFAULT NULL,
    `inserted_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `inserted_by` bigint(20),
    `updated_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` bigint(20),
    `is_deleted` TINYINT(1) DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8mb4;

--  upms sign
DROP TABLE IF EXISTS `upms_sign`;

CREATE TABLE `upms_sign` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id` bigINT NOT NULL,
    `sign_date` DATE NOT NULL,
    `amount` INT(4) NOT NULL COMMENT '連續籤到次數',
    `inserted_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `inserted_by` bigint(20),
    `updated_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` bigint(20),
    `is_deleted` TINYINT(1) DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;
