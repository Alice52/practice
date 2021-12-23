CREATE TABLE IF NOT EXISTS `practice_job_task_definition` (
    id bigint(20) primary key auto_increment,
    status varchar(10)  null ,
    name nvarchar(64)  null,
    delay int(8),
    next_run_at datetime(3) default current_timestamp(3),
    data nvarchar(256)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='任务定义表';