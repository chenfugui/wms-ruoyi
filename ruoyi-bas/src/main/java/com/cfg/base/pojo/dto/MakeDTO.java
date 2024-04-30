package com.cfg.base.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
/**
 * 产品生产表 DTO 对象
 *
 * @author chenfg
 */
@Data
public class MakeDTO {
    private Long makeId;
    private String makeCode;
    private Long makeNum;
    private Long empId;
    private String status;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private Long updateTime;
    private Long proId;
}
