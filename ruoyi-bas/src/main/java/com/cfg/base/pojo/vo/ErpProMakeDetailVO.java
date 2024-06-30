package com.cfg.base.pojo.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 服装生产明细 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProMakeDetailVO  {
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
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
}
