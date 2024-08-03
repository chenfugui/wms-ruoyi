package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * app功能菜单 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpAppMenuVO extends BaseAudit {
   /** 主键id */
    private Long id;
   /** 目录id */
    private String catalogId;
   /** 目录名称 */
    @Excel(name = "目录名称")
    private String catalogName;
   /** 菜单编码 */
    @Excel(name = "菜单编码")
    private String menuCode;
   /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String menuName;
   /** 菜单路径 */
    @Excel(name = "菜单路径")
    private String menuPath;
   /** 序号 */
    private Integer seqNo;
}
