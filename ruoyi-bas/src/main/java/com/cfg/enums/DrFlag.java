package com.cfg.enums;

/**
 * @author chenfg
 * @date 20240629
 * 删除状态枚举
 */
public enum DrFlag {

    NORMAL(0,"正常"),
    DEL(1,"删除");

    private int code;
    private String name;

    private DrFlag(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

    public static DrFlag of(int code) {
       DrFlag[] flags =  DrFlag.values();
       for (DrFlag flag : flags) {
           if (flag.getCode() == code) {
               return flag;
           }
       }
       return null;
    }


}
