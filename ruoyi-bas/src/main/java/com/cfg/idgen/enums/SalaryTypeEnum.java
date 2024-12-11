package com.cfg.idgen.enums;

/**
 * @ClassName : SalaryTypeEnum
 * @Description : 工资统计类型
 * @Author : chenfg
 * @Date: 2024-12-11 14:38
 */
public enum SalaryTypeEnum {
    SALARY_DAY("1","日统计"),
    SALARY_MONTH("2","月统计");

    private String code;
    private String name;

    SalaryTypeEnum(String code,String name){
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
