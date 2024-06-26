package com.cfg.base.pojo.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 产品生产表 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class MakeVO  {
   /** 生产id */
    private Long makeId;
   /** 生产编码 */
    @Excel(name = "生产编码")
    private String makeCode;
   /** 生产数量 */
    @Excel(name = "生产数量")
    private Long makeNum;
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
   /** 状态 */
    @Excel(name = "状态")
    private String status;
   /** 创建人 */
    private Long createBy;
   /** 创建时间 */
    private LocalDateTime createTime;
   /** 更新人 */
    private Long updateBy;
   /** 更新时间 */
    private Long updateTime;
   /** 产品ID */
    @Excel(name = "产品ID")
    private Long proId;
}
