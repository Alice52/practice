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

--  upms sign
DROP TABLE IF EXISTS `upms_sign`;

CREATE TABLE `upms_sign` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id` bigINT NOT NULL,
    `sign_date` DATE NOT NULL,
    `amount` INT(4) NOT NULL COMMENT '連續籤到次數',
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted` TINYINT(1) DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;
