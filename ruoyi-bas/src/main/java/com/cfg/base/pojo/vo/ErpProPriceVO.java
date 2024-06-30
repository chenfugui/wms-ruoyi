package com.cfg.base.pojo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 服装工价信息 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProPriceVO  {
   /** ID */
    private Long id;
   /** PROID */
    @Excel(name = "PROID")
    private Long proid;
   /** STEPID */
    @Excel(name = "STEPID")
    private Long stepid;
   /** SIZEID */
    @Excel(name = "SIZEID")
    private Long sizeid;
   /** EMP_ID */
    @Excel(name = "EMP_ID")
    private Long empId;
   /** PRICE */
    @Excel(name = "PRICE")
    private BigDecimal price;
   /** 创建人 */
    @Excel(name = "创建人")
    private Long creater;
   /** 创建时间 */
    private LocalDateTime createTime;
   /** 更新人 */
    @Excel(name = "更新人")
    private Long updater;
   /** 更新时间 */
    private LocalDateTime updateTime;
   /** dr 1 正常  2  删除 */
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;
}
