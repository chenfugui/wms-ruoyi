package com.cfg.base.pojo.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装工序信息 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProProcessVO extends BaseAudit {
   /** 工序id */
    private Long id;
   /** 产品id */
    @Excel(name = "产品id")
    private Long proId;
   /** 单位ID */
    @Excel(name = "单位ID")
    private String empId;
   /** 工序id */
    @Excel(name = "工序id")
    private Long stepId;
   /** 工序编码 */
    @Excel(name = "工序编码")
    private String stepCode;
   /** 工序名称 */
    @Excel(name = "工序名称")
    private String stepName;
   /** 工序工价 */
    @Excel(name = "工序工价")
    private BigDecimal price;
   /** 排序号 */
    @Excel(name = "排序号")
    private Long seqNo;
   /** 工序状态 */
    @Excel(name = "工序状态")
    private String status;
   /** dr 1 正常  2  删除 */
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;
}
