delimiter $$

CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sitename` varchar(255) NOT NULL,
  `question` varchar(255) NOT NULL,
  `answer` varchar(512) NOT NULL,
  `answer_text` varchar(255) DEFAULT NULL,
  `submitter` varchar(45) NOT NULL,
  `submitdate` datetime NOT NULL,
  `questionorder` int(11) NOT NULL,
  `comment` varchar(512) DEFAULT NULL,
  `questionid` varchar(45) NOT NULL,
  `siteid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=526 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `assessment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `site_name` varchar(255) DEFAULT NULL,
  `overall` varchar(45) DEFAULT NULL,
  `electrical` varchar(45) DEFAULT NULL,
  `mechanical` varchar(45) DEFAULT NULL,
  `telecoms` varchar(45) DEFAULT NULL,
  `sitestructure` varchar(45) DEFAULT NULL,
  `operations` varchar(45) DEFAULT NULL,
  `process` varchar(45) DEFAULT NULL,
  `submitter` varchar(255) NOT NULL,
  `submitdate` datetime DEFAULT NULL,
  `siteid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_order` bigint(20) NOT NULL,
  `question` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `subcategory` varchar(255) DEFAULT NULL,
  `reference` varchar(2000) DEFAULT NULL,
  `helptext` varchar(255) DEFAULT NULL,
  `questiontype` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `questionold` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `question_order` bigint(20) NOT NULL,
  `question` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `subcategory` varchar(255) DEFAULT NULL,
  `reference` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `selectedsites` (
  `siteid` varchar(45) NOT NULL,
  `submitter` varchar(45) NOT NULL,
  `isSelected` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `demand` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1 COMMENT='ss'$$


delimiter $$

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `status` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1$$


