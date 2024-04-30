package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 产品信息表 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ProductVO extends BaseAudit {
   /** 产品id */
    private Long proId;
   /** 产品编码 */
    @Excel(name = "产品编码")
    private String proCode;
   /** 产品名称 */
    @Excel(name = "产品名称")
    private String proName;
   /** 产品描述 */
    @Excel(name = "产品描述")
    private String proDesc;
   /** 产品类型 */
    @Excel(name = "产品类型")
    private String proType;
   /** 产品备注 */
    @Excel(name = "产品备注")
    private String proMemo;
   /** 产品状态 */
    @Excel(name = "产品状态")
    private String status;
   /** 单位ID */
    @Excel(name = "单位ID")
    private Long empId;
}
