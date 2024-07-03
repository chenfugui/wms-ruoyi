package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装颜色管理 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpColorVO extends BaseAudit {
   /** 颜色id */
    private Long id;
   /** 颜色编码 */
    @Excel(name = "颜色编码")
    private String colorCode;
   /** 颜色名称 */
    @Excel(name = "颜色名称")
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
