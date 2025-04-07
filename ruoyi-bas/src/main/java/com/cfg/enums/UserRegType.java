package com.cfg.enums;

/**
 * @Desc : 用户注册类型枚举
 * @Author : chenfugui
 * @Date : 2025/4/7
 */
public enum UserRegType {

    EMP_WORKER("1","企业员工"),
    EMP_BOSS("2","企业老板");

    private String code;
    private String desc;

    UserRegType(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

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
}
