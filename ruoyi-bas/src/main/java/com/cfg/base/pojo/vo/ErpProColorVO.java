package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 产品颜色管理 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProColorVO extends BaseAudit {
   /** id */
    @Excel(name = "id")
    private Long id;
   /** 产品ID */
    @Excel(name = "产品ID")
    private Long proId;
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
   /** 尺码ID */
    private Long colorId;
   /** 尺寸编码 */
    @Excel(name = "尺寸编码")
    private String colorCode;
   /** 尺寸名称 */
    @Excel(name = "尺寸名称")
    private String colorName;
   /** 排序号 */
    @Excel(name = "排序号")
    private Long seqno;
}
