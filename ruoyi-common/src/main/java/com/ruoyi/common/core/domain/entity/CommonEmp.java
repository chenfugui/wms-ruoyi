package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

/**
 * 单位信息对象 bas_common_emp
 * 
 * @author chenfg
 */
@Data
@TableName("bas_common_emp")
public class CommonEmp extends BaseAudit {
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

    private String status="0";

    private String delFlag;

}
