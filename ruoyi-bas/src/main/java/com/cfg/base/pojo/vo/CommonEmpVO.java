package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 单位信息 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class CommonEmpVO extends BaseAudit {
   /** 单位id */
    private Long empId;
   /** 单位编码 */
    @Excel(name = "单位编码")
    private String empCode;
   /** 单位名称 */
    @Excel(name = "单位名称")
    private String empName;
   /** 单位类型 */
    private String empType;
   /** 上级单位ID */
    @Excel(name = "上级单位ID")
    private Long parentId;
   /** 祖级列表 */
    @Excel(name = "祖级列表")
    private String ancestors;
   /** 排序号 */
    @Excel(name = "排序号")
    private Long orderNum;
   /** 状态 */
    private String status;
}
