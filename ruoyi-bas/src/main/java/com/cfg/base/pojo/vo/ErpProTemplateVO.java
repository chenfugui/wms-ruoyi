package com.cfg.base.pojo.vo;

import java.util.List;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import com.cfg.base.pojo.dto.ErpProTemplateDetailDTO;
import lombok.Data;
/**
 * 模板表 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProTemplateVO extends BaseAudit {
   /** id */
    private Long id;
   /** 模板编码 */
    @Excel(name = "模板编码")
    private String tmpCode;
   /** 模板名称 */
    @Excel(name = "模板名称")
    private String tmpName;
   /** 模板类型 */
    @Excel(name = "模板类型")
    private String tmpType;
   /** 备注 */
    @Excel(name = "备注")
    private String tmpMemo;
   /** 单位ID */
    private Long empId;
}
