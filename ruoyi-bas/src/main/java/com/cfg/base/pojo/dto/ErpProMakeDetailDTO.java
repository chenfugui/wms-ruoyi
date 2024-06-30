package com.cfg.base.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
/**
 * 服装生产明细 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProMakeDetailDTO {
    private Long id;
    private Long proMakeId;
    private String proMakeNo;
    private Long proId;
    private Long sizeId;
    private Long colorId;
    private Long clothId;
    private String clothCode;
    private String clothName;
    private Long makeNum;
    private Long creater;
    private LocalDateTime createTime;
    private Long updater;
    private LocalDateTime updateTime;
    private Integer dr;
    private Long empId;
}
