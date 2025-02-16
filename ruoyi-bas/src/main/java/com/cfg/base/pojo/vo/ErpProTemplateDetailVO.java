package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 模板项目明细管理 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProTemplateDetailVO extends BaseAudit {
   /** id */
    private Long id;
   /** 模板 */
    @Excel(name = "模板")
    private Long tmpId;
   /** 模板编码 */
    @Excel(name = "模板编码")
    private String tmpCode;
   /** 项目编码 */
    @Excel(name = "项目编码")
    private String itemCode;
   /** 项目名称 */
    @Excel(name = "项目名称")
    private String itemName;
   /** 备注 */
    @Excel(name = "备注")
    private String itemMemo;
   /** 顺序号 */
    private Long seqNo;
   /** 单位ID */
    private Long empId;
}
