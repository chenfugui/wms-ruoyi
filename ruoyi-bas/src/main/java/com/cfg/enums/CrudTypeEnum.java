package com.cfg.enums;

/**
 * @ClassName : CrudTypeEnum
 * @Description : crup操作类型
 * @Author : chenfg
 * @Date: 2024-11-15 13:57
 */
public enum CrudTypeEnum {
    ADD("add","新增"),
    EDIT("edit","修改"),
    DELETE("delete","删除"),
    QUERY("query","查询"),
    EXPORT("export","导出"),
    IMPORT("import","导入");

    private final String code;
    private final String name;

    CrudTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据code获取枚举
     * @author chenfg
     * @param code
     * @return CrudTypeEnum
     */
    public static CrudTypeEnum of(String code) {
        for (CrudTypeEnum crudTypeEnum : CrudTypeEnum.values()) {
            if (crudTypeEnum.getCode().equals(code)) {
                return crudTypeEnum;
            }
        }
        return null;
    }
}
