DROP
    DATABASE IF EXISTS `movie`;

CREATE
    DATABASE `movie`;

USE
    `movie`;

CREATE TABLE `user`
(
    `id`          INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
    `name`        VARCHAR(32)  NOT NULL COMMENT '用户名',
    `email`       VARCHAR(256) NOT NULL UNIQUE COMMENT '用户邮箱地址',
    `password`    VARCHAR(256) NOT NULL COMMENT '用户登录密码',
    `gender`      CHAR(1)      NOT NULL COMMENT '用户性别(M:男 F:女)',
    `type`        BOOLEAN      DEFAULT 0 COMMENT '账号权限(false:普通 true:VIP)',
    `avatar`      VARCHAR(256) DEFAULT NULL COMMENT '头像资源链接',
    `create_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL COMMENT '更新时间'
) COMMENT '用户';

CREATE TABLE `movie_type`
(
    `id`   INT AUTO_INCREMENT PRIMARY KEY COMMENT '电影类型id',
    `name` VARCHAR(32) NOT NULL UNIQUE COMMENT '电影类型名'
) COMMENT '电影类型';

CREATE TABLE `movie_zone`
(
    `id`   INT AUTO_INCREMENT PRIMARY KEY COMMENT '电影地区id',
    `name` VARCHAR(32) NOT NULL UNIQUE COMMENT '电影地区名'
) COMMENT '电影地区';

CREATE TABLE `actor`
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '演员id',
    `name`        VARCHAR(50) NOT NULL COMMENT '演员名',
    `avatar`      VARCHAR(256) DEFAULT NULL COMMENT '头像资源链接',
    `description` TEXT COMMENT '演员简介'
) COMMENT '演员';

CREATE TABLE director
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '导演id',
    `name`        VARCHAR(50) NOT NULL COMMENT '导演名',
    `avatar`      VARCHAR(256) DEFAULT NULL COMMENT '头像资源链接',
    `description` TEXT COMMENT '导演简介'
) COMMENT '导演';

CREATE TABLE `movie`
(
    `id`           INT AUTO_INCREMENT PRIMARY KEY COMMENT '电影id',
    `name`         VARCHAR(256) NOT NULL COMMENT '电影名',
    `description`  TEXT         NOT NULL COMMENT '简介',
    `zone_id`      INT          NOT NULL COMMENT '电影地区id',
    `view`         LONG         NOT NULL COMMENT '浏览数',
    `poster`       VARCHAR(256) DEFAULT NULL COMMENT '海报链接',
    `permission`   TINYINT      DEFAULT 0 COMMENT '观看权限(0:普通 1:VIP)',
    `release_date` DATE         NOT NULL COMMENT '上线日期',
    FOREIGN KEY (`zone_id`) REFERENCES `movie_zone` (`id`)
) COMMENT '电影';

CREATE TABLE `actor-movie`
(
    `actor_id` INT NOT NULL COMMENT '演员id',
    `movie_id` INT NOT NULL COMMENT '电影id',
    PRIMARY KEY (`actor_id`, `movie_id`),
    FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`),
    FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) COMMENT '演员-电影关联表';

CREATE TABLE `director-movie`
(
    `director_id` INT NOT NULL COMMENT '导演id',
    `movie_id`    INT NOT NULL COMMENT '电影id',
    PRIMARY KEY (`director_id`, `movie_id`),
    FOREIGN KEY (`director_id`) REFERENCES `director` (`id`),
    FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) COMMENT '导演-电影关联表';

CREATE TABLE `movie-movie_type`
(
    `movie_id` INT NOT NULL COMMENT '电影id',
    `type_id`  INT NOT NULL COMMENT '电影类型id',
    PRIMARY KEY (`type_id`, `movie_id`),
    FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
    FOREIGN KEY (`type_id`) REFERENCES `movie_type` (`id`)
) COMMENT '电影-类型关联表';

CREATE TABLE `ali_pay_order`
(
    `id`               BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '订单id',
    `trade_no`         VARCHAR(64)    NOT NULL UNIQUE COMMENT '商户订单号',
    `user_id`          INT            NOT NULL COMMENT '用户ID',
    `total_amount`     DECIMAL(10, 2) NOT NULL COMMENT '支付金额',
    `subject`          VARCHAR(100) COMMENT '订单主题',
    `ali_pay_trade_no` VARCHAR(64) COMMENT '支付宝订单号',
    `status`           BOOLEAN DEFAULT 0 COMMENT '订单状态(0:未完成 1:完成)',
    `create_time`      DATETIME       NOT NULL COMMENT '创建时间',
    `update_time`      DATETIME       NOT NULL COMMENT '更新时间',
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) COMMENT '支付记录';

CREATE TABLE `user_like`
(
    `id`          INT AUTO_INCREMENT PRIMARY KEY COMMENT '点赞id',
    `user_id`     INT      NOT NULL COMMENT '用户id',
    `movie_id`    INT      NOT NULL COMMENT '电影id',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) COMMENT '点赞';