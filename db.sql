DROP TABLE IF EXISTS `gitlab_users`;
CREATE TABLE `gitlab_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `gid` bigint(20) DEFAULT NULL COMMENT 'gitlab内用户id',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '用户手机号',
  PRIMARY KEY `pk_id` (`id`),
  UNIQUE KEY `uk_gid` (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;