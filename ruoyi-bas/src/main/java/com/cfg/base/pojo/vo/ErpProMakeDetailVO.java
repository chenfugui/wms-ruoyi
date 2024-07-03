package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装生产明细 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProMakeDetailVO extends BaseAudit {
   /** id */
    private Long id;
   /** 生产ID */
    @Excel(name = "生产ID")
    private Long proMakeId;
   /** 生产编码 */
    @Excel(name = "生产编码")
    private String proMakeNo;
   /** 产品id */
    @Excel(name = "产品id")
    private Long proId;
   /** 尺码ID */
    @Excel(name = "尺码ID")
    private Long sizeId;
   /** 颜色ID */
    @Excel(name = "颜色ID")
    private Long colorId;
   /** 布料ID */
    @Excel(name = "布料ID")
    private Long clothId;
   /** 布料编码 */
    @Excel(name = "布料编码")
    private String clothCode;
   /** 布料名称 */
    @Excel(name = "布料名称")
    private String clothName;
   /** 生产数量 */
    @Excel(name = "生产数量")
    private Long makeNum;
    /** 数据状态 1 正常  2  删除 */
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag;
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
}
