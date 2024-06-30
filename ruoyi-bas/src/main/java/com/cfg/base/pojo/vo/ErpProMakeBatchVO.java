package com.cfg.base.pojo.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 服装生产批次 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProMakeBatchVO  {
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
    private Long empid;
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
   /** 顺序号 */
    @Excel(name = "顺序号")
    private Long seqno;
}
