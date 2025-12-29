-- Active: 1766731607156@@127.0.0.1@3307@train_member
CREATE DATABASE IF NOT EXISTS train_member;

USE train_member;

DROP TABLE if EXISTS member;

CREATE TABLE member (
    `id` BIGINT PRIMARY KEY COMMENT 'id',
    `mobile` VARCHAR(11) UNIQUE NOT NULL COMMENT '手机号'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '会员表';