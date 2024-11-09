package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 常用字典管理 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpEmpDicVO extends BaseAudit {
   /** ID */
    private Integer id;
   /** 类型编码 */
    @Excel(name = "类型编码")
    private String typeCode;
   /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;
   /** 字典项编码 */
    @Excel(name = "字典项编码")
    private String itemCode;
   /** 字典项名称 */
    @Excel(name = "字典项名称")
    private String itemName;
   /** 字典项值 */
    @Excel(name = "字典项值")
    private String itemValue;
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
}
