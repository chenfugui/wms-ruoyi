package com.cfg.base.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
/**
 * 服装生产尺码 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProSizeDTO {
    private Long id;
    private Long proId;
    private Long empId;
    private Long sizeId;
    private String sizeCode;
    private String sizeName;
    private Long seqno;
    private Long creater;
    private LocalDateTime createTime;
    private Long updater;
    private LocalDateTime updateTime;
    private Integer dr;
}
