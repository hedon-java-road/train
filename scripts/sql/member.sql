-- Active: 1766731607156@@127.0.0.1@3307@train_member
CREATE DATABASE IF NOT EXISTS train_member;

USE train_member;

DROP TABLE if EXISTS member;

CREATE TABLE member (
    `id` BIGINT PRIMARY KEY COMMENT 'id',
    `mobile` VARCHAR(11) UNIQUE NOT NULL COMMENT '手机号'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '会员表';

DROP TABLE IF EXISTS `passenger`;

CREATE TABLE passenger (
    `id` BIGINT COMMENT 'id',
    `member_id` BIGINT NOT NULL COMMENT '会员id',
    `name` VARCHAR(20) NOT NULL COMMENT '姓名',
    `id_card` VARCHAR(18) NOT NULL COMMENT '身份证',
    `type` VARCHAR(1) NOT NULL COMMENT '旅客类型|枚举[PassengerTypeEnum]',
    `create_time` DATETIME(3) NOT NULL COMMENT '创建时间',
    `update_time` DATETIME(3) NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_member_id` (`member_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '乘车人';