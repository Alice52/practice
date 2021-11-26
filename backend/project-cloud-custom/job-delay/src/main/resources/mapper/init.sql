CREATE TABLE IF NOT EXISTS `practice_order_info` (
    id bigint(20) primary key auto_increment,
    status varchar(10) not null  default 'CREATED',
    create_time datetime(3) not null  default current_timestamp(3)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='order 延迟任务表';