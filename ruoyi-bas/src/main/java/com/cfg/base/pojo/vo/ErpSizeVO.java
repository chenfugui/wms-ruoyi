package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装尺码管理 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpSizeVO extends BaseAudit {
   /** 尺码id */
    private Long id;
   /** 尺码编码 */
    @Excel(name = "尺码编码")
    private String colorCode;
   /** 尺码名称 */
    @Excel(name = "尺码名称")
    private String colorName;
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
   /** 顺序号 */
    @Excel(name = "顺序号")
    private Long seqNo;
   /** dr 1 正常  2  删除 */
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;
}
