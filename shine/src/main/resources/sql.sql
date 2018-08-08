DROP TABLE IF EXISTS `COMMENT_INFO`;
CREATE TABLE `COMMENT_INFO` (
  `COMMENTS_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '备注评论',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `ARTICLE_ID` int(11) DEFAULT NULL COMMENT '文章ID',
  `CONTENT` text COMMENT '评论内容',
  `CREATED` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LASTUPDATED` datetime DEFAULT NULL,
  `LASTUPDATED_BY` int(11) DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`COMMENTS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


DROP TABLE IF EXISTS `USER_INFO`;
CREATE TABLE `USER_INFO` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_TEL` varchar(16) NOT NULL,
  `USER_NICK` varchar(20) NOT NULL,
  `USER_PASSWORD` varchar(50) NOT NULL,
  `USER_MAIL` varchar(50) NOT NULL,
  `USER_ROLE` varchar(10) DEFAULT '1',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_TEL` (`USER_TEL`) USING BTREE,
  UNIQUE KEY `USER_MAIL` (`USER_MAIL`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17775 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ARTICLE_INFO`;
CREATE TABLE `ARTICLE_INFO` (
  `ARTICLE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章主键',
  `ARTICLE_AUTHOR` varchar(20) DEFAULT NULL COMMENT '文章作者',
  `ARTICLE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `ARTICLE_CONTENT` longtext COMMENT '内容',
  `ARTICLE_TITLE` text COMMENT '标题',
  `ARTICLE_CATEGORY` varchar(20) DEFAULT NULL COMMENT '类别',
  `ARTICLE_STATUS` varchar(20) DEFAULT NULL COMMENT '文章状态',
  `DISPLAYORDER` float(8,2) DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY (`ARTICLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


DROP TABLE IF EXISTS `NEWS_INFO`;
CREATE TABLE `NEWS_INFO` (
  `NEWS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NEWS_TIME` date NOT NULL,
  `NEWS_TITLE` varchar(50) NOT NULL,
  `NEWS_CONTENT` varchar(500) NOT NULL,
  PRIMARY KEY (`NEWS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
