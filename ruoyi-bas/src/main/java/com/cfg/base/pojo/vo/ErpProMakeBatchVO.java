package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装生产批次 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProMakeBatchVO extends BaseAudit {
   /** id */
    private Long id;
   /** 产品生产id */
    @Excel(name = "产品生产id")
    private Long proMakeId;
   /** 产品生产编码 */
    @Excel(name = "产品生产编码")
    private String proMakeNo;
   /** 产品ID */
    @Excel(name = "产品ID")
    private Long proId;
   /** 尺码id */
    @Excel(name = "尺码id")
    private Long sizeId;
   /** 批次编码(同一个产品生产编码，批次号唯一) */
    @Excel(name = "批次编码(同一个产品生产编码，批次号唯一)")
    private String batchNo;
   /** 尺码名称 */
    @Excel(name = "尺码名称")
    private String sizeName;
   /** 尺码编码 */
    @Excel(name = "尺码编码")
    private String sizeCode;
   /** 颜色id */
    @Excel(name = "颜色id")
    private Long colorId;
   /** 颜色编码 */
    @Excel(name = "颜色编码")
    private String colorCode;
   /** 颜色名称 */
    @Excel(name = "颜色名称")
    private String colorName;
   /** 生产数量 */
    @Excel(name = "生产数量")
    private Long makeNum;
   /** 床次 */
    @Excel(name = "床次")
    private String bedNo;
   /** 开始扎号 */
    @Excel(name = "开始扎号")
    private Long pkgStartNo;
   /** 结束扎号 */
    @Excel(name = "结束扎号")
    private Long pkgEndNo;
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
    /** 数据状态 1 正常  2  删除 */
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag;
   /** 顺序号 */
    @Excel(name = "顺序号")
    private Long seqNo;
}
