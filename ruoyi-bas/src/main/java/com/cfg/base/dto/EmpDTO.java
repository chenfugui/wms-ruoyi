package com.cfg.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.CommonEmp;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : EmpDTO
 * @Description : 单位DTO
 * @Author : chenfg
 * @Date: 2024-04-26 18:29
 */
@Data
public class EmpDTO {
    private static final long serialVersionUID = 1L;

    private Long empId;

    @Excel(name = "单位编码")
    private String empCode;

    @Excel(name = "单位名称")
    private String empName;

    private String empType;

    @Excel(name = "上级单位ID")
    private Long parentId;

    @Excel(name = "祖级列表")
    private String ancestors;

    @Excel(name = "排序号")
    private Long orderNum;

    private String status;

    private String delFlag;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CommonEmp> children;

    public Long getId() {
        return empId;
    }

    public String getLabel() {
        return empName;
    }

}
