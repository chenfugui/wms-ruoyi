package com.cfg.base.pojo.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装生产管理 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProMakeVO extends BaseAudit {
   /** id */
    private Long id;
   /** 产品id */
    @Excel(name = "产品id")
    private Long proId;
   /** 产品生产编码 */
    @Excel(name = "产品生产编码")
    private String proMakeNo;
   /** 生产开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生产开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime makeStartTime;
   /** 生产结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生产结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime makeEndTime;
   /** 交付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "交付时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliverTime;
   /** 生产状态: 1 正常  2 停止 3 作废 */
    @Excel(name = "生产状态: 1 正常  2 停止 3 作废")
    private String status;
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
