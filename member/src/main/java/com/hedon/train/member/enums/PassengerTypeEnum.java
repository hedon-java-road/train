package com.hedon.train.member.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum PassengerTypeEnum {

    ADULT("1", "成人"),
    CHILD("2", "儿童"),
    STUDENT("3", "学生");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    PassengerTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static List<HashMap<String, String>> getEnumList() {
        List<HashMap<String, String>> list = new ArrayList<>();
        for (PassengerTypeEnum type : PassengerTypeEnum.values()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("code", type.getCode());
            map.put("desc", type.getDesc());
            list.add(map);
        }
        return list;
    }
}
