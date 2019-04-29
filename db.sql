DROP TABLE IF EXISTS `gitlab_users`;
CREATE TABLE `gitlab_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `gid` bigint(20) DEFAULT NULL COMMENT 'gitlab内用户id',
  `full_name` varchar(32) DEFAULT NULL COMMENT '用户fullName',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户userName',
  `mobile` varchar(32) DEFAULT NULL COMMENT '用户手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_gid` (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;