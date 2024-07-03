package com.cfg.base.pojo.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装工序管理 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProcVO extends BaseAudit {
   /** 工序id */
    private Long id;
   /** 工序编码 */
    @Excel(name = "工序编码")
    private String stepCode;
   /** 工序名称 */
    @Excel(name = "工序名称")
    private String stepName;
   /** 工序工价 */
    @Excel(name = "工序工价")
    private BigDecimal stepPrice;
   /** 单位编码 */
    @Excel(name = "单位编码")
    private String empCode;
   /** 顺序号 */
    @Excel(name = "顺序号")
    private Long seqNo;
   /** 工序状态 */
    @Excel(name = "工序状态")
    private String status;
    /** 数据状态 1 正常  2  删除 */
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag;
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
}
