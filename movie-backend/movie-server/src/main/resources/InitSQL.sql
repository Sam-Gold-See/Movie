DROP
    DATABASE IF EXISTS `movie`;

CREATE
    DATABASE `movie`;

USE
    `movie`;

CREATE TABLE `user`
(
    `id`          BIGINT      NOT NULL PRIMARY KEY COMMENT '用户id',
    `name`        VARCHAR(32) COMMENT '用户名',
    `email`       VARCHAR(256) UNIQUE COMMENT '用户邮箱地址',
    `password`    VARCHAR(64) NOT NULL COMMENT '用户登录密码',
    `gender`      CHAR(1) COMMENT '用户性别(M:男 F:女)',
    `level`       TINYINT      DEFAULT 0 COMMENT '账号权限(0:普通 1:VIP)',
    `avatar`      VARCHAR(500) DEFAULT NULL COMMENT '头像资源链接',
    `create_time` DATETIME COMMENT '创建时间',
    `update_time` DATETIME COMMENT '更新时间'
) COMMENT '用户';