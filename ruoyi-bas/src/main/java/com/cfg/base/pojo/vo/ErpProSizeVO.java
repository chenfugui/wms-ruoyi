package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装生产尺码 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProSizeVO extends BaseAudit {
   /** id */
    private Long id;
   /** 产品ID */
    @Excel(name = "产品ID")
    private Long proId;
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
   /** 尺码ID */
    @Excel(name = "尺码ID")
    private Long sizeId;
   /** 尺寸编码 */
    @Excel(name = "尺寸编码")
    private String sizeCode;
   /** 尺寸名称 */
    @Excel(name = "尺寸名称")
    private String sizeName;
   /** 排序号 */
    @Excel(name = "排序号")
    private Long seqNo;
   /** dr 1 正常  2  删除 */
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;
}
