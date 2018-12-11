CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `age` tinyint(4) NOT NULL,
  `sex` varchar(16) NOT NULL,
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `ix_updated_at` (`created_at`) USING BTREE,
  KEY `ix_created_at` (`updated_at`) USING BTREE,
  KEY `ix_age` (`age`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表'