package com.cfg.enums;

/**
 * @author chenf
 * @version 1.0
 * @description: 字典类型枚举
 * @date 2024/11/9 13:23
 */
public enum DicTypeEnum {
    CLOSH_PART("0001","部位");

    private final String code;
    private final String name;

    DicTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

    public static DicTypeEnum of(String code) {
        DicTypeEnum[] types =  DicTypeEnum.values();
        for (DicTypeEnum type : types) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
