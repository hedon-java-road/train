-- Active: 1766731607156@@127.0.0.1@3307@train_business
CREATE DATABASE IF NOT EXISTS train_business;

USE train_business;

--- 车站表

DROP TABLE IF EXISTS station;

CREATE TABLE station (
    `id` BIGINT PRIMARY KEY COMMENT 'id',
    `name` VARCHAR(20) NOT NULL COMMENT '站名',
    `name_pinyin` VARCHAR(20) NOT NULL COMMENT '站名拼音',
    `name_py` VARCHAR(20) NOT NULL COMMENT '站名拼音首字母',
    `create_time` DATETIME(3) NOT NULL COMMENT '创建时间',
    `update_time` DATETIME(3) NOT NULL COMMENT '更新时间',
    UNIQUE KEY `uniq_name` (`name`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '车站表';