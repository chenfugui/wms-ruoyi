package com.cfg.base.pojo.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 工序信息 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class CommonStepVO extends BaseAudit {
   /** 工序id */
    private Long stepId;
   /** 工序编码 */
    @Excel(name = "工序编码")
    private String stepCode;
   /** 工序名称 */
    @Excel(name = "工序名称")
    private String stepName;
   /** 工价 */
    @Excel(name = "工价")
    private BigDecimal stepPrice;
   /** 单位编码 */
    @Excel(name = "单位编码")
    private String empCode;
   /** 序号 */
    @Excel(name = "序号")
    private Long stepSort;
   /** 状态 */
    @Excel(name = "状态")
    private String status;
}
