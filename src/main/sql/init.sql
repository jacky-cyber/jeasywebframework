

DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `catalog_id` bigint(20) DEFAULT NULL,
  `content` text,
  `descp` varchar(255) DEFAULT NULL,
  `face_image_height` int(11) DEFAULT NULL,
  `face_image_url` varchar(255) DEFAULT NULL,
  `face_image_width` int(11) DEFAULT NULL,
  `from_source` varchar(255) DEFAULT NULL,
  `from_link_url` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cms_catalog`;
CREATE TABLE `cms_catalog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `face_image_height` int(11) DEFAULT NULL,
  `face_url` varchar(255) DEFAULT NULL,
  `face_image_width` int(11) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `link_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `target_url` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `sys_config_constant`;
CREATE TABLE `sys_config_constant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `val` varchar(255) NOT NULL,
  `enabled` varchar(255) NOT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ck7xods0e290s81ef58t9hpe` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_config_dict`;
CREATE TABLE `sys_config_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6o1d5upveevdxyelxutc9dvnr` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_config_dictitem`;
CREATE TABLE `sys_config_dictitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `item_text` varchar(255) NOT NULL,
  `item_value` varchar(255) NOT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `sys_dept_department`;
CREATE TABLE `sys_dept_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `oorder` bigint(20) NOT NULL,
  `children_num` bigint(20) NOT NULL,
  `level` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  `enabled` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m300wd8kf6syr0svrmv526d3y` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_dept_resource`;
CREATE TABLE `sys_dept_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `children_num` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `ext1` varchar(255) DEFAULT NULL,
  `ext2` varchar(255) DEFAULT NULL,
  `ext3` varchar(255) DEFAULT NULL,
  `ext4` varchar(255) DEFAULT NULL,
  `ext5` varchar(255) DEFAULT NULL,
  `java_method` varchar(255) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `method` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `oorder` bigint(20) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `path` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `enabled` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `oorder` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `sys_dept_role_of_resource`;
CREATE TABLE `sys_dept_role_of_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `sys_dept_user`;
CREATE TABLE `sys_dept_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthday` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` varchar(255) NOT NULL,
  `home_phone` varchar(255) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) NOT NULL,
  `msn` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `office_phone` varchar(255) DEFAULT NULL,
  `oorder` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `salt` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9oobvfdt5pmis84mmbi2duv4l` (`email`),
  UNIQUE KEY `UK_wtoxyeuqhebfx159tkt2pq3h` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_dev_gen_column`;
CREATE TABLE `sys_dev_gen_column` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `big_name` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `date_format` varchar(255) DEFAULT NULL,
  `db_key` varchar(255) DEFAULT NULL,
  `db_null` varchar(255) DEFAULT NULL,
  `db_type` varchar(255) DEFAULT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `html_type` varchar(255) DEFAULT NULL,
  `java_type` varchar(255) DEFAULT NULL,
  `max_length` int(11) DEFAULT NULL,
  `max_val` varchar(255) DEFAULT NULL,
  `min_length` int(11) DEFAULT NULL,
  `min_val` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `required` varchar(255) DEFAULT NULL,
  `required_msg` varchar(255) DEFAULT NULL,
  `small_name` varchar(255) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_dev_gen_table`;
CREATE TABLE `sys_dev_gen_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  `controller_class` varchar(255) DEFAULT NULL,
  `controller_pkg` varchar(255) DEFAULT NULL,
  `dao_class` varchar(255) DEFAULT NULL,
  `dao_pkg` varchar(255) DEFAULT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `domain_class` varchar(255) DEFAULT NULL,
  `domain_pkg` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url_prefix` varchar(255) DEFAULT NULL,
  `velocity_path_prefix` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_dev_sql_log`;
CREATE TABLE `sys_dev_sql_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `create_user_id` bigint(20) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `sql_str` varchar(255) NOT NULL,
  `sql_type` varchar(255) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_dev_tracker`;
CREATE TABLE `sys_dev_tracker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `children_num` bigint(20) NOT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `end_time` bigint(20) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `path` varchar(255) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `tag` varchar(255) NOT NULL,
  `thread_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

