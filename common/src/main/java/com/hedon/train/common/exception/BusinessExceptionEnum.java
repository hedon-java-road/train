package com.hedon.train.common.exception;

/**
 * 业务异常枚举类
 */
public enum BusinessExceptionEnum {
    MEMEBE_MOBILE_EXIST("手机号已存在");

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.name() + ": " + desc;
    }

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }
}
