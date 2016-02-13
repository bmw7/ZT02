/** 管理用户表 */
CREATE TABLE `account` (
  `id` int NOT NULL auto_increment,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `department` varchar(255) default NULL,
  `accountRoleId` smallInt NOT NULL,
  `loginIp` varchar(255) default NULL,
  `loginCount` int NOT NULL,
  `loginFailureCount` int(11) default 0 NOT NULL,
  `isEnabled` bit(1) default 1 NOT NULL,
  `isLocked` bit(1) default 0 NOT NULL,
  `lockedDate` datetime default NULL,
  `createDate` datetime default NULL,
  `modifyDate` datetime default NULL,
  `loginDate` datetime default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 角色表 */
CREATE TABLE `accountRole` (
  `id` int NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createDate` timestamp DEFAULT now(),
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 权限表 */
CREATE TABLE `accountPerm` (
  `id` int NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 角色权限表 */
CREATE TABLE `accountRoleAccountPerm` (
  `accountRoleId` int NOT NULL ,
  `accountPermId` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/** 重置密码 */
CREATE TABLE `accountReset` (
  `id` int NOT NULL auto_increment,
  `accountId` INT NOT NULL,
  `validateCode` varchar(255) NOT NULL,
  `expireDate` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `accountId` (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/** -------- 管理用户数据初始化 ------------ */
insert into accountRole(name,description) values('系统内置','拥有所有权限');   
insert into accountPerm(name) values('article_add'),('article_manage'),('article_category'),('account_edit'),('account_add'),('account_role'),('setting_navigation'),('setting_edit'),('db_save'),('db_update'),('db_delete');
insert into accountRoleAccountPerm(accountRoleId,accountPermId) values('1','1'),('1','2'),('1','3'),('1','4'),('1','5'),('1','6'),('1','7'),('1','8'),('1','9'),('1','10'),('1','11');
insert into account(username,password,loginCount,accountRoleId) values('admin','4767ef9d257ad0f5de252551ccc2852a554a5ac3','0','1');


/** 文章 */
CREATE TABLE `article` (
  `id` int NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `content` TEXT default NULL,
  `createDate` datetime NOT NULL,
  `source`  varchar(255) default NULL,
  `seoKeywords` varchar(255) default NULL,
  `seoDescription` varchar(255) default NULL,
  `articleCategoryId` smallint NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 文章分类 */
CREATE TABLE `articleCategory` (
  `id` int NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `grade` smallint NOT NULL,
  `orders` smallint default NULL,
  `parentId` smallint default NULL,
  `type` tinyint default NULL,
  `staticPath` varchar(255) default NULL,
  `seoTitle` varchar(255) default NULL,
  `seoKeywords` varchar(255) default NULL,
  `seoDescription` varchar(255) default NULL,
  INDEX (`orders`),
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 文章图片 */
CREATE TABLE `articleImage` (
  `id` int NOT NULL auto_increment,
  `articleId` int NOT NULL,
  `url` varchar(255) default NULL,
  `orders` int default NULL,
  `title` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 栏目导航  -- 因顶级栏目parentId为0,故parentId default NULL*/
CREATE TABLE `navigation` (
  `id` smallint NOT NULL auto_increment,
  `name` varchar(200) NOT NULL,
  `grade` smallint NOT NULL,
  `orders` smallint default NULL,
  `url` varchar(255) default NULL,
  `parentId` smallint default NULL,
   INDEX (`orders`),
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 留言本  */
CREATE TABLE `Guestbook` (
  `id` int NOT NULL auto_increment,
  `phone` varchar(50) default NULL,
  `email` varchar(255) default NULL,
  `createDate` datetime NOT NULL,
  `ip` varchar(255) NOT NULL,
  `message` TEXT NOT NULL,
  `reply` TEXT default NULL,
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 友情链接  */
CREATE TABLE `friendlinks` (
  `id` int NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `orders` int default NULL,
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/** 导入文章 */
CREATE TABLE `imports` (
  `id` int NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `content` TEXT default NULL,
  `createDate` datetime NOT NULL,
  `articleCategoryId` smallint NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

