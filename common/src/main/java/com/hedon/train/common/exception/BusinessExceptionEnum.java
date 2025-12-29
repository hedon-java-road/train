package com.hedon.train.common.exception;

/**
 * 业务异常枚举类
 */
public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已存在"),
    MEMBER_CODE_ERROR("验证码错误"),
    MEMBER_SHOULD_GET_CODE("请先获取验证码");

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
