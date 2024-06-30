package com.cfg.base.pojo.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 服装生产进度 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProBatchExeVO  {
   /** 进度ID */
    private Long id;
   /** 生产批次 */
    @Excel(name = "生产批次")
    private Long batchId;
   /** 工序Id */
    @Excel(name = "工序Id")
    private Long stepId;
   /** 生产状态 */
    @Excel(name = "生产状态")
    private String makeStatus;
   /** 实际生产数量 */
    @Excel(name = "实际生产数量")
    private Long realMakeNum;
   /** 单位id */
    @Excel(name = "单位id")
    private Long empId;
   /** 扫描人 */
    @Excel(name = "扫描人")
    private Long scanBy;
   /** 扫描时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "扫描时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scanTime;
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
   /** 工资 */
    @Excel(name = "工资")
    private BigDecimal salary;
   /** dr 1 正常  2  删除 */
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;
}
