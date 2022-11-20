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

-- practice-job#job-dynamic
CREATE TABLE IF NOT EXISTS `practice_job_task_definition` (
    id bigint (20) primary key auto_increment,
    status varchar (10) null,
    name nvarchar (64) null,
    delay int (8),
    next_run_at datetime (3) default current_timestamp (3),
    data nvarchar (256)
) ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8mb4 COMMENT = '任务定义表';

-- practice-job#job-delay
CREATE TABLE IF NOT EXISTS `practice_order_info` (
    id bigint (20) primary key auto_increment,
    status varchar (10) not null default 'CREATED',
    create_time datetime (3) not null default current_timestamp (3)
) ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8mb4 COMMENT = 'order 延迟任务表';

-- practice-mistake
create table practice_user (
    id bigint not null primary key,
    name varchar(50) charset utf8 null
);
